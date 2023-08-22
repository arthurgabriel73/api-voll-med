package med.voll.api.domain.appointment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.DomainException;
import med.voll.api.domain.appointment.validations.appointment.SchedulingValidator;
import med.voll.api.domain.appointment.validations.cancellation.CancelAppointmentValidator;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.physician.Physician;
import med.voll.api.domain.physician.PhysicianRepository;

@Service
public class AppointmentSchedule {

  @Autowired
  private AppointmentRepository appointmentRepository;

  @Autowired
  private PhysicianRepository physicianRepository;

  @Autowired
  private PatientRepository patientRepository;

  @Autowired
  private List<SchedulingValidator> validators;

  @Autowired
  private List<CancelAppointmentValidator> cancelValidators;
  
  public AppointmentDetailData doSchedule(RegisterAppointmentRecord data) {
    if (data.idPhysician() != null && !physicianRepository.existsById(data.idPhysician())) {
      throw new DomainException("Physician not found!");
    }

    if (!patientRepository.existsById(data.idPatient())) {
      throw new DomainException("Patient not found!");
    }

    validators.forEach((validator) -> validator.validate(data));

    var patient = patientRepository.getReferenceById(data.idPatient());
    var physician = choosePhysician(data);
    if (physician == null) {
      throw new DomainException("No physician available for this date");
    }
    var appointment = new Appointment(null, physician, patient, data.date(), null);
    appointmentRepository.save(appointment);
    
    return new AppointmentDetailData(appointment);
  }

  private Physician choosePhysician(RegisterAppointmentRecord data) {
    if (data.idPhysician() != null) {
      return physicianRepository.findById(data.idPhysician()).get();
    }

    if (data.specialty() != null) {
      throw new DomainException("Specialty is mandatory if physician Id is not given!");
    }

    return physicianRepository.chooseRandomPhysicianAvailableOnDate(data.specialty(), data.date());
  }

  public void cancel(CancelAppointmentRecord data) {
    if (!appointmentRepository.existsById(data.idAppointment())) {
      throw new DomainException("Appointment not found!");
    }

    cancelValidators.forEach(validator -> validator.validate(data));

    var appointment = appointmentRepository.getReferenceById(data.idAppointment());
    appointment.cancel(data.cancelReason());
  }
}

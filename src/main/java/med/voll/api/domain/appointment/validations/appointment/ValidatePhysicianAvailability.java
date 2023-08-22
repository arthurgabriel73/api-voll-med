package med.voll.api.domain.appointment.validations.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.DomainException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.RegisterAppointmentRecord;

@Component
public class ValidatePhysicianAvailability implements SchedulingValidator {
  
  @Autowired
  private AppointmentRepository appointmentRepository;

  public void validate(RegisterAppointmentRecord data) {
    var physicianHasAnotherAppointmentInTheSameTime = appointmentRepository.existsByPhysicianIdAndDate(data.idPhysician(), data.date());
    if (physicianHasAnotherAppointmentInTheSameTime) {
      throw new DomainException("Appointment time not available for this physician;");
    }
  }
}

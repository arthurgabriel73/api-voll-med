package med.voll.api.domain.appointment.validations.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.DomainException;
import med.voll.api.domain.appointment.RegisterAppointmentRecord;
import med.voll.api.domain.patient.PatientRepository;

@Component
public class ValidateActivePatient implements SchedulingValidator {
  
  @Autowired
  private PatientRepository patientRepository;
  
  public void validate(RegisterAppointmentRecord data) {
    if (data.idPatient() == null) return;
    
    var isPatientActive = patientRepository.findActiveById(data.idPatient());
    if (!isPatientActive) {
      throw new DomainException("The patient is not available.");
    }
  }
}

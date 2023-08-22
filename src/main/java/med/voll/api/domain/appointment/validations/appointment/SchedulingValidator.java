package med.voll.api.domain.appointment.validations.appointment;

import med.voll.api.domain.appointment.RegisterAppointmentRecord;

public interface SchedulingValidator {
  
  void validate(RegisterAppointmentRecord data);
}
package med.voll.api.domain.appointment.validations.cancellation;

import med.voll.api.domain.appointment.CancelAppointmentRecord;

public interface CancelAppointmentValidator {
  void validate(CancelAppointmentRecord data);
}

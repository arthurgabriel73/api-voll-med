package med.voll.api.domain.appointment.validations.appointment;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.DomainException;
import med.voll.api.domain.appointment.RegisterAppointmentRecord;

@Component
public class ValidateTimeInAdvance implements SchedulingValidator {
  public void validate(RegisterAppointmentRecord data) {
    var appointmentDate = data.date();
    var now = LocalDateTime.now();
    var differenceInMinutes = Duration.between(now, appointmentDate).toMinutes();

    if (differenceInMinutes < 30) {
      throw new DomainException(
        "The appointment must be scheduled at least 30 minutes in advance."
        );
    }
  }
  
}

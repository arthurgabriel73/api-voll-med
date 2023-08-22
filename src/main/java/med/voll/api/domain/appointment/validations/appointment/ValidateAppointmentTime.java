package med.voll.api.domain.appointment.validations.appointment;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.api.domain.DomainException;
import med.voll.api.domain.appointment.RegisterAppointmentRecord;

@Component
public class ValidateAppointmentTime implements SchedulingValidator {
  public void validate(RegisterAppointmentRecord data) {
    var appointmentDate = data.date();

    var sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    var beforeOpening = appointmentDate.getHour() < 7;
    var afterClosing = appointmentDate.getHour() >= 18;
    if (sunday || beforeOpening || afterClosing) {
      throw new DomainException("Appointment time out of hours");
    }
  }
}

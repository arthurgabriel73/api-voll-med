package med.voll.api.domain.appointment.validations.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.DomainException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.RegisterAppointmentRecord;

@Component
public class ValidatePatientWithNoAppointmentsAtSameDay implements SchedulingValidator {

  @Autowired
  private AppointmentRepository appointmentRepository;

  public void validate(RegisterAppointmentRecord data) {
    var firstTime = data.date().withHour(7);
    var lastTime = data.date().withHour(18);
    var patientHaveAnotherAppointmentAtSameDay = appointmentRepository.existsByPatientIdAndDateBetween(
      data.idPatient(), 
      firstTime, 
      lastTime
      );
      if (patientHaveAnotherAppointmentAtSameDay) {
        throw new DomainException("Patient already have an appointment at this day.");
      }
  }
}

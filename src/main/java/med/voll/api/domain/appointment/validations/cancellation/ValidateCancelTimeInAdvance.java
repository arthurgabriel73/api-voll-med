package med.voll.api.domain.appointment.validations.cancellation;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.DomainException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.CancelAppointmentRecord;

@Component
public class ValidateCancelTimeInAdvance implements CancelAppointmentValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(CancelAppointmentRecord data) {
        var appointment = appointmentRepository.getReferenceById(data.idAppointment());
        var now = LocalDateTime.now();
        var hoursDifference = Duration.between(now, appointment.getDate()).toHours();

        if (hoursDifference < 24) {
            throw new DomainException(
                "Appointment can only be canceled at least 24 hours in advance!"
                );
        }
    }
}
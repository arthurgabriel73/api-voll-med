package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.physician.Specialty;

public record RegisterAppointmentRecord(
  Long idPhysician,

  @NotNull
  Long idPatient,

  @NotNull
  @Future
  LocalDateTime date,

  Specialty specialty
) {
  
}

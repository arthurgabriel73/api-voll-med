package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record AppointmentDetailData(
  Long id,
  Long idPhysician,

  @NotNull
  Long idPatient,

  @NotNull
  @Future
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  LocalDateTime date
) {
  
  public AppointmentDetailData( Appointment appointment) {
    this(
      appointment.getId(),
      appointment.getPhysician().getId(),
      appointment.getPatient().getId(),
      appointment.getDate());
  }
  
}

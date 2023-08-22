package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record CancelAppointmentRecord (
  @NotNull
  Long idAppointment,

  @NotNull
  CancelReason cancelReason
  
) {}

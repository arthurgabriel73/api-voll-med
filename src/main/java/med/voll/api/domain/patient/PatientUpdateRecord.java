package med.voll.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressRecord;

public record PatientUpdateRecord (
  @NotNull
  Long id,
  String name,
  String email,
  String phone,
  AddressRecord address
) {}

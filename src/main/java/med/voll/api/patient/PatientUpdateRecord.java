package med.voll.api.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressRecord;

public record PatientUpdateRecord (
  @NotNull
  Long id,
  String name,
  String email,
  String phone,
  AddressRecord address
) {}

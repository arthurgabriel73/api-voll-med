package med.voll.api.physician;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressRecord;

public record PhysicianUpdateRecord (
  @NotNull
  Long id,
  String name,
  String phone,
  AddressRecord address
  ) {}

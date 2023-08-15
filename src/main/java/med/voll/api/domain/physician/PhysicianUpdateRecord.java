package med.voll.api.domain.physician;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressRecord;

public record PhysicianUpdateRecord (
  @NotNull
  Long id,
  String name,
  String phone,
  AddressRecord address
  ) {}

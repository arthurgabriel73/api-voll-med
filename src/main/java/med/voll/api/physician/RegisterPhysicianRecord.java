package med.voll.api.physician;

import med.voll.api.address.AddressRecord;

public record RegisterPhysicianRecord(
    String name,
    String email,
    String crm,
    Specialty specialty,
    AddressRecord address) {
}

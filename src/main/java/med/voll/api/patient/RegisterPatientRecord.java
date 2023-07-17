package med.voll.api.patient;

import med.voll.api.address.AddressRecord;

public record RegisterPatientRecord(
    String name,
    String email,
    String phone,
    String cpf,
    AddressRecord address) {

}

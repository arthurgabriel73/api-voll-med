package med.voll.api.address;

public record AddressRecord(
    String publicPlace,
    String neighborhood,
    String cep,
    String city,
    String uf,
    String number,
    String complement) {

}

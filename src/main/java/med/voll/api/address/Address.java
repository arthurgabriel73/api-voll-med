package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

  public Address(AddressRecord address) {
    this.publicPlace = address.publicPlace();
    this.neighborhood = address.neighborhood();
    this.cep = address.cep();
    this.city = address.city();
    this.uf = address.uf();
    this.number = address.number();
    this.complement = address.complement();
  }

  private String publicPlace;
  private String neighborhood;
  private String cep;
  private String city;
  private String uf;
  private String number;
  private String complement;

  public void updateData(AddressRecord address) {
    if (address.publicPlace() != null) {
      this.publicPlace = address.publicPlace();
    }
    if (address.neighborhood() != null) {
      this.neighborhood = address.neighborhood();
    }
    if (address.cep() != null) {
      this.cep = address.cep();
    }
    if (address.city() != null) {
      this.city = address.city();
    }
    if (address.uf() != null) {
      this.uf = address.uf();
    }
    if (address.publicPlace() != null) {
      this.publicPlace = address.publicPlace();
    }
    if (address.number() != null) {
      this.number = address.number();
    }
    if (address.complement() != null) {
      this.complement = address.complement();
    }
  }

}
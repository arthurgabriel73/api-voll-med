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

}
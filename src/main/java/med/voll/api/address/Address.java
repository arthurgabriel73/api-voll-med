package med.voll.api.address;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Table(name = "physicians")
@Entity(name = "Physician")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

  private String publicPlace;
  private String neighborhood;
  private String cep;
  private String city;
  private String uf;
  private String number;
  private String complement;

}
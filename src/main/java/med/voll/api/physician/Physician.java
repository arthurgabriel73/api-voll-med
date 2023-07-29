package med.voll.api.physician;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

@Table(name = "physicians")
@Entity(name = "Physician")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Physician {

  public Physician(RegisterPhysicianRecord data) {
    this.name = data.name();
    this.email = data.email();
    this.phone = data.phone();
    this.crm = data.crm();
    this.specialty = data.specialty();
    this.address = new Address(data.address());
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  private String phone;
  private String crm;

  @Enumerated(EnumType.STRING)
  private Specialty specialty;

  @Embedded
  private Address address;

  public void updateData(@Valid PhysicianUpdateRecord data) {
    if (data.name() != null) {
      this.name = data.name();
    }
    if (data.phone() != null) {
      this.phone = data.phone();
    }
    if (data.address() != null) {
      this.address.updateData(data.address());
    }
  }

}

package med.voll.api.patient;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  private String phone;
  private String cpf;

  @Embedded
  private Address address;

  private Boolean active;

  public Patient(RegisterPatientRecord data) {
    this.active = true;
    this.name = data.name();
    this.email = data.email();
    this.phone = data.phone();
    this.cpf = data.cpf();
    this.address = new Address(data.address());
  }

  public void delete() {
    this.active = false;
  }

  public void updateData(@Valid PatientUpdateRecord data) {
    if (data.name() != null) {
      this.name = data.name();
    }
    if (data.email() != null) {
      this.email = data.email();
    }
    if (data.phone() != null) {
      this.phone = data.phone();
    }
    if (data.address() != null) {
      this.address.updateData(data.address());
    }
  }
}

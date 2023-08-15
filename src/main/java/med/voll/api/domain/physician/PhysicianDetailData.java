package med.voll.api.domain.physician;

import med.voll.api.domain.address.Address;

public record PhysicianDetailData (
  Long id,
  String name,
  String email,
  String phone,
  String crm,
  Specialty specialty,
  Address address
  ) {
    public PhysicianDetailData(Physician physician) {
      this(
        physician.getId(), 
        physician.getName(), 
        physician.getPhone(), 
        physician.getEmail(), 
        physician.getCrm(), 
        physician.getSpecialty(), 
        physician.getAddress());
    };
  }

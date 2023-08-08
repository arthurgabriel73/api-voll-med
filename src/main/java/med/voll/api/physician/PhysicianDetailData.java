package med.voll.api.physician;

import med.voll.api.address.Address;

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

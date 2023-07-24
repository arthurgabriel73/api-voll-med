package med.voll.api.physician;

public record PhysicianListRecord (
   String name,
   String email,
   String crm,
   Specialty specialty

) {
  public PhysicianListRecord(Physician physician) {
    this(
      physician.getName(),
      physician.getEmail(),
      physician.getCrm(),
      physician.getSpecialty()
    );
  }
}

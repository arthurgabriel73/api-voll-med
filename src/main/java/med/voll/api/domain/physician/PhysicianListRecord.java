package med.voll.api.domain.physician;

public record PhysicianListRecord (
  Long id,
  String name,
  String email,
  String crm,
  Specialty specialty

) {
  public PhysicianListRecord(Physician physician) {
    this(
      physician.getId(),
      physician.getName(),
      physician.getEmail(),
      physician.getCrm(),
      physician.getSpecialty()
    );
  }
}

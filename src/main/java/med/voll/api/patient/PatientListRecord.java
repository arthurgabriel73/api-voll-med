package med.voll.api.patient;

public record PatientListRecord(
  Long id,
  String name, 
  String email, 
  String cpf
  ) {
  public PatientListRecord(Patient patient) {
    this(
      patient.getId(),
      patient.getName(), 
      patient.getEmail(), 
      patient.getCpf()
    );
  }
}

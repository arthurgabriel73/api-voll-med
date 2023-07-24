package med.voll.api.patient;

public record PatientListRecord(
  String name, 
  String email, 
  String cpf
  ) {
  public PatientListRecord(Patient patient) {
    this(
      patient.getName(), 
      patient.getEmail(), 
      patient.getCpf()
    );
  }
}

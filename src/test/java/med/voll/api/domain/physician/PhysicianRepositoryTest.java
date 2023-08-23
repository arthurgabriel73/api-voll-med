package med.voll.api.domain.physician;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import med.voll.api.domain.address.AddressRecord;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.RegisterPatientRecord;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PhysicianRepositoryTest {

  @Autowired
  private PhysicianRepository physicianRepository;

  @Autowired
  TestEntityManager testEntityManager;

  private void registerAppointment(Physician physician, Patient patient, LocalDateTime date) {
    testEntityManager.persist(new Appointment(null, physician, patient, date, null));
}

private Physician registerPhysician(String name, String email, String crm, Specialty specialty) {
    var Physician = new Physician(physicianData(name, email, crm, specialty));
    testEntityManager.persist(Physician);
    return Physician;
}

private Patient registerPatient(String name, String email, String cpf) {
    var Patient = new Patient(patientData(name, email, cpf));
    testEntityManager.persist(Patient);
    return Patient;
}

private RegisterPhysicianRecord physicianData(String name, String email, String crm, Specialty specialty) {
    return new RegisterPhysicianRecord(
            name,
            email,
            "61999999999",
            crm,
            specialty,
            addressData()
    );
}

private RegisterPatientRecord patientData(String nome, String email, String cpf) {
    return new RegisterPatientRecord(
            nome,
            email,
            "61999999999",
            cpf,
            addressData()
    );
}

private AddressRecord addressData() {
    return new AddressRecord(
            "rua xpto",
            "bairro",
            "00000000",
            "Brasilia",
            "DF",
            null,
            null
    );
}

@Test
@DisplayName("should return null when the only physician registered is not available on date")
  void testChooseRandomPhysicianAvailableOnDateScenario1() {
    // Arrange
    var nextMondayAt10 = LocalDate.now()
    .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
    .atTime(10, 0);
    var physician = registerPhysician("Physician", "email@gmail.com", "123456", Specialty.CARDIOLOGY);
    var patient = registerPatient("Patient", "patient@email.com", "1234332221");
    registerAppointment(physician, patient, nextMondayAt10);
    
    // Act
    var availablePhysician = physicianRepository.chooseRandomPhysicianAvailableOnDate(Specialty.CARDIOLOGY, nextMondayAt10);
    
    // Assert
    assertThat(availablePhysician).isNull();
  }

  @Test
  @DisplayName("should return physician when a physician is available on date")
  void testChooseRandomPhysicianAvailableOnDateScenario2() {
    // Arrange
    var nextMondayAt10 = LocalDate.now()
    .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
    .atTime(10, 0);
    var physician = registerPhysician("Physician", "email@gmail.com", "123456", Specialty.CARDIOLOGY);

    // Act
    var availablePhysician = physicianRepository.chooseRandomPhysicianAvailableOnDate(Specialty.CARDIOLOGY, nextMondayAt10);

    // Assert
    assertThat(availablePhysician).isEqualTo(physician);
  }
}

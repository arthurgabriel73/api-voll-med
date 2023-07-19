package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.patient.Patient;
import med.voll.api.patient.PatientRepository;
import med.voll.api.patient.RegisterPatientRecord;

@RestController
@RequestMapping("/patients")
public class PatientController {

  @Autowired
  private PatientRepository patientRepository;

  @PostMapping
  public void register(@RequestBody @Valid RegisterPatientRecord data) {
    Patient patient = new Patient(data);
    patientRepository.save(patient);
  }
}

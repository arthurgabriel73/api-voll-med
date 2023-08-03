package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.patient.Patient;
import med.voll.api.patient.PatientListRecord;
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

  @GetMapping
  public Page<PatientListRecord> list(
  @PageableDefault(
    size = 5,
    page = 0,
    sort = {"cpf"},
    direction =  Direction.ASC
  )
  Pageable pageable) {
    return patientRepository.findAllByActiveTrue(pageable).map(PatientListRecord::new);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public void delete(@PathVariable Long id) {
    var patient = patientRepository.getReferenceById(id);
    patient.delete();
  }

}

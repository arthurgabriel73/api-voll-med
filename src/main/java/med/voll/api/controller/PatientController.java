package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

  @Autowired
  private PatientRepository patientRepository;

  @PostMapping
  public ResponseEntity<PatientDetailData> register(@RequestBody @Valid RegisterPatientRecord data, UriComponentsBuilder uriBuilder) {
    var patient = patientRepository.save(new Patient(data));
    var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
    return ResponseEntity.created(uri).body(new PatientDetailData(patient));

  }

  @GetMapping
  public ResponseEntity<Page<PatientListRecord>> list(
  @PageableDefault(
    size = 5,
    page = 0,
    sort = {"cpf"},
    direction =  Direction.ASC
  )
  Pageable pageable) {
    var page = patientRepository.findAllByActiveTrue(pageable).map(PatientListRecord::new);
    return ResponseEntity.ok(page);
  }

  @GetMapping("/{id}")
  @Transactional
  public ResponseEntity<PatientDetailData> detail(@PathVariable Long id) {
    var patient = patientRepository.getReferenceById(id);
    return ResponseEntity.ok(new PatientDetailData(patient));
  }

  @PutMapping
  @Transactional
  public ResponseEntity<PatientDetailData> update(@RequestBody @Valid PatientUpdateRecord data) {
    var patient = patientRepository.getReferenceById(data.id());
    patient.updateData(data);
    return ResponseEntity.ok(new PatientDetailData(patient));
  }


  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    var patient = patientRepository.getReferenceById(id);
    patient.delete();

    return ResponseEntity.noContent().build();
  }

}

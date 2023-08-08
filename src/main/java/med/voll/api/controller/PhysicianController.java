package med.voll.api.controller;

import org.apache.catalina.connector.Response;
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
import med.voll.api.physician.Physician;
import med.voll.api.physician.PhysicianDetailData;
import med.voll.api.physician.PhysicianListRecord;
import med.voll.api.physician.PhysicianRepository;
import med.voll.api.physician.PhysicianUpdateRecord;
import med.voll.api.physician.RegisterPhysicianRecord;

@RestController
@RequestMapping("/physicians")
public class PhysicianController {

  @Autowired
  private PhysicianRepository physicianRepository;

  @PostMapping
  @Transactional
  public ResponseEntity<PhysicianDetailData> register(@RequestBody @Valid RegisterPhysicianRecord data, UriComponentsBuilder uriBuilder) {
    var physician = physicianRepository.save(new Physician(data));

    var uri = uriBuilder.path("/physicians/{id}").buildAndExpand(physician.getId()).toUri();

    return ResponseEntity.created(uri).body(new PhysicianDetailData(physician));
  }

  @GetMapping
  public ResponseEntity<Page<PhysicianListRecord>> list(
    @PageableDefault(
      size = 10, 
      page = 0, 
      sort = {"crm", "name"},
      direction = Direction.DESC
      ) Pageable pageable) {
    var page = physicianRepository.findAllByActiveTrue(pageable).map(PhysicianListRecord::new);
    return ResponseEntity.ok(page);
  }

  @GetMapping("/{id}")
  @Transactional
  public ResponseEntity detail(@PathVariable Long id) {
    var physician = physicianRepository.getReferenceById(id);
    return ResponseEntity.ok(new PhysicianDetailData(physician));
  }

  @PutMapping
  @Transactional
  public ResponseEntity<PhysicianDetailData> update(@RequestBody @Valid PhysicianUpdateRecord data) {
    var physician = physicianRepository.getReferenceById(data.id());
    physician.updateData(data);
    return ResponseEntity.ok(new PhysicianDetailData(physician));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity delete(@PathVariable Long id) {
    var physician = physicianRepository.getReferenceById(id);
    physician.delete();

    return ResponseEntity.noContent().build();
  }
}

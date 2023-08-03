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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.physician.Physician;
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
  public void register(@RequestBody @Valid RegisterPhysicianRecord data) {
    physicianRepository.save(new Physician(data));
  }

  @GetMapping
  public Page<PhysicianListRecord> list(
    @PageableDefault(
      size = 10, 
      page = 0, 
      sort = {"crm", "name"},
      direction = Direction.DESC
      ) Pageable pageable) {
    return physicianRepository.findAllByActiveTrue(pageable).map(PhysicianListRecord::new);
  }

  @PutMapping
  @Transactional
  public void update(@RequestBody @Valid PhysicianUpdateRecord data) {
    var physician = physicianRepository.getReferenceById(data.id());
    physician.updateData(data);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public void delete(@PathVariable Long id) {
    var physician = physicianRepository.getReferenceById(id);
    physician.delete();
  }
}

package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import med.voll.api.physician.Physician;
import med.voll.api.physician.PhysicianRepository;
import med.voll.api.physician.RegisterPhysicianRecord;

@RestController
@RequestMapping("/physicians")
public class PhysicianController {

  @Autowired
  private PhysicianRepository physicianRepository;

  @PostMapping
  @Transactional
  public void register(@RequestBody RegisterPhysicianRecord data) {
    Physician physician = new Physician(data);
    physicianRepository.save(physician);
  }
}

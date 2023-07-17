package med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.physician.RegisterPhysicianRecord;

@RestController
@RequestMapping("/physicians")
public class PhysicianController {

  @PostMapping
  public void register(@RequestBody RegisterPhysicianRecord data) {
    System.out.println(data);
  }
}

package med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.patient.RegisterPatientRecord;

@RestController
@RequestMapping("/patients")
public class PatientController {

  @PostMapping
  public void register(@RequestBody RegisterPatientRecord data) {
    System.out.println(data);
  }
}

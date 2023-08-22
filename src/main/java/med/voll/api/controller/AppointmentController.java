package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.AppointmentDetailData;
import med.voll.api.domain.appointment.AppointmentSchedule;
import med.voll.api.domain.appointment.CancelAppointmentRecord;
import med.voll.api.domain.appointment.RegisterAppointmentRecord;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
  @Autowired
  private AppointmentSchedule schedule;
  
  @PostMapping
  @Transactional
  public ResponseEntity doSchedule(@RequestBody @Valid RegisterAppointmentRecord data) {
    var result = schedule.doSchedule(data);
    return ResponseEntity.ok(result);
  }

  @DeleteMapping
  @Transactional
  public ResponseEntity cancel(@RequestBody @Valid CancelAppointmentRecord data) {
    schedule.cancel(data);
    return ResponseEntity.noContent().build();
}
}

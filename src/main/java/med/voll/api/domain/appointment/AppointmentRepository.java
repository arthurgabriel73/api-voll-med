package med.voll.api.domain.appointment;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
  boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstTime, LocalDateTime lastTime);

  boolean existsByPhysicianIdAndDate(Long idPhysician, LocalDateTime date);
}

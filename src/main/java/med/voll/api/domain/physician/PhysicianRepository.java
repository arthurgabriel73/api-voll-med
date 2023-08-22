package med.voll.api.domain.physician;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhysicianRepository extends JpaRepository<Physician, Long> {

  Page<Physician> findAllByActiveTrue(Pageable pageable);

  @Query("""
      select p from Physician p
      where p.active = true
      and p.specialty = :specialty
      and p.id not in(
        select a.physician.id from Appointment a
        where a.date = :date
      )
      order by rand()
      limit 1
      """)
  Physician chooseRandomPhysicianAvailableOnDate(Specialty specialty, LocalDateTime date);

  @Query("""
      select p.active from Physician p
      where p.id = :physicianId
      """)
  Boolean findActiveById(Long physicianId);
}

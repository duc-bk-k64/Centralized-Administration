package hust.admin.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hust.admin.project.Entity.Schedule;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}

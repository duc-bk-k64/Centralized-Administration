package hust.admin.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hust.admin.project.Entity.AccessLog;
@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog,Long>{
}

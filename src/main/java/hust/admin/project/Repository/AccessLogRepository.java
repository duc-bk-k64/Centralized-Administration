package hust.admin.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hust.admin.project.Entity.AccessLog;
@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog,Long>{
	@Query(value = "SELECT * FROM access_log where username= :username",nativeQuery = true)
	List<AccessLog> getLogByUsername(@Param("username") String username);
	
}

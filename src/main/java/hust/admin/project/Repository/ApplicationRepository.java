package hust.admin.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hust.admin.project.Entity.Application;
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>{
	@Query(value = "SELECT * FROM application WHERE app_code= :app_code", nativeQuery = true)
	Application findApplicationByAppCode(@Param("app_code") String app_code);
}

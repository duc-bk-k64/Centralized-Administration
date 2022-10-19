package hust.admin.project.Repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hust.admin.project.Entity.Application;
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>{
	@Query(value = "SELECT * FROM application WHERE app_code= :app_code", nativeQuery = true)
	Application findApplicationByAppCode(@Param("app_code") String app_code);
	
	@Query(value = "UPDATE application SET status = 1 WHERE id = :appId", nativeQuery = true)
	@Modifying
	@Transactional
	void activeApp(@Param("appId") Long appId);

	@Query(value = "UPDATE application SET status = 0 WHERE id = :appId", nativeQuery = true)
	@Modifying
	@Transactional
	void deactiveApp(@Param("appId") Long appId);
	
	@Query(value = "SELECT * FROM application WHERE id IN(SELECT object_id FROM object_map where object_name =:object_name and map_name =:map_name and map_id = :groupId )", nativeQuery = true)
	List<Application> findApplicationByGroupId(@Param("groupId") Long groupId,@Param("object_name") String object_name,@Param("map_name") String map_name);
}

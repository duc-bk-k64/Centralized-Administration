package hust.admin.project.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hust.admin.project.Entity.Application;
import hust.admin.project.Entity.Group;
import hust.admin.project.Entity.ObjectMap;
import hust.admin.project.Entity.Schedule;

@Repository
public interface ObjectMapRepository extends JpaRepository<ObjectMap, Long> {
	@Query(value = "DELETE FROM object_map where object_id= :object_id and object_name = :object_name and map_id = :map_id and map_name = :map_name", nativeQuery = true)
	@Modifying
	@Transactional
	void removeObjectMap(@Param("object_id") Long object_id,@Param("object_name") String object_name,@Param("map_id") Long map_id,@Param("map_name") String map_name);
	@Query(value = "DELETE FROM object_map where object_id= :object_id and object_name = :object_name ", nativeQuery = true)
	@Modifying
	@Transactional
	void removeObjectMap(@Param("object_id") Long object_id,@Param("object_name") String object_name);
	@Query(value = "DELETE FROM object_map where map_id= :map_id and map_name = :map_name ", nativeQuery = true)
	@Modifying
	@Transactional
	void removeObjectMap(@Param("map_name") String map_name,@Param("map_id") Long map_id);
	
	@Query(value = "INSERT INTO object_map (object_id,object_name,map_id,map_name) VALUES (:object_id,:object_name,:map_id,:map_name)",nativeQuery = true)
	@Modifying
	@Transactional
	void insertObjectMap(@Param("object_id") Long object_id,@Param("object_name") String object_name,@Param("map_id") Long map_id,@Param("map_name") String map_name);
	
	@Query(value = "SELECT * FROM group_user WHERE id IN (SELECT object_id FROM object_map where object_name =:object_name and map_name =:map_name and map_id = :userId )", nativeQuery = true)
	List<Group> findGroupByUserId(@Param("userId") Long userId,@Param("object_name") String object_name,@Param("map_name") String map_name);
	
	@Query(value = "SELECT * FROM application WHERE id IN(SELECT object_id FROM object_map where object_name =:object_name and map_name =:map_name and map_id = :groupId )", nativeQuery = true)
	Application findApplicationByGroupId(@Param("groupId") Long groupId,@Param("object_name") String object_name,@Param("map_name") String map_name);
	
	@Query(value = "SELECT * FROM schedule WHERE id IN(SELECT object_id FROM object_map where object_name =:object_name and map_name =:map_name and map_id = :userId )", nativeQuery = true)
	Schedule findScheduleByUserId(@Param("userId") Long userId,@Param("object_name") String object_name,@Param("map_name") String map_name);
}

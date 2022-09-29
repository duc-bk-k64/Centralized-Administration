package hust.admin.project.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hust.admin.project.Entity.ObjectMap;

@Repository
public interface ObjectMapRepository extends JpaRepository<ObjectMap, Long> {
	@Query(value = "DELETE FROM object_map where object_id= :object_id and object_name = :object_name and map_id = :map_id and map_name = :map_name", nativeQuery = true)
	@Modifying
	@Transactional
	void removeObjectMap(@Param("object_id") Long object_id,@Param("object_name") String object_name,@Param("map_id") Long map_id,@Param("map_name") String map_name);
	@Query(value = "DELETE FROM object_map where object_id= :object_id and object_name = :object_name ", nativeQuery = true)
	@Modifying
	@Transactional
	void removeObjectMapApplication(@Param("object_id") Long object_id,@Param("object_name") String object_name);
	
	@Query(value = "INSERT INTO object_map (object_id,object_name,map_id,map_name) VALUES (:object_id,:object_name,:map_id,:map_name)",nativeQuery = true)
	@Modifying
	@Transactional
	void insertObjectMap(@Param("object_id") Long object_id,@Param("object_name") String object_name,@Param("map_id") Long map_id,@Param("map_name") String map_name);
}

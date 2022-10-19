package hust.admin.project.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import hust.admin.project.Entity.Group;
@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{
	@Query(value = "SELECT * FROM group_user WHERE group_code= :group_code", nativeQuery = true)
	Group findApplicationByGroupCode(@Param("group_code") String group_code);
	
	@Query(value = "UPDATE group_user SET status = 1 WHERE id = :groupId", nativeQuery = true)
	@Modifying
	@Transactional
	void activeGroup(@Param("groupId") Long groupId);

	@Query(value = "UPDATE group_user SET status = 0 WHERE id = :groupId", nativeQuery = true)
	@Modifying
	@Transactional
	void deactiveGroup(@Param("groupId") Long userId);
	
	@Query(value = "SELECT * FROM group_user WHERE id IN (SELECT object_id FROM object_map where object_name =:object_name and map_name =:map_name and map_id = :userId )", nativeQuery = true)
	Group findGroupByUserId(@Param("userId") Long userId,@Param("object_name") String object_name,@Param("map_name") String map_name);
	
	@Query(value = "SELECT * FROM group_user WHERE id IN (SELECT map_id FROM object_map where object_name =:object_name and map_name =:map_name and object_id = :appId )", nativeQuery = true)
	List<Group> findGroupByAppId(@Param("appId") Long appId,@Param("object_name") String object_name,@Param("map_name") String map_name);
	
}

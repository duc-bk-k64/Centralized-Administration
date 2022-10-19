package hust.admin.project.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import hust.admin.project.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT * FROM user where username = :username", nativeQuery = true)
	User findByUsername(@Param("username") String username);

	@Query(value = "UPDATE user SET status = 1 WHERE id = :userId", nativeQuery = true)
	@Modifying
	@Transactional
	void activeUser(@Param("userId") Long userId);

	@Query(value = "UPDATE user SET status = 0 WHERE id = :userId", nativeQuery = true)
	@Modifying
	@Transactional
	void deactiveUser(@Param("userId") Long userId);

	@Query(value = "SELECT * FROM user where status = :status", nativeQuery = true)
	List<User> getUserByStatus(@Param("status") Long status);
	
	@Query(value = "SELECT * FROM user WHERE id IN (SELECT map_id FROM object_map where object_name =:object_name and map_name =:map_name and object_id = :groupId )", nativeQuery = true)
	List<User> findUserByGroupId(@Param("groupId") Long groupId,@Param("object_name") String object_name,@Param("map_name") String map_name);

}

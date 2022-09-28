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
public interface UserRepository extends JpaRepository<User,Long> {
	User findByUsername(String username);
	@Query(value = "UPDATE user SET status = 1 WHERE id = :userId",nativeQuery = true)
	@Modifying
    @Transactional
	void activeUser(@Param("userId") Long userId);
	@Query(value = "UPDATE user SET status = 0 WHERE id = :userId",nativeQuery = true)
	@Modifying
    @Transactional
	void deactiveUser(@Param("userId") Long userId);
	@Query(value = "SELECT * FROM user where status = :status", nativeQuery = true)
	List<User> getUserByStatus(@Param("status") Long status);

}

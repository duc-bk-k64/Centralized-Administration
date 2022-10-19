package hust.admin.project.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hust.admin.project.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
	@Query(value = "SELECT * FROM  role",nativeQuery = true)
	List<Role> findAllRole();

}

package hust.admin.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hust.admin.project.Entity.Permission;



@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	Permission findByName(String name);
	@Query(value ="SELECT * FROM permission",nativeQuery=true)
	List<Permission> findAllPermissions();

}

package hust.admin.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hust.admin.project.Entity.Permission;



@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	Permission findByName(String name);

}

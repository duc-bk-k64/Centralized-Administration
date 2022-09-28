package hust.admin.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hust.admin.project.Entity.Group;
@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{
}

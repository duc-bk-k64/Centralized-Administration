package hust.admin.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hust.admin.project.Entity.ObjectMap;

@Repository
public interface ObjectMapRepository extends JpaRepository<ObjectMap, Long> {

}

package hust.admin.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hust.admin.project.Entity.Application;
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>{
}

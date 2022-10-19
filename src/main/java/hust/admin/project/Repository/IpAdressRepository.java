package hust.admin.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hust.admin.project.Entity.IpAdress;

@Repository
public interface IpAdressRepository extends JpaRepository<IpAdress, Long> {

}

package videoshack.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import videoshack.entity.Employee;


public interface EmployeeDao extends JpaRepository<Employee, Long>  {

}

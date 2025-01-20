package videoshack.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import videoshack.entity.Customer;




public interface CustomerDao extends JpaRepository<Customer, Long>  {

}
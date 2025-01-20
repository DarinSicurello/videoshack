package videoshack.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import videoshack.controller.model.VideoShackData;
import videoshack.controller.model.VideoShackData.VideoShackCustomer;
import videoshack.controller.model.VideoShackData.VideoShackEmployee;
import videoshack.dao.CustomerDao;
import videoshack.dao.EmployeeDao;
import videoshack.dao.VideoShackDao;
import videoshack.entity.Customer;
import videoshack.entity.Employee;
import videoshack.entity.VideoShack;


//Final PRoject  Create VideoShack Service and Add Copy Customers and transitional 


@Service
public class VideoShackService {
	@Autowired
	private VideoShackDao videoShackDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private EmployeeDao employeeDao;
	
	@Transactional(readOnly = false) 
		public VideoShackData saveVideoShack(VideoShackData videoShackData)
		{
	Long videoShackId = videoShackData.getVideoShackId();
	VideoShack videoShack = findOrCreateVideoShack(videoShackId);
	
	copyVideoShackFields(videoShack, videoShackData);
	return new VideoShackData(videoShackDao.save(videoShack));
	

}
	//FINAL PROJECTS  Add Copy Customers and employees
	private void copyVideoShackFields(VideoShack videoShack,
			VideoShackData videoShackData) {
		videoShack.setVideoShackId(videoShackData.getVideoShackId());
		videoShack.setVideoShackName(videoShackData.getVideoShackName());
		videoShack.setVideoShackAddress(videoShackData.getVideoShackAddress());
		videoShack.setVideoShackCity(videoShackData.getVideoShackCity());
		videoShack.setVideoShackState(videoShackData.getVideoShackState());
		videoShack.setVideoShackZip(videoShackData.getVideoShackZip());
		videoShack.setVideoShackPhone(videoShackData.getVideoShackPhone());
	}
	private void copyEmployeeFields(Employee employee,
			VideoShackEmployee videoShackEmployee) {
	employee.setEmployeeId(videoShackEmployee.getEmployeeId());
	employee.setEmployeeFirstName(videoShackEmployee.getEmployeeFirstName());
	employee.setEmployeeLastName(videoShackEmployee.getEmployeeLastName());
	employee.setEmployeeJobTitle(videoShackEmployee.getEmployeeJobTitle());
	employee.setEmployeePhone(videoShackEmployee.getEmployeePhone());
	}
	private void copyCustomerFields(Customer customer,
			VideoShackCustomer videoShackCustomer) {
	customer.setCustomerId(videoShackCustomer.getCustomerId());
	customer.setCustomerFirstName(videoShackCustomer.getCustomerFirstName());
	customer.setCustomerLastName(videoShackCustomer.getCustomerLastName());
	customer.setCustomerEmail(videoShackCustomer.getCustomerEmail());
	}

	private VideoShack findOrCreateVideoShack(Long videoShackId) {
		if (Objects.isNull(videoShackId)) {
			return new VideoShack();
		}
		
		else {
			return findVideoShackById(videoShackId); 
		}
	}	
	private Employee findOrCreateEmployee(Long videoShackId, Long employeeId) {
		if (Objects.isNull(employeeId)) {
			return new Employee();
			}	
		return findEmployeeId(videoShackId, employeeId); 
			}
	private Customer findOrCreateCustomer(Long videoShackId, Long customerId) {
		if (Objects.isNull(customerId)) {
			return new Customer();
			}	
		return findCustomerId(videoShackId, customerId); 
	}
	
	private Employee findEmployeeId(Long videoShackId, Long employeeId) {
		Employee employee = employeeDao.findById(employeeId)
				.orElseThrow(() -> new NoSuchElementException(
						"Employee with ID=" + employeeId + " was not found."));
		if (employee.getVideoShack().getVideoShackId() !=videoShackId) {
			throw new IllegalArgumentException("The employee with ID=" + employee +
					" is not employed by the Video Shack with ID=" + videoShackId + ".");
		}
		return employee;
	}
	
	private Customer findCustomerId(Long videoShackId, Long customerId) {
		Customer customer = customerDao.findById(customerId)
				.orElseThrow(() -> new NoSuchElementException(
						"Customer with ID=" + customerId + " was not found."));
		boolean found = false;
		
		for (VideoShack videoShack : customer.getVideoShacks()) {
			if (videoShack.getVideoShackId() == videoShackId) {
				found = true;
				break;
			}
		}
		
		if (!found) {
			throw new IllegalArgumentException("The Customer with ID=" + customerId 
					+ " is not a member of the Video Shack with ID=" + videoShackId);
		}
		return customer;
	}
	private VideoShack findVideoShackById(Long videoShackId) {
		return videoShackDao.findById(videoShackId)
				.orElseThrow(() -> new NoSuchElementException(
						"Video Shack with ID=" + videoShackId + " was not found."));
	}
	
	//Final Project Video SHack Add  transitional 

@Transactional(readOnly = false)
public VideoShackEmployee saveEmployee (Long videoShackId,
		VideoShackEmployee videoShackEmployee) {
	VideoShack videoShack = findVideoShackById(videoShackId);
	Long employeeId = videoShackEmployee.getEmployeeId();
	Employee employee = findOrCreateEmployee(videoShackId, employeeId);
	
	copyEmployeeFields(employee, videoShackEmployee);
	
	employee.setVideoShack(videoShack);
	videoShack.getEmployees().add(employee);
	
	Employee dbEmployee = employeeDao.save(employee);
	
	return new VideoShackEmployee(dbEmployee);	
}

@Transactional(readOnly = false)
public VideoShackCustomer saveCustomer (Long videoShackId,
		VideoShackCustomer videoShackCustomer) {
	VideoShack videoShack = findVideoShackById(videoShackId);
	Long customerId = videoShackCustomer.getCustomerId();
	Customer customer = findOrCreateCustomer(videoShackId, customerId);
	
	copyCustomerFields(customer, videoShackCustomer);
	
	customer.getVideoShacks().add(videoShack);
	videoShack.getCustomers().add(customer);
	
	Customer dbCustomer = customerDao.save(customer);
	
	return new VideoShackCustomer(dbCustomer);
}

@Transactional(readOnly = true)
public List<VideoShackData> retrieveAllVideoShacks() {
	List<VideoShack> videoShacks = videoShackDao.findAll();
	
	List<VideoShackData> result = new LinkedList<>();
	
	for(VideoShack videoShack : videoShacks) {
		VideoShackData psd = new VideoShackData(videoShack);
		
		psd.getCustomers().clear();
		psd.getEmployees().clear();
		
		result.add(psd);
	}
	return result;
	
	}
	
@Transactional(readOnly = true)
public VideoShackData retrieveVideoShackById(Long videoShackId) {
	return new VideoShackData(findVideoShackById(videoShackId));
	}

@Transactional(readOnly = false)	
public void deleteVideoShackById(Long videoShackId) {
	VideoShack videoShack = findVideoShackById(videoShackId);
	videoShackDao.delete(videoShack);

}

}


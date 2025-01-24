package videoshack.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import videoshack.entity.Customer;
import videoshack.entity.Employee;
import videoshack.entity.VideoShack;

//Final Create VideoShack Data

@Data
@NoArgsConstructor

public class VideoShackData {
	private Long videoShackId;
	private String videoShackName;
	private String videoShackAddress;
	private String videoShackCity;
	private String videoShackState;
	private String videoShackZip;
	private String videoShackPhone;
	private Set<VideoShackCustomer> customers = new HashSet<>();
	private Set<VideoShackEmployee> employees = new HashSet<>();

	public VideoShackData(VideoShack videoShack) {
		videoShackId = videoShack.getVideoShackId();
		videoShackName = videoShack.getVideoShackName();
		videoShackAddress = videoShack.getVideoShackAddress();
		videoShackCity = videoShack.getVideoShackCity();
		videoShackState = videoShack.getVideoShackState();
		videoShackZip = videoShack.getVideoShackZip();
		videoShackPhone = videoShack.getVideoShackPhone();
		
		for(Customer customer : videoShack.getCustomers()) {
			customers.add(new VideoShackCustomer(customer));
		}
		
		for(Employee employee : videoShack.getEmployees()) {
			employees.add(new VideoShackEmployee(employee));
		}
	}
	//Final Create VideoShack Data
	@Data
	@NoArgsConstructor
	public static class VideoShackCustomer {
		private Long customerId;
		private String customerFirstName;
		private String customerLastName;
		private String customerEmail;
		
	public VideoShackCustomer (Customer customer) {
		customerId = customer.getCustomerId();
		customerFirstName = customer.getCustomerFirstName();
		customerLastName = customer.getCustomerLastName();
		customerEmail = customer.getCustomerEmail();
	}
	}
	@Data
	@NoArgsConstructor

	public static class VideoShackEmployee {	
		private Long employeeId;
		private String employeeFirstName;
		private String employeeLastName;
		private String employeeEmail;
		private String employeeJobTitle;	
		
	
	public VideoShackEmployee (Employee employee) {
		employeeId = employee.getEmployeeId();
		employeeFirstName = employee.getEmployeeFirstName();
		employeeLastName = employee.getEmployeeLastName();
		employeeEmail = employee.getEmployeeEmail();
		employeeJobTitle = employee.getEmployeeJobTitle();
	}
	}	
	}
	
	

	
	

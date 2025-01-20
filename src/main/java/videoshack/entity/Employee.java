package videoshack.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Employee {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long employeeId;
 private String employeeFirstName;
 private String employeeLastName;
 private String employeePhone;
 private String employeeJobTitle;
 @EqualsAndHashCode.Exclude
 @ToString.Exclude
	
@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "pet_store_id")
 private VideoShack videoShack;

public String getemployeeJobTitle() {
	// TODO Auto-generated method stub
	return null;
}

public void setEmployeeJobTitle(String employeeJobTitle2) {
	// TODO Auto-generated method stub
	
}
}
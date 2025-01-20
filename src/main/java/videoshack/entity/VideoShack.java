package videoshack.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data

public class VideoShack {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long videoShackId;
	private String videoShackName;
	private String videoShackAddress;
	private String videoShackCity;
	private String videoShackState;
	private String videoShackZip;
	private String videoShackPhone;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude

	@OneToMany (mappedBy = "videoShack", cascade = CascadeType.ALL, orphanRemoval = true)
	Set<Employee> employees = new HashSet<>();

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "videoShack_customer",
		joinColumns = @JoinColumn(name = "videoShack_id"),
		inverseJoinColumns = @JoinColumn(name = "customer_id"))
	private Set<Customer> customers = new HashSet<>();
}
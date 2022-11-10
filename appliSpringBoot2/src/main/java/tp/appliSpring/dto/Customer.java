package tp.appliSpring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//cette classe "Customer" ou "ClientDto" ou ...
//correspond à un DTO (Data Transfert Object):
//un objet de données qui sera transférer à travers le réseau ou entre couches logicielles
// entity.Client --> dto.Customer
// dto.Customer --> entity.Client
@Getter @Setter @ToString @NoArgsConstructor
public class Customer {
   private Long number;
   private String firstName;
   private String lastName;
   private String email;
   private String address;
   
	public Customer(Long number, String firstName, String lastName, String email, String address) {
		super();
		this.number = number;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
	}
}

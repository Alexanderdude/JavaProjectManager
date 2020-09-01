
public class person {
	
	String workType;
	String name;
	String telephone;
	String email;
	String address;
	
	public person(String workType, String name, String telephone, String email, String address) { //create constructor
		
		this.workType = workType;
		this.name = name;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
		
	}
	
    public String toString() { //toString method
		
		String output = "Work Type: " + workType;
		output += "\nName: " + name;
		output += "\nTelephone number: " + telephone;
		output += "\nEmail address: " + email;
		output+= "\nPhysical address: " + address;
		
		return output;
		
		
    }

}

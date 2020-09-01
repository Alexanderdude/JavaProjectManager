
public class Project {
	
	String proName;
	int proNum;
	String buildType;
	String address;
	String ERFnum;
	int totalFee;
	int totalFeeDue;
	String proDeadline;
	
	public Project(String proName, int proNum,  String buildType, String address, String ERFnum,
			int totalFee, int totalFeeDue, String proDeadline) { //create constructor
		
		this.proName = proName;
		this.proNum = proNum;
		this.buildType = buildType;
		this.address = address;
		this.ERFnum = ERFnum;
		this.totalFee = totalFee;
		this.totalFeeDue = totalFeeDue;
		this.proDeadline = proDeadline;
		
		
	}
	
    public String toString() { //toString method
		
		String output = "Project Name: " + proName;
		output += "\nProject Number: " + proNum;
		output += "\nType of building: " + buildType;
		output += "\nPhysical Address: " + address;
		output += "\nERF number : " + ERFnum;
		output += "\nTotal Fee: " + totalFee;
		output += "\nTotal Fee still due: " + totalFeeDue;
		output += "\nProjects Deadline: " + proDeadline;
		
		return output;
		
		
	}
	

}

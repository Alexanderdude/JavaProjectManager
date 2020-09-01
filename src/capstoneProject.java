import java.util.Formatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class capstoneProject {
	
	public static void main(String [] args) throws FileNotFoundException{
		
		Scanner Objec = new Scanner(System.in);  // Create a Scanner
		
		System.out.println("What is the customers first name: ");
		
		String cusName = Objec.nextLine();
		
		System.out.println("What is the customers surname: ");
		
		String cusSurname = Objec.nextLine();
		
		cusName = cusName + " " + cusSurname;
		
		String cusWorkType = "Customer";
		
        System.out.println("What is the customers telephone number: ");
		
		String cusTele = Objec.nextLine();
		
        System.out.println("What is the customers email address: ");
		
		String cusEmailaddress = Objec.nextLine();
		
        System.out.println("What is the customers physical address: ");
		
		String cusAddress = Objec.nextLine();
		
		person customer = new person(cusWorkType, cusName, cusTele, cusEmailaddress, cusAddress);  //creates the customer constructor
		
		
		
		
		System.out.println("What is the project number: ");
		
		int proNum = Integer.parseInt(Objec.nextLine());
		
        System.out.println("What is the building type: ");
		
		String buildType = Objec.nextLine();
		
		String proName = buildType + " " + cusSurname;
		
        System.out.println("What is the physical address: ");
		
		String address = Objec.nextLine();
		
        System.out.println("What is the ERF number: ");
		
		String ERFnum = Objec.nextLine();
		
        System.out.println("What is the total Fee for the project: ");
		
		int totalFee = Integer.parseInt(Objec.nextLine());
		
        System.out.println("What is the total fee due: ");
		
		int totalFeeDue = Integer.parseInt(Objec.nextLine());
		
        System.out.println("What is the projects deadline: ");
		
		String proDeadline = Objec.nextLine();
		
		
		String line = "";
		
		boolean bStop = true;
		
		while (bStop) {
			
			System.out.println("The project number is " + proNum);
			
			System.out.println("The project name is " + proName);
			
			System.out.println("The building type is " + buildType);
			
			System.out.println("The physical address is " + address);
			
			System.out.println("The ERF number is " + ERFnum);
			
			System.out.println("The total Fee of the project is R" + totalFee);
			
			System.out.println("The total fee due is R" + totalFeeDue);
			
			System.out.println("The deadline is " + proDeadline);
		
		
	    	System.out.println("\nIs this the correct information? ('no' or 'yes')");  //checks if the user wants to change any information
		    
	    	line = Objec.nextLine();
	    	
	    	if (line.equals("yes")) {
	    		
	    		bStop = false;
	    	}
	    	
	    	if (line.equals("no")) {
			
	    		System.out.println("Do you want to change the deadline of the project?");  //change deadline?
	    		
	    		line = Objec.nextLine();
			
	    		if (line.equals("yes")) {
				
	    			System.out.println("What is the new deadline?") ;
				
		    		proDeadline = Objec.nextLine();
				
		    	}
			
		    	System.out.println("Do you want to change the total Fee Due of the project?"); //change the fee due
		    	
		    	line = Objec.nextLine();
			
		    	if (line.equals("yes")) {
				
		    		System.out.println("What is the new total fee due?") ;
				
		    		totalFeeDue = Integer.parseInt(Objec.nextLine());
				
		    	}
		    	
	    	}
	    	
		}
	    	
	    		
			
			
	    Project project = new Project(proName, proNum, buildType, address, ERFnum, totalFee, totalFeeDue, proDeadline);  //creates the project constructor
	    
	    System.out.println("\n\nWhat person do you want to add to the project");
	    
	    System.out.println("What is the work type :");
	    
	    String workType = Objec.nextLine();
	    
        System.out.println("What is the name :");
	    
	    String name = Objec.nextLine();
	    
        System.out.println("What is the phone number :");
	    
	    String telephone = Objec.nextLine();
	    
        System.out.println("What is the email address :");
	    
	    String email = Objec.nextLine();
	    
        System.out.println("What is the physical address :");
	    
	    String Paddress = Objec.nextLine();
	    
	    person person = new person(workType, name, telephone, email, Paddress);  //adds a person to this project.
	    
	    
	    
	    System.out.println("\n\n");
	    
	    System.out.println(customer.toString());
	    
	    System.out.println("The customer must still pay " + project.totalFeeDue);
	    
	    System.out.println("\nThe people working on the project: ");
	    
	    System.out.println(person.toString());
	    
	    
	    
	    if (totalFeeDue == 0) {  //checks if the customer has finished paying
	    	
	    	Formatter T = new Formatter("finalised.txt");  //creates a textfile
	    	
	    	Date today = Calendar.getInstance().getTime();
	    	
	    	T.format("Time finished: " + today);
	    	T.format("\n\n");
	    	T.format(customer.toString());
	    	T.format("\n\n"); 
	    	T.format(project.toString());
	    	T.format("\n\n"); 
	    	T.format(person.toString());
	    	T.close();

	    }
	    
        if (totalFeeDue > 0) { //if the customer has not payed the full amount
	    	
	    	Formatter T = new Formatter("invoice.txt");  //creates a textfile
	    
	    	T.format(customer.toString());
	    	
	    	T.format("\n\n");
	    	
	    	T.format("You still have to pay" + Integer.toString(project.totalFeeDue));
	    	T.close();
	    	

	    }
        
	    

	    
	    
			
		}
		
		
		
		
	}

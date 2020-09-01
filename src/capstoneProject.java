import java.util.Formatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class capstoneProject {
	
	public static void main(String [] args) throws FileNotFoundException{
		
		Scanner objec = new Scanner(System.in);  // Create a Scanner
		
		System.out.println("What is the customers first name: ");
		
		String cusName = objec.nextLine();
		
		System.out.println("What is the customers surname: ");
		
		String cusSurname = objec.nextLine();
		
		cusName = cusName + " " + cusSurname;
		
		String cusWorkType = "Customer";
		
        System.out.println("What is the customers telephone number: ");
		
		String cusTele = objec.nextLine();
		
        System.out.println("What is the customers email address: ");
		
		String cusEmailaddress = objec.nextLine();
		
        System.out.println("What is the customers physical address: ");
		
		String cusAddress = objec.nextLine();
		
		person customer = new person(cusWorkType, cusName, cusTele, cusEmailaddress, cusAddress);  //creates the customer constructor
		
		
		
		
		System.out.println("What is the project number: ");
		
		String line = objec.nextLine();
		
		try {  //makes sure it is a valid integer
	
		    int proNum = Integer.parseInt(line);
		
		}
		
		catch(Exception e) {
			
			System.out.println("Not a valid integer.Please enter a valid project number: ");  //throws exception
			
			line = objec.nextLine();
			
			int proNum = Integer.parseInt(line);
		}
		
		int proNum = Integer.parseInt(line);
		
		
        System.out.println("What is the building type: ");
		
		String buildType = objec.nextLine();
		
		String proName = buildType + " " + cusSurname;
		
        System.out.println("What is the physical address: ");
		
		String address = objec.nextLine();
		
        System.out.println("What is the ERF number: ");
		
		String erfNum = objec.nextLine();
		
        System.out.println("What is the total Fee for the project: ");
        
        line = objec.nextLine();
        
        try {  //checks for a valid integer
			
        	int totalFee = Integer.parseInt(line);
    		
    	}
    		
    	catch(Exception e) {
    			
    		System.out.println("Not a valid integer. Please enter the total fee of the project: ");
    		
    		line = objec.nextLine();
    		
    		int totalFee = Integer.parseInt(line);
    		

    	}
        
        int totalFee = Integer.parseInt(line);
		
        System.out.println("What is the total fee due: ");
        
        line = objec.nextLine();
        
        try { //checks for a valid integer
        	
        	
        	int totalFeeDue = Integer.parseInt(line);
    		
    	}
    		
    	catch(Exception e) {
    			
    		System.out.println("Not a valid integer. Please enter the total fee due: ");
    		
    		line = objec.nextLine();
    		
    		int totalFeeDue = Integer.parseInt(line);
    		
    	}
		
		int totalFeeDue = Integer.parseInt(line);
		
        System.out.println("What is the projects deadline: ");
		
		String proDeadline = objec.nextLine();
		
		
		
		line = "";
		
		boolean bStop = true;
		
		while (bStop) {
			
			System.out.println("The project number is " + proNum);
			
			System.out.println("The project name is " + proName);
			
			System.out.println("The building type is " + buildType);
			
			System.out.println("The physical address is " + address);
			
			System.out.println("The ERF number is " + erfNum);
			
			System.out.println("The total Fee of the project is R" + totalFee);
			
			System.out.println("The total fee due is R" + totalFeeDue);
			
			System.out.println("The deadline is " + proDeadline);
		
		
	    	System.out.println("\nIs this the correct information? ('no' or 'yes')");  //checks if the user wants to change any information
		    
	    	line = objec.nextLine();
	    	
	    	if (line.equals("yes")) {
	    		
	    		bStop = false;
	    	}
	    	
	    	if (line.equals("no")) {
			
	    		System.out.println("Do you want to change the deadline of the project?");  //change deadline?
	    		
	    		line = objec.nextLine();
			
	    		if (line.equals("yes")) {
				
	    			System.out.println("What is the new deadline?") ;
				
		    		proDeadline = objec.nextLine();
				
		    	}
			
		    	System.out.println("Do you want to change the total Fee Due of the project?"); //change the fee due
		    	
		    	line = objec.nextLine();
			
		    	if (line.equals("yes")) {
				
		    		System.out.println("What is the new total fee due?") ;
				
		    		totalFeeDue = Integer.parseInt(objec.nextLine());
				
		    	}
		    	
	    	}
	    	
		}	
		
		
			
	    Project project = new Project(proName, proNum, buildType, address, erfNum, totalFee, totalFeeDue, proDeadline);  //creates the project constructor
	    
	    System.out.println("\n\nWhat person do you want to add to the project");
	    
	    System.out.println("What is the work type :");
	    
	    String workType = objec.nextLine();
	    
        System.out.println("What is the name :");
	    
	    String name = objec.nextLine();
	    
        System.out.println("What is the phone number :");
	    
	    String telephone = objec.nextLine();
	    
        System.out.println("What is the email address :");
	    
	    String email = objec.nextLine();
	    
        System.out.println("What is the physical address :");
	    
	    String pAddress = objec.nextLine();
	    
	    person person = new person(workType, name, telephone, email, pAddress);  //adds a person to this project.
	    
	    
	    
	    System.out.println("\n\n");
	    
	    System.out.println(customer.toString());
	    
	    System.out.println("The customer must still pay " + project.totalFeeDue);
	    
	    System.out.println("\nThe people working on the project: ");
	    
	    System.out.println(person.toString());
	    
	    
	    
	    if (totalFeeDue == 0) {  //checks if the customer has finished paying
	    	
	    	Formatter textFile = new Formatter("finalised.txt");  //creates a textfile
	    	
	    	Date today = Calendar.getInstance().getTime();
	    	
	    	textFile.format("Time finished: " + today);
	    	textFile.format("\n\n");
	    	textFile.format(customer.toString());
	    	textFile.format("\n\n"); 
	    	textFile.format(project.toString());
	    	textFile.format("\n\n"); 
	    	textFile.format(person.toString());
	    	textFile.close();

	    }
	    
        if (totalFeeDue > 0) { //if the customer has not payed the full amount
	    	
	    	Formatter textFile = new Formatter("invoice.txt");  //creates a textfile
	    
	    	textFile.format(customer.toString());
	    	
	    	textFile.format("\n\n");
	    	
	    	textFile.format("You still have to pay" + Integer.toString(project.totalFeeDue));
	    	textFile.close();
	    	

	    }
        
	    

	    
	    
			
		}
		
		
		
		
	}

import java.util.Formatter;

import java.time.LocalDate;
import java.time.Month;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class capstoneProject {
	
	public static void main(String [] args) throws FileNotFoundException{
		
		try {
			
			boolean stop = false; //declare variables
			
			int counter = 0;
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/manager_db?useSSL=false",
					"otheruser","Cheesee123#");
			
			Statement statement = connection.createStatement(); //setups connections
			
			ResultSet results;
			
			int rowsAffected; //setup connections
			
			ArrayList<String> proNameList = new ArrayList<String>(100); //create the lists
			ArrayList<Integer> proNumList = new ArrayList<Integer>(100); 
			ArrayList<String> buildTypeList = new ArrayList<String>(100); 
			ArrayList<String> addressList = new ArrayList<String>(100); 
			ArrayList<String> erfNumList = new ArrayList<String>(100); 
			ArrayList<Integer> totalFeeList = new ArrayList<Integer>(100); 
			ArrayList<Integer> totalFeeDueList = new ArrayList<Integer>(100); 
			ArrayList<String> proDeadlineList = new ArrayList<String>(100); 
			
			Scanner objec = new Scanner(System.in);  // Create a Scanner
				
			System.out.println("Do you want to create a new project or edit an existing one? 'new' or 'edit': "); //asks user
			
			String answer = objec.nextLine(); //user input
			
			answer.trim(); //trims the " " off it
			
			if (answer.equals("new")) {
				
				System.out.println("Do you want a new invoice or project? 'invoice','project':");// asks user
				
				if (objec.nextLine().equals("invoice")) {
					
					results = statement.executeQuery("SELECT * FROM prrojects");
					
					while (results.next()) {  //shows each row
						
						proNameList.add(results.getString("pro_name"));  //gets the variables from the database
						proNumList.add(results.getInt("pro_num"));
						buildTypeList.add(results.getString("pro_type"));
						addressList.add(results.getString("pro_address"));
						erfNumList.add(results.getString("pro_erf"));
						totalFeeList.add(results.getInt("pro_fee"));
						totalFeeDueList.add(results.getInt("pro_feedue"));
						proDeadlineList.add(results.getString("pro_deadline"));
						
						
					}
					
					
					System.out.println("Projects: \n");
					
					for (int i = 0; i < counter; i++) { //for loop
					    String output = "\nProject Name: " + proNameList.get(i);
					    output += "\nProject Number: " + proNumList.get(i);
					    output += "\nType of building: " + buildTypeList.get(i);
					    output += "\nPhysical Address: " + addressList.get(i);
					    output += "\nERF number : " + erfNumList.get(i);
					    output += "\nTotal Fee: " + totalFeeList.get(i);
					    output += "\nTotal Fee still due: " + totalFeeDueList.get(i);
					    output += "\nProjects Deadline: " + proDeadlineList.get(i);
					    output += "\nThis is number " + i+1;
					
					    System.out.println(output);  //displays each project
					    
					}
							
				    
							
				    System.out.println("What number do you want to make an invoice for: ");
				
				    answer = objec.nextLine(); //user input
					
				    int newIndex = Integer.parseInt(answer) -1;
					
					Project project = new Project(proNameList.get(newIndex), proNumList.get(newIndex), buildTypeList.get(newIndex), addressList.get(newIndex), erfNumList.get(newIndex), totalFeeList.get(newIndex), totalFeeDueList.get(newIndex), proDeadlineList.get(newIndex));  //creates the project constructor
					
					System.out.println("What is the customers first name: "); //gets the users input
					
					String cusName = objec.nextLine();//user input
					
					System.out.println("What is the customers surname: ");
					
					String cusSurname = objec.nextLine();//user input
					
					cusName = cusName + " " + cusSurname;
					
					String cusWorkType = "Customer";
					
			        System.out.println("What is the customers telephone number: ");
					
					String cusTele = objec.nextLine();//user input
					
			        System.out.println("What is the customers email address: ");
					
					String cusEmailaddress = objec.nextLine();//user input
					
			        System.out.println("What is the customers physical address: ");
					
					String cusAddress = objec.nextLine();//user input
					
					person customer = new person(cusWorkType, cusName, cusTele, cusEmailaddress, cusAddress);  //creates the customer constructor
					
	                System.out.println("\n\nWhat person do you want to add to the project");
				    
				    System.out.println("What is the work type :");
				    
				    String workType = objec.nextLine();//user input
				    
			        System.out.println("What is the name :");
				    
				    String name = objec.nextLine();//user input
				    
			        System.out.println("What is the phone number :");
				    
				    String telephone = objec.nextLine();//user input
				    
			        System.out.println("What is the email address :");
				    
				    String email = objec.nextLine();//user input
				    
			        System.out.println("What is the physical address :");
				    
				    String pAddress = objec.nextLine(); //user input
				    
				    person person = new person(workType, name, telephone, email, pAddress);  //adds a person to this project.
				    
				    int answerIndex = Integer.parseInt(answer) -1;
				    
					if (totalFeeDueList.get(newIndex) == 0) {  //checks if the customer has finished paying
				    	
				    	Formatter textFile = new Formatter("finalised.txt");  //creates a textfile
				    	
				    	Date today = Calendar.getInstance().getTime(); //gets todays date
				    	
				    	textFile.format("Time finished: " + today); //adds to the file
				    	textFile.format("\n\n");
				    	textFile.format(customer.toString());
				    	textFile.format("\n\n"); 
				    	textFile.format(project.toString());
				    	textFile.format("\n\n"); 
				    	textFile.format(person.toString());
				    	textFile.close();
				    	
						rowsAffected = statement.executeUpdate("DELETE FROM projects WHERE erf = '" + erfNumList.get(answerIndex) + "'");
				    
					}

				    
			        if (totalFeeDueList.get(newIndex) > 0) { //if the customer has not payed the full amount
				    	
				    	Formatter textFile = new Formatter("invoice.txt");  //creates a textfile
				    
				    	textFile.format(customer.toString());  //adds to the file
				    	
				    	textFile.format("\n\n");
				    	
				    	textFile.format("You still have to pay " + Integer.toString(project.totalFeeDue)); 
				    	textFile.close();
			        }
			        	
					
				} else {
					
					System.out.println("What is the project number: "); //asks for number
					
					String line = objec.nextLine();
					
					try {  //makes sure it is a valid integer
				
					    int proNum = Integer.parseInt(line); //user input
					
					}
					
					catch(Exception e) {
						
						System.out.println("Not a valid integer.Please enter a valid project number: ");  //throws exception
						
						line = objec.nextLine();
						
						int proNum = Integer.parseInt(line); //adds it
					}
					
					int proNum = Integer.parseInt(line);
					
					System.out.println("What is the customers surname: "); //asks for the surname
					
					String cusSurname = objec.nextLine();//user input
					
			        System.out.println("What is the building type: "); //asks for building type
					
					String buildType = objec.nextLine(); //user input
					
					String proName = buildType + " " + cusSurname; //gets the name of project
					
			        System.out.println("What is the physical address: "); //asks for the address
					
					String address = objec.nextLine();//user input
					
			        System.out.println("What is the ERF number: "); //asks the erf number
					
					String erfNum = objec.nextLine(); //user input
					
			        System.out.println("What is the total Fee for the project: "); //asks the fee
			        
			        line = objec.nextLine();
			        
			        try {  //checks for a valid integer
						
			        	int totalFee = Integer.parseInt(line); //gets users input
			    		
			    	}
			    		
			    	catch(Exception e) {
			    			
			    		System.out.println("Not a valid integer. Please enter the total fee of the project: "); //displays not a valid integer and asks again
			    		
			    		line = objec.nextLine();
			    		
			    		int totalFee = Integer.parseInt(line); //adds it
			    		

			    	}
			        
			        int totalFee = Integer.parseInt(line);
					
			        System.out.println("What is the total fee due: "); //asks for the total fee due
			        
			        line = objec.nextLine();
			        
			        try { //checks for a valid integer
			        	
			        	
			        	int totalFeeDue = Integer.parseInt(line); //adds it
			    		
			    	}
			    		
			    	catch(Exception e) {
			    			
			    		System.out.println("Not a valid integer. Please enter the total fee due: "); //asks for fee due
			    		
			    		line = objec.nextLine();
			    		
			    		int totalFeeDue = Integer.parseInt(line); //gets users input
			    		
			    	}
					
					int totalFeeDue = Integer.parseInt(line);
					
			        System.out.println("What is the projects deadline: 'dd/mm/yyyy'"); //gets the dealine
					
					String proDeadline = objec.nextLine(); //gets users input
					
					
					
					line = "";
					
					boolean bStop = true;
					
					while (bStop == true) { //while loop
						
						System.out.println("The project number is " + proNum); //displays the output and asks user if they want to change it
						
						System.out.println("The project name is " + proName);
						
						System.out.println("The building type is " + buildType);
						
						System.out.println("The physical address is " + address);
						
						System.out.println("The ERF number is " + erfNum);
						
						System.out.println("The total Fee of the project is R" + totalFee);
						
						System.out.println("The total fee due is R" + totalFeeDue);
						
						System.out.println("The deadline is " + proDeadline);
					
					
				    	System.out.println("\nIs this the correct information? ('no' or 'yes')");  //checks if the user wants to change any information
					    
				    	line = objec.nextLine();
				    	
				    	if (line.equals("yes")) { //stops the loop
				    		
				    		bStop = false;
				    	}
				    	
				    	if (line.equals("no")) {
						
				    		System.out.println("Do you want to change the deadline of the project?");  //change deadline?
				    		
				    		line = objec.nextLine();
						
				    		if (line.equals("yes")) {
							
				    			System.out.println("What is the new deadline? 'dd/mm/yyyy' ") ;
							
					    		proDeadline = objec.nextLine(); //gets the users new value
							
					    	}
						
					    	System.out.println("Do you want to change the total Fee Due of the project?"); //change the fee due
					    	
					    	line = objec.nextLine();
						
					    	if (line.equals("yes")) {
							
					    		System.out.println("What is the new total fee due?") ;
							
					    		totalFeeDue = Integer.parseInt(objec.nextLine()); //gets the users new value
							
					    	}
					    	
				    	}
				    	
					}	
					
			        
			        rowsAffected = statement.executeUpdate("INSERT INTO projects VALUES ('" + proName + "', " + proNum + ", '" + buildType + "', '" + address + "', '" + erfNum + "', " + totalFee + ", " + totalFeeDue + ", '" + proDeadline + "' )" ); //adds new project
			         
									
				}
		       
			}else {	
				
				results = statement.executeQuery("SELECT * FROM prrojects"); //displays everything
				
				while (results.next()) {  //shows each row
					
					proNameList.add(results.getString("pro_name"));  //adds to list
					proNumList.add(results.getInt("pro_num"));
					buildTypeList.add(results.getString("pro_type"));
					addressList.add(results.getString("pro_address"));
					erfNumList.add(results.getString("pro_erf"));
					totalFeeList.add(results.getInt("pro_fee"));
					totalFeeDueList.add(results.getInt("pro_feedue"));
					proDeadlineList.add(results.getString("pro_deadline"));
					
					
				}
				
				
				System.out.println("Projects: \n");
				
				int num = 0;
				
				for (int i = 0; i < counter; i++) { //for loop
				    String output = "\nProject Name: " + proNameList.get(i);
				    output += "\nProject Number: " + proNumList.get(i);
				    output += "\nType of building: " + buildTypeList.get(i);
				    output += "\nPhysical Address: " + addressList.get(i);
				    output += "\nERF number : " + erfNumList.get(i);
				    output += "\nTotal Fee: " + totalFeeList.get(i);
				    output += "\nTotal Fee still due: " + totalFeeDueList.get(i);
				    output += "\nProjects Deadline: " + proDeadlineList.get(i);
				    num = i +1;
				    output += "\nThis is number " + num;
				
				    System.out.println(output); //displays the output
				    
				}    
			
			
			
				System.out.println("What number do you want to edit: "); //users input
			
				answer = objec.nextLine(); 
			
				if (Integer.parseInt(answer) > counter) {  //makes sure it was an integer
				
					System.out.println("Not a valid number");
				
				} else {
				
					int newIndex = Integer.parseInt(answer) -1; //gets the correct index
					String output = "\n1) Project Name: " + proNameList.get(Integer.parseInt(answer)-1);
					output += "\n2) Project Number: " + proNumList.get(Integer.parseInt(answer)-1);
					output += "\n3) Type of building: " + buildTypeList.get(Integer.parseInt(answer)-1);
					output += "\n4) Physical Address: " + addressList.get(Integer.parseInt(answer)-1);
					output += "\n5) ERF number : " + erfNumList.get(Integer.parseInt(answer)-1);
					output += "\n6) Total Fee: " + totalFeeList.get(Integer.parseInt(answer)-1);
					output += "\n7) Total Fee still due: " + totalFeeDueList.get(Integer.parseInt(answer)-1);
					output += "\n8) Projects Deadline: " + proDeadlineList.get(Integer.parseInt(answer)-1);
			
					System.out.println(output); //display the output
			    
					int index = Integer.parseInt(answer) -1;
			    
					System.out.println("What number do you want to edit? ");
			    
					answer = objec.nextLine();
			    
					switch(answer) {  //case statement
			    
					case "1" :  //edit the name
			    	
						System.out.println("What is the new project name?");
						proNameList.set(index,objec.nextLine());
						
						rowsAffected = statement.executeUpdate("UPDATE projects SET pro_name = '" + proNameList.get(index) +"' WHERE pro_erf = '" + erfNumList.get(index) + "'");
						
						break;
			    	
					case "2" ://edit the number
			    	
						System.out.println("What is the new project number?");
						proNumList.set(index,Integer.parseInt(objec.nextLine()));
						rowsAffected = statement.executeUpdate("UPDATE projects SET pro_num = '" + proNumList.get(index) +"' WHERE pro_erf = '" + erfNumList.get(index) + "'");
						break;
						
					case "3" ://edit the building
			    	
						System.out.println("What is the new type of building?");
						buildTypeList.set(index,objec.nextLine());
						rowsAffected = statement.executeUpdate("UPDATE projects SET pro_type = '" + buildTypeList.get(index) +"' WHERE pro_erf = '" + erfNumList.get(index) + "'");
						break;
			    	
					case "4" ://edit the address
			    	
						System.out.println("What is the new physical address?");
						addressList.set(index,objec.nextLine());
						rowsAffected = statement.executeUpdate("UPDATE projects SET pro_address = '" + addressList.get(index) +"' WHERE pro_erf = '" + erfNumList.get(index) + "'");
						break;
			    	
					case "5" ://edit the erf number
			    	
						System.out.println("What is the new ERF number?");
						erfNumList.set(index,objec.nextLine());
						rowsAffected = statement.executeUpdate("UPDATE projects SET pro_erf = '" + erfNumList.get(index) +"' WHERE pro_name = '" + proNameList.get(index) + "'");
						break;
			    	
					case "6" ://edit the totalfee
			    	
						System.out.println("What is the new total fee?");
						totalFeeList.set(index,Integer.parseInt(objec.nextLine()));
						rowsAffected = statement.executeUpdate("UPDATE projects SET pro_fee = " + totalFeeList.get(index) +"WHERE pro_erf = '" + erfNumList.get(index) + "'");
						break;
			    	
					case "7" ://edit the totalfee due
			    	
						System.out.println("What is the new total fee due?");
						totalFeeDueList.set(index,Integer.parseInt(objec.nextLine()));
						rowsAffected = statement.executeUpdate("UPDATE projects SET pro_feedue = '" + totalFeeDueList.get(index) +"' WHERE pro_erf = '" + erfNumList.get(index) + "'");
						break;
			    	
					case "8" ://edit the deadline
			    	
						System.out.println("What is the new project deadline? 'dd/mm/yyyy'");
						proDeadlineList.set(index,objec.nextLine());
						rowsAffected = statement.executeUpdate("UPDATE projects SET pro_deadline = '" + proDeadlineList.get(index) +"' WHERE pro_erf = '" + erfNumList.get(index) + "'");
						break;
			    	
					}
			    
			    
					
				}
			}
				
			
			results.close();
			statement.close(); //close connections
			connection.close();   
			    	
		} catch (IOException e) {
			    	
			e.printStackTrace();
		} 
			    
		
	}
	
}
		

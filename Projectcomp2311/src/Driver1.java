import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Scanner;

public class Driver1 {
private static final String FILE_PATH = "Filee.txt";

	public static void main(String[] args)
			throws MissingParentException, AgeException, GenderException, InvalidFamilyStructureException {
		Manager manager = new Manager();

		Scanner scan = new Scanner(System.in);

		ArrayList<Family> families = new ArrayList<>();

		while (true) {
			System.out.println("Choose an option:");
			System.out.println("1. Add Member and write file");
			System.out.println("2. Remove Member");
			System.out.println("3.Deep Copy ");
			System.out.println("4. Remove Parent");
			System.out.println("5. Equals");
			System.out.println("6. Add Family");
			System.out.println("7. Update Family");
			System.out.println("8. Delete Family");
			System.out.println("9. Search by Name");
			System.out.println("10. Search Person by ID");
			System.out.println("11. Calculate Total Martyrs");
			System.out.println("12. Calculate Total Orphans");
			System.out.println("13. Calculate Total Live Persons");
			System.out.println("14. Calculate Family Statistics");
			System.out.println("15. Calculate Global Statistics");
			System.out.println("16:                            ");
			System.out.println("17. read file");
			System.out.println("18. exist");

			int choice = scan.nextInt();
			scan.nextLine();

			switch (choice) {
			case 1:
				// Add Member
				System.out.println("Enter Family Name:");
				String familyName = scan.nextLine();
				Family family = manager.searchByName(familyName);

				if (family != null) {
					// Ask the user if the member is a martyr or alive
					System.out.println("Is the member a martyr? (yes/no)");
					String isMartyrInput = scan.nextLine();
					System.out.println("Enter Member ID to add:");
					String memberid = scan.nextLine();
					Person memberToAdd = manager.searchPersonByID(memberid);

					if (memberToAdd != null) {
				        System.out.println("Member ID already add. Please enter a unique ID.");
				        break;
				    }
					
					if (isMartyrInput.equalsIgnoreCase("yes")) {
						// If the person is a martyr, create a Martyr object
						Martyr martyr = new Martyr();

						System.out.println("Enter Martyr Name:");
						martyr.setName(scan.nextLine());

						System.out.println("Enter Martyr Age:");
						while (true) {
							try {
								martyr.setAge(Integer.parseInt(scan.nextLine()));
								break; // Break the loop if the input is a valid integer
							} catch (NumberFormatException e) {
								System.out.println("Invalid input. Please enter a valid integer for Martyr Age:");
							}
						}

						System.out.println("Enter Martyr Gender:");
						martyr.setGender(scan.nextLine());

						System.out.println("Enter Martyr Address:");
						martyr.setAddress(scan.nextLine());

						System.out.println("Enter Martyr Contact Info:");
						martyr.setContactInfo(scan.next());
						martyr.setID(memberid);
						System.out.println("Enter Martyr Role (son, daughter):");
						String role = scan.next();

						// Ask for details specific to martyrs
						System.out.println("Enter Date Of Martyrdom:");
						martyr.setDateOfMartyrdom(scan.nextLine());

						System.out.println("Enter Cause Of Death:");
						martyr.setCauseOfDeath(scan.nextLine());

						System.out.println("Enter Place Of Death:");
						martyr.setPlaceOfDeath(scan.nextLine());

						// Add the martyr to the family
						family.addMember(martyr, role);
						System.out.println("Martyr added successfully: " + martyr.toString());
					} else if (isMartyrInput.equalsIgnoreCase("no")) {
						// If the person is alive, create a LivePerson object
						LivePerson newMember = new LivePerson();

						System.out.println("Enter Member Name:");
						newMember.setName(scan.next());

						System.out.println("Enter Member Age:");
						while (true) {
							try {
								newMember.setAge(Integer.parseInt(scan.next()));
								break; // Break the loop if the input is a valid integer
							} catch (NumberFormatException e) {
								System.out.println("Invalid input. Please enter a valid integer for Member Age:");
							}
						}

						System.out.println("Enter Member Gender:");
						newMember.setGender(scan.next());

						System.out.println("Enter Member Address:");
						newMember.setAddress(scan.next());

						System.out.println("Enter Member Contact Info:");
						newMember.setContactInfo(scan.next());

						System.out.println("Enter Member Role (son, daughter):");
						String role = scan.next();
						newMember.setID(memberid);
						// Add the live person to the family
						family.addMember(newMember, role);
						System.out.println(family.toString());
						System.out.println("Live person added successfully: " + newMember.toString());
					} else {
						System.out.println("Invalid input. Please enter 'yes' or 'no'.");
					}
				} else {
					System.out.println("Family not found.");
				}
				writeDataToFile(family);
			//	writeDataToFile(family.toString());
				//writeDataToFile(families);
				break;

			case 2:

				System.out.println("Enter Family Name:");
				familyName = scan.nextLine();
				family = manager.searchByName(familyName);

				if (family != null) {

					System.out.println("Enter Member ID to remove:");
					String memberIdToRemove = scan.nextLine();
					Person memberToRemove = manager.searchPersonByID(memberIdToRemove);

					if (memberToRemove != null) {
						family.removeMember(memberToRemove);
						System.out.println("Member removed successfully.");
					} else {
						System.out.println("Failed to remove member");
					}
				} else {
					System.out.println("Family not found.");
				}
				break;

			// Inside your main program loop
			case 3:
				// Deep copy Martyrs
				System.out.println("Enter Family Name to deep copy Martyrs:");
				String familyToDeepCopyMartyrs = scan.nextLine();
				Family familyForMartyrs = manager.searchByName(familyToDeepCopyMartyrs);

				if (familyForMartyrs != null) {
					try {
						Family clonedMartyrsFamily = familyForMartyrs.deepCopyMartyrs();
						System.out.println("Deep copied Martyrs family: " + clonedMartyrsFamily.toString());
						System.out.println(clonedMartyrsFamily == familyForMartyrs);
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
						System.out.println("Failed to deep copy Martyrs family.");
					}
				} else {
					System.out.println("Family not found.");
				}

				// Deep copy LivePersons
				System.out.println("Enter Family Name to deep copy LivePersons:");
				String familyToDeepCopyLivePersons = scan.nextLine();
				Family familyForLivePersons = manager.searchByName(familyToDeepCopyLivePersons);

				if (familyForLivePersons != null) {
					try {
						Family clonedLivePersonsFamily = familyForLivePersons.deepCopyLivePersons();
						System.out.println("Deep copied LivePersons family: " + clonedLivePersonsFamily.toString());
						System.out.println(clonedLivePersonsFamily == familyForLivePersons);
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
						System.out.println("Failed to deep copy LivePersons family.");
					}
				} else {
					System.out.println("Family not found.");
				}

				// Clone the original family
				System.out.println("Enter Family Name to deep copy Martyrs:");
				String familyToDeepCopy = scan.nextLine();
				Family familyFor = manager.searchByName(familyToDeepCopy);
				Family clonedFamily = null;
				try {
					clonedFamily = (Family) familyFor.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}

				if (clonedFamily != null) {
					System.out.println("Family copied successfully.");
					System.out.println(clonedFamily.toString());
					System.out.println(clonedFamily == familyFor);

					System.out.println("Enter new Family Name:");
					String newFamilyNameInput = scan.nextLine();
					clonedFamily.setFamilyName(newFamilyNameInput);
					System.out.println(clonedFamily.toString());
				} else {
					System.out.println("Failed to copy family.");
				}

				break;

			case 4:
				// Remove Parent
				Family famil = new Family();
				System.out.println("Enter Parent ID to remove:");
				String parentIdToRemove = scan.nextLine();
				Person parentToRemove = manager.searchPersonByID(parentIdToRemove);// Call the method in the
																					// class
																					// manager
				// It checks the parentId if it exists
				if (parentIdToRemove != null) {
					famil.removeParent(parentToRemove);// Call the method in the class family
					System.out.println("Parent removed successfully.");

				} else {
					System.out.println("Failed to remove parent.");
				}

				break;

			case 5:
				System.out.println("Enter Family Name 1:");
				String family11 = scan.nextLine();
				Family family1 = manager.searchByName(family11);

				System.out.println("Enter Family Name 2:");
				String family22 = scan.nextLine();
				Family family23 = manager.searchByName(family22);

				if (family11 != null && family22 != null) {
					boolean Equal = family1.equals(family23);
					System.out.println("Families are " + Equal);
					System.out.println(family1.toString());
					System.out.println(family23.toString());
				} else {
					System.out.println("One or both families not found.");
				}
				break;

			case 6:
			    // Add Family
			    System.out.println("Enter Family Name:");
			    String newFamilyName = scan.nextLine();
			    Family newFamily = new Family();
			    newFamily.setFamilyName(newFamilyName);

			    // Add parents for the new family
			    boolean errorOccurred = false; // Flag to check if an error occurred during parent addition
			    for (int i = 1; i <= 2; i++) {
			        // Ask the user for details of the parent
			        System.out.println("Enter Parent " + i + " ID:");
			        String parentId = scan.nextLine();
			        Person memberToAdd = manager.searchPersonByID(parentId);

					if (memberToAdd != null) {
				        System.out.println("Member ID already add. Please enter a unique ID.");
				        break;
				    }
			        // Create a new parent (you can customize this based on your Person class hierarchy)
			        Person parent;
			        System.out.println("Is Parent " + i + " a martyr? (yes/no)");
			        String isMartyr = scan.nextLine();
			        if (isMartyr.equalsIgnoreCase("yes")) {
			            // If the parent is a martyr, create a Martyr object
			            parent = new Martyr();

			            // Set Martyr-specific properties
			            Martyr martyr = (Martyr) parent;
			            System.out.println("Enter Date Of Martyrdom for Parent " + i + ":");
			            martyr.setDateOfMartyrdom(scan.nextLine());
			            martyr.setID(parentId);
			            System.out.println("Enter Cause Of Death for Parent " + i + ":");
			            martyr.setCauseOfDeath(scan.nextLine());

			            System.out.println("Enter Place Of Death for Parent " + i + ":");
			            martyr.setPlaceOfDeath(scan.nextLine());
			        } else {
			            // If the parent is alive, create a LivePerson object
			            parent = new LivePerson();
			        }

			        // Set common properties for both LivePerson and Martyr
			        parent.setID(parentId);
			        System.out.println("Enter Parent " + i + " Name:");
			        parent.setName(scan.nextLine());

			        System.out.println("Enter Parent " + i + " Gender:");
			        parent.setGender(scan.nextLine());
			        System.out.println("Enter Parent " + i + " Address:");
			        parent.setAddress(scan.nextLine());
			        System.out.println("Enter Parent " + i + " Contact Info:");
			        parent.setContactInfo(scan.nextLine());
			        parent .setID(parentId);
			        // Try to add the parent to the family
			        newFamily.addParent(parent);
			    }

			    // Add the family to the manager (assuming FamilyManager is instantiated and named 'manager')
			    if (manager.addFamily(newFamily)) {
			        System.out.println("Family added successfully.");
			        System.out.println(newFamily.toString());
			    } else {
			        System.out.println("Failed to add family to the manager.");
			    }

			    
			    break;

			case 7:
				// Update Family
				System.out.println("Enter Family Name to update:");
				String familyUpdate = scan.nextLine();
				Family updatedFamily = manager.searchByName(familyUpdate);// Call the method in the class manager
				// It checks the FamilyName if it exists
				if (familyUpdate != null) {// If the method searchByName returns family
					System.out.println("Enter new Family Name:");
					String newFamilyName1 = scan.nextLine();
					updatedFamily.setFamilyName(newFamilyName1);
					System.out.println("Family upda8ted successfully.");
					System.out.println(updatedFamily.toString()); // The print statement prints the family
																	// information
																	// that has been update
				} else {// If the method searchByName returns null
					System.out.println("Family not found.");
				}
				break;

			case 8:
				// Delete Family
				System.out.println("Enter Family Name to delete:");
				String familyToDeletee = scan.nextLine();
				Martyr Martyr = new Martyr();
				Family familyToDelet = manager.searchByName(familyToDeletee);// Call the method in the class manager
				// It checks the FamilyName if it exists
				if (familyToDelet != null) {// If the method searchByName returns family The family is deleted

					manager.deleteFamily(familyToDeletee);
					System.out.println("Family deleted successfully.");

					// If the method searchByName returns null
				} else {
					System.out.println("Failed to delete family.");
				}
				break;

			case 9:
				// Search by Name
				System.out.println("Enter Family Name :");
				String FamilyName = scan.nextLine();
				Family searchedFamily = manager.searchByName(FamilyName);// Call the method in the class manager
//It checks the FamilyName if it exists
				if (FamilyName != null) {// If the method searchByName returns family
					System.out.println("Family found: " + searchedFamily.toString());
				} else {// If the method searchByName returns null
					System.out.println("Family not found.");
					System.out.println(searchedFamily.toString());
				}
				break;

			case 10:
				// Search Person by ID
				LivePerson p = new LivePerson();
				System.out.println("Enter Person ID to search:");

				String PersonId = scan.nextLine();

				Person searchedPerson = manager.searchPersonByID(PersonId);// Call the method in the class manager
//It checks the PersonId if it exists
				if (searchedPerson != null) {// If the method searchPersonByID returns person
					System.out.println("Person found: ");
					System.out.println(searchedPerson.toString());
				} else {// If the method searchPersonByID returns null
					System.out.println("Person not found.");
				}
				break;

			case 11:
				// Calculate Total Martyrs
				int totalMartyrs = manager.calculateTotalMartyrs();
				System.out.println("Total Martyrs: " + totalMartyrs);
				break;

			case 12:
				// Calculate Total Orphans
				int totalOrphans = manager.calculateTotalOrphans();
				System.out.println("Total Orphans: " + totalOrphans);

				break;
			case 13:
				int totallive = manager.calculateTotalLivePersons();
				System.out.println("totallive" + totallive);
				break;
			case 14:
				System.out.println("Enter the family name:");
				String familyNamee = scan.nextLine();
				ArrayList<Integer> familyStatistics = manager.calculateFamilyStatistics(familyNamee);
				System.out.println("Family Statistics for " + familyNamee + ": " + familyStatistics);
				break;

			case 15:
				// Calculate Global Statistics
				ArrayList<Integer> globalStatistics = manager.calculateGlobalStatistics();
				System.out.println("Global Statistics: " + globalStatistics);
				break;
			case 16:
				
				break;
			case 17:
				
				readAndProcessFile();
				break;
			case 18:

				// Quit
				System.out.println("Exiting");
				System.exit(0);
				break;
			default:
				System.out.println("Please choose a valid option.");
				break;
			}

		}
	}

	//This method is for writing to the file

	private static void writeDataToFile(Family family) {
	    try (PrintWriter out = new PrintWriter(FILE_PATH))
	    { 
	    	/*The family name is first written and then called the method that exists in the family 
	    	classThis method calculates the number of martyrs, and the second method calculates the number of orphans*/ 
	       out.println("Family " + family.getFamilyName() + ": number of martyrs (" + family.countMartyrsInFamily() + ")"+": number of Orphans  (" + family.countOrphansInFamily() + ")");
	        
	        out.println("______________________________________");
	        for (Person parent : family.getParents()) {
	        	//called the method that exists in the family  class
	            out.println("Parent: " + parent.toString());
	        }
	        out.println("*****************************************");
	      
	        for (Person member : family.getMembers()) {
	            out.println("Member: " + member.toString());
	        }

	        out.println(); // Add a blank line after each family details
	        System.out.println("Data written to file successfully.");
	    } catch (IOException e) {
	        System.out.println("Error writing to file: " + e.getMessage());
	    }
	}
	 private static void readAndProcessFile() {
		 //This method reads the file content
	        try (Scanner scanner = new Scanner(new File("Filee.txt"))) {
	            while (scanner.hasNextLine()) {
	            	// Reading the current line from the file
	                String line = scanner.nextLine();
	              // / Calling a method to process each line
	           
	                processLine(line);
	            }
	        } catch (FileNotFoundException e) {
	            System.out.println("File not found: " + FILE_PATH);
	        }
	    }

	    private static void processLine(String line) {
	    	// Method to process each line of the file
	        System.out.println("Processing line: " + line);
	    }
	}


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Manager {
	private static final String FILE_PATH = "Filee.txt";
	private ArrayList<Family> families = new ArrayList<>();
	Scanner scan = new Scanner(System.in);

	public Manager() {// no-arg constructor
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Family> getFamilies() {
		return families;
	}

// getter and setter methods
	public void setFamilies(ArrayList<Family> families) {
		this.families = families;
	}

	public boolean addFamily(Family family) {
		return families.add(family);// add family
	}

	public boolean updateFamily(String familyName, Family updatedFamily) {

		Scanner scan = new Scanner(System.in);
		System.out.println("What do you want to update? If yes, write 'yes' ");
		String answer = scan.nextLine();

		if (answer.equalsIgnoreCase("yes")) {
			for (int i = 0; i < families.size(); i++) {// The array List is checked
				Family current = families.get(i);

				if (current.getFamilyName().equals(familyName))
					System.out.println("Please enter the new Family name: ");
				String newFamilyName = scan.nextLine();
				ArrayList<Person> Parents = new ArrayList<>();
				ArrayList<Person> member = new ArrayList<>();
				// Update family name
				current.setFamilyName(newFamilyName);
				current.setParents(Parents);
				current.setMembers(member);

				System.out.println("Current Family: " + current.toString());
				return true;// Family updated successfully
			}
		}

		return false;// Family updated not found
	}

	public boolean deleteFamily(String familyName) {

		Scanner scan = new Scanner(System.in);
		System.out.println("What do you want to deleteFamily?If yes, write 'yes' ");
		String answer = scan.nextLine();
		if (answer.equalsIgnoreCase("yes")) {
			for (int i = 0; i < families.size(); i++) {// The array List is checked
				Family n = families.get(i);

				if (n.getFamilyName().equals(familyName))
					return families.remove(familyName);// delete family
			}
		}
		return false;
	}

	public Family searchByName(String familyName) {
		Scanner scan = new Scanner(System.in);
		System.out.println("What do you want to searchByName?If yes, write 'yes' ");
		String answer = scan.nextLine();
		if (answer.equalsIgnoreCase("yes")) {
			for (Family family : families) {// The family array list is checked

				if (family instanceof Family) {
					return family;// Return the family if found
				}
			}
		}
		return null;// Return the family not found
	}

	public Person searchPersonByID(String personID) {
		Scanner scan = new Scanner(System.in);
		System.out.println("What do you want to searchByid?If yes, write 'yes' ");
		String answer = scan.nextLine();
		if (answer.equalsIgnoreCase("yes")) {

			for (Family family : families) {// The family array list is checked
				for (Person person : family.getMembers()) {// The person array list is checked
					if (person.getID() != null && person.getID().equalsIgnoreCase(personID)) {
						return person;// Return the parson if found
					}

				}
			}

		}
		return null;// Return the parson not found
	}

	public int calculateTotalMartyrs() {
		// In this method, the number of martyrs in all families is calculated
		System.out.println("Do you want to calculate the total number of Martyrs persons? If yes, write 'yes'");
		String answer = scan.nextLine();

		if (answer.equalsIgnoreCase("yes")) {
			int total = 0;

			for (Family family : families) {
				// Check members for martyrs
				for (Person person : family.getMembers()) {
					if (person instanceof Martyr) {
						total++;
					}
				}

				// Check parents for martyrs
				for (Person person : family.getParents()) {
					if (person instanceof Martyr) {
						total++;
					}
				}
			}

			return total;
		} else {
			return 0;
		}
	}

	public int calculateTotalOrphans() {
		// In this method, the number of Orphans in all families is calculated
		int total = 0;

		System.out.println("What do you want to calculateTotalOrphans? If yes, write 'yes' ");
		String answer = scan.nextLine();

		if (answer.equalsIgnoreCase("yes")) {
			for (Family family : families) {
				boolean momIsMartyr = false;
				boolean dadIsMartyr = false;

				// Check parents for martyrs
				for (Person parent : family.getParents()) {
					if (parent instanceof Martyr) {
						if (((Martyr) parent).getGender().equalsIgnoreCase("male")) {
							momIsMartyr = true;
						} else if (((Martyr) parent).getGender().equalsIgnoreCase("female")) {
							dadIsMartyr = true;
						}
					}
				}

				// Check if at least one parent is a martyr
				if (momIsMartyr || dadIsMartyr) {
					// Iterate over members to count orphans
					for (Person member : family.getMembers()) {
						if (member instanceof LivePerson) {
							total++;
						}
					}
				}
			}
		} else {
			System.out.println("Not calculating the total number of orphans.");
		}

		// Returns the total number of orphans in the system. Orphans are
		// individuals whose parents (both mom and dad) have passed away.
		return total;
	}

	public int calculateTotalLivePersons() {
		// In this method, the number of live person in all families is calculated

		// In this method, the number of martyrs in all families is calculated
		System.out.println("Do you want to calculate the total number of Martyrs persons? If yes, write 'yes'");
		String answer = scan.nextLine();

		if (answer.equalsIgnoreCase("yes")) {
			int total = 0;

			for (Family family : families) {
				// Check members for martyrs
				for (Person person : family.getMembers()) {
					if (person instanceof LivePerson) {
						total++;
					}
				}

				// Check parents for martyrs
				for (Person person : family.getParents()) {
					if (person instanceof LivePerson) {
						total++;
					}
				}
			}

			return total;
		} else {
			return 0;
		}
	}

	public ArrayList<Integer> calculateFamilyStatistics(String familyName) {
		// In this method, the number of Orphans and Martyrs and livePerson in all
		// families is calculated
		ArrayList<Integer> statistics = new ArrayList<>();

		// : Returns statistics for a specific family, including
		// the number of martyrs, orphans, and live persons. Store the returned values
		// in ArrayList.
		System.out.println("Choose an option:");
		System.out.println("1. Calculate Total Martyrs &&Calculate Total Orphans&& Calculate Total Live Persons");

		int choice = scan.nextInt();
		scan.nextLine();

		int martyr = 0;
		int orphan = 0;
		int livePerson = 0;

		switch (choice) {
		case 1:
			martyr = calculateTotalMartyrs();
			System.out.println("Total Martyrs: " + martyr);
			orphan = calculateTotalOrphans();
			System.out.println("Total Orphans: " + orphan);
			livePerson = calculateTotalLivePersons();
			System.out.println("Total Live Persons: " + livePerson);

			break;

		default:
			System.out.println("Invalid choice.");
		}

		statistics.add(martyr); // add in the array list statistics
		statistics.add(orphan);
		// Returns statistics for a specific family, including
		// the number of martyrs, orphans, and live persons. Store the returned values
		// in ArrayList.
		statistics.add(livePerson);

		return statistics;
	}

	public ArrayList<Integer> calculateGlobalStatistics() {
		// : Returns overall statistics for the system. Store the returned values
		// in ArrayList.
		ArrayList<Integer> globalStatistics = new ArrayList<>();
		int totalMartyrs = 0;
		totalMartyrs = calculateTotalMartyrs();
		int totalOrphans = 0;
		totalOrphans = calculateTotalOrphans();
		int totalLivePersons = 0;
		totalLivePersons = calculateTotalLivePersons();

		globalStatistics.add(totalMartyrs);
		globalStatistics.add(totalOrphans);
		globalStatistics.add(totalLivePersons);

		return globalStatistics;
	}

	@Override
	public String toString() {
		return "Manager [families=" + families + "]";
	}
}

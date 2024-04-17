import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Family implements Sortable, Comparable<Family>, Cloneable {
	private ArrayList<Person> members = new ArrayList<>();
	private String familyName;
	private ArrayList<Person> parents = new ArrayList<>();
	private boolean sortByOrphans = false;
	Scanner scan = new Scanner(System.in);

	public Family() {// no-arg constructor

	}

	public Family(String familyName) {
		this.familyName = familyName;
		this.members = new ArrayList<>();
		this.parents = new ArrayList<>();
	}

	protected Object clone() throws CloneNotSupportedException {
		Family Fam = (Family) super.clone();
		Fam.setFamilyName(familyName);
		return super.clone();
	}

	// getter and setter methods
	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public ArrayList<Person> getMembers() {
		return members;
	}

	public ArrayList<Person> getParents() {
		return parents;
	}

	public void setMembers(ArrayList<Person> members) {
		this.members = members;
	}

	public void setParents(ArrayList<Person> newParents) {
		this.parents = newParents;
	}

	public boolean addMember(Person member, String roleFamily)
			throws MissingParentException, AgeException, GenderException {

		if (members == null) {
			members = new ArrayList<>();
		}

		if (members.contains(member)) {
			return false; // Member already exists, return false
		}

		if ("son".equalsIgnoreCase(roleFamily) || "daughter".equalsIgnoreCase(roleFamily)) {
			if (parents.size() != 2) {
				// show an error message
				throw new MissingParentException("Cannot add a family member without including parents first.");
			}
		}

		if (member.getAge() < 0) {
			throw new AgeException("Invalid age. Age must be greater than or equal to 0.");
		}

		if (!member.getGender().equalsIgnoreCase("male") && !member.getGender().equalsIgnoreCase("female")) {
			throw new GenderException("Invalid gender. Gender must be either 'male' or 'female'.");
		}

		members.add(member);

		if (member instanceof LivePerson) {
			// Your logic for updating the LivePerson status goes here
		}
		if (member instanceof Martyr) {
			// Your logic for updating the Martyr status goes here
		}
		return true; // Member added successfully

	}

	public boolean removeMember(Person member) {

		if (member != null) {// The list is checked if the person has been added previously. If true, the
								// person is deleted
			// ArrayList<Person> membersCopy = new ArrayList<>(members);

			for (Person person : members) {
				if (person != null && person.getID() != null && person.getID().equals(member.getID())) {
					members.remove(person);

				}
			}
		}
		return false;
	}

	public boolean addParent(Person parent) throws InvalidFamilyStructureException, GenderException, AgeException {

		if (parent instanceof LivePerson) {
			// Your logic for updating the LivePerson status goes here
		} else if (parent instanceof Martyr) {
			// Your logic for updating the Martyr status goes here
		}

		// Check if the family already has two parents
		if (parents.size() != 2) {
			throw new InvalidFamilyStructureException("A family can only have two parents.");
		}

		// Check gender
		String gender = parent.getGender().toLowerCase();
		if (!gender.equals("male") && !gender.equals("female")) {
			throw new GenderException("Invalid gender. Gender must be either 'male' or 'female'.");
		}

		// Check age
		if (parent.getAge() < 0) {
			throw new AgeException("Invalid age. Age must be a non-negative value.");
		}

		// Add the parent to the family
		parents.add(parent);

		// If there are exactly 2 parents, return true
		return parents.size() == 2;
	}

	public boolean removeParent(Person parent) {

		return parents.remove(parent);// parent remove successfully
	}

	@Override
	public boolean equals(Object obj) {

		Family family = (Family) obj;
		return familyName.equals(family.familyName);
	}

	@Override
	public ArrayList<Family> sortByMartyrs(ArrayList<Family> families) {
		setSortByOrphans(false);
		Collections.sort(families);
		return families;

	}

	@Override
	public ArrayList<Family> sortByOrphans(ArrayList<Family> families) {
		setSortByOrphans(true);
		Collections.sort(families);
		return families;

	}

	public int countOrphansInFamily() {
		int orphansCount = 0;
		for (Person person : members) {
			if (person instanceof LivePerson && !hasParents(person)) {
				orphansCount++;
			}
		}
		return orphansCount;
	}

	private boolean hasParents(Person person) {
		for (Person parent : parents) {
			if (parent.equals(person)) {
				return true;
			}
		}
		return false;

	}

	public int countMartyrsInFamily() {
		int martyrsCount = 0;
		for (Person person : members) {
			if (person instanceof Martyr) {
				martyrsCount++;
			}
		}
		return martyrsCount;
	}

	@Override
	public int compareTo(Family o) {
		if (sortByOrphans) {
			return Integer.compare(this.countOrphansInFamily(), o.countOrphansInFamily());
		} else {
			return Integer.compare(this.countMartyrsInFamily(), o.countMartyrsInFamily());
		}
	}

	public void setSortByOrphans(boolean sortByOrphans) {
		this.sortByOrphans = sortByOrphans;
	}

	// Add these methods to your Family class
	public Family deepCopyMartyrs() throws CloneNotSupportedException {
		Family clonedFamily = (Family) this.clone();

		// Clear LivePerson instances from the cloned family
		ArrayList<Person> martyrs = new ArrayList<>();
		for (Person parent : clonedFamily.getParents()) {
			if (parent instanceof Martyr) {
				// Deep copy Martyr instances for parents
				martyrs.add((Person) ((Martyr) parent).clone());
			}
		}

		// Clear LivePerson instances from the cloned family members
		for (Person member : clonedFamily.getMembers()) {
			if (member instanceof Martyr) {
				// Deep copy Martyr instances for members
				martyrs.add((Person) ((Martyr) member).clone());
			}
		}

		clonedFamily.setParents(martyrs);
		clonedFamily.setFamilyName(familyName); // Change familyName

		return clonedFamily;
	}

	public Family deepCopyLivePersons() throws CloneNotSupportedException {
		Family clonedFamily = (Family) this.clone();

		// Clear Martyr instances from the cloned family
		ArrayList<Person> livePersons = new ArrayList<>();
		for (Person parent : clonedFamily.getParents()) {
			if (parent instanceof LivePerson) {
				// Deep copy LivePerson instances for parents
				livePersons.add((Person) ((LivePerson) parent).clone());
			}
		}

		// Clear Martyr instances from the cloned family members
		for (Person member : clonedFamily.getMembers()) {
			if (member instanceof LivePerson) {
				// Deep copy LivePerson instances for members
				livePersons.add((Person) ((LivePerson) member).clone());
			}
		}

		clonedFamily.setParents(livePersons);
		clonedFamily.setFamilyName(familyName); // Change familyName

		return clonedFamily;
	}

	@Override
	public String toString() {
		return "Family [familyName=" + familyName + ", members=" + members + ", parents=" + parents + "]";
	}

}
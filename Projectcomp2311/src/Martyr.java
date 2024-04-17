
public class Martyr extends Person implements  Cloneable {
	private String DateOfMartyrdom;
	private String CauseOfDeath;
	private String PlaceOfDeath;

	public Martyr() {//no-arg constructor
		super();
	}
	public Martyr(int ID, String name, String gender) {
		super();
     
    }
		// TODO Auto-generated constructor stub
		@Override
		protected Object clone() throws CloneNotSupportedException {
			
			return super.clone();
		}
// getter and setter methods
	public String getDateOfMartyrdom() {
		return DateOfMartyrdom;
	}

	public void setDateOfMartyrdom(String dateOfMartyrdom) {
		DateOfMartyrdom = dateOfMartyrdom;
	}

	public String getCauseOfDeath() {
		return CauseOfDeath;
	}

	public void setCauseOfDeath(String causeOfDeath) {
		CauseOfDeath = causeOfDeath;
	}

	public String getPlaceOfDeath() {
		return PlaceOfDeath;
	}

	public void setPlaceOfDeath(String placeOfDeath) {
		PlaceOfDeath = placeOfDeath;
	}
	 @Override
	    public String toString() {
	        // Implement the toString method for Martyr
	        return "Martyr: " +
	                "ID=" + getID() +
	                ", Name=" + getName() +
	                ", Age=" + getAge() +
	                ", Gender=" + getGender() +
	                ", Address=" + getAddress() +
	                ", ContactInfo=" + getContactInfo() +
	                ", DateOfMartyrdom=" + DateOfMartyrdom +
	                ", CauseOfDeath=" + CauseOfDeath +
	                ", PlaceOfDeath=" + PlaceOfDeath;
	    }
	
}
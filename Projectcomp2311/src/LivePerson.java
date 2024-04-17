
public class LivePerson extends Person implements  Cloneable  {
	public LivePerson() {//no-arg constructor
		super();
		// TODO Auto-generated constructor stub
	} public LivePerson (int ID, String name, String gender) {
        super();
    }

		@Override
		protected Object clone() throws CloneNotSupportedException {
		    return super.clone(); // Creates a shallow copy of the object
		}
	 @Override
	    public String toString() {
	        // Implement the toString method for LivePerson
	        return "LivePerson: " +
	                "ID=" + getID() +
	                ", Name=" + getName() +
	                ", Age=" + getAge() +
	                ", Gender=" + getGender() +
	                ", Address=" + getAddress() +
	                ", ContactInfo=" + getContactInfo();
	    }
	
}
	


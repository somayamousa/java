
abstract class Person {//abstract
	private String ID;
	private String Name;
	private int Age;
	private String Gender;
	private String Address;
	private String ContactInfo;

	
	public Person(String iD, String name, String gender) {
		super();
		ID = iD;
		Name = name;
		Gender = gender;
	}
	public Person() {
		super();
	}
	// getter and setter methods
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getContactInfo() {
		return ContactInfo;
	}

	public void setContactInfo(String contactInfo) {
		ContactInfo = contactInfo;
	}

	@Override
	public abstract String toString();

}
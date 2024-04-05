package application;

public class LivePerson extends Person {
	
	
	public static int totalNumberOfLivePerson;
	
	public LivePerson() {
		super();
	}
	
	public LivePerson(String ID) {
		super(ID);
	}

	public LivePerson(String iD, String name, int age, String gender, String address, String contactInfo, String role) {
		super(iD, name, age, gender, address, contactInfo, role);
		totalNumberOfLivePerson++;
	}

	@Override
	public String toString() {
		return "[ID=" + getID() + ", Name=" + getName() + ", Age=" + getAge() + ", Gender=" + getGender() + ", Address=" + getAddress()
				+ ", ContactInfo=" + getContactInfo() + "]";
	}
	
	
	public LivePerson deepCopy() {
			LivePerson livePerson = new LivePerson();
			livePerson.setID(this.getID());
			livePerson.setName(this.getName());
			livePerson.setAge(this.getAge()); 
			livePerson.setGender(this.getGender());
			livePerson.setAddress(this.getAddress());
			livePerson.setRole(this.getRole());
			
			
			return livePerson;
		
	}
	
	

}


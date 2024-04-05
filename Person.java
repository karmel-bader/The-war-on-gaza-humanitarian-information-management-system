package application;
import java.util.*;
public abstract class Person {
	private String ID;
	private String Name;
	private int Age;
	private String Gender;
	private String Address;
	private String ContactInfo;
	private String role;
	
	

	public Person() {
		
	}



	public Person(String iD, String name, int age, String gender, String address, String contactInfo, String role) {
		ID = iD;
		Name = name;
		Age = age;
		Gender = gender;
		Address = address;
		ContactInfo = contactInfo;
		this.role = role;
	}
	
	
	public Person(String iD) {
		super();
		ID = iD;
	}


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
	
	
	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "[ID=" + ID + ", Name=" + Name + ", Age=" + Age + ", Gender=" + Gender + ", Address=" + Address
				+ ", ContactInfo=" + ContactInfo + "]";
	}
	
	
	
	

}


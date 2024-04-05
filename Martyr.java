package application;
import java.io.*;
public class Martyr extends Person {
	private String MartyrName;
	private String DateOfMartyrdom;
	private String CauseOfDeath;
	private String PlaceOfDeath;
	public static int totalNumberOfMartyr;
	
	
	public Martyr() {
		super();
	}
	public Martyr(String iD, String name, int age, String gender, String address, String contactInfo, String DateOfMartyrdom, String CauseOfDeath, String PlaceOfDeath, String role ) {
		super(iD, name, age, gender, address, contactInfo, role);
		this.DateOfMartyrdom = DateOfMartyrdom;
		this.CauseOfDeath = CauseOfDeath;
		this.PlaceOfDeath = PlaceOfDeath;
		totalNumberOfMartyr++;
	}
	
	
	public Martyr(String dateOfMartyrdom, String name) {
		this.setName(name);
		DateOfMartyrdom = dateOfMartyrdom;
	}
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
		return "[ID=" + getID() + ", Name=" + getName() + ", Age=" + getAge() + ", Gender=" + getGender() + ", Address=" + getAddress()
		+ ", ContactInfo=" + getContactInfo()  + "] Martyr [DateOfMartyrdom=" + DateOfMartyrdom + ", CauseOfDeath=" + CauseOfDeath + ", PlaceOfDeath="
				+ PlaceOfDeath + "]";
	}
	
	
	public Martyr deepCopy( ) {
		Martyr martyr = new Martyr();
		martyr.setID(this.getID());
		martyr.setName(this.getName());
		martyr.setAge(this.getAge()); 
		martyr.setGender(this.getGender());
		martyr.setAddress(this.getAddress());
		martyr.setContactInfo(this.getContactInfo());
		martyr.setDateOfMartyrdom(this.getDateOfMartyrdom());
		martyr.setPlaceOfDeath(this.getPlaceOfDeath());;
		martyr.setCauseOfDeath(this.getCauseOfDeath());
		martyr.setRole(this.getRole());
		
		
		return martyr;
	}
	
	public void writeToBinaryFile() { // write to the file
		try {
			File file = new File("MartyrsList.dat");
			FileOutputStream output = new FileOutputStream(file,true);
			DataOutputStream writeOutout = new DataOutputStream(output);
			writeOutout.writeUTF(this.getName() + " " +this.getDateOfMartyrdom() );
			writeOutout.writeChar('\n');
			writeOutout.close();
		}catch(IOException ex) {
			System.out.println("There is Something wrong");
		}
	}
	
	
	
}
















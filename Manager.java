package application;
import java.util.*;
public class Manager {
	
	private ArrayList<Family> families = new ArrayList<>();
	
	
	public boolean addFamily(Family family) {  // Adds a new family to the system. 
		int counter = 0;
		if(families.size() == 0) {
			families.add(family);
			return true;
		}
		for(int i=0;i<families.size();i++) {
			counter++;
			if(families.get(i).getFamilyName().equals(family.getFamilyName())) { // to check if the family is exist before or not
				break;	
			}
			else if(counter < families.size()) {  // to check all families in the array list before adding a new family to it
				continue;
			}
			else {
				families.add(family); // add the family to the list and return true
				return true;
			}
		}
		return false;
	}
	
	public boolean updateFamily(String familyName, Family updateFamily) { // Updates information about a family based on Family name.
		for(int i=0;i<families.size();i++) {
			if(families.get(i).getFamilyName().equals(familyName)) { // to check if the family name id exist before or not
				families.set(i,updateFamily); // update the family information
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteFamily(String familyName) { // Deletes a family from the system based on Family name
		for(int i=0;i<families.size();i++) {
			if(families.get(i).getFamilyName().equals(familyName)) { // to check if the family name exist before or not
				families.remove(i); // remove it if it exist before
				return true;
			}
		}
		return false;
	}
	
	public Family searchByName(String familyName) { // Searches for a family based on Family name. 
		for(int i=0;i<families.size();i++) {
			if(families.get(i).getFamilyName().equals(familyName)) { // to check if the family name exist before or not
				return families.get(i); 
			}
		}
		return null;
	}
	
	public Person searchPersonByID(String personID) { // Searches for a person based on their ID.  
		for(int i=0;i<families.size();i++) {
			ArrayList<Person> members = families.get(i).getMembers();
			ArrayList<Person> parents = families.get(i).getParents();// Retrieves the list of family members.
			for(int j=0;j<members.size();j++) {
				if(members.get(j).getID().equals(personID)) { // to check if the id exist before or not
					return members.get(j); // return the member information
				}
			}
			for(int k=0;k<parents.size();k++) {
				if(parents.get(k).getID().equals(personID)) {
					return parents.get(k);
				}
			}
			
		}
		
		return null;
	}
	
	public int claculateTotalMartyrs() { //  Returns the total number of martyrs in the system.
		int martyrs = Martyr.totalNumberOfMartyr;  // static variable to calculate total martyrs
		return martyrs;
	}
	
	public int claculateTotalOrphans() {  //  Returns the total number of orphans in the system.
		int counter = 0;
		int TotalOrphans = 0;
		for(int i=0;i<families.size();i++) {
			counter = 0;
			ArrayList<Person> parents = families.get(i).getParents(); // Retrieves the list of family parents.
			for(int j=0;j<parents.size();j++) {
				if(parents.get(j) instanceof Martyr) {
					counter++;
				}
			}
			if(counter == 2) { // 2 means that the two parents are martyrs
				TotalOrphans+=families.get(i).getMembers().size(); // all members are orphans expect the two parents
			}
			
		}
		return TotalOrphans;
			
	}
	
	public int claculateTotalLivePerson() { // Returns the total number of livePerson in the system.
		int livePerson = LivePerson.totalNumberOfLivePerson; // static variable to calculate total livePerson 
		return livePerson;
	}
	
	public ArrayList<Integer> calculateFamilyStatistics(String familyName){ //  Returns statistics for a specific family, including the number of martyrs, livePerson and orphans, stored the returned values in array list
		ArrayList<Integer> familyStatixtics = new ArrayList<>();
		int Martyrs = 0;
		int livePerson = 0;
		int Orphans = 0;
		int counter = 0;
		for(int i=0;i<families.size();i++) {
			ArrayList<Person> members = families.get(i).getMembers();
			ArrayList<Person> parents = families.get(i).getParents();
			if(families.get(i).getFamilyName().equals(familyName)) {
				for(int j=0;j<members.size();j++) {
					if(members.get(j) instanceof Martyr) {
						Martyrs++; // increase the number of martyrs if the member instance of class martyr
					}
					else if(members.get(j) instanceof LivePerson) {
						livePerson++;  // increase the number of live persons  if the member instance of class LivePerson
					}
				}
				
				for(int k=0;k<parents.size();k++) {
					if(parents.get(k) instanceof Martyr) {
						counter++; 
					}
				}
				if(counter == 2) {
					Orphans = families.get(i).getMembers().size() ; // all members are orphans expect two parents
				}
				
				for(int n=0;n<parents.size();n++) {
					if(parents.get(n) instanceof Martyr) {
						Martyrs++; // increase the number of martyrs if the parent instance of class martyr
					}
					else if(parents.get(n) instanceof LivePerson) {
						livePerson++;  // increase the number of live persons  if the parent instance of class LivePerson
					}
				}
				
				
			}
		}
		familyStatixtics.add(Martyrs);
		familyStatixtics.add(livePerson);
		familyStatixtics.add(Orphans);
		
		return familyStatixtics;
	}
	
	public ArrayList<Integer> calculateGlobalStatistics(){ // Returns overall statistics for the system. Store the returned values in array list
		ArrayList<Integer> globalStatistics = new ArrayList<>();
		int Martyrs = Martyr.totalNumberOfMartyr;
		int livePerson = LivePerson.totalNumberOfLivePerson;
		int Orphans = claculateTotalOrphans();
		globalStatistics.add(Martyrs);
		globalStatistics.add(livePerson);
		globalStatistics.add(Orphans);
		
		return globalStatistics;
		
	}

	@Override
	public String toString() {
		return "Manager [families=" + families + "]";
	}

	public ArrayList<Family> getFamilies() {
		return families;
	}

	public void setFamilies(ArrayList<Family> families) {
		this.families = families;
	}
	
	
	
	
	

}


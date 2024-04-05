package application;

import java.util.*;
import java.io.*;

public class Family implements Sortable, Cloneable {

	private String familyName;
	private ArrayList<Person> members = new ArrayList<>();
	private ArrayList<Person> parents = new ArrayList<>();
	Manager manager = new Manager();

	public Family() {

	}

	public Family(String familyName) {
		this.familyName = familyName;
	}

	public boolean addMember(Person member, String role) throws InvalidAddMembersWithoutParents { // a method adds a person to the family with specific role
		if ((member.getRole().toLowerCase().equals("daughter")) && (member.getGender().toLowerCase().equals("male"))) {
			System.out.println("The daughter cannot be male");
			if (member instanceof Martyr) {
				Martyr.totalNumberOfMartyr--;
			} else if (member instanceof LivePerson) {
				LivePerson.totalNumberOfLivePerson--;
			}
			return false;
		}
		if ((member.getRole().toLowerCase().equals("son")) && (member.getGender().toLowerCase().equals("female"))) {
			System.out.println("The son cannot be female");
			if (member instanceof Martyr) {
				Martyr.totalNumberOfMartyr--;
			} else if (member instanceof LivePerson) {
				LivePerson.totalNumberOfLivePerson--;
			}
			return false;
		} 
		if ((member.getAge() < 0) || (member.getAge() > 120)) { // the normal age is between 0 and 120
			System.out.println("The Age is impossible):");
			if (member instanceof Martyr) {
				Martyr.totalNumberOfMartyr--;
			} else if (member instanceof LivePerson) {
				LivePerson.totalNumberOfLivePerson--;
			}
			System.out.println("The age cannot be less than 0 or mor than 120");
			return false;
		} 
		if (parents.size() < 2) { // can not adding members without two parenst
			if (member instanceof Martyr) {
				Martyr.totalNumberOfMartyr--;
			} else if (member instanceof LivePerson) {
				LivePerson.totalNumberOfLivePerson--;
			}
			throw new InvalidAddMembersWithoutParents("Can Not Adding Members Without Parents"); // throw exception
		}

		for (int i = 0; i < this.parents.size(); i++) { // the age difference between members and parents must be more than 17
			int x = parents.get(i).getAge() - member.getAge();
			if (x < 17) {
				if (member instanceof Martyr) {
					Martyr.totalNumberOfMartyr--;
				} else if (member instanceof LivePerson) {
					LivePerson.totalNumberOfLivePerson--;
				}
				System.out.println("The age difference cannot be less than 17");
				return false;
			}
		}
		if (!(role.toLowerCase().equals("son")) && !(role.toLowerCase().equals("daughter"))) {
			return false;
		}
		if (manager.searchPersonByID(member.getID()) != null) { // to check if the member exist before or not
			return false;
		}
		int counter = 0;
		if (members.size() == 0) {
			members.add(member);
			this.wToFile();
			return true;
		}
		for (int i = 0; i < members.size(); i++) {
			counter++;
			if (members.get(i).getID().equals(member.getID())) { // to check if the member is exist before or not
				break;
			} else if (counter < members.size()) { // to check all members in the array list before adding a new member
													// to it
				continue;
			}

			else {

				members.add(member);
				this.wToFile();
				return true;
			}
		}
		return false;
	}

	public boolean removeMember(Person member) { // Removes a person from the family.
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getID().equals(member.getID())) { // to check if the member is exist before or not
				if (members.get(i) instanceof Martyr) { // to check if the member instance of Martyr class
					Martyr.totalNumberOfMartyr--; // to reduce the total number of martyrs
				} else if (members.get(i) instanceof LivePerson) { // to check if the member instance of LivePerson
																	// class
					LivePerson.totalNumberOfLivePerson--; // to reduce the total number of live Person
				}
				members.remove(i);
				return true;
			}
		}
		return false;
	}

	public ArrayList<Person> getMembers() { // Retrieves the list of family members.
		return members;
	}

	public void addParent(Person parent, String role) { // add parent to the family family with Specific role
		for(int i=0;i<members.size();i++) {
			int x = parent.getAge() - members.get(i).getAge();
			if(x < 17) {
				if (parent instanceof Martyr) {
					Martyr.totalNumberOfMartyr--;
				} else if (parent instanceof LivePerson) {
					LivePerson.totalNumberOfLivePerson--;
				}
				System.out.println("The age difference cannot be less than 17");
				return;
			}
		}
		if ((parent.getRole().toLowerCase().equals("mom")) && (parent.getGender().toLowerCase().equals("male"))) {
			System.out.println("The mother cannot be male");
			if (parent instanceof Martyr) {
				Martyr.totalNumberOfMartyr--;
			} else if (parent instanceof LivePerson) {
				LivePerson.totalNumberOfLivePerson--;
			}
			return;
		}
		if ((parent.getRole().toLowerCase().equals("dad")) && (parent.getGender().toLowerCase().equals("female"))) {
			System.out.println("The father cannot be female");
			if (parent instanceof Martyr) {
				Martyr.totalNumberOfMartyr--;
			} else if (parent instanceof LivePerson) {
				LivePerson.totalNumberOfLivePerson--;
			}
			return;
		}
		if (parent.getAge() < 16) {
			System.out.println("The Age is incorrect):");
			if (parent instanceof Martyr) {
				Martyr.totalNumberOfMartyr--;
			} else if (parent instanceof LivePerson) {
				LivePerson.totalNumberOfLivePerson--;
			}
			return;
		}
		if ((parent.getAge() < 16) || (parent.getAge() > 120)) { // a parents age cannot be less than 16
			System.out.println("The Age is impossible):");
			if (parent instanceof Martyr) {
				Martyr.totalNumberOfMartyr--;
			} else if (parent instanceof LivePerson) {
				LivePerson.totalNumberOfLivePerson--;
			}
			System.out.println("The age cannot be less than 0 or mor than 120");
			return;
		}

		for (int i = 0; i < parents.size(); i++) {
			if ((parents.get(i).getRole().toLowerCase().equals("dad")) && (role.toLowerCase().equals("dad"))) {
				System.out.println("The dad is already exist");
				if (parent instanceof Martyr) {
					Martyr.totalNumberOfMartyr--;
				} else if (parent instanceof LivePerson) {
					LivePerson.totalNumberOfLivePerson--;
				}
				return;
			}
			if ((parents.get(i).getRole().toLowerCase().equals("mom")) && (role.toLowerCase().equals("mom"))) {
				System.out.println("The mom is already exist");
				if (parent instanceof Martyr) {
					Martyr.totalNumberOfMartyr--;
				} else if (parent instanceof LivePerson) {
					LivePerson.totalNumberOfLivePerson--;
				}
				return;
			}
		}
		if (manager.searchPersonByID(parent.getID()) != null) { // to check if the member exist before or not
			System.out.println("The parent is exist before");
			return;
		}
		if (!(role.toLowerCase().equals("mom")) && !(role.toLowerCase().equals("dad"))) {
			System.out.println("There is something wrong, The person is not a parent");
			if (parent instanceof Martyr) {
				Martyr.totalNumberOfMartyr--;
			} else if (parent instanceof LivePerson) {
				LivePerson.totalNumberOfLivePerson--;
			}
			return;
		}

		int counter = 0;
		if (parents.size() == 0) {
			parents.add(parent);
			System.out.println("added successfully!");
		}
		for (int i = 0; i < parents.size(); i++) {
			counter++;
			if (parents.get(i).getID().equals(parent.getID())) { // to check if the parent is exist before or not
				break;
			} else if (counter < parents.size()) { // to check all members in the array list before adding a new member
													// to it
				continue;
			}

			else { // if it dose not exist add it to the two arrays list
				parents.add(parent);
				System.out.println("added successfully!");
				this.wToFile();
			}
		}

	}

	// setter and getter method for family name

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public boolean removeParent(Person parent) { // remove parent from the family
		for (int i = 0; i < parents.size(); i++) {
			if (parents.get(i).getID().equals(parent.getID())) { // // to check if the member is exist before or not
				if (parents.get(i) instanceof Martyr) { // // to check if the member instance of Martyr class
					Martyr.totalNumberOfMartyr--; // to reduce the total number of martyrs
				} else if (parents.get(i) instanceof LivePerson) { // to check if the member instance of LivePerson
																	// class
					LivePerson.totalNumberOfLivePerson--; // to reduce the total number of live person
				}
				parents.remove(i);
				return true;
			}
		}
		return false;
	}

	public ArrayList<Person> getParents() { // Retrieves the list of family parents.
		return parents;
	}

	@Override
	public String toString() {
		return "Family [familyName=" + familyName + ", members=" + members + ", parents=" + parents + "]";
	}

	@Override
	public boolean equals(Object obj) { // to consider two families as equal if they have the same number of martyrs.
		if (obj instanceof Family) {
			int martyr1 = 0;
			int martyr2 = 0;
			for (int i = 0; i < members.size(); i++) {
				if (members.get(i) instanceof Martyr) {
					martyr1++;
				}
			}

			for (int i = 0; i < parents.size(); i++) {
				if (parents.get(i) instanceof Martyr) {
					martyr1++;
				}
			}

			for (int i = 0; i < ((Family) obj).members.size(); i++) {
				if (((Family) obj).members.get(i) instanceof Martyr) {
					martyr2++;
				}
			}
			for (int i = 0; i < ((Family) obj).parents.size(); i++) {
				if (((Family) obj).parents.get(i) instanceof Martyr) {
					martyr2++;
				}
			}
			if (martyr1 == martyr2) {
				return true;
			}

		}

		return false;
	}

	@Override
	public ArrayList<Family> sortByMartyrs(ArrayList<Family> families) { // sort the families in descending order depending on martyrs
		families = (ArrayList<Family>) families.clone();
		int martyr = 0;
		int max = -1;
		ArrayList<Integer> martyrs = new ArrayList<>();
		ArrayList<Family> orderOfFamilies = new ArrayList<>();
		for (int i = 0; i < families.size(); i++) {
			martyr = 0;
			for (int j = 0; j < families.get(i).members.size(); j++) { // calculate the number of martyrs in member array list for each family
				if (families.get(i).members.get(j) instanceof Martyr) {
					martyr++;
				}
			}
			for (int n = 0; n < families.get(i).parents.size(); n++) { // calculate the number of martyrs in parent array list for each family
				if (families.get(i).parents.get(n) instanceof Martyr) {
					martyr++;
				}
			}
			martyrs.add(martyr); // storing the number of martyrs in array list
		}
		int n = 0;
		for (int i = 0; i < families.size(); i++) { // compare the number of martyrs
			max = martyrs.get(i);
			n = i;
			for (int k = 0; k < martyrs.size(); k++) {
				if (max < martyrs.get(k)) {
					max = martyrs.get(k);
					n = k;
				}
			}
			orderOfFamilies.add(families.get(n)); // storing the families depending on martyrs in array list
			martyrs.set(n, -1);
		}
		return orderOfFamilies;
	}

	@Override
	public ArrayList<Family> sortByOrphans(ArrayList<Family> families) { // sort the families in descending order depending on orphans
		families = (ArrayList<Family>) families.clone();
		int counter = 0;
		int max = -1;
		ArrayList<Integer> Orphans = new ArrayList<>();
		ArrayList<Family> orderOfFamilies = new ArrayList<>();
		for (int i = 0; i < families.size(); i++) {
			counter = 0;
			for (int n = 0; n < families.get(i).parents.size(); n++) { // calculate the number of martyrs in parents array list to count the number of orphans
				if (families.get(i).parents.get(n) instanceof Martyr) {
					counter++;
				}
			}
			if (counter == 2) { // it means that two parents are martyrs
				Orphans.add(families.get(i).members.size()); // storing the orphans in array list
			}
			else {
				Orphans.add(0);
			}
		}
		int n = 0;
		for (int i = 0; i < families.size(); i++) { // compare the number of orphans
			max = Orphans.get(i);
			n = i;
			for (int k = 0; k < Orphans.size(); k++) { 
				if (max < Orphans.get(k)) {
					max = Orphans.get(k);
					n = k;
				}
			}
			orderOfFamilies.add(families.get(n)); // sorting the families in descending order depending on number of orphans in array list
			Orphans.set(n, -1);
		}
		return orderOfFamilies;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { 
		try {
			return super.clone();
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public Family deepCopy() { // a deep copy mechanism that enables making copies of Martyr, LivePerson, and Family objects.
		Family family = new Family();
		family.setFamilyName(this.getFamilyName());
		
		for (int i = 0; i < parents.size(); i++) {
			if (parents.get(i) instanceof Martyr) {
				family.parents.add(((Martyr) parents.get(i)).deepCopy()); // making copies of martyrs for parents
			}

			else if (parents.get(i) instanceof LivePerson) {
				family.parents.add(((LivePerson) parents.get(i)).deepCopy()); // making copies of live people for parents
			}
		}
		
		
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i) instanceof Martyr) {
				family.members.add(((Martyr) members.get(i)).deepCopy()); // making copies of martyrs for members
			}

			else if (members.get(i) instanceof LivePerson) {
				family.members.add(((LivePerson) members.get(i)).deepCopy()); // making copies of live people for members
			}
		}


		return family;
	}

	public boolean writeToFile(ArrayList<Family> families) { // Print to the file all families with their members in descending order depending on martyrs
		try {
			File file = new File("data.txt");
			PrintWriter pw = new PrintWriter(file);
			int martyr = 0;
			for (int i = 0; i < families.size(); i++) {
				ArrayList<Family> families1 = this.sortByMartyrs(families); // calling the method which order the families depending on martyrs number
				pw.print(families1.get(i).getFamilyName() + ": " + "Number Of Martyrs(");
				for (int n = 0; n < families1.get(i).parents.size(); n++) {
					if (families1.get(i).parents.get(n) instanceof Martyr) { // calculate the number of martyrs in parents array list for each family
						martyr++;
					}
				}
				for (int n = 0; n < families1.get(i).members.size(); n++) { // calculate the number of martyrs in members array list for each family
					if (families1.get(i).members.get(n) instanceof Martyr) {
						martyr++;
					}
				}
				pw.println(martyr + ")");
				martyr = 0;
				pw.println(".........................");
				pw.print("Parents: ");
				for (int j = 0; j < families1.get(i).parents.size(); j++) {
					pw.print(families1.get(i).parents.get(j).getName() + " ");
				}
				pw.print("\n");
				pw.print("Members: ");
				for (int k = 0; k < families1.get(i).members.size(); k++) {
					pw.print(families1.get(i).members.get(k).getName() + " ");
				}

				pw.print("\n" + "\n");
			}
			pw.close(); // close the file and save all data
			return true;

		} catch (IOException ex) {
			System.out.print("There is something wrong, The File Maybe Dose Not Exist");

		}

		return false;
	}

	public Person rFromFile(String ID) { // read the data from a file to enable the user to add people from console and file
		try {
			File file = new File("allData.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNext()) {
				String id = scan.next();
				String name = scan.next();
				int age = scan.nextInt();
				String gender = scan.next();
				String address = scan.next();
				String contactInfo = scan.next();
				String role = scan.next();
				String situation = scan.next();
				String DateOfMartyrdom = "";
				String CauseOfDeath = "";
				String PlaceOfDeath = "";
				if (situation.equals("Martyr")) {
					DateOfMartyrdom = scan.next();
					CauseOfDeath = scan.next();
					PlaceOfDeath = scan.next();
				}
				if (id.equals(ID)) { // compare if the id which user enter it equal the id in the file to return it
					if (situation.equals("Martyr")) {
						return new Martyr(id, name, age, gender, address, contactInfo, DateOfMartyrdom, CauseOfDeath,
								PlaceOfDeath, role);
					} else {
						return new LivePerson(id, name, age, gender, address, contactInfo, role);
					}
				}
			}
			scan.close();

		} catch (IOException ex) {
			System.out.println("There is something wrong, The File Maybe Dose Not Exist");
		}

		return null;
	}


	public void wToFile() { // write the data that user add it from the console
		try {
			File file = new File("allData.txt");
			Scanner scan = new Scanner(file);
			ArrayList<String> lines = new ArrayList<>();
			String[] array;
			ArrayList<String> ID = new ArrayList<>();
			while (scan.hasNext()) {
				String line = scan.nextLine(); // read each line 
				lines.add(line);			// and store it in array list 
				array = line.split(" "); // spilt the line at every space 
				ID.add(array[0]);		// to store the id in array list
			}
			scan.close();
			PrintWriter pw = new PrintWriter(file);
			for (int i = 0; i < lines.size(); i++) { // write all data to the file
				pw.println(lines.get(i));
			}
			for (int i = 0; i < parents.size(); i++) {
				if (ID.contains(parents.get(i).getID())) { // to check if the id exist in the file
	
				}

				else { // if the id dose not exist write the person information to the file
					if (parents.get(i) instanceof Martyr) {
						pw.println(
								parents.get(i).getID() + " " + parents.get(i).getName() + " " + parents.get(i).getAge()
										+ " " + parents.get(i).getGender() + " " + parents.get(i).getAddress() + " "
										+ parents.get(i).getContactInfo() + " " + parents.get(i).getRole() + " Alive");
					} else if (parents.get(i) instanceof LivePerson) {
						pw.println(
								parents.get(i).getID() + " " + parents.get(i).getName() + " " + parents.get(i).getAge()
										+ " " + parents.get(i).getGender() + " " + parents.get(i).getAddress() + " "
										+ parents.get(i).getContactInfo() + " " + parents.get(i).getRole() + " Alive");
					}
				}
			}

			for (int i = 0; i < members.size(); i++) {
				if (ID.contains(members.get(i).getID())) {
					
				} else {
					if (members.get(i) instanceof Martyr) {
						pw.println(
								members.get(i).getID() + " " + members.get(i).getName() + " " + members.get(i).getAge()
										+ " " + members.get(i).getGender() + " " + members.get(i).getAddress() + " "
										+ members.get(i).getContactInfo() + " " + members.get(i).getRole() + " Alive");
					} else if (members.get(i) instanceof LivePerson) {
						pw.println(
								members.get(i).getID() + " " + members.get(i).getName() + " " + members.get(i).getAge()
										+ " " + members.get(i).getGender() + " " + members.get(i).getAddress() + " "
										+ members.get(i).getContactInfo() + " " + members.get(i).getRole() + " Alive");
					}
				}

			}

			pw.close();

		} catch (IOException ex) {
			System.out.println("There is something wrong, The File Maybe Dose Not Exist");
		}

	}

}


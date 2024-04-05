package application;
import java.util.*;

public class Driver {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Manager manager = new Manager();
		Family family = new Family();
		int x = 0, Age = 0;
		int y = 0;
		String Name, Gender = "male", Address, ContactInfo, ID;
		String role = "";
		do {
			System.out.println("Please, choose the operation: ");
			System.out.print(
					"1- Add Family\n2- Add Member\n3- Add Parent\n4- Remove Member\n5- Remove Parent\n6- Compare number of martyrs between two families\n7- Update Family\n8- Delete Family\n9- Search for a family by name\n10-search for a Person by ID\n11-Calculate family Statixtics\n12-Calculate Global Statixtics\n13-Sort By Martyrs\n14-Sort By Orphans\n15-Copy Family\n16-Copy Person\n17-Write To File\n18-closed The System\nYour choice:  ");
			boolean tryGender;
			boolean continueInput = true; // force the user to enter number from the list
			while (continueInput) {
				try {
					x = scan.nextInt();
					continueInput = false;
					if(x>18 || x<0) {
						System.out.println("Invalid input");
						continueInput=true;
					}
				} catch (InputMismatchException ex) {
					System.out.print("Try again. (" + "Incorrect input: an integer is required): ");
					scan.nextLine();
				}
			}
			switch (x) {
			case 1:
				System.out.print("Enter The family Name: ");
				String familyName = scan.next();
				if (manager.addFamily(new Family(familyName)) == true) { // add new family to the system
					System.out.println(
							"Added successfully!, if you want to add members or parents choose 2 and 3 operater");
				} else {
					System.out.println("The family is already in the system!");
				}

				break;

			case 2:

				System.out.print("Enter The Family Name: ");
				String FamilyName = scan.next();
				Family family1 = manager.searchByName(FamilyName);
				if (manager.searchByName(FamilyName) == null) { // check if the family name exist before or not
					System.out.println("The Family dose not Exist!");
					break;
				}

				// enter the person information
				System.out.println("If you want to read from a file Enter '1'");
				System.out.print("If you want to Enter the information yourself Enter '0': ");
				continueInput = true;
				while (continueInput) {
					try {
						y = scan.nextInt();
						continueInput = false;
						if(y != 0 && y != 1) {
							System.out.print("Try again, you must enter 0 or 1: ");
							continueInput = true;
						}
					} catch (InputMismatchException ex) {
						System.out.print("Try again. (" + "Incorrect input: an integer is required): ");
						scan.nextLine();
					}
				}
				if (y == 0) { // if the user enter 0 then he/she will add the information from the console
					System.out.print("Enter The Person's ID: ");
					ID = scan.next();
					System.out.print("Enter The Person's Name: ");
					Name = scan.next();
					System.out.print("Enter The Person's Age: ");
					Age = 0;
					continueInput = true;
					while (continueInput) { // force the user to enter integer for the age
						try {
							Age = scan.nextInt();
							continueInput = false;
						} catch (InputMismatchException ex) {
							System.out.print("Try again. (" + "Incorrect input: an integer is required(0 OR 1) ");
							scan.nextLine();
						}
					}
					System.out.print("Enter The Person's Gender: ");
					tryGender = true;
					while (tryGender) {
						Gender = scan.next(); // force the user to enter male or female for the gender 
						if (!(Gender.toLowerCase().equals("male")) && !(Gender.toLowerCase().equals("female"))) {
							System.out.print("Try again. (" + "Incorrect input: The Gender must bs male or female:) ");
							scan.nextLine();
						}
						if ((Gender.toLowerCase().equals("male")) || (Gender.toLowerCase().equals("female"))) {
							tryGender = false;
						}

					}

					System.out.print("Enter The Person's Address: ");
					Address = scan.next();
					System.out.print("Enter The ContactInfo: ");
					ContactInfo = scan.next();
					System.out.print("Enter The Person's Role: ");
					role = scan.next();
					if (manager.searchPersonByID(ID) == null) { // check if the id used before or not
						System.out.print("Is The Person A Martyr Or Alive? ");
						String person = scan.next();
						if (person.toLowerCase().equals("martyr")) {
							// if the person martyr enter the information about martyrdom
							System.out.print("Enter The Date Of Martyrdom: ");
							String DateOfMartyrdom = scan.next();
							System.out.print("Enter The Cause Of The Death: ");
							String CauseOfDeath = scan.next();
							System.out.print("Enter The Place Of The Death: ");
							String PlaceOfDeath = scan.next();
							try { // deal with exception
								if (family1.addMember(new Martyr(ID, Name, Age, Gender, Address, ContactInfo,
										DateOfMartyrdom, CauseOfDeath, PlaceOfDeath, role), role)) {
									System.out.println("added successfully!");
								} else {
									Martyr.totalNumberOfMartyr--;
									System.out.println("Dose not added successfully!");
								}

							} catch (InvalidAddMembersWithoutParents ex) {
								System.out.println(ex);
							}
						}

						else if (person.toLowerCase().equals("alive")) {
							try {
								if (family1.addMember(new LivePerson(ID, Name, Age, Gender, Address, ContactInfo, role),
										role)) {
									System.out.println("added successfully!");
								} else {
									LivePerson.totalNumberOfLivePerson--;
									System.out.println("Dose not added successfully!");
								}
							} catch (InvalidAddMembersWithoutParents ex) {
								System.out.println(ex);
							}
						}

					}

					else {
						System.out.println("The Member is alrady in The System!");
					}
				} else if (y == 1) { // if user enter 1 he/she will add person from the file
					System.out.print("Enter The Person's ID: ");
					ID = scan.next();
					Person person = family1.rFromFile(ID);
					if (person != null) { // check if the id used before or not
						if (manager.searchPersonByID(ID) != null) {
							System.out.println("The person exist before");
							break;
						}
						try {
							if (family1.addMember(person, person.getRole())) {
								System.out.println("added successfully!");
							}
						} catch (InvalidAddMembersWithoutParents ex) {
							System.out.println(ex);
						}
					} else {
						System.out.println("The ID dose not Exist");
					}

				} else {
					System.out.println("Incorrect Input!");
				}

				break;

			case 3:
				System.out.print("Enter The Family Name: ");
				FamilyName = scan.next();
				family1 = manager.searchByName(FamilyName);
				if (manager.searchByName(FamilyName) == null) {
					System.out.println("The Family dose not Exist!");
					break;
				}
				boolean check = true;
				while (family1.getParents().size() < 2) { // force the user to enter two parents
					check = false;
					System.out.println("If you want to read from a file Enter '1'");
					System.out.print("If you want to Enter the information yourself Enter '0': ");
					continueInput = true;
					while (continueInput) {
						try {
							y = scan.nextInt();
							continueInput = false;
							if(y != 0 && y != 1) { // force the user to enter 1 or 0
								System.out.print("Try again, you must enter 0 or 1: ");
								continueInput = true;
							}
						} catch (InputMismatchException ex) {
							System.out.print("Try again. (" + "Incorrect input: an integer is required(0 OR 1) ");
							scan.nextLine();
						}
					}

					if (y == 0) { // write data from console
						System.out.print("Enter The Person's ID: ");
						ID = scan.next();
						if (manager.searchPersonByID(ID) != null) { // check the id
							System.out.println("The person exist before");
							break;
						}
						System.out.print("Enter The Person's Name: ");
						Name = scan.next();
						System.out.print("Enter The Person's Age: ");
						Age = 0;
						continueInput = true;
						while (continueInput) { // force the user to enter integer for age
							try {
								Age = scan.nextInt();
								continueInput = false;
								if(Age<0 || Age>120) {
									System.out.println("The age should be between 0 and 120");
									continueInput=true;
								}
							} catch (InputMismatchException ex) {
								System.out.print("Try again. (" + "Incorrect input: an integer is required): ");
								scan.nextLine();
							}
						}
						System.out.print("Enter The Person's Gender: ");
						tryGender = true;
						while (tryGender) {
							Gender = scan.next();
							if (!(Gender.toLowerCase().equals("male")) && !(Gender.toLowerCase().equals("female"))) {
								System.out.print("Try again. (" + "Incorrect input: The Gender must bs male or female:) ");
								scan.nextLine();
							}
							if ((Gender.toLowerCase().equals("male")) || (Gender.toLowerCase().equals("female"))) {
								tryGender = false;
							}

						}
						System.out.print("Enter The Person's Address: ");
						Address = scan.next();
						System.out.print("Enter The ContactInfo: ");
						ContactInfo = scan.next();
						System.out.print("Enter The Person's Role: ");
						role = scan.next();

						if (manager.searchPersonByID(ID) == null) {
							System.out.print("Is The Person A Martyr Or Alive? ");
							String person = scan.next();
							if (person.toLowerCase().equals("martyr")) {
								System.out.print("Enter The Date Of Martyrdom: ");
								String DateOfMartyrdom = scan.next();
								System.out.print("Enter The Cause Of The Death: ");
								String CauseOfDeath = scan.next();
								System.out.print("Enter The Place Of The Death: ");
								String PlaceOfDeath = scan.next();
								family1.addParent(new Martyr(ID, Name, Age, Gender, Address, ContactInfo,
										DateOfMartyrdom, CauseOfDeath, PlaceOfDeath, role), role);

							}

							else if (person.toLowerCase().equals("alive")) {
								family1.addParent(new LivePerson(ID, Name, Age, Gender, Address, ContactInfo, role),
										role);

							}
						}

						else {
							System.out.println(" The parent is already in the list!");
						}
					}

					else if (y == 1) { // write data from file
						System.out.print("Enter The Person's ID: ");
						ID = scan.next();
						Person person = family1.rFromFile(ID);
						if (person != null) {
							if (manager.searchPersonByID(ID) != null) {
								System.out.println("The person exist before");
								break;
							}
							family1.addParent(person, person.getRole());
						} else {
							System.out.println("The ID dose not Exist");
						}
					} else {
						System.out.println("Incorrect Input!");
					}

					check = false;

				}
				if (check == true) {
					System.out.println("Sorry, but you added two parent!");
				}

				break;
			case 4:
				System.out.print("Enter The Family Name: ");
				FamilyName = scan.next();
				family1 = manager.searchByName(FamilyName);
				if (manager.searchByName(FamilyName) == null) {
					System.out.println("The Family dose not Exist!");
					break;
				}
				System.out.print("Enter The Person's ID: ");
				ID = scan.next();
				if (manager.searchPersonByID(ID) == null) { // check if the member is exist
					System.out.println("Sorry, The Member is not in the System");
				}

				else {
					family1.removeMember(new LivePerson(ID)); // remove the member
					System.out.println("Removed successfully!");
				}

				break;
			case 5:
				System.out.print("Enter The Family Name: ");
				FamilyName = scan.next();
				family1 = manager.searchByName(FamilyName);
				if (manager.searchByName(FamilyName) == null) {
					System.out.println("The Family dose not Exist!");
					break;
				}
				System.out.print("Enter The Person's ID: ");
				ID = scan.next();
				if (manager.searchPersonByID(ID) == null) { // check if the parent exist
					System.out.println("Sorry, The Parent is not in the System");
				}

				else {
					if (family1.removeParent(new LivePerson(ID))) { // remove parent if exist
						System.out.println("Removed successfully!, But you must add a second parent!");
						while (family1.getParents().size() < 2) {
							System.out.println("If you want to read from a file Enter '1'");
							System.out.print("If you want to Enter the information yourself Enter '0': ");
							y = 5;
							continueInput = true;
							while (continueInput) {
								try {
									y = scan.nextInt();
									continueInput = false;
									if(y != 0 && y != 1) {
										System.out.print("Try again, you must enter 0 or 1: ");
										continueInput = true;
									}
								} catch (InputMismatchException ex) {
									System.out
											.print("Try again. (" + "Incorrect input: an integer is required(0 OR 1) ");
									scan.nextLine();
								}
							}

							if (y == 0) {
								System.out.print("Enter The Person's ID: ");
								ID = scan.next();
								System.out.print("Enter The Person's Name: ");
								Name = scan.next();
								System.out.print("Enter The Person's Age: ");
								Age = 0;
								continueInput = true;
								while (continueInput) {
									try {
										Age = scan.nextInt();
										continueInput = false;
									} catch (InputMismatchException ex) {
										System.out.print("Try again. (" + "Incorrect input: an integer is required): ");
										scan.nextLine();
									}
								}
								System.out.print("Enter The Person's Gender: ");
								tryGender = true;
								while (tryGender) {
									Gender = scan.next();
									if (!(Gender.toLowerCase().equals("male")) && !(Gender.toLowerCase().equals("female"))) {
										System.out.print("Try again. (" + "Incorrect input: The Gender must bs male or female:) ");
										scan.nextLine();
									}
									if ((Gender.toLowerCase().equals("male")) || (Gender.toLowerCase().equals("female"))) {
										tryGender = false;
									}

								}
								System.out.print("Enter The Person's Address: ");
								Address = scan.next();
								System.out.print("Enter The ContactInfo: ");
								ContactInfo = scan.next();
								System.out.print("Enter The Person's Role: ");
								role = scan.next();

								if (manager.searchPersonByID(ID) == null) {
									System.out.print("Is The Person A Martyr Or Alive? ");
									String person = scan.next();
									if (person.toLowerCase().equals("martyr")) {
										System.out.print("Enter The Date Of Martyrdom: ");
										String DateOfMartyrdom = scan.next();
										System.out.print("Enter The Cause Of The Death: ");
										String CauseOfDeath = scan.next();
										System.out.print("Enter The Place Of The Death: ");
										String PlaceOfDeath = scan.next();
										family1.addParent(new Martyr(ID, Name, Age, Gender, Address, ContactInfo,
												DateOfMartyrdom, CauseOfDeath, PlaceOfDeath, role), role);
									}

									else if (person.toLowerCase().equals("alive")) {
										family1.addParent(
												new LivePerson(ID, Name, Age, Gender, Address, ContactInfo, role),
												role);
									}
								}

								else {
									System.out.println(" The parent is already in the list!");
								}
							}

							else if (y == 1) {
								System.out.print("Enter The Person's ID: ");
								ID = scan.next();
								Person person = family1.rFromFile(ID);
								if (person != null) {
									if (manager.searchPersonByID(ID) != null) {
										System.out.println("The person exist before");
									}
									family1.addParent(person, person.getRole());
								} else {
									System.out.println("The ID dose not Exist");
								}
							} else {
								System.out.println("Incorrect Input!");
							}
						}
					}
				}

				break;

			case 6:
				System.out.print("Enter The First Family Name: ");
				String FamilyName1 = scan.next();
				Family family2 = manager.searchByName(FamilyName1);
				System.out.print("Enter The Second Family Name: ");
				String FamilyName2 = scan.next();
				Family family3 = manager.searchByName(FamilyName2);
				if (manager.searchByName(FamilyName1) == null || manager.searchByName(FamilyName2) == null) { // to
																												// check
																												// if
																												// the
																												// two
																												// families
																												// are
																												// exist
					System.out.println("One of the families or both dose not exist!");
				}

				else {
					if (family2.equals(family3)) { // compare between the number of martyr for the two families
						System.out.println("They Have The Same Number of Martyrs");
					} else {
						System.out.println("They Don't Have The Same Number Of Martyrs");
					}
				}

				break;
			case 7:
				System.out.print("Enter The Family Name: ");
				FamilyName = scan.next();
				if (manager.searchByName(FamilyName) == null) {
					System.out.println("The Family Does Not Exist!");
					break;
				}

				else {
					System.out.print("Enter The new Name for The Family: ");
					String newName = scan.next();
					Family f = manager.searchByName(FamilyName);
					if (manager.searchByName(newName) != null && !FamilyName.toLowerCase().equals(newName)) {
						System.out.println("This family name is added before");
						break;
					}
					f.setFamilyName(newName);
					ArrayList<Person> members = f.getMembers();
					ArrayList<Person> parents = f.getParents();
					System.out.print("Enter The ID Of The Person you Want To Update his information: ");
					ID = scan.next();
					if (manager.searchPersonByID(ID) == null) {
						System.out.println("The person dose no exist before");
						break;
					}

					// enter the new information
					for (int i = 0; i < members.size(); i++) {
						if (members.get(i).getID().equals(ID)) {
							System.out.print("Enter the New Name: ");
							Name = scan.next();
							f.getMembers().get(i).setName(Name); // the role can not change(the mom cannot be a dad)
							role = f.getMembers().get(i).getRole();
							f.getMembers().get(i).setRole(role);
							System.out.print("Enter The New Age: ");
							continueInput = true;
							while (continueInput) {
								try {
									Age = scan.nextInt();
									f.getMembers().get(i).setAge(Age);
									continueInput = false;
									// some restricts for age
									if ((Age < 0 || Age > 120) && (role.toLowerCase().equals("son")
											|| role.toLowerCase().equals("daughter"))) {
										System.out.println("incorrect age, The age must be between 0 and 120");
										continueInput = true;
										scan.nextLine();
									}
									if ((Age < 16 || Age > 120)
											&& (role.toLowerCase().equals("mom") || role.toLowerCase().equals("dad"))) {
										System.out.print(
												"try again, incorrect age, The age must be between 16 and 120: ");
										continueInput = true;
										scan.nextLine();
									}
									boolean z = false;
									for (int j = 0; j < parents.size(); j++) {
										for (int k = 0; k < members.size(); k++) {
											int n = parents.get(j).getAge() - members.get(k).getAge();
											if (n < 17) {
												System.out.print(
														"try again, The age difference cannot be less than 17: ");
												continueInput = true;
												scan.nextLine();
												z = true;
												break;
												}
										}
										if(z == true) {
											break;
										}
									}

								} catch (InputMismatchException ex) {
									System.out.print("Try again. (" + "Incorrect input: an integer is required): ");
									scan.nextLine();
								}
							}
							Gender = f.getMembers().get(i).getGender(); // the gender can not change
							f.getMembers().get(i).setGender(Gender);
							System.out.print("Enter The New Address: ");
							Address = scan.next();
							f.getMembers().get(i).setAddress(Address);
							System.out.print("Enter The New ContactInfo: ");
							ContactInfo = scan.next();
							f.getMembers().get(i).setContactInfo(ContactInfo);
							if (members.get(i) instanceof LivePerson) {
								System.out.print("Is The Person A Martyr Or Alive? ");
								String person = scan.next();
								if (person.toLowerCase().equals("martyr")) {
									System.out.print("Enter The Date Of Martyrdom: ");
									String DateOfMartyrdom = scan.next();
									System.out.print("Enter The Cause Of The Death: ");
									String CauseOfDeath = scan.next();
									System.out.print("Enter The Place Of The Death: ");
									String PlaceOfDeath = scan.next();
									LivePerson.totalNumberOfLivePerson--;
									f.getMembers().set(i, new Martyr(ID, Name, Age, Gender, Address, ContactInfo,
											DateOfMartyrdom, CauseOfDeath, PlaceOfDeath, role));
								} else if (!(person.toLowerCase().equals("martyr"))
										&& !(person.toLowerCase().equals("alive"))) {
									System.out.println("Invalid input"); // force the user to enter only alive or martyr 
								}
							}
							manager.updateFamily(newName, f);
							System.out.println("Updated successfully");
						}
					}
					for (int i = 0; i < parents.size(); i++) {
						if (parents.get(i).getID().equals(ID)) { 
							System.out.print("Enter the New Name: ");
							Name = scan.next();
							f.getParents().get(i).setName(Name);
							role = f.getParents().get(i).getRole();
							f.getParents().get(i).setRole(role);
							System.out.print("Enter The New Age: ");
							continueInput = true;
							while (continueInput) {
								try {
									Age = scan.nextInt();
									f.getParents().get(i).setAge(Age);
									continueInput = false;
									if ((Age < 0 || Age > 120) && (role.toLowerCase().equals("son")
											|| role.toLowerCase().equals("daughter"))) {
										System.out.print("incorrect age, The age must be between 0 and 120: ");
										continueInput = true;
										scan.nextLine();
									}
									boolean t = false;
									if ((Age < 16 || Age > 120)
											&& (role.toLowerCase().equals("mom") || role.toLowerCase().equals("dad"))) {
										System.out.print("incorrect age, The age must be between 16 and 120: ");
										continueInput = true;
										scan.nextLine();
									}
									
									else for (int j = 0; j < parents.size(); j++) {
										for (int k = 0; k < members.size(); k++) {
											int n = parents.get(j).getAge() - members.get(k).getAge();
											if (n < 17) {
												System.out.println("The age difference cannot be less than 17");
												continueInput = true;
												scan.nextLine();
												t = true;
												break;
											}
										}
										if(t) {
											break;
										}
									}
								} catch (InputMismatchException ex) {
									System.out.print("Try again. (" + "Incorrect input: an integer is required): ");
									scan.nextLine();
								}
							}
							Gender = f.getParents().get(i).getGender();
							f.getParents().get(i).setGender(Gender);
							System.out.print("Enter The New Address: ");
							Address = scan.next();
							f.getParents().get(i).setAddress(Address);
							System.out.print("Enter The New ContactInfo: ");
							ContactInfo = scan.next();
							f.getParents().get(i).setContactInfo(ContactInfo);
							if (parents.get(i) instanceof LivePerson) {
								System.out.print("Is The Person A Martyr Or Alive? ");
								String person = scan.next();
								if (person.toLowerCase().equals("martyr")) {
									System.out.print("Enter The Date Of Martyrdom: ");
									String DateOfMartyrdom = scan.next();
									System.out.print("Enter The Cause Of The Death: ");
									String CauseOfDeath = scan.next();
									System.out.print("Enter The Place Of The Death: ");
									String PlaceOfDeath = scan.next();
									LivePerson.totalNumberOfLivePerson--;
									f.getParents().set(i, new Martyr(ID, Name, Age, Gender, Address, ContactInfo,
											DateOfMartyrdom, CauseOfDeath, PlaceOfDeath, role));
								} else if (!(person.toLowerCase().equals("martyr"))
										&& !(person.toLowerCase().equals("alive"))) {
									System.out.println("Invalid input");
								}
							}
							manager.updateFamily(newName, f);
							System.out.println("Updated successfully");
						}
					}
				}

				break;

			case 8:
				System.out.print("Enter The Family Name: ");
				FamilyName = scan.next();
				if (manager.searchByName(FamilyName) == null) {
					System.out.println("The Family dose not Exist!");
					break;
				} else {
					manager.deleteFamily(FamilyName); // delete family with specific family name
					System.out.println("Deleted successfully!");

				}
				break;

			case 9:
				System.out.print("Enter The Family Name: ");
				FamilyName = scan.next();
				family1 = manager.searchByName(FamilyName);
				if (manager.searchByName(FamilyName) == null) { // search a family by name
					System.out.println("The Family dose not Exist!");
					break;
				}

				else {
					ArrayList<Person> members = family1.getMembers();
					ArrayList<Person> parents = family1.getParents();
					for (int i = 0; i < members.size(); i++) {
						System.out.println(members.get(i).toString()); // get the family information
					}
					for(int i=0;i<parents.size();i++) {
						System.out.println(parents.get(i).toString());
					}
				}
				break;

			case 10:
				System.out.print("Enter The Person's ID: ");
				ID = scan.next();
				if (manager.searchPersonByID(ID) == null) { // search s person by id
					System.out.println("The Person Dose Not Exist");
				}

				else {
					Person p = manager.searchPersonByID(ID);
					System.out.println("The person information is: " + p.toString()); // return person's information
				}
				break;

			case 11:
				System.out.print("Enter The Family Name: ");
				FamilyName = scan.next();
				if (manager.searchByName(FamilyName) == null) {
					System.out.println("The Family dose not Exist!");
					break;
				}

				// return the family statistics : martyrs, live persons and orphans
				ArrayList<Integer> familyStatixtics = manager.calculateFamilyStatistics(FamilyName);
				System.out.println("The Number of Martyrs is: " + familyStatixtics.get(0));
				System.out.println("The Number of livePerson is: " + familyStatixtics.get(1));
				System.out.println("The Number of Orphans is: " + familyStatixtics.get(2));
				break;

			case 12:
				// return global statistics : martyrs, live persons and orphans in all system
				ArrayList<Integer> globalStatixtics = manager.calculateGlobalStatistics();
				System.out.println("The Total Number of Martyrs is: " + globalStatixtics.get(0));
				System.out.println("The Total Number of livePerson is: " + globalStatixtics.get(1));
				System.out.println("The Total Number of Orphans is: " + globalStatixtics.get(2));
				break;

			case 13:
				ArrayList<Family> families1 = family.sortByMartyrs(manager.getFamilies()); // sort families depending on martyrs
				for (int i = 0; i < families1.size(); i++) {
					System.out.println(families1.get(i).getFamilyName());
				}

				break;

			case 14:
				ArrayList<Family> families2 = family.sortByOrphans(manager.getFamilies()); // sort families depending on orphans
				for (int i = 0; i < families2.size(); i++) {
					System.out.println(families2.get(i).getFamilyName());
				}

				break;

			case 15:
				System.out.print("Enter The Family Name: "); // copy family
				FamilyName = scan.next();
				Family f = manager.searchByName(FamilyName);
				if (f == null) {
					System.out.println("The Family dose not Exist!");
					break;
				} else {
					Family f1 = f.deepCopy();
					System.out.println("Done");
					System.out.println(f1.toString());
				}

				break;

			case 16: // copy person(martyr or alive)
				System.out.print("Enter The Person's ID: ");
				ID = scan.next();
				Person p = manager.searchPersonByID(ID);
				if (p == null) {
					System.out.println("Sorry, The Member is not in the System");
					break;
				} else {
					if (p instanceof Martyr) {
						Person p1 = ((Martyr) p).deepCopy();
						System.out.println("Done");
						System.out.println(p1.toString());
					} else if (p instanceof LivePerson) {
						Person p2 = ((LivePerson) p).deepCopy();
						System.out.println("Done");
						System.out.println(p2.toString());
					}
				}

				break;

			case 17:
				// Print to the file all families with their members in sorted order
				boolean a = family.writeToFile(manager.getFamilies());
				if (a == true) {
					System.out.println("Done");
				}

				else {
					System.out.println("Error!");
				}

				break;

			default: // close the system
				System.out.println("Closed successfully");
			}
		} while (x >= 1 && x <= 17);


	}

}

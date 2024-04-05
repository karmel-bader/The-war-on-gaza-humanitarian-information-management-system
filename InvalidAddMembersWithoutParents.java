package application;

public class InvalidAddMembersWithoutParents extends Exception {
	private String Message;
	
	public InvalidAddMembersWithoutParents() {
		Message = "Can Not Adding Members Without Parents";
	}
	
	public InvalidAddMembersWithoutParents(String Message) {
		this.Message = Message;
	}

	public String getMessage() {
		return Message;
	}

	@Override
	public String toString() {
		return Message;
	}
	
	
	

}

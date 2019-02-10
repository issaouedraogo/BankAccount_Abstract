/*****************************************************************************
                                Issa Ouedraogo
                                CISC 3115 MY9
                                Prof C.Zeigler
                                HW7
*****************************************************************************/
abstract public class genName {
	// Abstract protected Data Members 
	protected String lastName; 
	protected String firstName;
	
	// abstract setters 
	public abstract void setLastName(String str);
	public abstract void setFirstName(String str);
	
	//getter 
	public abstract String getLastName();
	public abstract String getFirstName();
	
	// Default Constructor
	public genName() {
		lastName ="";
		firstName = "";
	}
	
	// constructor 
	public genName(String last, String first) {
		 lastName = last;
		 firstName = first;
	}
	// copy
	public genName(genName myName) {
		lastName = myName.lastName;
		firstName = myName.firstName;
	}
	// toString() method
	public String toString() {
		
		String str = String.format("%-11s%-7s", 
						lastName,
						firstName);
		return str;
	}
	public boolean equal(Name myName){
		if(lastName.equals(myName.getLastName())) {
			return true;
		}else {
			return false;
		}
		 
	}

}

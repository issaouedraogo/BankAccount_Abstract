/******************************************************************************
                                                Issa Ouedraogo
                                                CISC 3115 MY9
                                                Prof C.Zeigler
                                                HW4
******************************************************************************/
public class Name extends genName {

			// setter 
			public void setLastName(String str) {
				lastName=str;
			}
			public void setFirstName(String str) {
				firstName=str;
			}
			//getter 
			public String getLastName() {
				return lastName;
			}
			public String getFirstName() {
				return firstName;
			}
			
			//No-Arg Constructor
			public Name() {
				lastName = "";
				firstName = "";
				}
			
			// constructor 
			public Name(String last, String first) {
				 super(last, first);
			}
			//copy constructor
			public Name(Name myName) {
				super(myName);
			}
			
}

/******************************************************************************
                                            Issa Ouedraogo
                                            CISC 3115 MY9
                                            Prof C.Zeigler
                                            HW4
******************************************************************************/
public class Depositor extends genDepositor {
		
		// overwritten setters
		public void setName(Name n) {
			names = n;
		}
		public void setSocSecNum(String str) {
			socSecNum = str;
		}
		
		// overwritten Getters
		public Name getName() {
			return new Name(names);
		}
		public String getSocSecNum() {
			return socSecNum;
		}
		
		// non Argument Constructor
		public Depositor() {
			names = new Name();
			socSecNum = "";
		}
		// constructor with parameters
		public Depositor(Name n, String ssn) {
			names = n;
			socSecNum = ssn;
		}
		//copy constructor 
		public Depositor(Depositor myDeposi) {
			names = new Name(myDeposi.names);
			socSecNum = myDeposi.socSecNum;
		}
		
}

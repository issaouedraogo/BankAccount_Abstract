/******************************************************************************
                                        Issa Ouedraogo
                                        CISC 3115 MY9
                                        Prof C.Zeigler
                                        HW7									 
*****************************************************************************/
public class SavingsAccount extends Account {
	// No-Arg constructor
	public SavingsAccount() {
		super();
	}
	// Arg_Constructor
	public SavingsAccount(Depositor D, int string, String acTyp, 
                double balance,String status) {
		super( D,  string,  acTyp,  balance, status);
	}
	public SavingsAccount(Account myAccount) {
		super(myAccount);
	}
}

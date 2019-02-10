/******************************************************************************
                                Issa Ouedraogo
                                CISC 3115 MY9
                                Prof C.Zeigler
                                HW7									 
*****************************************************************************/
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CDAccount extends Account {
	
	// private data member members
	private String maturityDate;
	private int termOdCD;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
	//No_ Argument_Constructor
		public CDAccount() {
			super();
		}
	// Arg-Contructor
	public CDAccount(Depositor D, int string, String acTyp, double balance,
                String status, String maturity, int term) {
			super( D, string, acTyp, balance, status);
			maturityDate = maturity;
			termOdCD = term;	
		}
	// Copy Ones
	public CDAccount(CDAccount myAccount) {
		Depositors = new Depositor(myAccount.Depositors);
		accountNum = myAccount.accountNum;
		accountType = myAccount.accountType;
		accountBalance = myAccount.accountBalance;
		accountStatus = myAccount.accountStatus;
		transaction = myAccount.transaction;
		maturityDate = myAccount.maturityDate;
		termOdCD = myAccount.termOdCD;
		
	}
	// setters 
	public void setMaturityDate(String maturity) {
		maturityDate = maturity;
	}
	public void setTermOdCD(int term) {
		termOdCD = term;
	}
	
	// getters
	public String getMaturityDate() { 
		return maturityDate;
	}
	public int getTermOdCD() {
		return termOdCD;
	}
	
	
// this the method that takes a String in the Date format and convert 
        into a Date type
    private static final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	 
    private static Date getDate(String date) throws ParseException{
        return df.parse(date);
    }
    public  static boolean withIn5days(String maturityDate) 
            throws ParseException {
		
		Date mst = getDate(maturityDate);
		Date tt = new Date();
		long diff_On = mst.getTime()-tt.getTime();
		int day_on  = (int)(diff_On/(1000*60*60*24)); 
		if(day_on >= 5) {
			return true;
		}else {
			return false;
		}
    }
	
	
	//overriding the withdrawal methode 
    public int makeWithdrawal(double amountToWithdraw) throws ParseException {
		
            if(withIn5days(maturityDate)) {
                double oldAmount = accountBalance;
                if(amountToWithdraw <= 0.00) {
                        return 0;
                  }else if(amountToWithdraw > oldAmount) {
                        return 1;
                } else 
                        {
                        accountBalance = (oldAmount - amountToWithdraw);
                        return 2;
                 }
                }
            else {
                    return 3;	
            }
}
	// override the toString method 
	public String toString() {
		String str = super.toString();
                    str += String.format("%6s%15s",
                                    getTermOdCD(),
                                    getMaturityDate()
                                    );
		return str;	
	}
}
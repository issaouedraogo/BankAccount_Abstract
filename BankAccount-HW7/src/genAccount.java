/******************************************************************************
						Issa Ouedraogo
						CISC 3115 MY9
						Prof C.Zeigler
				 		HW7
******************************************************************************/
import java.text.ParseException;
import java.util.ArrayList;

public abstract class genAccount {
	// protected Data Members 
	protected Depositor Depositors; 
	protected int accountNum;
	protected String accountType;
	protected String accountStatus;
	protected double accountBalance;
	protected ArrayList<Transaction> transaction;
	
	// abstract setters
	public abstract void setDepositor(Depositor d);
	public abstract void setAccountNum(int str);
	
	public abstract void setAccounType(String str);
	
	public abstract void setAccountStatus(String status);
	public abstract void setAccountBalance(Double dbl);
	
	//astract  addTransaction method 
	public abstract void addTranstion(Transaction newTrans);
	
	// abstract Getters
	public abstract Depositor getDepositor();
	public abstract int getAccounNum();
	public abstract String getAccoutType();
	
	public abstract String getAccountStatus();
	public abstract double getAccountBalance();
	
	//abstract method 
	//this methode will return an ArrayList of Transaction
	public abstract ArrayList<Transaction> getTranstion();
	
	// non Argument Constructor 
	public genAccount() {
		Depositors = new Depositor();
		accountNum = 0;
		accountType = "";
		accountBalance = 0;
		accountStatus = "";
		transaction = new ArrayList<>(); 
	}
	// Constructor with parameter
	public genAccount(Depositor D, int string, String acTyp, 
                double balance,String status) {
		Depositors = D;
		accountNum= string;
		accountType = acTyp;
		accountBalance = balance;
		accountStatus= status;
		//tran = new ArrayList<>(); 
		
	}
	// copy constructor
	public genAccount(genAccount myAccount) {
		Depositors = new Depositor(myAccount.Depositors);
		accountNum = myAccount.accountNum;
		accountType = myAccount.accountType;
		accountBalance = myAccount.accountBalance;
		accountStatus = myAccount.accountStatus;
		transaction = myAccount.transaction;
	}
	// Class abstract Methods 
	public abstract double getBalance(Account myAccount);
	
	public  abstract int makeDeposit(double amountToDeposit ) 
                throws ParseException;
	
	public abstract int makeWithdrawal(double amountToWithdraw) 
                throws ParseException;
	
	public abstract boolean closeAcct(Account myAccount);
	
	public abstract boolean ReopenAcct(Account myAccount);
	
	// .toString() methode 
	public String toString() {
            Name myName = Depositors.getName();
            String str = String.format("%-12s%-14s%-17s%-13s%-12s$%-8.2f%9s",
                                    myName.getLastName(), 
                                    myName.getFirstName(),
                                    Depositors.getSocSecNum(),
                                    accountNum,
                                    accountType,
                                    accountBalance,
                                    accountStatus
                                    );
            return str;	
	}
	// .equal() method
    public boolean equals(genAccount myAcccount) {
        if(Depositors.getName().equals(myAcccount.getDepositor().getName())){
                    return true;
            }else {
                    return false;
            } 
    }
			
}

/******************************************************************************
                                    Issa Ouedraogo
                                    CISC 3115 MY9
                                    Prof C.Zeigler
                                    HW7									 
******************************************************************************/
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Account extends genAccount{
	
    // setters
    public void setDepositor(Depositor d) {
            Depositors = d;
    }
    public void setAccountNum(int str) {
            accountNum = str;
    }
    public void setAccounType(String str) {
            accountType = str;
    }
    public void setAccountStatus(String status) {
            accountStatus = status;
    }
    public void setAccountBalance(Double dbl) {
            accountBalance=dbl;
    }
    // addTransaction method 
    public void addTranstion(Transaction newTrans) {
            transaction.add(newTrans);
    }
    // Getters
    public Depositor getDepositor() {
            return new Depositor(Depositors);
    }
    public int getAccounNum() {
            return accountNum;
    }
    public String getAccoutType() {
            return accountType;
    }
    public String getAccountStatus() {
            return accountStatus;
    }
    public double getAccountBalance() {
            return accountBalance;
    }

    // this methode will return an ArrayList of Transaction
    public ArrayList<Transaction> getTranstion(){
            return transaction;
    }

    // non Argument Constructor 
    public Account() {
            super();
    }
    // Constructor with parameter
    public Account(Depositor D, int string, String acTyp, 
            double balance,String status ) {
            super(D, string, acTyp, balance, status);
            transaction = new ArrayList<>();

    }
    // copy constructor
    public Account(Account myAccount) {
            super(myAccount);
    }

    private final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

private  Date getDate(String date) throws ParseException{
    return df.parse(date);
}
public  boolean withIn180days(String issueDate) throws ParseException {
            Date mst = getDate(issueDate);
            Date tt = new Date();
            long diff_On = mst.getTime()-tt.getTime();
            int day_on  = (int)(diff_On/(1000*60*60*24)); 
            // this does take care of days with 24hour only
            if(day_on <= 180) {
                    return true;
            }else {
                    return false;
            }
    }

                                    // Class Methods 
    /* Method getBalance()
     * Input:
     * myAccount :an Account class bank 
     * Process:
     *  look for the balance of the account
     * Output:
     *  return the balance associate to the account
     */
    public double getBalance(Account myAccount) {
            double balance = myAccount.getAccountBalance();
            return balance;
    }
    /* Method makeDeposit()
     * Input:
      * myAccount :an Account class bank
      * amountToDeposit: the amount to deposit
     * Process:
     *  add the amountToDeposit to the old amount of the account 
     * Output:
     *  set the deposit associate to the account
     */
    public int makeDeposit(double amountToDeposit ) throws ParseException {
            double oldAmount = accountBalance;
            accountBalance = (oldAmount + amountToDeposit);
            return 0;
    }
    /* Method makeWithdrawal()
     * Input:
      * myAccount :an Account class bank
      * amountToWithdraw: the amount to withdraw
     * Process:
      *  Subtract the amountToWithdraw to the old amount of the account
     * Output:
      *  set the withdrawal associate to the account
     */
    public int makeWithdrawal(double amountToWithdraw) throws ParseException {

            double oldAmount = accountBalance;
            if(amountToWithdraw <= 0.00) {
                    return 0;
              }
            if(amountToWithdraw > oldAmount) {
                    return 1;
            } else 
                    {
                    accountBalance = (oldAmount - amountToWithdraw);
                    return 2;
            }
    }
    // Overloading of makeWithdrawal method 
    public int makeWithdrawal(double amountToWithdraw, String issueDate) 
                    throws ParseException {

            double oldAmount = accountBalance;
            if(amountToWithdraw <= 0.00) {
                    return 0;
              }
            if(amountToWithdraw > oldAmount) {
                    return 1;
               } 
            if(withIn180days(issueDate)) {
                    if(accountBalance < 2500){
                            accountBalance =(oldAmount - amountToWithdraw);
                            accountBalance = (accountBalance - 1.5);
                            }
                    else {
                            accountBalance = (oldAmount - amountToWithdraw);
                    }
                    return 3;
            }else {
                    return 4;
            }	
    }	
    /* Method CloseAcct()
     * Input:
      * myAccount :an Account of bank class
     * Process:
      *  set the value of account stautus to close if Open
      *  	 and return true
     * Output:
      *  return true if account close successfuly
      *  otherwise flase
     */			
    public boolean closeAcct(Account myAccount) {
            if(myAccount.getAccountStatus().equals("Open")) {
                    myAccount.setAccountStatus("Close");
                    return true;
            }
            return false;

    }

    /* Method ReopenAcct()
     * Input:
      * myAccount :an Account of bank class
     * Process:
      *  set the value of account stautus to Open if close
      *  	 and return true
     * Output:
      *  return true if account open successfuly
      *  otherwise flase
     */		
    public boolean ReopenAcct(Account myAccount) {
            if(myAccount.getAccountStatus().equals("Close")) {
                    myAccount.setAccountStatus("Open");
                    return true;
            }
            return false;	
    }
}

/******************************************************************************
                            Issa Ouedraogo
                            CISC 3115 MY9
                            Prof C.Zeigler
                            HW7									 
******************************************************************************/
import java.io.*;
import java.text.ParseException;
import java.util.*;

public class BankAccountUsingClass {

	public static void main(String[] args) 
				throws IOException, ParseException {
		// local variables
		char choice;// menu selection choice
		boolean not_done = true;// loop control flag
		// creation of an instance object of the Bank class
		Bank myBank = new Bank();
		// Create a Scanner object for the keyboard
		Scanner keyboard = new Scanner(System.in);
		// create an output file object
		PrintWriter outputFile = new PrintWriter("myoutput.txt");
		/* first part */
		/* fill the database */
		readData(myBank);
		// print the database
		printDatabase(outputFile, myBank);
		/* second part */
		/* call functions to read and process requests */
   do {
		printMenu();
		choice = keyboard.next().charAt(0);
		  switch (choice) {
		case 'q':
		case 'Q':
		  not_done = false;
		  printDatabase(outputFile, myBank);
		 break;
		case 'b':
		case 'B':
		  balance(myBank,keyboard,outputFile);
		  break;
		case 'd':
		case 'D':
		  deposit(myBank,keyboard,outputFile);
		  break;
		case 'i':
		case 'I':
		  accountInfo(myBank,outputFile,keyboard);
		  break;
		case 'h':
		case 'H':
		  accountInfoTranHist(myBank,outputFile,keyboard);
		  break;
		case 'c':
		case 'C':
		CloseAccount(myBank,outputFile,keyboard);
		break;
		case 'r':
		case 'R':
		  ReOpenAccount(myBank,keyboard,outputFile);
		  break;
		case 'w':
		case 'W':
		  Withdrawal(myBank,keyboard,outputFile);
		  break;
		case 'n':
		case 'N':
		  newAcct(myBank,outputFile,keyboard);
		  break;
		case 'x':
		case 'X':
		  deleteAcct(myBank,outputFile,keyboard);
		  break;
		default:
		  outputFile.println("Error: " +choice+ 
			" is an invalid selection -  try again");
		  outputFile.println();
		  outputFile.flush();
		  break;
					}
	  } while (not_done);
	  // close the output file
	  outputFile.close();
	  // close the Keyboard input option
	  keyboard.close();

	  System.out.println();
	  System.out.println("The program is terminating....");

	}
	/* Method readAccts()
	* Input:
	 * myBank : a bank of the class Bank 
	* Process:
	 *  Reads the initial database of Bank Account
	 * Output:
	 *  Fills in the initial Last name, First Name,  and balance arrays 
            *and returns the number of active accounts
	*/
	public static void readData(Bank myBank) throws IOException {

            // local variables
            String line;
            // open the bank account input file
            File dbFile = new File("myinput.txt"); 
            // create Scanner object to read in the input file
            Scanner sc = new Scanner(dbFile);
            while (sc.hasNext()) {
              // as long as there is a line remaining in the input 
              // we get that line
              line = sc.nextLine();
              // Tokenize each line using a StringTokenizer
              StringTokenizer myToken = new StringTokenizer(line);
              // extract the data from the line just read
              // create set the name object components
            Name myName=new Name(myToken.nextToken(),
                    myToken.nextToken());//extract and set first name
            //System.out.println(myName.getFirstName());
            // create and set the Depositor object components
            Depositor myDepositor=new Depositor(myName, //the name object
                            myToken.nextToken());//extract and set ssn
            // Setting a new Transaction Object
            Transaction myTransac = new Transaction();
            myTransac.setTransAmount(0.0);
            myTransac.setTransStat("success");
            myTransac.setTransTyp("New Account");
            //System.out.println(myTransac +"HERE");
            //System.out.println(myTransac.getTransStat());
            int acctNum = Integer.parseInt(myToken.nextToken());
            String acctTyp = myToken.nextToken();
            Double balance = Double.parseDouble(myToken.nextToken());
            String stat = "Open";

            // Using Polymorphism to reference a  Account reference object to 
            //  	either a Subclass SavingAccount, Checking or CDAccount
    // 		And we are also setting the value of static member variable 
    if(acctTyp.equals("Savings")) {
            SavingsAccount myAccount = new SavingsAccount(
                            myDepositor, //set the depositor component
                            acctNum,
                            acctTyp,
                            balance,
                            stat);
            myAccount.addTranstion(new Transaction(myTransac));
            //the my account to the bank database
            myBank.addAcct(myAccount);
            myBank.setTotalSavings(myBank.getTotalSavings() + 
                            myAccount.getAccountBalance());
		}else if(acctTyp.equals("Checking")) {
			 CheckingAccount myAccount = new CheckingAccount(
                                 myDepositor, //set the depositor component
                                acctNum,
                                acctTyp,
                                balance,
                                stat);
			 //System.out.println(myTransac);
			 //myAccount.addTranstion(myTransac);
			 myAccount.addTranstion(new Transaction(myTransac));
			 //System.out.println("worked");
			 
				//the my account to the bank database
				myBank.addAcct(myAccount);
			myBank.setTotalChecking(myBank.getTotalChecking() +
					myAccount.getAccountBalance());
		}else{
			String str = myToken.nextToken();
			int str2 = Integer.parseInt(myToken.nextToken());
			System.out.println(str + str2);
			CDAccount myAccount = new CDAccount(
                                myDepositor, //set the depositor component
                                acctNum,
                                acctTyp,
                                balance,
                                stat,
                                str,
                                str2);
			myAccount.addTranstion(new Transaction(myTransac));
			//myAccount.addTranstion(myTransac);
			//the my account to the bank database
			myBank.addAcct(myAccount);
			myBank.setTotalCD(myBank.getTotalCD() + 
					myAccount.getAccountBalance());
		}
		myBank.setTotal(myBank.getTotalCD() + myBank.getTotalSavings()+ 
                        myBank.getTotalChecking());
		
	   }
		// close the input file
		sc.close();
	}
			
/* Method printAccts:
 * Input:
 *  myBank - array of bank accounts 
 *  outputFile - reference to the output file
 * Process:
 *  Prints the database of last name, first accounts number, 
 *  	ssn, account type and and balances
 * Output:
 *  Prints the database of last name, first accounts number, 
 *  	ssn, account type and and balances
*/
public static void printDatabase(PrintWriter outputFile, Bank mybank) {

        Account myAccounts = new Account();	
        // print table title
        outputFile.println("\t\tBank Accounts in the Database");
        outputFile.println();
        // print table column headings
        outputFile.printf("%-11s%-7s%-11s%-4s%-11s%-15s%-8s%-10s%10s", 
                "Last_Name", " First_Name ", " Social_Security ",
                " Acct_Number ", " Account_Type ", " Balance ","Status", 
                "CD_Term", "Maturity_Date");
        outputFile.println();
        for (int count = 0; count < mybank.getNumAccounts(); count++) {
            myAccounts = mybank.getAccount(count);
            // implicite use of the toString() methode
            outputFile.println(myAccounts);
            outputFile.printf("%-20s","Transaction History:");
            outputFile.println();
            outputFile.printf("%20s %22s %22s","Transaction_Type", 
                    "Transaction_Amount", "Transaction_status" );
            outputFile.println();
            for(int j=0; j< myAccounts.getTranstion().size(); j++) {
                outputFile.println(myAccounts.getTranstion().get(j));
            }
            outputFile.println(
            "-------------------------------------------------------"
            + "---------------------------------------------------------");
        }
        outputFile.printf("the Total Amount for Savings Accounts is: ");
        outputFile.printf("$%.2f\n", mybank.getTotalSavings());
        outputFile.printf("The Total Amount for Checking Accounts is: ");
        outputFile.printf("$%.2f\n", mybank.getTotalChecking());
        outputFile.printf("the Total Amount for CD Accounts is: ");
        outputFile.printf("$%.2f\n", mybank.getTotalCD());
        outputFile.printf("the Total Amount for All Accounts is: ");
        outputFile.printf("$%.2f\n", mybank.getTotal());
        outputFile.println(
                    "-------------------------------------------------------"
                    + "---------------------------------------");
        outputFile.println();
        outputFile.flush(); // flush the output file buffer
        }
        /* Method menu()
         * Input:
         *  none
         * Process:
         *  Prints the menu of transaction choices
         * Output:
         *  Prints the menu of transaction choices
         */
public static void printMenu() {

        System.out.println();
        System.out.println("Select one of the following transactions:");
        System.out.println("\t****************************");
        System.out.println("\t    List of Choices         ");
        System.out.println("\t****************************");
        System.out.println("\t     W -- Withdrawal");
        System.out.println("\t     D -- Deposit");
        System.out.println("\t     N -- New Account");
        System.out.println("\t     B -- Balance Inquiry");
        System.out.println("\t     I -- Account Info");
        System.out.println("\t     H -- Account Info plus Account "
                + "Transaction History");
        System.out.println("\t     C -- Close Account");
        System.out.println("\t     R -- Reopen a close Account");
        System.out.println("\t     X -- Delete Account");
        System.out.println("\t     Q -- Quit");
        System.out.println();
        System.out.print("\tEnter your selection: ");
}
/* Method findAcct:
 * Input:
 *  myBank - object of Bank Class 
 *  requestedAccount - requested account requested_number
 * Process:
 *  Performs a linear search on the bank account array 
        for the requested account number
 * Output:
 *  If found, the index of the requested account is returned
 *  Otherwise, returns -1
 */
public static int findAccts(Bank myBank, int requestedAccount) {
  int requestedAccount1;
  for (int index = 0; index < myBank.getNumAccounts(); index++) {
  requestedAccount1 = myBank.getAccount(index).getAccounNum();
    if (requestedAccount1 == requestedAccount) {
        return index;
     }
  }
return -1;
}
/* Method balance:
 * Input:
 * 	myBank - object of Bank Class 
 *  outputFile - reference to output file
 *  kybd - reference to the "test cases" input file
 * Process:
 *  Prompts for the requested account
 *  Calls findAcct() to see if the account exists
 *  If the account exists, the balance is printed along with
                 the client name 
 *  Otherwise, an error message is printed
 * Output:
 *  If the account exists, the balance is printed
 *  Otherwise, an error message is printed
 */
public static void balance(Bank myBank, Scanner kybd, PrintWriter outputFile) {
    int requestedAccount;
    int index;
    System.out.println();
    System.out.print("Enter the account number: ");// prompt for account number
    requestedAccount = kybd.nextInt(); // read-in the account number

    // call findAcct to search if requestedAccount exists
    index = findAccts(myBank, requestedAccount);
    //System.out.println(index);
    if (index == -1) // invalid account
    {
      outputFile.println("Transaction Requested: Balance Inquiry");
      outputFile.println("Error: Account number " + requestedAccount + 
              " does not exist");
    }else {  // valid account		
       Account myAccount = new Account();
       myAccount = myBank.getAccount(index);
       if(myAccount.getAccountStatus() == "Open" || 
               myAccount.getAccountStatus() == "open" ) {
       outputFile.println();
       outputFile.println("Transaction Requested: Balance Inquiry");
       outputFile.println("Account Number: " + requestedAccount);
       outputFile.println("Account holder: " + myAccount.getDepositor().
               getName().getFirstName() + " "
            + myAccount.getDepositor().getName().getLastName());
       outputFile.println("Account type: "+ myAccount.getAccoutType());
       outputFile.printf("Current Balance: $%.2f", 
               myAccount.getBalance(myAccount));
       outputFile.println();
       Transaction newTrans = new Transaction("Balance Inquiry", 
               myAccount.getBalance(myAccount), "success");
       myAccount.addTranstion(newTrans);
    }else {
     outputFile.println("Transaction type: Balance Inquiry");
     outputFile.println("Account number:" + requestedAccount + " is Close");
     outputFile.println("Please Open the Account Before Checking of Balance !");
     outputFile.println();		
    }

     }
outputFile.println();
outputFile.flush();
}
/* Method deposit:
 * Input:
  * myBank - object of Bank Class 
  * outFile - reference to the output file
  * kybd - reference to the "test cases" input file
* Process:
 *  Prompts for the requested account
 *  Calls findacct() to see if the account exists
 *  If the account exists, prompts for the amount to deposit
 *  If the amount is valid, it makes the deposit and prints the new balance
 *  Otherwise, an error message is printed
 * Output:
 *  For a valid deposit, the deposit transaction is printed
 *  Otherwise, an error message is printed
 */
public static void deposit(Bank myBank, Scanner kybd, PrintWriter outputFile)
        throws ParseException {
  // Local Variables
  int requestedAccount;
  int index;
  double amountToDeposit;

  System.out.println();
  System.out.print("Enter the account number: "); // prompt for account number
  requestedAccount = kybd.nextInt(); // read-in the account number
  // call findAcct to search if requestedAccount exists
  index = findAccts(myBank, requestedAccount);
  if (index == -1) { // invalid account
     outputFile.println("Transaction Requested: Deposit");
        outputFile.println("Error: Account number " + requestedAccount + 
                " does not exist");
   } else { // valid Account
        Account myAccount = new Account();
        myAccount = myBank.getAccount(index);
        // get depositor and name from myAccount
        //Depositor myDepo = myAccount.getDepositor();
        //Name myName = myDepo.getName();

        if(myAccount.getAccountStatus() == "Open" || 
                myAccount.getAccountStatus() == "open" ) {
            // prompt for amount to deposit
        System.out.print("Enter amount to deposit: "); 
        amountToDeposit = kybd.nextDouble(); // read-in the amount to deposit
    if (amountToDeposit <= 0.00) {
                // invalid amount to deposit
                outputFile.println("Transaction Requested: Deposit");
                outputFile.println("Account Number: " + requestedAccount);
                outputFile.printf("Error: $%.2f is an invalid amount", 
                        amountToDeposit);
                outputFile.println();
                Transaction newTrans =new Transaction("Deposite", 0.00,"Fail");
                myAccount.addTranstion(newTrans);
    }else {

        outputFile.println("Transaction Requested: Deposit");
        outputFile.println("Account Number: " + requestedAccount);
        outputFile.println("Account holder: " + myAccount.getDepositor().
                getName().getFirstName() + " "
        + myAccount.getDepositor().getName().getLastName());
        outputFile.println("Account type: "+ myAccount.getAccoutType());
        outputFile.printf("Old Balance: $%.2f", myAccount.getAccountBalance());
        outputFile.println();
        outputFile.printf("Amount to Deposit: $%.2f" ,amountToDeposit);
        outputFile.println();
        //oldAmount = myAccount.getAccountBalance();
        int success = myAccount.makeDeposit(amountToDeposit);//make the deposit
        if (success == 0) {
            myBank.addAmountstoTotal(myAccount, amountToDeposit); 
        // updated the total values
            Transaction newTrans = new Transaction("Deposite", 
                    amountToDeposit, "success");
            myAccount.addTranstion(newTrans);

            myBank.updateBankAccount(index, myAccount); 
           // revise the contestant database.
            outputFile.printf("New Balance: $%.2f", 
                    myAccount.getAccountBalance());
            outputFile.println();
        }else if(success == 1 ){
                outputFile.println("Transaction type: Deposite");
                outputFile.println("Account number:" + requestedAccount);
                outputFile.println("Error: this Check might be expired ");
                outputFile.println();
        }else {
                outputFile.println("Transaction type: Deposite");
                outputFile.println("Account number:" + requestedAccount);
                outputFile.println("Error: The maturity Date of this Account "
                        + "is not reach");
                outputFile.println();
        }
    }
        }else {
        outputFile.println("Transaction type: Deposite");
        outputFile.println("Account number:" + requestedAccount + " is Close");
        outputFile.println("Please Open the Account Before Making a Deposite!");
        outputFile.println();
      }	 
     }
        outputFile.println();
        outputFile.flush();
}
/* Method Withdrawal:
* Input:
 *  myBank - object of Bank Class 
 *  outputFile - reference to the output file
 *  kybd - reference to the keyboard input
* Process:
 *  Prompts for the requested account for withdrawal
 *  Calls findacct() to see if the account exists
 *  If the account exists, prompts for the amount for the withdrawal
 *  If the account as sufficient fund, it makes the withdrawal and prints 
    the new balance
 *  Otherwise, an error message is printed
 * Output:
 *  For a valid account and fund, the withdrawal transaction is printed
 *  Otherwise, an error message is printed
 */
public static void Withdrawal(Bank myBank, Scanner kybd, PrintWriter outputFile)
    throws ParseException {

    int acctToWithdrNum;
    int index; // an index to get the check the new account index to verify it
            //existence
    double amountToWithdraw =0;
    outputFile.println();
    System.out.println("Enter the account number of the "
            + "account for Withdrawal!");
    acctToWithdrNum = kybd.nextInt();
    index = findAccts(myBank, acctToWithdrNum);

    if (index == -1) {	
      outputFile.println("Transaction Requested: Withdrawal");
      outputFile.println("Account Number: " + acctToWithdrNum);
      outputFile.println("Error: invalid Accout Number");
      outputFile.println();
    }else {

      Account myAccount = new Account();
      int success;
      myAccount = myBank.getAccount(index);
      double oldAmount = myAccount.getAccountBalance();
      System.out.println("Enter the amount of the withdrawal !");
      amountToWithdraw = kybd.nextDouble();
      if(myAccount.getAccountStatus() == "Open" || 
              myAccount.getAccountStatus() == "open" ) {

        if(myAccount.getAccoutType().equals("Checking")) 
        {
            System.out.println("Enter please Enter the Check expiration date ");
            String expDate = kybd.next();
            if(myAccount.withIn180days(expDate)) {
                success = myAccount.makeWithdrawal(amountToWithdraw, expDate);
            }else {
                    success = 5;
            }
        }
      else {
                success = myAccount.makeWithdrawal(amountToWithdraw);
               }
        if (success == 0) {
          outputFile.println("Transaction Requested: Withdrawal");
          outputFile.println("Account Number: " + acctToWithdrNum);
          outputFile.println("Account holder: " + myAccount.getDepositor().
                  getName().getFirstName() + " "
              + myAccount.getDepositor().getName().getLastName());
          outputFile.println("Account type: "+ myAccount.getAccoutType());
          outputFile.println("Error: invalide amount for withdral");
          outputFile.println();
          Transaction newTrans = new Transaction(
                  "Withdrawal", amountToWithdraw, "Fail");
          myAccount.addTranstion(newTrans);
        }
        if (success == 1) {
          outputFile.println("Transaction Requested: Withdrawal");
          outputFile.println("Account Number: " + acctToWithdrNum);
          outputFile.println("Account holder: " + myAccount.getDepositor().
                  getName().getFirstName() + " "
              + myAccount.getDepositor().getName().getLastName());
          outputFile.println("Account type: "+ myAccount.getAccoutType());
              outputFile.println("Error: account number " + acctToWithdrNum + 
                      " does not have sufficient amount");
              outputFile.println();
              Transaction newTrans = new Transaction("Withdrawal",0.00, "Fail");
              myAccount.addTranstion(newTrans);
              } 
        if ((success == 3) && myAccount.getAccoutType().equals("CD")) {
            outputFile.println("Transaction Requested: Withdrawal");
            outputFile.println("Account Number: " + acctToWithdrNum);
            outputFile.println("Account holder: " + myAccount.
                      getDepositor().getName().getFirstName() + " "
                  + myAccount.getDepositor().getName().getLastName());
            outputFile.println("Account type: "+ myAccount.getAccoutType());
            outputFile.println("Error: account number " + 
                    acctToWithdrNum + " Did not reach the maturity Date");
            outputFile.println();
            Transaction newTrans = new Transaction("Withdrawal",0.00, "Fail");
            myAccount.addTranstion(newTrans);
                } 
         if(success ==2){
              myBank.subAmountFromTotal(myAccount, amountToWithdraw);
               // Update the information in the BankDb
              myBank.updateBankAccount(index, myAccount);
          outputFile.println("Transaction Requested: Withdrawal");
          outputFile.println("Account Number: " + acctToWithdrNum);
          outputFile.println("Account holder: " + myAccount.getDepositor().
                  getName().getFirstName() + " "
              + myAccount.getDepositor().getName().getLastName());
          outputFile.println("Account type: "+ myAccount.getAccoutType());
          outputFile.printf("Old Balance: $%.2f", oldAmount);
          outputFile.println();
          outputFile.printf("Amount for withdrawal: $%.2f", amountToWithdraw);
          outputFile.println();
          Transaction newTrans = new Transaction("Withdrawal", 
                  amountToWithdraw, "success");
          myAccount.addTranstion(newTrans);													
          outputFile.printf("New Balance:$%.2f",myAccount.getAccountBalance()); 
          outputFile.println();
         }
         if(success == 5) {
                outputFile.println("Transaction Requested: Withdrawal");
                outputFile.println("Account Number: " + acctToWithdrNum);
                outputFile.println("Error: This Check is Invalide");
                outputFile.println();
         }


       }else {
        outputFile.println("Transaction type: Withdrawal");
        outputFile.println("Account number:" + acctToWithdrNum + " is Close");
       outputFile.println("Please OPEN the Account Before Making the Widrawal");
        outputFile.println();
      }
    }
    outputFile.flush();
}

/* Method newAccount:
* Input:
 *	myBank - object of Bank Class 
 *  numAccts - number of active accounts
 *  outputFile - reference to the output file
 *  kybd - reference to the keyboard input 
        * Process:
 *  Prompts for the last and first name, account type type, ssn 
 *  Calls the openNewAcct() method which return a boolean value
 *  If true is return add a new account
 *  Otherwise, an error message is printed
        * Output:
 *  add a new account to the account array
 *  Otherwise, an error message is printed
 */
public static void newAcct(Bank myBank, PrintWriter outputFile, Scanner kybd) {
        // local variables
        String newAccountLastname;
        String newAccountFirstname;
        String newAccountTyp;
        String newAccountSsn;
        int newAccountNumber; // a new account value holder
        outputFile.println();
        // prompt for the new account number
        System.out.print("Enter the new account number: "); 
        // Get the value to be assign to the variable newAccountNumber
        newAccountNumber = kybd.nextInt(); 
        // Prompting for the Element for a new account creating
        System.out.print("Enter the new account holder Last name: ");
        newAccountLastname = kybd.next();
        System.out.print("Enter the new account holder First name: ");
        newAccountFirstname = kybd.next();
        System.out.print("Enter the new account Type:");
        newAccountTyp = kybd.next();
        System.out.print("Enter the new account holder Social Security Number:"
                + " Not duplicate ssn is allowed: ");
        newAccountSsn = kybd.next();
        // we use the constructors to build up the new account
        Name newName = 
                        new Name(newAccountFirstname, newAccountLastname);
        Depositor newDep = 
                        new Depositor(newName, newAccountSsn);
        Account newAccount;
        if(newAccountTyp.equals("CD")) {
                System.out.print("Please Enter a maturity Date in the format: "
                        + "dd-mm-yyyy: ");
                String maturity = kybd.next();
                System.out.print("Please Enter a the term in month: ");
                int term =  kybd.nextInt();
                newAccount = new CDAccount(newDep, 
                                newAccountNumber, 
                                newAccountTyp, 
                                0.00, 
                                "Open",
                                maturity,
                                term);
                }else if(newAccountTyp.equals("Savings")) {
                                newAccount = new SavingsAccount(
                                                newDep, 
                                                newAccountNumber, 
                                                newAccountTyp, 
                                                0.00, 
                                                "Open");
                }else {
                                        newAccount = new CheckingAccount(
                                                        newDep, 
                                                        newAccountNumber, 
                                                        newAccountTyp, 
                                                        0.00, 
                                                        "Open");
                                        }

        boolean success = myBank.openNewAcct(newAccount);
        if(success) { // if success = true or no problem occur
           outputFile.println("Transaction type: New Account creation");
           outputFile.println("New Account create Succesfulty");
           outputFile.println("New Account Number: "+newAccountNumber);
           outputFile.println("Account holder: " + newAccountFirstname+ " "+ 
                   newAccountLastname);
           outputFile.println("Account type: "+ newAccountTyp);
           outputFile.printf("New Account Balance: $0.00");
           outputFile.println();
           // Add transaction to the associated account
           Transaction newTrans = new Transaction(
                        "NewAccountcreation", 
                        0.00, 
                        "success");
           newAccount.addTranstion(newTrans);
        }else { // exisitent account Number
           outputFile.println("Transaction type: New Account Creation");
           outputFile.println("New Account Number: "+newAccountNumber);
           outputFile.printf(
                "Error: The Account number enter is already existente");
           outputFile.println();
        }
        outputFile.println();
        outputFile.flush();

}
/* Method accountInfo:
* Input:
 *	myBank - object of Bank Class 
 *  outFile - reference to the output file
 *  kybd - reference to the keyboard input 
* Process:
 *  Prompts for the requested account
 *  Calls findacct() to see if the account is not already existent
 *  If the account exists:
 *  print for last, first, ssn, account number, account type and the balance 
 *  Otherwise, an error message is printed
* Output:
 *  print the output 
 *  Otherwise, an error message is printed
 */
public static void accountInfo(Bank myBank, PrintWriter outputFile, 
                        Scanner kybd) {
        //local variable
        int acctNumInfo;
        int index;
        outputFile.println();
        System.out.println("Enter the account number for Information");
        acctNumInfo = kybd.nextInt();
        index = findAccts(myBank, acctNumInfo);
        if (index == -1) {
          outputFile.println("Transaction type: Account Information ");
          outputFile.println("Account Number: " + acctNumInfo);
          outputFile.printf("Error: The Account number " + 
                acctNumInfo + " enter does not existe");
          outputFile.println();
        } else {
          Account myAccount = new Account();
          myAccount = myBank.getAccount(index);
          if(myAccount.getAccountStatus().equals("Open") 
                || myAccount.getAccountStatus().equals("open")) {
          outputFile.println("Transaction type: Account Information");
          outputFile.println("Account Number: " + acctNumInfo);
          outputFile.printf("%-11s%-7s%-11s%-4s%-11s%-11s%-10s", "Last_Name", 
                  " First_Name ", " Social_Security_Number ",
                            " Account_Number ", " Account_Type ", " Balance "
                                    + "","Account_Status");
          outputFile.println();
          outputFile.println(myAccount.toString());
          Transaction myTransac = new Transaction(
                "Account Info",
                0.00, 
                "success");
          myAccount.addTranstion(myTransac);
          outputFile.println();
        }else {
          outputFile.println("Transaction type: Account Info");
          outputFile.println("Account number: "+ acctNumInfo+" is Close");
          outputFile.println(
                "Please OPEN the Account Before Getting Info!");
          outputFile.println();
          Transaction myTransac = new Transaction(
                        "Account Info",
                         0.00,
                         "Fail");
          myAccount.addTranstion(myTransac);
         }
        }
        outputFile.println();
        outputFile.flush();

}
/* Method accountInfoWithTransactionInfo:
* Input:
 *	myBank - object of Bank Class 
 *  outFile - reference to the output file
 *  kybd - reference to the keyboard input 
        * Process:
 *  Prompts for the requested account
 *  Calls findacct() to see if the account is not already existent
 *  If the account exists:
 *  print for last, first, ssn, account number, account type and the balance
 *  	accompany with the account Transaction history 
 *  Otherwise, an error message is printed
        * Output:
 *  print the output 
 *  Otherwise, an error message is printed
 */
public static void accountInfoTranHist(Bank myBank, PrintWriter outputFile, 
                Scanner kybd) {
        //local variable
        int acctNumInfo;
        int index;
        outputFile.println();
        System.out.println("Enter the account number for Information");
        acctNumInfo = kybd.nextInt();
        index = findAccts(myBank, acctNumInfo);
        if (index == -1) {
          outputFile.println("Transaction type: Account Info With Transaction "
                  + "History ");
          outputFile.println("Account Number: " + acctNumInfo);
          outputFile.printf("Error: The Account number " + acctNumInfo + " "
                  + "enter does not existe");
          outputFile.println();
        }else {
          Account myAccount = new Account();
          myAccount = myBank.getAccount(index);
          if(myAccount.getAccountStatus().equals("Open") 
                || myAccount.getAccountStatus().equals("open")) {
            outputFile.println("Transaction type: Account Info With Transaction"
                    + "Historyn");
            outputFile.printf("%-11s%-7s%-11s%-4s%-11s%-11s%-10s", "Last_Name",
                    " First_Name ", " Social_Security_Number ",
                                " Account_Number ", " Account_Type ", " Balance"
                                        + " ","Account_Status");
            outputFile.println();
            outputFile.println(myAccount.toString());
            // new arrayList of Transaction
            ArrayList<Transaction> myTransac = new ArrayList<>(); 
            myTransac = myAccount.getTranstion();
            outputFile.println("Transaction History");
            outputFile.printf("%20s %22s %22s", 
                "Transaction_Type", "Transaction_Amount","Transaction_status");
            outputFile.println();
            for(int j=0; j<myTransac.size();j++) {
                outputFile.println(myTransac.get(j));

                }
                outputFile.println();
                Transaction newTransac = new Transaction(
                        "Info-Transac-History ", 
                        0.00, 
                        "success");
                myAccount.addTranstion(newTransac);
      }else {
        outputFile.println("Transaction type: Account Info");
        outputFile.println("Account number:" + acctNumInfo + " is Close");
        outputFile.println("Please OPEN the Account Before Getting Info!");
        outputFile.println();
        Transaction newTransac = new Transaction("Info-Transaction History ", 
                                0.00, 
                                "Fail");
        myAccount.addTranstion(newTransac);
       }
      }
      outputFile.println();
      outputFile.flush();
}
	/* Method deleteAccount:
	 * Input:
	 *  myBank - object of Bank Class 
	 *  outputFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	* Process:
	 *  Prompts for the requested account
	 *  Calls findacct() to see if the account exists
	 *  If the account exists, prompts for the amount to deposit
	 *  If the amount is valid, it makes the deposit and prints the 
            new balance
	 *  Otherwise, an error message is printed
	 * Output:
	 *  For a valid deposit, the deposit transaction is printed
	 *  Otherwise, an error message is printed
	 */
	public static void deleteAcct(Bank myBank, PrintWriter outputFile,
			 Scanner kybd) {
		//local variable 
		int acctToDeleteNum;
		int index; 
		outputFile.println();
		//prompt for account number
		System.out.println(
                    "Enter the account number of the account to be deleted:");
		acctToDeleteNum = kybd.nextInt();//read-in the account number
	    	//call findAcct to search if requestedAccount exists
		index  = findAccts(myBank, acctToDeleteNum);
		if (index == -1) //invalid account
		{
		  outputFile.println("Transaction type: Delete Account ");
		  outputFile.println("Account Number: " + acctToDeleteNum);
		  outputFile.printf("Error: The Account number " 
			+ acctToDeleteNum + " enter does not existe");
		  outputFile.println();
		  outputFile.println();
		} 
		else { // valid account
		Account myAccountTodelete = myBank.getAccount(index);
		if(myAccountTodelete.getAccountStatus() == "Close" || 
			myAccountTodelete.getAccountStatus() == "close" ) {
		boolean success = myBank.deleteAcct(myBank.getAccount(index));
		  if (success) {
                      // this will remove the account from the Database as well
			myBank.DeleteUpdateBankAccount(index);
			outputFile.println("Transaction type: Delete Account");
			outputFile.println("Account number:"
				+ acctToDeleteNum + " deleted Succesfulty");
			outputFile.println();
		  } else {
			outputFile.println("Transaction type: Delete Account ");
			outputFile.println("Account Number: " 
				+ acctToDeleteNum);
			outputFile.println("Account holder: " 
				+ myAccountTodelete.getDepositor().getName().
					getFirstName() +" "
				+ myAccountTodelete.getDepositor().getName().
					getLastName());
			outputFile.println("Account type: "+ myAccountTodelete.
                                getAccoutType());
			outputFile.printf("Error: The Account number "
			 + acctToDeleteNum + " has a balance");
			outputFile.println();
			outputFile.println();
		   }
		   }else {
			outputFile.println("Transaction type: Delete Account");
			outputFile.println("Account number:" 
				+ acctToDeleteNum + " is Open");
			outputFile.println(
				"Please Close the Account Before Deleting !");
				outputFile.println();
			    }
			}
		outputFile.flush();
	}
	/* Method ReopenAccount()
	 * Input:
	  *  myBank - object of Bank Class 
	  *  outputFile - reference to the output file
	  *  kybd - reference to the "test cases" input file
	 * Process:
	  * Call ReOpenAcct() to Reopen the Account
	  *  print to result to the outputfile
	  *  and add the transaction made to the transaction array List
	 * Output:
	 * For a valid accountNumber, the Reopen account transaction is printed
	  * Otherwise, an error message is printed
	 */	
		
	public static void ReOpenAccount(Bank myBank,Scanner kybd, 
			PrintWriter outputFile) {
		int acctToReopenNum;
		System.out.println(
			"Enter the account number of the account to Reopen:");
		//read-in the account number
		acctToReopenNum = kybd.nextInt();
		int index = findAccts(myBank, acctToReopenNum);
		if(index != -1) {
		  Account myAccount = myBank.getAccount(index);
		//  Transaction mytran = myAccount.getTranstion()
		  if(myAccount.ReopenAcct(myAccount)) {
		  outputFile.println();
		  outputFile.println("Transaction type: ReOpen Account ");
		  outputFile.println("Account Number: "+ acctToReopenNum);
		  outputFile.println("Account Reopen Succesfulty");
		  Transaction newTrans = new Transaction("Reopen Account", 
                                        0.00, "success");
		  myAccount.addTranstion(newTrans);
		  myBank.updateBankAccount(index, myAccount);
		  
		}
		else {
		  outputFile.println();
		  outputFile.println("No need to");
		  outputFile.println(
			"The Account Associated to the Account Number " 
			+ acctToReopenNum + "is ALREADY OPEN");
		  Transaction newTrans = new Transaction("Reopen Account", 
			0.00, 
			"Fail");
		  myAccount.addTranstion(newTrans);
	 	  outputFile.println();
		  }
		}
	}
/* Method CloseAccount()
 * Input:
  *  myBank - object of Bank Class 
  *  outputFile - reference to the output file
  *  kybd - reference to the "test cases" input file
 * Process:
  * Call CloseAcct() to Close the Account
  *  print to result to the outputfile
  *  and add the transaction made to the transaction array List
 * Output:
  * For a valid accountNumber, the Close account transaction is printed
  * Otherwise, an error message is printed
 */		
public static void CloseAccount(Bank myBank,PrintWriter outputFile, 
                Scanner kybd) {
    int acctToCloseNum;
    System.out.println("Enter the account number of the account "
            + "to Close:");
    acctToCloseNum = kybd.nextInt();//read-in the account number
    int index = findAccts(myBank, acctToCloseNum);
    if(index != -1) {
        Account myAccount = myBank.getAccount(index);
        if(myAccount.closeAcct(myAccount)) {
          outputFile.println();
          outputFile.println("Transaction type: Close Account ");
          outputFile.println("Account Number: "+ acctToCloseNum);
          outputFile.println("Account Close Succesfulty");
          Transaction newTrans = new Transaction("Close Account", 0.00, 
                  "success");
          myAccount.addTranstion(newTrans);
          myBank.updateBankAccount(index, myAccount);
        }
        else {
          outputFile.println();
          outputFile.println("No need to");
          outputFile.println("The Account Associated to the Account Number" 
                + acctToCloseNum + "is ALREADY CLOSE");
          Transaction newTrans = new Transaction("Close Account", 0.00, "Fail");
                  myAccount.addTranstion(newTrans);
                  outputFile.println();	
                }
        outputFile.println();
        }
    }		
}

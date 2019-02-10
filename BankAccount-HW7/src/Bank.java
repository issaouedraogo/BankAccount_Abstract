/******************************************************************************
                                    Issa Ouedraogo
                                    CISC 3115 MY9
                                    Prof C.Zeigler
                                    HW7
******************************************************************************/

import java.util.ArrayList;

	public class Bank {
	// private data member 
	private ArrayList<Account> myAccount; 
	
	// declaring an arrayList of type Account
	private static double totalAmountInSavingsAccts;
	private static double totalAmountInCheckingAccts;
	private static double totalAmountInCDAccts;
	private static double totalAmountInAllAccts;
	
	// setter
	public void setAcct(Account newAccount) {
		myAccount.add(newAccount);
	}
	public void setTotalSavings(double savings) {
		totalAmountInSavingsAccts = savings;
	} 
	public void setTotalChecking(double savings) {
		totalAmountInCheckingAccts = savings;
	} 
	public void setTotalCD(double savings) {
		totalAmountInCDAccts = savings;
	} 
	public void setTotal(double savings) {
		totalAmountInAllAccts = savings;
	} 
	// getter 	
	public Account getAccount(int index) {
            Account account = myAccount.get(index);
            Account tempAccount;
            if(account.getAccoutType().equals("CD")) {
                    tempAccount = new CDAccount((CDAccount)account);
                    return tempAccount;
            } else if(account.getAccoutType().equals("Savings")){
                tempAccount = new SavingsAccount((SavingsAccount)account);
                return tempAccount;
                }else {
                    tempAccount = new CheckingAccount((CheckingAccount)account);
                    return tempAccount;
                }
            }
	public int getNumAccounts() {
		return myAccount.size();	
	}
	public double getTotalSavings() {
		return totalAmountInSavingsAccts;
	} 
	public double getTotalChecking() {
		return totalAmountInCheckingAccts;
	} 
	public double getTotalCD() {
		return totalAmountInCDAccts;
	} 
	public double getTotal() {
		return totalAmountInAllAccts;
	} 		
	// No-Agr constructor
	public Bank() {
		myAccount = new ArrayList<>();	
		
	}
						// Class methods creation
	/* Method addAcct()
	 * Input:
	  * newAccount :an account of class Account
	 * Process:
	 *  add a new account to the Account array List of the Bank class 
	 * Output:
	 *  add a account to the Account array
	 */
	public void addAcct(Account newAccount) {
		myAccount.add(newAccount);
		
	}
	/* Method openNewAcct()
	 * Input:
	  * newAccount: the account of class Account to add
	 * Process:
	  * add a new account the Account arrayList after checking 
	  * if the account does not exist
	 * Output:
	 *  return true if account add successfully else return false 
	 */
	public boolean openNewAcct(Account newAccount) {
		int index = findAccts(newAccount);
		if (index == -1) {
			myAccount.add(newAccount);			
			return true;
		}else {
			return false;
		}
		
	}

	/* Method deleteAcct()
	 * Input:
	  * AccountToDelet : an account of class Account
	 * Process:
	  * Remove mydeleteAccount form the account array after checking 
	  * if the account does not exist
	  *   and increment the index
	 * Output:
	 *  return true if account delete successfully else return false 
	 */
	public boolean deleteAcct(Account AccountToDelet) {
		int index = findAccts(AccountToDelet);
		if (index != -1) {
			if(myAccount.get(index).getAccountBalance() == 0.00) {
			myAccount.remove(AccountToDelet);	
			return true;
			}
		}	
			return false;
	}
	/* Method findAccts() : this a private class
	 * Input:
	  * myAccount : an account of class Account
	  * requestedAccount: The requested account number
	 * Process:
	  * look through the account array to see if the account number match
	  * with the requested one. if so return the it index else return -1
	 * Output:
	 *  return the account index if match else return -1
	 */
	private int findAccts(Account myAccountDesire) {
 		int requestedAccount1, requestedAccount2;
		for (int index = 0; index < myAccount.size(); index++) {
                    requestedAccount1 = myAccount.get(index).getAccounNum();
                    requestedAccount2 = myAccountDesire.getAccounNum();
                    if 	(requestedAccount1 == requestedAccount2) {

                            return index; 
                    }

		}return -1;
	}
 	/* Method findAcctSSN() : this is a private class
	 * Input:
	  * myAccountDesire : an account of class Account
	 * Process:
	  * look through the account ArrayList to see if the SSN matches
	  * with the one requested . if so return the it index else return -1
	 * Output:
	 *  return the account index if match else return -1
	 */
 	public int findAcctSSN(Account myAccountDesire) {
    	String requestedSSN1 , requestedSSN2;
    	for (int index = 0; index < myAccount.size(); index++) {
            requestedSSN1 = myAccount.get(index).getDepositor().getSocSecNum();
            requestedSSN2 = myAccountDesire.getDepositor().getSocSecNum();

                    if (requestedSSN1.equals(requestedSSN2)) {
                            return index;
                    }
		
    	}return -1;
	}
	 //method updateBankAccountDb
	 public void updateBankAccount(int index, Account myAccounts) {
              // myAccount information gas been updated
            myAccount.set(index, myAccounts);
	 }
	 // UpdateBankAccount in case of account Deletion
	 public void DeleteUpdateBankAccount(int index) {
		 myAccount.remove(index);
	 }
	 //additionofAmounts method()
	 /* Method addAmountstoTotal() : 
	 * Input:
	  * myAccount : an account of class Account
	  * amount: the amount for addition of
	 * Process:
	  * depending on the type of account we are going to increase
	  * associated static member variable including totalALLAccount.
	 * Output:
	 * increase the value of the static member variables
	 */
	public void addAmountstoTotal(Account myAccount, double amount){
		if(myAccount.getAccoutType().equals("Savings")) {
			totalAmountInSavingsAccts += amount;
		}
		if(myAccount.getAccoutType().equals("Checking")) {
			totalAmountInCheckingAccts +=amount;
		}
		if(myAccount.getAccoutType().equals("CD")) {
			totalAmountInCDAccts +=amount;
		}
		// add the change to the totals
		totalAmountInAllAccts += amount;
	}
	//subtraction methode()
	/* Method subAmountFromTotal() : 
	 * Input:
	  * myAccount : an account of class Account
	  * amount: the amount subtras=ction from
	 * Process:
	  * depending on the type of account we are going to decrease
	  * associated static member variable including totalALLAccount.
	 * Output:
	 * decrease the value of the static member variables
	 */
	public void subAmountFromTotal(Account myAccount, double amount) {
		if(myAccount.getAccoutType().equals("Savings")) {
			totalAmountInSavingsAccts -= amount;
		}
		if(myAccount.getAccoutType().equals("Checking")) {
			totalAmountInCheckingAccts -= amount;
		}
		if(myAccount.getAccoutType().equals("CD")) {
			totalAmountInCDAccts -= amount;
		}
		// add the change to the totals
				totalAmountInAllAccts -= amount;
			}
		
}

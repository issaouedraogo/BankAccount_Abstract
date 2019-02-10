/******************************************************************************
                                        Issa Ouedraogo
                                        CISC 3115 MY9
                                        Prof C.Zeigler
                                        HW7
******************************************************************************/

public class Transaction extends genTransaction {
	
	
	//setters
	public void setTransTyp(String type) {
		transType = type;
	}
	public void setTransAmount(double amount) {
		transAmount = amount;
	}
	public void setTransStat(String stat) {
		transStat = stat;
	}
	// getters
	public String getTransTyp() {
		return transType;
	}
	public double gettransAmount() {
		return transAmount;
	}
	public String getTransStat() {
		return transStat;
	}
	// Not-Argument Constructor
	public Transaction() {
		super();
	}
	// Argument Constractor
	public Transaction(String type, double amount, String stat) {
		super(type, amount, stat);
	}
	//copy Constructor
	public Transaction(Transaction myTrans) {
		super(myTrans);
	}
	//toString() Method
	public String toString() {
		String str = String.format("%18s \t\t\t$%-10.2f %20s",
                                            transType,
                                            transAmount,
                                            transStat);
            return str;
	}
	//equal() Method 
	public  boolean equals(Transaction myTransac) {
		if(transType.equals(myTransac.transType)) {
			return true;
		}else {
			return false;
		}
	}
}

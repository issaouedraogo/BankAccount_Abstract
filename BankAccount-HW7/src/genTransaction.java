/*****************************************************************************
                                Issa Ouedraogo
                                CISC 3115 MY9
                                Prof C.Zeigler
                                HW7
*****************************************************************************/
public abstract class genTransaction {
	// private data member
	protected String transType;
	protected double transAmount;
	protected String transStat;
		
	//setters
	public abstract void setTransTyp(String type);
	public abstract void setTransAmount(double amount) ;
	public abstract void setTransStat(String stat);
	
	// getters
	public abstract String getTransTyp();
	public abstract double gettransAmount(); 
	public abstract String getTransStat();
	
	// No-Argument Constructor
	public genTransaction() {
		transType = "";
		transAmount = 0.00;
		transStat = " ";
	}
	// Argument Constractor
	public genTransaction(String type, double amount, String stat) {
		transType = type;
		transAmount = amount;
		transStat = stat;
	}
	//copy Constructor
	public genTransaction(genTransaction myTrans) {
		transType = myTrans.transType;
		transAmount = myTrans.transAmount;
		transStat = myTrans.transStat;
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
	public  boolean equals(genTransaction myTransac) {
		if(transType.equals(myTransac.transType)) {
			return true;
		}else {
			return false;
		}
	}
}

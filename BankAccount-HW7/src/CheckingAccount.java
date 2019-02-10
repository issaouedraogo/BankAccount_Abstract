
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
/******************************************************************************
                                Issa Ouedraogo
                                CISC 3115 MY9
                                Prof C.Zeigler
                                HW7									 
****************************************************************************/
import java.text.SimpleDateFormat;
public class CheckingAccount extends Account{
	
public CheckingAccount() {
        super();

}
// Arg-Contructor
public CheckingAccount(Depositor D, int string, String acTyp, 
        double balance, String status) {
                super( D, string, acTyp, balance, status);

}
public CheckingAccount(Account myAccount) {
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
}

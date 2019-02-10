/*****************************************************************************
                                Issa Ouedraogo
                                CISC 3115 MY9
                                Prof C.Zeigler
                                HW7
*****************************************************************************/
public abstract class genDepositor {
	// Private Data Members 
        protected Name names ;
        protected String socSecNum;

        // abstract setter 
        public abstract void setName(Name n);
        public abstract void setSocSecNum(String str);
        // abstract Getter 
        public abstract Name getName() ;
        public  abstract String getSocSecNum(); 

        // non Argument Constructor
        public genDepositor() {
                names = new Name();
                socSecNum = "";
        }
        // constructor with parameters
        public genDepositor(Name n, String ssn) {
                names = n;
                socSecNum = ssn;
        }
        //copy constructor 
        public genDepositor(genDepositor myDeposi) {
                names = new Name(myDeposi.names);
                socSecNum = myDeposi.socSecNum;
        }

        //.toString Method
        public String toString() {

                String str = String.format("%-11s%-7s%-10s", 
                                    names.getLastName(),
                                    names.getFirstName(),
                                    socSecNum
                                );
                return str;
        }
        // .equals() methods
        public boolean equals(genDepositor myDepositor) {
                if(socSecNum.equals(myDepositor.getSocSecNum())){
                        return true;	//socialsecurity found 
                }else {
                        return false;
                } 
    }
}

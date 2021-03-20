
public class Suspect {
	private int AFM;
	 private String firstName;
	 private String lastName;
	 private double savings;
	 private double taxedIncome;
	 private double sus;
	 Suspect(int AFM,String firstName,String lastName,double savings,double taxedIncome){
		 this.AFM=AFM;
		 setfirstName(firstName);
		 setlastName(lastName);
		 setSavings(savings);
		 settaxedIncome(taxedIncome);
		 if (taxedIncome<9000)sus=Double.MAX_VALUE;
		 else sus=savings-taxedIncome;
	 }
	 int key() {return AFM;} 
	 public String toString() {return "AFM:" + AFM + ", First Name:"  + firstName + ", Last Name:" + lastName + ", Savings:" + savings + ", " +  "Taxed Income:" + taxedIncome + ", " +  "Sus:" + sus;}
	 public String getfirstName() {return firstName;}
	 public String getlastName() {return lastName;}
	 public double getSavings() {return savings;}
	 public double getSus() {return sus;}
	 public double gettaxedIncome() {return taxedIncome;}
	 public void setfirstName(String s) {firstName=s;}
	 public void setlastName(String s) {lastName = s;}
	 public void setSavings(double s) {savings = s;}
	 public void settaxedIncome(double s) {taxedIncome = s;}
}

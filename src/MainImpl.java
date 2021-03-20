import java.util.Scanner;

public class MainImpl {
	
	static Suspect SuspInput(Scanner scannerr) {
		System.out.println("Dwse AFM");
		int AFM = scannerr.nextInt();
		scannerr.nextLine();
		System.out.println("Dwse First Name");
		String FirstName = scannerr.nextLine();
		System.out.println("Dwse Last Name");
		String LastName = scannerr.nextLine();
		System.out.println("Dwse Savings");
		double Savings = scannerr.nextDouble();
		scannerr.nextLine();
		System.out.println("Dwse Taxed Income");
		double taxedIncome = scannerr.nextDouble();
		scannerr.nextLine();
		Suspect s = new Suspect(AFM,FirstName,LastName,Savings,taxedIncome);
		return s;
	}

	public static void main(String[] args) {
		int select;
		int input1;
		double input2;
		String input;
		Suspect t;
		Suspect s;
		double Avg;
		RandomizedBST tree = new RandomizedBST();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Select Function");
	        System.out.println("-------------------------\n");
	        System.out.println("1 - Insert");
	        System.out.println("2 - Load from file");
	        System.out.println("3 - Update Savings");
	        System.out.println("4 - Search AFM");
	        System.out.println("5 - Search Last Name");
	        System.out.println("6 - Remove");
	        System.out.println("7 - Mean Savings");
	        System.out.println("8 - Print Top Suspects");
	        System.out.println("9 - Print By AFM");
	        System.out.println("0 - Quit");
			select=scanner.nextInt();
			scanner.nextLine();
			switch (select) {
			case 0:
				System.exit(0);
	        case 1:
	        	s = SuspInput(scanner);
	        	t = tree.searchByAFM(s.key());
	        	if (t==null)tree.insert(s);
	            break;
	        case 2:
	        	System.out.println("Dwse Filename");
	            input = scanner.nextLine();
	            tree.load(input);
	            break;
	        case 3:
	        	System.out.println("Dwse AFM");
	        	input1=scanner.nextInt();
	        	scanner.nextLine();
	        	System.out.println("Dwse Savings");
	    		input2 = scanner.nextDouble();
	    		scanner.nextLine();
	    		tree.updateSavings(input1, input2);
	            break;
	        case 4:
	        	System.out.println("Dwse AFM");
	        	input1=scanner.nextInt();
	        	scanner.nextLine();
	        	t = tree.searchByAFM(input1);
	        	if (t==null)System.out.println("De Bretthike");
	        	else System.out.println(t);
	            break;
	        case 5:
	        	System.out.println("Dwse Last Name");
	            input = scanner.nextLine();
	            tree.searchByLastName(input);
	        	break;
	        case 6:
	        	System.out.println("Dwse AFM");
	        	input1=scanner.nextInt();
	        	scanner.nextLine();
	        	tree.remove(input1);
	        	break;
	        case 7:
	        	Avg = tree.getMeanSavings();
	        	System.out.println(Avg);
	        	break;
	        case 8:
	        	System.out.println("Dwse k pio Ypoptous Katathetes (k<=N)");
	        	input1=scanner.nextInt();
	        	tree.printTopSuspects(input1);
	        	break;
	        case 9:
	        	tree.printByAFM();
	        	break;
	        default:
	            System.out.println("Not an option.");
	    }
		}

	}

}

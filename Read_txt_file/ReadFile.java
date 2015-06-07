import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ReadFile {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		System.out.println("please enter a text file for example test.txt :");
		// get input file from the user
		Scanner keyboard = new Scanner(System.in);
		String input = keyboard.nextLine();
		File file = new File(input);
		Scanner fileScan = new Scanner(file);

		// calculate how many lines of the text file to assign
		// make an array of package objects with that size
		int arraySize = 0;
		while (fileScan.hasNext()) {
			String l = fileScan.nextLine();
			arraySize++;
		}
		// make an array of package objects with arraySize
		Package[] packList = new Package[arraySize];
		Scanner fileRead = new Scanner(file);

		// countLines to assign to index of package object array
		int countLines = 0;
		while (fileRead.hasNext()) {
			// read line by line
			String line = fileRead.nextLine();

			// check if line is package or nuclide
			String[] check = line.split(":");
			if (check[0].equals("Package")) {
				String delims = "[:=,=]+";
				// split lines into tokens
				String[] tokens = line.split(delims);
				String packageName = tokens[4];
				String activityUnit = tokens[2];
				String nuclideName = "";
				int lld = 0;
				double activity = 0.00;
				// use contructor in Package class to assign an object to
				// packList
				packList[countLines] = new Package(packageName, activityUnit,
						nuclideName, lld, activity);
				// increment
				countLines++;
			}// end if
            
			
			// if the line is nuclide line
			if (check[0].equals("Nuclide")) {
				String delims = "[:=,=,=]+";
				
				// split that line into tokens
				String[] tokens = line.split(delims);

				String packageName = "";
				String activityUnit = "";
				String nuclideName = tokens[6];
				// converting String  to Integer
				int lld = Integer.parseInt(tokens[2]);
				// converting String to Double
				double activity = Double.parseDouble(tokens[4]);
				
				// use contructor in Package class to assign an object to
				// packList
				packList[countLines] = new Package(packageName, activityUnit,
						nuclideName, lld, activity);
				// increment 
				countLines++;
			}// end if
		}// end while

		
		// set package name for missing packageName objects in packList array
		// objects
		for (int j = 1; j < countLines; j++) {
			if (packList[j].getPackageName().equals("")) {
				packList[j].setPackageName(packList[j - 1].getPackageName());
			}// end if
			
		}// end for

		
		// set activity unit for missing activityUnit objects in packList array
		// objects
		for (int i = 1; i < countLines; i++) {
			if (packList[i].getActivityUnit().equals("")) {
				packList[i].setActivityUnit(packList[i - 1].getActivityUnit());
			}// end if
		}// end for

		
		
		// converting all TBq and cCi units of packages to Curies
		for (Package p : packList) {
			if (p.getActivityUnit().equals("TBq")) {
				p.setActivity(p.getActivity() * 27.027027);
			}
			if (p.getActivityUnit().equals("mCi")) {
				p.setActivity(p.getActivity() * 0.001);
			}
			
			
		}// end for

		
		// calculations Non-LLD Total Activity(Ci) and LLD Total Activity(Ci)
		double nonLLD = 0.00;
		double LLD = 0.00;
		for (Package n : packList) {
			if (n.getLLD() == 0) {
				nonLLD += n.getActivity();
			}
			if (n.getLLD() == 1) {
				LLD += n.getActivity();
			}
		}// end for
		
		// formating 
		DecimalFormat fmt = new DecimalFormat("0.00E00");
        // output to the screen
		System.out.println("None-LLD Total Activity(Ci)  "
				+ " 	LLD Total Activity(Ci)	" + "	None-LLD/LLD Ratio");
		System.out.println(" " + fmt.format(nonLLD) + "			" + fmt.format(LLD)
				+ "			" + fmt.format(nonLLD / LLD));

		
		
		// Writing data to OutPut.txt file
		String output= "output.txt";
		PrintWriter writer = null;
		FileWriter fw = new FileWriter(output,false);
		writer = new PrintWriter(fw);
		writer.println("None-LLD Total Activity(Ci)  "
				+ " 	LLD Total Activity(Ci)	" + "	None-LLD/LLD Ratio");
		writer.println(" " + fmt.format(nonLLD) + "		   	     		" + fmt.format(LLD)
				+ "					    " + fmt.format(nonLLD / LLD));
		writer.close();
		
		
	}// end main
}// end class
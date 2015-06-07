import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// getting a xml file from user
		System.out.println("please enter a xml file:");
		// get input file from the user
		Scanner keyboard = new Scanner(System.in);
		String filename = keyboard.nextLine();
		
		// read xml file
        ReadXML reader = new ReadXML();
        List<Package> data = reader.readDataFromXML(filename);
        // count number of objects in data List
        int countLines = data.size();
        
       // make an array of package objects with the same number of objects in data List
        Package [] packList = new Package[countLines];
       // transfer every object in the data List to packList
       int count=0;
        for (Package package1 : data) {
			packList[count]=package1;
			count++;
		}
     // set package name for missing packageName objects in packList array
     		// objects
     		for (int j = 1; j < countLines; j++) {
     			if (null == packList[j].getPackageName()) {
     				packList[j].setPackageName(packList[j - 1].getPackageName());
     			}// end if
     			
     		}// end for

     		
     		// set activity unit for missing activityUnit objects in packList array
     		// objects
     		for (int i = 1; i < countLines; i++) {
     			if (null==packList[i].getActivityUnit()) {
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
        
        
        
        
        
        
	}

}

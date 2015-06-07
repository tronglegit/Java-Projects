
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ReadXML extends DefaultHandler{
	private List<Package> data;
	private Package pack;
	private String currentElement="";
	
	
	// parsing process
	public List<Package> readDataFromXML(String filename) throws ParserConfigurationException, SAXException, IOException{
	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser parser = factory.newSAXParser();
	parser.parse(filename, this);
		return data;
	}
	
	
	@Override
	public void startDocument() throws SAXException {
             data = new ArrayList<>();
	}
	@Override
	public void endDocument() throws SAXException {
		
	}
  @Override
public void startElement(String uri, String localName, String qName,
		Attributes attributes) throws SAXException {
	  
	  // go through all elements and get their values to make objects
	  // and add objects to data List
        currentElement = qName;
        switch (currentElement) {
		case "shipment":
			break;
		case "package":
			pack = new Package();
			String nameAsString = attributes.getValue("packagename");
			String unitAsString = attributes.getValue("activityunit");
			pack.setPackageName(nameAsString);
			pack.setActivityUnit(unitAsString);
			data.add(pack);
			break;
		case "nuclides":
			break;
		case "nuclide":
			pack = new Package();
			String nuclideName = attributes.getValue("nuclidename");
			String lld = attributes.getValue("lld");
			String activity = attributes.getValue("activity");
			pack.setNuclideName(nuclideName);
			pack.setLLD(Integer.parseInt(lld));
			pack.setActivity(Double.parseDouble(activity));
			data.add(pack);
			break;
		default:
			break;
		}
}
  @Override
public void endElement(String uri, String localName, String qName)
		throws SAXException {
	  currentElement = "";
}
  
  @Override
public void characters(char[] ch, int start, int length)
		throws SAXException {
}
  
}

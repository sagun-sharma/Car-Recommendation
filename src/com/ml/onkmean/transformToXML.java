package com.ml.onkmean;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.*;

import javax.sql.rowset.spi.XmlWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class transformToXML {
	private static String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
	private  static String url = "jdbc:odbc:Ontology";
	private static String userName="sa";
	private static String passWord="123456";
	private static Connection con;
	
	public static void readText() {
		String line="";
		try
		{
	    Scanner scan = new Scanner(new File("c:\\webBot\\Cars"));
	    Formation.collate_formation();
	    while(scan.hasNextLine()){
	    line = scan.nextLine();
	    }
		String[] allString=line.split("--");
		 FileWriter stringWriter = new FileWriter("c:\\webBot\\cars.xml");

	         XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();	
	         XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);
	         xMLStreamWriter.writeStartDocument();
	        xMLStreamWriter.writeStartElement("AutoOntology");
	        for(int x=1;x<allString.length-1;x++)
			{
			System.out.println(allString[x].trim().toString());
			if(allString[x].trim().equalsIgnoreCase("Tata")|| allString[x].trim().equalsIgnoreCase("Maruti")||allString[x].trim().equalsIgnoreCase("Hyundai")||allString[x].trim().equalsIgnoreCase("Honda")||allString[x].trim().equalsIgnoreCase("Toyota")||allString[x].trim().equalsIgnoreCase("VolksWagen")||allString[x].trim().equalsIgnoreCase("Mahindra")||allString[x].trim().equalsIgnoreCase("Ford")||allString[x].trim().equalsIgnoreCase("Chevrolet")||allString[x].trim().equalsIgnoreCase("Nissan"))
				{
					xMLStreamWriter.writeStartElement("Manufacturer");
					xMLStreamWriter.writeAttribute("CompanyName",allString[x].trim());
					xMLStreamWriter.writeAttribute("Version",allString[x+2].trim());
					xMLStreamWriter.writeAttribute("Price",allString[x+8].trim());
					xMLStreamWriter.writeStartElement("ModelName");
					xMLStreamWriter.writeCharacters(allString[x+1].trim());
					xMLStreamWriter.writeEndElement();
					
					xMLStreamWriter.writeStartElement("Engine");
					xMLStreamWriter.writeAttribute("Capacity",allString[x+3].trim());
					xMLStreamWriter.writeAttribute("HorsePower",allString[x+4].trim());
					xMLStreamWriter.writeAttribute("Torque",allString[x+5].trim());
					xMLStreamWriter.writeStartElement("GearBox");
					xMLStreamWriter.writeCharacters(allString[x+6].trim());
					xMLStreamWriter.writeEndElement();
					xMLStreamWriter.writeStartElement("TopSpeed");
					xMLStreamWriter.writeCharacters("NA");
					xMLStreamWriter.writeEndElement();
					xMLStreamWriter.writeStartElement("FuelEfficiencyInLtrs");
					xMLStreamWriter.writeCharacters(allString[x+7].trim());
					xMLStreamWriter.writeEndElement();
					xMLStreamWriter.writeStartElement("MilageKMPL");
					xMLStreamWriter.writeCharacters(allString[x+9].trim());
					xMLStreamWriter.writeEndElement();
					xMLStreamWriter.writeStartElement("Petrol");
					xMLStreamWriter.writeCharacters(allString[x+10].trim());
					xMLStreamWriter.writeEndElement();
					xMLStreamWriter.writeStartElement("Diesel");
					xMLStreamWriter.writeCharacters(allString[x+11].trim());
					xMLStreamWriter.writeEndElement();
					xMLStreamWriter.writeStartElement("CNG");
					xMLStreamWriter.writeCharacters("NA");
					xMLStreamWriter.writeEndElement();
					xMLStreamWriter.writeStartElement("LPG");
					xMLStreamWriter.writeCharacters("NA");
					xMLStreamWriter.writeEndElement();
					xMLStreamWriter.writeStartElement("Hybrid");
					xMLStreamWriter.writeCharacters("NA");
					xMLStreamWriter.writeEndElement();
					
					xMLStreamWriter.writeEndElement();
					xMLStreamWriter.writeEndElement();
					Formation.generateXML(allString[x].trim(),allString[x+2].trim(),allString[x+8].trim(),allString[x+1].trim(),allString[x+3].trim(),allString[x+4].trim(),allString[x+5].trim(),Integer.parseInt(allString[x+6].trim()),"NA",allString[x+7].trim(),allString[x+9].trim(),allString[x+10].trim(),allString[x+11].trim(),"NA","NA","NA");
				}
			}
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeEndDocument();
				xMLStreamWriter.flush();
				xMLStreamWriter.close();
			
				
		}
		catch(Exception ee)
		{
			System.out.println(ee.toString());
		}  
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readText();
	}

}

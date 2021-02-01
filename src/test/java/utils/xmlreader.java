package utils;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;


public class xmlreader {
	String fileloaction;
	public static String name;
	public xmlreader(String filepath)
	{
		fileloaction=filepath;
	}
	
public String getlocator(String nodepath) throws DocumentException, InterruptedException
{
	
	
	String locator=null;
//	String locator1=null;
	File Inputfile=new File(fileloaction);
	SAXReader xmlreader=new SAXReader();
	Document doc=xmlreader.read(Inputfile);
	name=doc.selectSingleNode(nodepath).valueOf("@name");
	System.out.println(name);
	locator=doc.selectSingleNode(nodepath).getText();
//	locator1=doc.getNodeTypeName();
//	System.out.println(locator1);
	//Log.info(locator);
	return locator;
	
}





}

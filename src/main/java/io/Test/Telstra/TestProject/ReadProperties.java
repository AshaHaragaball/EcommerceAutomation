package io.Test.Telstra.TestProject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	public  FileInputStream fis = null;
	public  FileOutputStream fos =null;
	private Properties p = null;
	
	public String getData(String key) throws IOException{
		
		FileInputStream fis = new FileInputStream("src/main/resource/config.properties");
		
		 p = new Properties();
		p.load(fis);
		return p.getProperty(key);		
	}
		
}

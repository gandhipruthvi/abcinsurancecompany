package dao;

import java.io.*;
import java.util.Properties;

import javax.servlet.ServletContext;

public class EnvProperty{
	public static Properties props = null;
	
	public EnvProperty() throws IOException {
		if(props == null) {
			props = new Properties();
			InputStream is = this.getClass().getResourceAsStream("db.properties");
	    	if(is != null) {
	    	    props.load(is);
	    	}else {
	    		System.out.println("No props");
	    	}
		}
	}
	
}
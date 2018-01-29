package com.exa.reader;
import java.awt.color.ProfileDataException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigReader {
	static Logger log =LoggerFactory.getLogger(ConfigReader.class.getName());
	static FileInputStream fis ;
	public static Properties ReadConfig(String path)
	{
		log.debug("Starting reading config files ..." );
		
		Properties properties = new Properties();
		try {
			fis = new FileInputStream(path);
			properties.load(fis);
			fis.close();
		} catch (FileNotFoundException e) 
		{
			log.error(e.getMessage());
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			log.error("Error:" + sw.toString());
			throw new ProfileDataException(path + " not found!");
		} catch (IOException e) 
		{
			log.error(e.getMessage());
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			log.error("Error:" + sw.toString());
			throw new ProfileDataException("Error while reading " + path +". Check StackTrace for more info.");
		}
		catch (Exception e) 
		{
			log.error(e.getMessage());
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			log.error("Error:" + sw.toString());
			throw new ProfileDataException("Error while reading " + path +". Check StackTrace for more info.");
		}
		log.debug("Read Configuration ..." );
		return properties;
	}
	

}

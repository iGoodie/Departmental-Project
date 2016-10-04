package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Created to make I/O operations easier.
 * @author Anılcan Metinyurt
 */
public class FileUtils 
{
	private static String externalDataPath = System.getProperty("user.dir")+"\\data";
	
	/*Checks if the files exist or not*/
	public static boolean fileExists(String path) { return new File(path).exists(); }
	public static boolean fileExists(File file)
	{
		return file.exists();
	}

	/*Creates empty file*/
	public static void createFile(String path) { createFile(new File(path)); }
	public static void createFile(File file)
	{
		try
		{
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		catch(IOException e)
		{
			System.out.println("File creation exception. "+e.getMessage());
		}
	}
	
	/*Writes String data to file or path*/
	public static void writeString(String path, String data) { writeString(new File(path), data); }
	public static void writeString(File file, String data)
	{
		try
		{
			if(!file.exists()) createFile(file);
			
			FileWriter fw = new FileWriter(file);
			fw.write(data);
			fw.close();
		}
		catch(IOException e)
		{
			System.out.println("Exception while writing String data. "+e.getMessage());
		}
	}
	
	/*Reads String data from file or path*/
	public static String readString(String path) { return readString(new File(path)); }
	public static String readString(File file)
	{		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			
			while(line!=null)
			{
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			
			br.close();
			return sb.toString();
		}
		catch(IOException e)
		{
			System.out.println("Exception while reading String. "+e.getMessage());
			System.out.println("Returning empty String.");
			return "";
		}		
	}

	/*Writes Properties file to path*/
	public static void writeProperties(String path, Properties data) { writeProperties(new File(path), data); }
	public static void writeProperties(File file, Properties data)
	{
		try { data.store(new FileOutputStream(file), ""); }
		catch(IOException e) { System.out.println("Exception while writing Properties file. "+e.getMessage()); }
	}
	
	/*Reads Properties file from path*/
	public static Properties readProperties(String path) { return readProperties(new File(path)); }
	public static Properties readProperties(File file)
	{
		try
		{
			Properties data = new Properties();
			data.load(new FileInputStream(file));
			return data;
		}
		catch(IOException e)
		{
			System.out.println("Exception while reading Property. "+e.getMessage());
			System.out.println("Returning empty Properties.");
			return new Properties();
		}
	}
	
	/*External Path specific methods*/
	public static void createExternalFile(String name) { createFile(externalDataPath+"\\"+name); }
	
	public static void writeExternalString(String fileName, String data) { writeString(externalDataPath+"\\"+fileName, data); }
	
	public static String readExternalString(String fileName) { return readString(externalDataPath+"\\"+fileName); }

	public static void writeExternalProperties(String fileName, Properties data) { writeProperties(externalDataPath+"\\"+fileName, data); }

	public static Properties readExternalProperties(String fileName) { return readProperties(externalDataPath+"\\"+fileName); }
}
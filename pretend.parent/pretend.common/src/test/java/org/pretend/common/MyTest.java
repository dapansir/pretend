package org.pretend.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MyTest {

	public static void main(String[] args) {
		
		File tables = new File("D:\\test\\tables.txt");
		BufferedReader bis;
		try {
			bis = new BufferedReader(new FileReader(tables));
			String tableName = bis.readLine();
			File file = new File("D:\\test\\test.txt");
			if(!file.exists()){
				file.createNewFile();
			}
			String content = "";
			FileOutputStream fos = new FileOutputStream(file);
			StringBuilder xml = new StringBuilder();
			int id = 3;
			xml.append("<o:SPdoODBCSelectionObject Id=\"o1435\">\n");
			xml.append("    <a:ObjectID>356ED208-2E27-4B05-95D5-8259F50912C7</a:ObjectID>\n");
			xml.append("    <a:Name>tableName</a:Name>\n");
			xml.append("    <a:CreationDate>0</a:CreationDate>\n");
			xml.append("    <a:Creator/>\n");
			xml.append("    <a:ModificationDate>0</a:ModificationDate>\n");
			xml.append("    <a:Modifier/>\n");
			xml.append("    <a:Owner>FTMS_YZ</a:Owner>\n");
			xml.append("</o:SPdoODBCSelectionObject>\n");
			while(tableName != null){
				content = xml.toString().replace("o1435", "0"+id).replace("tableName", tableName);
				fos.write(content.getBytes());
				fos.flush();
				tableName = bis.readLine();
				id++;
			}
			bis.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	
		
		
		

	}

}

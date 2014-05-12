package com.server.learning.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.core.MultivaluedMap;

public class FileUtils {
	
	/**
	 * header sample
	 * {
	 * 	Content-Type=[image/png], 
	 * 	Content-Disposition=[form-data; name="file"; filename="filename.extension"]
	 * }
	 **/
	public static String getFileName(MultivaluedMap<String, String> header) {
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		 
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
 
				String[] name = filename.split("=");
 
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return Constant.UNKNOWN_FILE;
	}
	
	public static void saveFile(InputStream inputStream, String location) {
		try {
			OutputStream outputStream = new FileOutputStream(new File(location));
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			
			outputStream.flush();
			outputStream.close();
			
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

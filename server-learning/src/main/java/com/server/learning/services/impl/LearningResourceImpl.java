package com.server.learning.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.server.learning.form.FileUploadForm;
import com.server.learning.services.LearningResource;
import com.server.learning.util.FileUtils;

public class LearningResourceImpl implements LearningResource{
	
	private final String UPLOADED_FILE_PATH = "C:\\uploadedFile\\";

	@Override
	public String uploadTugas(@Context HttpServletRequest context,
			@MultipartForm MultipartFormDataInput dataInput,
			@QueryParam("idSiswa") String idSiswa,
			@QueryParam("idTugas") String idTugas) {
		
		File uploadDir = new File(UPLOADED_FILE_PATH);
		if(!uploadDir.exists()) uploadDir.mkdirs();
		
		String fileName = "";
		Map<String, List<InputPart>> uploadForm = dataInput.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("uploaded_file");
		
		System.out.println(String.valueOf(inputParts.size()));
		
		for(InputPart inputPart : inputParts) {
			try {
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = FileUtils.getFileName(header);
				
				//convert the uploaded file to inputstream
				InputStream inputStream = inputPart.getBody(InputStream.class,null);
				
				FileUtils.saveFile(inputStream, UPLOADED_FILE_PATH + fileName);
            } catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("filename = "+fileName);
		
		String result = idSiswa + " " + idTugas;
		
		return result;
	}
	
	public String uploadTugas2(@Context HttpServletRequest context, 
			@MultipartForm FileUploadForm form) {
		
		String fileName = form.getFileName() == null ? "Unknown" : form.getFileName() ;
        
        String completeFilePath = UPLOADED_FILE_PATH + fileName;
        try
        {
            //Save the file
            File file = new File(completeFilePath);
              
            if (!file.exists())
            {
                file.createNewFile();
            }
      
            FileOutputStream fos = new FileOutputStream(file);
      
            fos.write(form.getFileData());
            fos.flush();
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
		return null;
	}

}

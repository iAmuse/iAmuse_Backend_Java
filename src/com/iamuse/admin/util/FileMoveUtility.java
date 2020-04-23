package com.iamuse.admin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class FileMoveUtility {
	
	
	public static void copyFileFromSourceToDestinationFiles(File source, File dest)throws IOException {
		Files.copy(source.toPath(), dest.toPath());
	}
	
	public static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	    finally {
	        is.close();
	        os.close();
	    }
	}
    public static void main(String[] args)
    {
    	try{
    		File source =new File("C:\\Users\\Abhishek\\Desktop\\1.jpg");
     	   File dest =new File("C:\\Users\\Abhishek\\Desktop\\folderb\\1.jpg");
     	  copyFileFromSourceToDestinationFiles(source, dest);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }


}

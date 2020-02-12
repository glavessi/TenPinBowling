package com.challenge.proimpl;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class File {
	
	private StringBuilder ReadedString;
	
	public StringBuilder getReadedString() {
		return ReadedString;
	}

	public void setReadedString(String line) {
		if(ReadedString == null)
			ReadedString = new StringBuilder();
		
		if(line != "")
			ReadedString.append(line + "\n");
	}

	public File() {
		OpenFile();
	}
	
	private final void OpenFile() {
		FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
	    dialog.setMode(FileDialog.LOAD);
	    dialog.setVisible(true);
	    
	    String filepath = (dialog.getDirectory() == null ? "":dialog.getDirectory()).toString() 
	    		+ (dialog.getFile() == null ? "":dialog.getFile().toString());
	    
	    boolean ValidFile = filepath.substring(filepath.lastIndexOf(".") + 1).contains("txt");
	    
	    
	    if(filepath != "" && ValidFile) {
	    	ReadFile(filepath);
	    }
	    else {
	    	System.out.println("No valid file selected.");
	    	System.exit(0);
	    }
	}

	private final void ReadFile(String filepath) {
		
	    try (Stream<String> stream = Files.lines(Paths.get(filepath))) 
	    {
	        stream.forEach((s) -> setReadedString(s.trim()));
	    }
	    catch (IOException e) 
	    {			
			System.out.println(e.getMessage()); 
		}
	}
	
}

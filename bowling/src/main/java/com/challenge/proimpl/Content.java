package com.challenge.proimpl;

import java.util.regex.Pattern;

public class Content {

	public static final boolean Validate(StringBuilder StringContent) {
				
		Pattern pattern = Pattern.compile("\\d+");		
		
		if(StringContent != null && StringContent.length() > 0) {
			int i = 0;
			String[] lines = StringContent.toString().split("\n");
			for(String s : lines) 
			{
				String[] fields = s.split(" ");
				if(fields.length == 2) 
				{
					if(pattern.matcher(fields[1]).matches()) 
					{
						if(Integer.parseInt(fields[1]) > 10) 
						{
							System.out.println("Pinfall must be in range [0 - 10].");
							break;
						}
					}
					else if(fields[1].equals("F")) 
					{
						
					}
					else 
					{
						System.out.println("Incorrect value for pinfall found.");
						break;
					}
				}
				else 
				{
					System.out.println("The file does not contain valid data.");
					break;
				}
				i++;
			}
			
			if(i == lines.length)
				return true;
		}
		else 
		{
			System.out.println("File is empty.");
		}
		
		return false;
	}
}

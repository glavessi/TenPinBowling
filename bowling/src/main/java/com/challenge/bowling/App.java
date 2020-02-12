package com.challenge.bowling;

import com.challenge.proimpl.BuildGame;
import com.challenge.proimpl.Content;
import com.challenge.proimpl.File;
import com.challenge.proimpl.Score;

/**
 * Created by: Gillian Gabriel Sosa.
 * 2020/02/11
 */
public class App 
{
    public static void main( String[] args )
    {
    	File file = new File();
		if(Content.Validate(file.getReadedString())) {
			BuildGame build = new BuildGame(file.getReadedString());
			Score score = new Score(build.dto.getlPlayer());
			score.Calculate();
			System.out.println(score.AssemblePrintableScore().toString());
		}
		
		System.exit(0);
    }
}

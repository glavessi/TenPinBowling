package com.challenge.dto;

import java.util.ArrayList;
import java.util.List;

import com.challenge.proimpl.Player;


public class BuildGameDTO {
	
	public BuildGameDTO() {
		lPlayer = new ArrayList<Player>();		
	}

	public StringBuilder ScoreContent;
	private List<Player> lPlayer;	
	public Boolean Skip;
	
	public List<Player> getlPlayer() {
		return lPlayer;
	}

	public void setlPlayer(Player p) {
		if(this.lPlayer == null)
			this.lPlayer = new ArrayList<Player>();
		
		this.lPlayer.add(p);
	}
	
}

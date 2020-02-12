package com.challenge.dto;

import java.util.ArrayList;
import java.util.List;

import com.challenge.proimpl.Frame;
import com.challenge.proimpl.Plays;

public class PlayerDTO {
	
	public PlayerDTO() {
		this.lFrame = new ArrayList<Frame>();
		this.plays = new ArrayList<Plays>();
	}

	private String player;
	private List<Plays> plays;
	private int TotalPlaysMade;
	private List<Frame> lFrame;

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public List<Plays> getPlays() {
		return plays;
	}

	public void setPlays(Plays plays) {
		if(this.plays == null)
			this.plays = new ArrayList<Plays>();
		
		this.plays.add(plays);
	}

	public int getTotalPlaysMade() {
		return TotalPlaysMade;
	}

	public void setTotalPlaysMade(int totalPlaysMade) {
		TotalPlaysMade = totalPlaysMade;
	}
	
	public List<Frame> getlFrame() {
		return lFrame;
	}

	public void setlFrame(Frame f) {
		if(this.lFrame == null)
			this.lFrame = new ArrayList<Frame>();
		
		this.lFrame.add(f);
	}
	
}

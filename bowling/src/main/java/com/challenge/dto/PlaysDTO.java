package com.challenge.dto;

public class PlaysDTO {
	
	public PlaysDTO() {		
	}
	
	private String DownedPins;	
	private int pos;

	public String getDownedPins() {
		return DownedPins;
	}

	public void setDownedPins(String downedPins) {
		DownedPins = downedPins;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	
}

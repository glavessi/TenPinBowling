package com.challenge.interfaces;

import java.util.List;

import com.challenge.dto.BuildGameDTO;
import com.challenge.proimpl.Frame;
import com.challenge.proimpl.Player;
import com.challenge.proimpl.Plays;



public interface Frames {

	static final int MAX_FRAMES = 10;
	static final int strikes = 3;
	static final int spares = 2;
	static final String PINFOUL = "F";
	static final int PINSTRIKE = 10;
	static final String SPARE_LETTER = "/";
	static final String STRIKE_LETTER = "X";	

	Boolean isStrike(int pin);

	Boolean isSpare(int pin1, int pin2);

	Boolean isFoul(String pin);

	void Miss(Frame f);

	void DoStrikesRule(List<Plays> plays, Frame f, int pos, Player p);

	void DoSpareRule(int firstPin, int nextpin, List<Plays> plays, Frame f, int pos, BuildGameDTO dto, Player p);

	void DoNormalRule(int firstPin, int nextpin, Frame f, BuildGameDTO dto, Player p, List<Plays> plays, int pos);

	void DoNormalRule(String firstPin, String nextpin, Frame f, BuildGameDTO dto, Player p, List<Plays> plays, int pos);

}

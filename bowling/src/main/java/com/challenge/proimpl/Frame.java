package com.challenge.proimpl;

import java.util.ArrayList;
import java.util.List;

import com.challenge.dto.BuildGameDTO;
import com.challenge.interfaces.Frames;

public class Frame implements Frames {

	public List<Integer> sets;
	public int currentFrame;
	public int ScorePerFrame;	

	public Frame() {
		sets = new ArrayList<Integer>();		
	}

	public Frame(int current) {
		sets = new ArrayList<Integer>();		
		currentFrame = current;
	}

	@Override
	public Boolean isStrike(int pin) {
		if (pin == PINSTRIKE)
			return true;

		return false;
	}

	@Override
	public Boolean isSpare(int pin1, int pin2) {

		Boolean spare = pin1 + pin2 == 10;

		return spare;
	}

	@Override
	public Boolean isFoul(String pin) {

		if (pin.equals(PINFOUL))
			return true;

		return false;
	}

	@Override
	public void Miss(Frame f) {
		f.sets.add(-1);
	}

	@Override
	public void DoStrikesRule(List<Plays> plays, Frame f, int pos, Player p) {
		int i = 0;
		while (i < Frames.strikes) {
			int next = pos + i;
			Plays obj = plays.get(next);
			if (isFoul(obj.dto.getDownedPins())) {
				Miss(f);
			} else {
				f.sets.add(Integer.parseInt(obj.dto.getDownedPins()));
			}
			i++;
		}
		p.dto.setlFrame(f);
	}

	@Override
	public void DoSpareRule(int firstPin, int nextpin, List<Plays> plays, Frame f, int pos, BuildGameDTO Builddto,
			Player p) {

		f.sets.add(firstPin);
		f.sets.add(nextpin);

		int sparePosition = pos + Frames.spares;
		Plays obj = plays.get(sparePosition);
		if (isFoul(obj.dto.getDownedPins())) {
			Miss(f);
		} else {
			f.sets.add(Integer.parseInt(obj.dto.getDownedPins()));
		}
		p.dto.setlFrame(f);
		Builddto.Skip = true;
	}

	@Override
	public void DoNormalRule(int firstPin, int nextpin, Frame f, BuildGameDTO Builddto, Player p, List<Plays> plays,
			int pos) {

		f.sets.add(firstPin);
		f.sets.add(nextpin);
		if (f.currentFrame == MAX_FRAMES - 1) {
			int last = pos + Frames.spares;
			Plays obj = plays.get(last);
			if (isFoul(obj.dto.getDownedPins())) {
				Miss(f);
			} else {
				f.sets.add(Integer.parseInt(obj.dto.getDownedPins()));
			}
		}
		p.dto.setlFrame(f);
		Builddto.Skip = true;
	}

	@Override
	public void DoNormalRule(String firstPin, String nextpin, Frame f, BuildGameDTO Builddto, Player p,
			List<Plays> plays, int pos) {

		Boolean FirstFoul = false;
		Boolean NextFoul = false;

		if (isFoul(firstPin)) {
			Miss(f);
			FirstFoul = true;
		} else {
			f.sets.add(Integer.parseInt(firstPin));
		}

		if (isFoul(nextpin)) {
			Miss(f);
			NextFoul = true;
		} else {
			f.sets.add(Integer.parseInt(nextpin));
		}

		if (f.currentFrame == MAX_FRAMES - 1) {
			int last = pos + Frames.spares;
			Plays obj = plays.get(last);
			if (isFoul(obj.dto.getDownedPins())) {
				Miss(f);
			} else {
				f.sets.add(Integer.parseInt(obj.dto.getDownedPins()));
			}
		}

		p.dto.setlFrame(f);

		if (FirstFoul && NextFoul)
			Builddto.Skip = false;
		else
			Builddto.Skip = true;
	}

}

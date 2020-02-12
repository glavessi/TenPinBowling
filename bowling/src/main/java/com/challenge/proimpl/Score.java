package com.challenge.proimpl;

import java.util.List;

import com.challenge.interfaces.Builder;
import com.challenge.interfaces.Scores;

public class Score implements Scores, Builder {

	private List<Player> players;
	private StringBuilder sb;

	public Score(List<Player> players) {
		this.players = players;
	}

	@Override
	public void Calculate() {
		players.forEach(p -> {
			p.dto.getlFrame().forEach(f -> {
				f.sets.forEach(s -> {
					if (s < 0)
						s = 0;

					f.ScorePerFrame += s;
				});
				SumScores(f, p);
			});
		});
	}

	@Override
	public StringBuilder AssemblePrintableScore() {
		sb = new StringBuilder();
		PrintFrames();
		PrintPlays();

		return sb;
	}

	private void PrintPlays() {
		StringBuilder sb = new StringBuilder();
		for (Player p : players) {
			sb.append(LINEJUMP);
			sb.append(p.dto.getPlayer());
			sb.append(LINEJUMP);
			sb.append("Pinfalls");
			sb.append(GetPinfalls(p.dto.getPlays()));
			sb.append(LINEJUMP);
			sb.append("Score");
			for (Frame f : p.dto.getlFrame()) {
				sb.append(BLANK_SPACE).append(BLANK_SPACE).append(f.ScorePerFrame);
			}
		}

		this.sb.append(sb);
	}

	private void PrintFrames() {
		StringBuilder sb = new StringBuilder();
		sb.append("Frame").append(BLANK_SPACE);
		for (int i = 1; i <= Frame.MAX_FRAMES; i++) {
			sb.append(BLANK_SPACE).append(BLANK_SPACE).append(" ").append(i);
		}

		this.sb.append(sb);
	}

	public String GetPinfalls(List<Plays> plays) {

		StringBuilder sb = new StringBuilder();
		
		for (Plays p : plays) {

			if (p.dto.getDownedPins().equals(String.valueOf(Frame.PINSTRIKE))) {
				sb.append(BLANK_SPACE).append(BLANK_SPACE).append(Frame.STRIKE_LETTER).append(" ");
			} else if (p.dto.getDownedPins().equals("F")) {
				sb.append(BLANK_SPACE).append(Frame.PINFOUL);
			} else {
				sb.append(BLANK_SPACE).append(p.dto.getDownedPins());
			}
		}

		return sb.toString();
	}

	private void SumScores(Frame f, Player p) {

		if (f.currentFrame < Frame.MAX_FRAMES && f.currentFrame > 0) {
			f.ScorePerFrame += p.dto.getlFrame().get(f.currentFrame - 1).ScorePerFrame;
		}
	}

}

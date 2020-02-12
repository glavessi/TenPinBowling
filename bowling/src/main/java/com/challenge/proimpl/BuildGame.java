package com.challenge.proimpl;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.challenge.dto.BuildGameDTO;
import com.challenge.interfaces.ContentParser;


public class BuildGame implements ContentParser {

	public BuildGameDTO dto;

	public BuildGame(StringBuilder s) {
		dto = new BuildGameDTO();
		dto.ScoreContent = s;
		Parse();
	}

	@Override
	public void Parse() {
		String[] lines = dto.ScoreContent.toString().split("\n");
		for (String s : lines) {
			String[] fields = s.split(" ");
			AssignValues(fields);
		}
		BuildFrames();
	}
	
	private void AssignValues(String[] fields) {

		if (dto.getlPlayer().stream().anyMatch(p -> p.dto.getPlayer().equals(fields[0]))) {
			AddToPlayer(fields);
		} else {
			NewPlayer(fields);
		}
	}

	private void AddToPlayer(String[] fields) {
		Plays play = new Plays();
		play.dto.setDownedPins(fields[fields.length - 1]);
		Stream<Player> filtered_data = dto.getlPlayer().stream().filter(p -> p.dto.getPlayer().equals(fields[0]));
		filtered_data.forEach(player -> {
			int pos = player.dto.getPlays().size() - 1;
			int playsDone = player.dto.getTotalPlaysMade() + 1;
			player.dto.setPlays(play);
			play.dto.setPos(pos + 1);
			player.dto.setTotalPlaysMade(playsDone);
		});
	}

	private void NewPlayer(String[] fields) {
		Player obj = new Player();
		Plays play = new Plays();

		play.dto.setDownedPins(fields[fields.length - 1]);
		play.dto.setPos(0);
		obj.dto.setPlayer(fields[0]);
		obj.dto.setPlays(play);
		obj.dto.setTotalPlaysMade(1);
		dto.setlPlayer(obj);
	}

	private void BuildFrames() {

		Pattern pattern = Pattern.compile("\\d+");

		if (dto.getlPlayer().size() > 0) {
			dto.getlPlayer().forEach(p -> {

				dto.Skip = false;
				AtomicInteger FrameNumber = new AtomicInteger(0);

				p.dto.getPlays().forEach(play -> {
					if (FrameNumber.get() < Frame.MAX_FRAMES) {
						if (!dto.Skip) {

							Frame frame = new Frame(FrameNumber.getAndIncrement());
							if (pattern.matcher(play.dto.getDownedPins()).matches()
									&& pattern.matcher(p.dto.getPlays().get(play.dto.getPos() + 1).dto.getDownedPins())
											.matches()) {
								int currentpin = Integer.parseInt(play.dto.getDownedPins());
								int nextpin = Integer
										.parseInt(p.dto.getPlays().get(play.dto.getPos() + 1).dto.getDownedPins());
								if (frame.isStrike(currentpin)) {
									frame.DoStrikesRule(p.dto.getPlays(), frame, play.dto.getPos(), p);
								} else if (frame.isSpare(currentpin, nextpin)) {
									frame.DoSpareRule(currentpin, nextpin, p.dto.getPlays(), frame, play.dto.getPos(),
											dto, p);
								} else {
									frame.DoNormalRule(currentpin, nextpin, frame, dto, p, p.dto.getPlays(),
											play.dto.getPos());
									return;
								}
							} else {
								frame.DoNormalRule(play.dto.getDownedPins(),
										p.dto.getPlays().get(play.dto.getPos() + 1).dto.getDownedPins(), frame, dto, p,
										p.dto.getPlays(), play.dto.getPos());
							}
						} else {
							dto.Skip = false;
						}
					}
				});
			});
		}
	}

}

package com.sourabh.ipldashboard.processor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sourabh.ipldashboard.data.MatchInput;
import com.sourabh.ipldashboard.model.Match;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

  @Override
  public Match process(final MatchInput matchInput) throws Exception {
    Match match = new Match();
    match.setId(Long.parseLong(matchInput.getId()));
    match.setCity(matchInput.getCity());
    match.setDate(LocalDate.parse(matchInput.getDate()));
    match.setPlayerOfMatch(matchInput.getPlayer_of_match());

    // Set team 1 and team 2
    String firstInningsTeam, secondInningsTeam;
    if ("bat".equalsIgnoreCase(matchInput.getToss_decision())) {
      firstInningsTeam = matchInput.getToss_winner();
      secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2()
          : matchInput.getTeam2();
    } else {
      firstInningsTeam = matchInput.getToss_winner();
      secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2()
          : matchInput.getTeam2();
    }
    match.setTeam1(firstInningsTeam);
    match.setTeam2(secondInningsTeam);

    match.setTossWinner(matchInput.getToss_winner());
    match.setTossDecision(matchInput.getToss_decision());
    match.setMatchWinner(matchInput.getWinner());
    match.setResult(match.getResult());
    match.setResultMargin(match.getResultMargin());
    match.setUmpire1(match.getUmpire1());
    match.setUmpire2(match.getUmpire2());

    return match;
  }

}
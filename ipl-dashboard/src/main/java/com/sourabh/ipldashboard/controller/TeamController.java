package com.sourabh.ipldashboard.controller;

import java.util.List;

import com.sourabh.ipldashboard.model.Match;
import com.sourabh.ipldashboard.model.Team;
import com.sourabh.ipldashboard.repository.MatchRepository;
import com.sourabh.ipldashboard.repository.TeamRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("teams/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = teamRepository.findByTeamName(teamName);

        List<Match> listOfMatches = matchRepository.findLatestMatchesByTeam(teamName, 4);
        team.setListOfMatches(listOfMatches);
        return team;
    }
}

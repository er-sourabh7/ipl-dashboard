package com.sourabh.ipldashboard.repository;

import java.util.List;

import com.sourabh.ipldashboard.model.Match;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {

    List<Match> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    default List<Match> findLatestMatchesByTeam(String teamName, int number) {
        PageRequest pageable = PageRequest.of(0, number);
        List<Match> listOfMatches = getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable);
        return listOfMatches;
    }
}

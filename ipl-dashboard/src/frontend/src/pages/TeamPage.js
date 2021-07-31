import React, { useEffect, useState } from "react";
import { MatchDetailCard } from "../components/MatchDetailCard";
import { MatchSmallCard } from "../components/MatchSmallCard";

export const TeamPage = () => {
  const [team, setTeam] = useState({ listOfMatches: [] });

  useEffect(() => {
    const fetchMatches = async () => {
      const response = await fetch(
        "http://localhost:8080/teams/Rajasthan%20Royals"
      );
      const data = await response.json();
      setTeam(data);
    };

    fetchMatches();
  }, []);

  if (!team || !team.listOfMatches) return null;
  return (
    <div className="TeamPage">
      <h1>{team.teamName}</h1>
      <MatchDetailCard match={team.listOfMatches[0]} />
      {team.listOfMatches.slice(1).map((match) => (
        <MatchSmallCard match={match} />
      ))}
    </div>
  );
};

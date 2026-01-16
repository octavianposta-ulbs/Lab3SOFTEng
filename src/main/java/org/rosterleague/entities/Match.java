package org.rosterleague.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PERSISTENCE_ROSTER_MATCH")
public class Match {
    @Id
    public String matchId;
    public String leagueId;
    public String team1Id;
    public String team2Id;
    public int scoreTeam1;
    public int scoreTeam2;

    public Match() {}

    public Match(String matchId, String leagueId, String team1Id, String team2Id, int scoreTeam1, int scoreTeam2) {
        this.matchId = matchId;
        this.leagueId = leagueId;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public String getTeam1Id() {
        return team1Id;
    }

    public String getTeam2Id() {
        return team2Id;
    }

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }

    public String toString() {
        return matchId + ": " + team1Id + " VS " + team2Id + " | Score: " + scoreTeam1 + " - "+ scoreTeam2;
    }
}
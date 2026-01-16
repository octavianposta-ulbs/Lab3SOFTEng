package org.rosterleague.common;

public class MatchDetails {
    private String matchId;
    private String leagueId;
    private String team1Id;
    private String team2Id;
    private int scoreTeam1;
    private int scoreTeam2;

    public MatchDetails(String matchId, String leagueId, String team1Id,
                        String team2Id, int scoreTeam1, int scoreTeam2) {
        this.matchId = matchId;
        this.leagueId = leagueId;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
    }

    @Override
    public String toString() {
        return String.format("%s: %s VS %s | Score: %d - %d",
                matchId, team1Id, team2Id, scoreTeam1, scoreTeam2);
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(String team1Id) {
        this.team1Id = team1Id;
    }

    public String getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(String team2Id) {
        this.team2Id = team2Id;
    }

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    public void setScoreTeam1(int scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }

    public void setScoreTeam2(int scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }
}

package org.rosterleague.servlet;

import java.io.*;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.rosterleague.common.*;
import org.rosterleague.ejb.LeagueBean;
import org.rosterleague.ejb.MatchBean;
import org.rosterleague.ejb.PlayerBean;
import org.rosterleague.ejb.TeamBean;
import org.rosterleague.entities.Match;

@WebServlet(name = "mainServlet", value = "")
public class MainServlet extends HttpServlet {
    @Inject
    Request ejbRequest;

    @Inject
    LeagueBean leagueBean;

    @Inject
    TeamBean teamBean;

    @Inject
    PlayerBean playerBean;

    @Inject
    MatchBean matchBean;

    PrintWriter printer;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ejbRequest.clearAllEntities();
        insertInfo();

        printer = response.getWriter();
        response.setContentType("text/plain");

        getSomeInfo();
        getMoreInfo();
        matchesAndStandings();
        removeInfo();
    }

    private void insertInfo() {
        try {
            // Leagues
            leagueBean.createLeague(new LeagueDetails("L1", "Mountain", "Soccer"));
            leagueBean.createLeague(new LeagueDetails("L2", "Valley", "Basketball"));
            leagueBean.createLeague(new LeagueDetails("L3", "Foothills", "Soccer"));
            leagueBean.createLeague(new LeagueDetails("L4", "Alpine", "Snowboarding"));

            // Teams
            teamBean.createTeamInLeague(new TeamDetails("T1", "Honey Bees", "Visalia"), "L1");
            teamBean.createTeamInLeague(new TeamDetails("T2", "Gophers", "Manteca"), "L1");
            teamBean.createTeamInLeague(new TeamDetails("T5", "Crows", "Orland"), "L1");

            teamBean.createTeamInLeague(new TeamDetails("T3", "Deer", "Bodie"), "L2");
            teamBean.createTeamInLeague(new TeamDetails("T4", "Trout", "Truckee"), "L2");

            teamBean.createTeamInLeague(new TeamDetails("T6", "Marmots", "Auburn"), "L3");
            teamBean.createTeamInLeague(new TeamDetails("T7", "Bobcats", "Grass Valley"), "L3");
            teamBean.createTeamInLeague(new TeamDetails("T8", "Beavers", "Placerville"), "L3");

            teamBean.createTeamInLeague(new TeamDetails("T9", "Penguins", "Incline Village"), "L4");
            teamBean.createTeamInLeague(new TeamDetails("T10", "Land Otters", "Tahoe City"), "L4");

            // Players, Team T1
            playerBean.createPlayer("P1", "Phil Jones", "goalkeeper", 100.00);
            playerBean.addPlayer("P1", "T1");

            playerBean.createPlayer("P2", "Alice Smith", "defender", 505.00);
            playerBean.addPlayer("P2", "T1");

            playerBean.createPlayer("P3", "Bob Roberts", "midfielder", 65.00);
            playerBean.addPlayer("P3", "T1");

            playerBean.createPlayer("P4", "Grace Phillips", "forward", 100.00);
            playerBean.addPlayer("P4", "T1");

            playerBean.createPlayer("P5", "Barney Bold", "defender", 100.00);
            playerBean.addPlayer("P5", "T1");

            // Players, Team T2
            playerBean.createPlayer("P6", "Ian Carlyle", "goalkeeper", 555.00);
            playerBean.addPlayer("P6", "T2");

            playerBean.createPlayer("P7", "Rebecca Struthers", "midfielder", 777.00);
            playerBean.addPlayer("P7", "T2");

            playerBean.createPlayer("P8", "Anne Anderson", "forward", 65.00);
            playerBean.addPlayer("P8", "T2");

            playerBean.createPlayer("P9", "Jan Wesley", "defender", 100.00);
            playerBean.addPlayer("P9", "T2");

            playerBean.createPlayer("P10", "Terry Smithson", "midfielder", 100.00);
            playerBean.addPlayer("P10", "T2");

            // Players, Team T3
            playerBean.createPlayer("P11", "Ben Shore", "point guard", 188.00);
            playerBean.addPlayer("P11", "T3");

            playerBean.createPlayer("P12", "Chris Farley", "shooting guard", 577.00);
            playerBean.addPlayer("P12", "T3");

            playerBean.createPlayer("P13", "Audrey Brown", "small forward", 995.00);
            playerBean.addPlayer("P13", "T3");

            playerBean.createPlayer("P14", "Jack Patterson", "power forward", 100.00);
            playerBean.addPlayer("P14", "T3");

            playerBean.createPlayer("P15", "Candace Lewis", "point guard", 100.00);
            playerBean.addPlayer("P15", "T3");

            // Players, Team T4
            playerBean.createPlayer("P16", "Linda Berringer", "point guard", 844.00);
            playerBean.addPlayer("P16", "T4");

            playerBean.createPlayer("P17", "Bertrand Morris", "shooting guard", 452.00);
            playerBean.addPlayer("P17", "T4");

            playerBean.createPlayer("P18", "Nancy White", "small forward", 833.00);
            playerBean.addPlayer("P18", "T4");

            playerBean.createPlayer("P19", "Billy Black", "power forward", 444.00);
            playerBean.addPlayer("P19", "T4");

            playerBean.createPlayer("P20", "Jodie James", "point guard", 100.00);
            playerBean.addPlayer("P20", "T4");

            // Players, Team T5
            playerBean.createPlayer("P21", "Henry Shute", "goalkeeper", 205.00);
            playerBean.addPlayer("P21", "T5");

            playerBean.createPlayer("P22", "Janice Walker", "defender", 857.00);
            playerBean.addPlayer("P22", "T5");

            playerBean.createPlayer("P23", "Wally Hendricks", "midfielder", 748.00);
            playerBean.addPlayer("P23", "T5");

            playerBean.createPlayer("P24", "Gloria Garber", "forward", 777.00);
            playerBean.addPlayer("P24", "T5");

            playerBean.createPlayer("P25", "Frank Fletcher", "defender", 399.00);
            playerBean.addPlayer("P25", "T5");

            // Players, Team T9
            playerBean.createPlayer("P30", "Lakshme Singh", "downhill", 450.00);
            playerBean.addPlayer("P30", "T9");

            playerBean.createPlayer("P31", "Mariela Prieto", "freestyle", 420.00);
            playerBean.addPlayer("P31", "T9");

            // Players, Team T10
            playerBean.createPlayer("P32", "Soren Johannsen", "freestyle", 375.00);
            playerBean.addPlayer("P32", "T10");

            playerBean.createPlayer("P33", "Andre Gerson", "freestyle", 396.00);
            playerBean.addPlayer("P33", "T10");

            playerBean.createPlayer("P34", "Zoria Lepsius", "downhill", 431.00);
            playerBean.addPlayer("P34", "T10");

            //Players, Team T7
            playerBean.createPlayer("P35", "Reymar Robinson", "defender", 499.00);
            playerBean.addPlayer("P35", "T7");

            playerBean.createPlayer("P36", "Jackie Willson", "forward", 899.00);
            playerBean.addPlayer("P36", "T7");

            playerBean.createPlayer("P37", "Alexander Lioness", "midfielder", 389.00);
            playerBean.addPlayer("P37", "T7");

            //Players, Team T8
            playerBean.createPlayer("P38", "James Gilbert", "defender", 599.00);
            playerBean.addPlayer("P38", "T8");

            playerBean.createPlayer("P39", "Willy Wales", "point guard", 299.00);
            playerBean.addPlayer("P39", "T8");

            playerBean.createPlayer("P40", "Jimmy Jellyman", "forward", 199.00);
            playerBean.addPlayer("P40", "T8");

            // Players, no team
            playerBean.createPlayer("P26", "Hobie Jackson", "pitcher", 582.00);
            playerBean.createPlayer("P27", "Melinda Kendall", "catcher", 677.00);

            // Players, multiple teams
            playerBean.createPlayer("P28", "Constance Adams", "substitute", 966.00);
            playerBean.addPlayer("P28", "T1");
            playerBean.addPlayer("P28", "T3");

            // Adding existing players to second soccer league
            playerBean.addPlayer("P24", "T6");
            playerBean.addPlayer("P21", "T6");
            playerBean.addPlayer("P9", "T6");
            playerBean.addPlayer("P7", "T5");

        } catch (Exception ex) {
            printer.println("Caught an exception: " + ex.getClass() + " : " + ex.getMessage());
            ex.printStackTrace(printer);
        }
    }

    private void getSomeInfo() {
        try {
            List<PlayerDetails> playerList;
            List<TeamDetails> teamList;
            List<LeagueDetails> leagueList;

            printer.println("List all players in team T2: ");
            playerList = playerBean.getPlayersOfTeam("T2");
            printDetailsList(playerList);
            printer.println();

            printer.println("List all teams in league L1: ");
            teamList = leagueBean.getTeamsOfLeague("L1");
            printDetailsList(teamList);
            printer.println();

            printer.println("List all defenders: ");
            playerList = playerBean.getPlayersByPosition("defender");
            printDetailsList(playerList);
            printer.println();

            printer.println("List the leagues of player P28: ");
            leagueList = leagueBean.getLeaguesOfPlayer("P28");
            printDetailsList(leagueList);
            printer.println();

        } catch (Exception ex) {
            printer.println("Caught an exception: " + ex.getClass() + " : " + ex.getMessage());
            ex.printStackTrace(printer);
        }
    }

    private void getMoreInfo() {
        try {
            LeagueDetails leagueDetails;
            TeamDetails teamDetails;
            PlayerDetails playerDetails;
            List<PlayerDetails> playerList;
            List<TeamDetails> teamList;
            List<LeagueDetails> leagueList;
            List<String> sportList;

            printer.println("Details of league L1: ");
            leagueDetails = leagueBean.getLeague("L1");
            printer.println(leagueDetails.toString());
            printer.println();

            printer.println("Details of team T3: ");
            teamDetails = teamBean.getTeam("T3");
            printer.println(teamDetails.toString());
            printer.println();

            printer.println("Details of player P20: ");
            playerDetails = playerBean.getPlayer("P20");
            printer.println(playerDetails.toString());
            printer.println();

            printer.println("List all teams in league L3: ");
            teamList = leagueBean.getTeamsOfLeague("L3");
            printDetailsList(teamList);
            printer.println();

            printer.println("List all players: ");
            playerList = playerBean.getAllPlayers();
            printDetailsList(playerList);
            printer.println();

            printer.println("List all players not on a team: ");
            playerList = playerBean.getPlayersNotOnTeam();
            printDetailsList(playerList);
            printer.println();

            printer.println("Details of Jack Patterson, a power forward: ");
            playerList = playerBean.getPlayersByPositionAndName("power forward", "Jack Patterson");
            printDetailsList(playerList);
            printer.println();

            printer.println("List all players in the city of Truckee: ");
            playerList = playerBean.getPlayersByCity("Truckee");
            printDetailsList(playerList);
            printer.println();

            printer.println("List all soccer players: ");
            playerList = playerBean.getPlayersBySport("Soccer");
            printDetailsList(playerList);
            printer.println();

            printer.println("List all players in league L1: ");
            playerList = playerBean.getPlayersByLeagueId("L1");
            printDetailsList(playerList);
            printer.println();

            printer.println("List all players making a higher salary than Ian Carlyle: ");
            playerList = playerBean.getPlayersByHigherSalary("Ian Carlyle");
            printDetailsList(playerList);
            printer.println();

            printer.println("List all players with a salary between 500 and 800: ");
            playerList = playerBean.getPlayersBySalaryRange(500.00, 800.00);
            printDetailsList(playerList);
            printer.println();

            printer.println("List all players of team T5: ");
            playerList = playerBean.getPlayersOfTeam("T5");
            printDetailsList(playerList);
            printer.println();

            printer.println("List all the leagues of player P28: ");
            leagueList = leagueBean.getLeaguesOfPlayer("P28");
            printDetailsList(leagueList);
            printer.println();

            printer.println("List all the sports of player P28: ");
            sportList = leagueBean.getSportsOfPlayer("P28");
            printDetailsList(sportList);
            printer.println();

        } catch (Exception ex) {
            printer.println("Caught an exception: " + ex.getClass() + " : " + ex.getMessage());
            ex.printStackTrace(printer);
        }
    }

    private void matchesAndStandings() {
        try {
            List<PlayerDetails> playerList;
            List<TeamDetails> teamList;
            teamList = leagueBean.getTeamsOfLeague("L3");

            matchBean.addMatch(new Match("M1", "L2", "T3", "T4", 0, 3));
            matchBean.addMatch(new Match("M2", "L2", "T3", "T4", 3, 0));
            matchBean.addMatch(new Match("M3", "L3", "T6", "T7", 0, 3));
            matchBean.addMatch(new Match("M4", "L3", "T6", "T8", 3, 2));
            matchBean.addMatch(new Match("M5", "L3", "T7", "T8", 1, 3));
            matchBean.addMatch(new Match("M6", "L4", "T9", "T10", 2, 3));
            matchBean.addMatch(new Match("M7", "L4", "T9", "T10", 3, 1));

            printer.println("All matches for team T6:");
            for (MatchDetails m : matchBean.getMatchesByTeam("T6")) {
                printer.println(m);
            }
            printer.println();

            Map<String, Integer> points = new HashMap<>();
            for (MatchDetails m : matchBean.getMatchesByLeague("L3")) {
                int pointsForTeam1 = 0, pointsForTeam2 = 0;
                if (m.getScoreTeam1() > m.getScoreTeam2())
                    pointsForTeam1 = 3;
                else if (m.getScoreTeam1() < m.getScoreTeam2())
                    pointsForTeam2 = 3;
                else { pointsForTeam1 = 1; pointsForTeam2 = 1; }

                points.put(m.getTeam1Id(), points.getOrDefault(m.getTeam1Id(), 0) + pointsForTeam1);
                points.put(m.getTeam2Id(), points.getOrDefault(m.getTeam2Id(), 0) + pointsForTeam2);
            }

            printer.println("Standings for league L3:");
            List<Map.Entry<String, Integer>> scores = new ArrayList<>(points.entrySet());
            scores.sort((a, b) -> b.getValue() - a.getValue());

            for (Map.Entry<String, Integer> e : scores) {
                for(TeamDetails t : teamList) {
                    if(t.getId().equals(e.getKey())) {
                        printer.println(e.getKey() + " - " + t.getName());
                        printer.println("Score: " + e.getValue());
                        break;
                    }
                }
                printer.println("Players for team " + e.getKey());
                playerList = playerBean.getPlayersOfTeam(e.getKey());
                printDetailsList(playerList);
            }
            printer.println();

        } catch (Exception ex) {
            printer.println("Exception: " + ex.getMessage());
            ex.printStackTrace(printer);
        }
    }

    private void removeInfo() {
        try {
            printer.println("Removing team T6. ");
            teamBean.removeTeam("T6");
            printer.println();

            printer.println("Removing player P24 ");
            playerBean.removePlayer("P24");
            printer.println();

        } catch (Exception ex) {
            printer.println("Caught an exception: " + ex.getClass() + " : " + ex.getMessage());
            ex.printStackTrace(printer);
        }
    }

    private void printDetailsList(List list) {
        for (Object details : list) {
            printer.println(details.toString());
        }
        printer.println();
    }
}
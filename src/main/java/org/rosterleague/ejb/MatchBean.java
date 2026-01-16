package org.rosterleague.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.rosterleague.common.MatchDetails;
import org.rosterleague.entities.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class MatchBean {

    private static final Logger logger = Logger.getLogger(MatchBean.class.getName());

    @PersistenceContext
    private EntityManager em;

    private CriteriaBuilder cb;

    @PostConstruct
    private void init() {
        cb = em.getCriteriaBuilder();
    }

    public List<MatchDetails> copyMatchesToDetails(List<Match> matches) {
        logger.log(Level.INFO, "copyMatchesToDetails");
        List<MatchDetails> matchesDetails = new ArrayList<>();
        for (Match match : matches) {
            MatchDetails details = new MatchDetails(
                    match.matchId,
                    match.leagueId,
                    match.team1Id,
                    match.team2Id,
                    match.scoreTeam1,
                    match.scoreTeam2
            );
            matchesDetails.add(details);
        }
        return matchesDetails;
    }

    public List<MatchDetails> getMatchesByTeam(String teamId) {
        logger.log(Level.INFO, "getMatchesByTeam");
        CriteriaQuery<Match> cq = cb.createQuery(Match.class);
        Root<Match> match = cq.from(Match.class);

        cq.where(cb.or(
                cb.equal(match.get("team1Id"), teamId),
                cb.equal(match.get("team2Id"), teamId)
        ));

        TypedQuery<Match> q = em.createQuery(cq);
        List<Match> results = q.getResultList();
        return copyMatchesToDetails(results);
    }

    public List<MatchDetails> getMatchesByLeague(String leagueId) {
        logger.log(Level.INFO, "getMatchesByLeague");
        try {
            CriteriaQuery<Match> cq = cb.createQuery(Match.class);
            Root<Match> match = cq.from(Match.class);
            cq.where(cb.equal(match.get("leagueId"), leagueId));

            TypedQuery<Match> q = em.createQuery(cq);
            List<Match> results = q.getResultList();

            return copyMatchesToDetails(results);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void addMatch(Match match) {
        logger.log(Level.INFO, "addMatch");
        try {
            em.persist(match);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }
}

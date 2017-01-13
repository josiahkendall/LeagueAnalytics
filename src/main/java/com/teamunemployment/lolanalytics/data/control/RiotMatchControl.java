package com.teamunemployment.lolanalytics.data.control;

import com.google.gson.GsonBuilder;
import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.MatchDetailsModel;
import com.teamunemployment.lolanalytics.models.MatchList;
import com.teamunemployment.lolanalytics.models.MatchSummary;
import com.teamunemployment.lolanalyticsv3.RestApi.MatchService;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

import java.util.Iterator;
import java.util.List;

/**
 * @author Josiah Kendall
 */
public class RiotMatchControl {

    private ParticipantControl participantControl;
    private ParticipantIdentityControl participantIdentityControl;
    private DBHelper dbHelper;
    private MatchParticipantSummaryJunctionControl matchParticipantSummaryJunctionControl;
    private RestAdapter serviceAdapter;
    private MatchSummaryControl matchSummaryControl;
    private MatchDetailsControl matchDetailsControl;

    public RiotMatchControl(ParticipantControl participantControl, ParticipantIdentityControl participantIdentityControl,
                            DBHelper dbHelper, MatchParticipantSummaryJunctionControl matchParticipantSummaryJunctionControl,
                            RestAdapter serviceAdapter, MatchSummaryControl matchSummaryControl, MatchDetailsControl matchDetailsControl) {
        this.participantControl = participantControl;
        this.participantIdentityControl = participantIdentityControl;
        this.dbHelper = dbHelper;
        this.matchParticipantSummaryJunctionControl = matchParticipantSummaryJunctionControl;
        this.serviceAdapter = serviceAdapter;
        this.matchSummaryControl = matchSummaryControl;
        this.matchDetailsControl = matchDetailsControl;
    }

    /**
     * Download all the matches for a summoner, and save them to the db This is used by the refresh function.
     * @param summonerId
     */
    public void UpdateMatchesForASummoner(long summonerId) throws InterruptedException {
        MatchService service = serviceAdapter.create(MatchService.class);
        MatchList matchlist = service.getMatchListForSummoner(summonerId);
        List<MatchSummary> matchSummaries = matchlist.matches;
        Iterator<MatchSummary> matchSummariesIterator = matchSummaries.iterator();
        // TODO - find a better solution to this.
        Thread.sleep(2000);
        while (matchSummariesIterator.hasNext()) {
            MatchSummary next = matchSummariesIterator.next();
            MatchSummary previousMatchSummary = matchSummaryControl.getMatchSummary(next.matchId);
            if (previousMatchSummary == null) {
                matchSummaryControl.saveMatchSummary(next, summonerId);
                try {
                    MatchDetailsModel match = service.getMatchSummary(next.matchId);
                    matchDetailsControl.SaveMatch(match, summonerId);
                } catch(RetrofitError ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}

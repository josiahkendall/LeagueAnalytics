package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.MatchDetailsModel;
import com.teamunemployment.lolanalytics.models.MatchList;
import com.teamunemployment.lolanalytics.models.MatchSummary;
import com.teamunemployment.lolanalyticsv3.RestApi.MatchService;
import org.junit.Test;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

/**
 *@author Josiah Kendall
 */
public class RiotMatchControlTests {

    @Test
    public void TEstThatWeCanDownloadThreeMatchesAndSaveThem() throws InterruptedException {

        // Create mocks
        ParticipantControl participantControl = mock(ParticipantControl.class);
        ParticipantIdentityControl participantIdentityControl = mock(ParticipantIdentityControl.class);
        DBHelper dbHelper = mock(DBHelper.class);
        MatchParticipantSummaryJunctionControl matchParticipantSummaryJunctionControl = mock(MatchParticipantSummaryJunctionControl.class);
        MatchSummaryControl matchSummaryControl = mock(MatchSummaryControl.class);
        RestAdapter serviceAdapter = mock(RestAdapter.class);
        MatchService mockMatchService = mock(MatchService.class);
        MatchDetailsControl matchDetailsControl = mock(MatchDetailsControl.class);
        List<MatchSummary> matchSummaries1 = new ArrayList<>();
        MatchSummary matchSummary = mock(MatchSummary.class);
        MatchSummary matchSummary2 = mock(MatchSummary.class);
        MatchSummary matchSummary3 = mock(MatchSummary.class);
        matchSummaries1.add(matchSummary);
        matchSummaries1.add(matchSummary2);
        matchSummaries1.add(matchSummary3);

        MatchList matchList1 = new MatchList();
        matchList1.matches = matchSummaries1;

        // ruleset for method
        when(serviceAdapter.create(MatchService.class)).thenReturn(mockMatchService);
        when(mockMatchService.getMatchListForSummoner(anyLong())).thenReturn(matchList1);
        when(matchSummaryControl.getMatchSummary(anyLong())).thenReturn(null);

        RiotMatchControl riotMatchControl = new RiotMatchControl(participantControl, participantIdentityControl, dbHelper,
                matchParticipantSummaryJunctionControl, serviceAdapter, matchSummaryControl, matchDetailsControl);
        riotMatchControl.UpdateMatchesForASummoner(new Long(12321312));
        verify(matchSummaryControl, times(3)).saveMatchSummary(any(MatchSummary.class), anyLong());
    }

    @Test
    public void TestThatWeCanDontCallSaveIfWeAlreadyHaveTheSummarySaved() throws InterruptedException {
        // Create mocks
        ParticipantControl participantControl = mock(ParticipantControl.class);
        ParticipantIdentityControl participantIdentityControl = mock(ParticipantIdentityControl.class);
        DBHelper dbHelper = mock(DBHelper.class);
        MatchParticipantSummaryJunctionControl matchParticipantSummaryJunctionControl = mock(MatchParticipantSummaryJunctionControl.class);
        MatchSummaryControl matchSummaryControl = mock(MatchSummaryControl.class);
        RestAdapter serviceAdapter = mock(RestAdapter.class);
        MatchService mockMatchService = mock(MatchService.class);
        MatchDetailsControl matchDetailsControl = mock(MatchDetailsControl.class);
        List<MatchSummary> matchSummaries1 = new ArrayList<>();
        MatchSummary matchSummary = mock(MatchSummary.class);
        MatchSummary matchSummary2 = mock(MatchSummary.class);
        MatchSummary matchSummary3 = mock(MatchSummary.class);
        matchSummaries1.add(matchSummary);
        matchSummaries1.add(matchSummary2);
        matchSummaries1.add(matchSummary3);

        MatchList matchList1 = new MatchList();
        matchList1.matches = matchSummaries1;

        // ruleset for method
        when(serviceAdapter.create(MatchService.class)).thenReturn(mockMatchService);
        when(mockMatchService.getMatchListForSummoner(anyLong())).thenReturn(matchList1);
        when(matchSummaryControl.getMatchSummary(anyLong())).thenReturn(matchSummary);

        RiotMatchControl riotMatchControl = new RiotMatchControl(participantControl, participantIdentityControl, dbHelper,
                matchParticipantSummaryJunctionControl, serviceAdapter, matchSummaryControl, matchDetailsControl);
        riotMatchControl.UpdateMatchesForASummoner(new Long(12321312));
        verify(matchSummaryControl, times(0)).saveMatchSummary(any(MatchSummary.class), anyLong());
    }
}

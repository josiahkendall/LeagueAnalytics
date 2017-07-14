/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import com.teamunemployment.lolanalytics.data.control.*;
import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.MatchDetailsModel;
import com.teamunemployment.lolanalytics.models.MatchSummary;
import com.teamunemployment.lolanalyticsv3.RestApi.EntryApi;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jek40
 */
public class crawler {
    
    private DBHelper dbHelper;
    private ParticipantControl participantControl;
    private ParticipantIdentityControl participantIdentityControl;
    private EntryApi api;
    
    
    private void init() {
        dbHelper = new DBHelper("jdbc:mysql://localhost:3306/local_lolanlaytics", "root", "Idnw2bh2");
        dbHelper.Connect();
        RuneControl runeControl = new RuneControl();
        BaseDeltaControl baseDeltaControl = new BaseDeltaControl(dbHelper);
        DeltaControl deltaControl =new DeltaControl(baseDeltaControl);
        StatControl statControl = new StatControl(dbHelper);
        MasteriesControl masteriesControl = new MasteriesControl();
        TimelineControl timelineControl = new TimelineControl(dbHelper, deltaControl);

        participantControl = new ParticipantControl(dbHelper, runeControl, timelineControl, statControl, masteriesControl);
        participantIdentityControl = new ParticipantIdentityControl(dbHelper);
    }
    
    private void start(long userId) {
        init();

        api = new EntryApi();
        List<MatchSummary> matchSummaries = api.FetchMatchList(1542360);
        MatchSummaryControl matchSummaryControl = new MatchSummaryControl(dbHelper);
        MatchControl matchControl = new MatchControl(dbHelper, participantControl);
        Iterator<MatchSummary> matchSummariesIterator = matchSummaries.iterator();
        while (matchSummariesIterator.hasNext()) {
            MatchSummary next = matchSummariesIterator.next();
            matchSummaryControl.saveMatchSummary(next, 1542360);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(crawler.class.getName()).log(Level.SEVERE, null, ex);
            }
            // load match
            MatchDetailsModel match = api.FetchMatchDetails(next.matchId);
            matchControl.SaveMatch(match);
        }        
    }
    
    
}

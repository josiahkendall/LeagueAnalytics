/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import com.teamunemployment.lolanalytics.data.control.CreepsPerMinDeltaControl;
import com.teamunemployment.lolanalytics.data.control.CsDiffPerMinDeltasControl;
import com.teamunemployment.lolanalytics.data.control.GoldPerMinDeltasControl;
import com.teamunemployment.lolanalytics.data.control.MasteriesControl;
import com.teamunemployment.lolanalytics.data.control.MatchControl;
import com.teamunemployment.lolanalytics.data.control.MatchDetailsControl;
import com.teamunemployment.lolanalytics.data.control.MatchSummaryControl;
import com.teamunemployment.lolanalytics.data.control.ParticipantControl;
import com.teamunemployment.lolanalytics.data.control.ParticipantIdentityControl;
import com.teamunemployment.lolanalytics.data.control.RuneControl;
import com.teamunemployment.lolanalytics.data.control.StatControl;
import com.teamunemployment.lolanalytics.data.control.TimelineControl;
import com.teamunemployment.lolanalytics.data.control.XpPerMinDeltaControl;
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
        CreepsPerMinDeltaControl cpmControl = new CreepsPerMinDeltaControl(dbHelper);
        CsDiffPerMinDeltasControl csdControl = new CsDiffPerMinDeltasControl(dbHelper);
        GoldPerMinDeltasControl gpmControl = new GoldPerMinDeltasControl(dbHelper);
        XpPerMinDeltaControl xpPerMinDeltaControl = new XpPerMinDeltaControl(dbHelper);
        StatControl statControl = new StatControl(dbHelper);
        MasteriesControl masteriesControl = new MasteriesControl();
        TimelineControl timelineControl = new TimelineControl(cpmControl, csdControl, gpmControl, xpPerMinDeltaControl, dbHelper);

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

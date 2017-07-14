package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.MatchParticipantSummaryJunctionModel;
import org.junit.Test;

/**
 * @author Josiah Kendall
 */
public class MatchSummaryJunctionTests {

    @Test
    public void MatchParticipantSummaryJunctionSaves() {
        MatchParticipantSummaryJunctionModel matchParticipantSummaryJunctionModel = new MatchParticipantSummaryJunctionModel();
        matchParticipantSummaryJunctionModel.MatchId = 1234;

        DBHelper dbHelper = new DBHelper();
        MatchParticipantSummaryJunctionControl matchParticipantSummaryJunctionControl = new MatchParticipantSummaryJunctionControl(dbHelper);
        int id = matchParticipantSummaryJunctionControl.SaveMatchParticipantJunctionModel(matchParticipantSummaryJunctionModel);
        //MatchParticipantSummaryJunctionModel model2 = matchParticipantSummaryJunctionControl.
    }
}

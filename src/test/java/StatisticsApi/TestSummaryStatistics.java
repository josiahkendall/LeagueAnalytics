/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StatisticsApi;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.stats.summary.SummaryModel;
import com.teamunemployment.lolanalytics.models.stats.summary.SummaryStatistics;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 *
 * @author Josiah Kendall
 */
public class TestSummaryStatistics {
    
    @Test
    public void TestThatWeCanCalculateAverageCorrectlyTo2DecimalPlaces() {
       SummaryModel summaryModel = Mockito.mock(SummaryModel.class);
       Mockito.when(summaryModel.GetAllLosses(Matchers.anyLong(), Matchers.anyString(), Matchers.anyInt())).thenReturn(54);
       Mockito.when(summaryModel.GetAllWins(Matchers.anyLong(), Matchers.anyString(), Matchers.anyInt())).thenReturn(46);
       
       SummaryStatistics summaryStatistics = new SummaryStatistics(summaryModel);
       double wr = summaryStatistics.CalculateWinRate(1, "test", 1);
       Assert.assertTrue(wr == 46.0);
    }
    
    @Test
    public void TestThatWeCanFetchLossesForAUserFromTheRawDatabase() {
//        DBHelper dbHelper = new DBHelper();
//        dbHelper.Connect();
//        SummaryModel summaryModel = new SummaryModel(dbHelper);
//
//        int wins = summaryModel.getRankedWinsAllTime(1542360);
//        System.out.println(wins);
//        Assert.assertTrue(wins > 20);
        
    }
    
    public void TestThatWeCanFetchWinsForAUserFromtheRawDatabase() {
        
    }
}

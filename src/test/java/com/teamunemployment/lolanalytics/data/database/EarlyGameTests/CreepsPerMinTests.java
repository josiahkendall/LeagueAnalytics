package com.teamunemployment.lolanalytics.data.database.EarlyGameTests;

import com.teamunemployment.lolanalytics.data.control.CreepsPerMinDeltaControl;
import com.teamunemployment.lolanalytics.data.control.EarlyGame.CreepsPerMinCalculator;
import com.teamunemployment.lolanalytics.models.stats.CreepsPerMinDeltas;
import junit.framework.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author Josiah Kendall
 */
public class CreepsPerMinTests {

    @Test
    public void GetAverageOverSeason_GetsCorrectAverage() {
        // Get all the results for a season out of the db. Make sure they are returned as an arrayList of doubles
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = Mockito.mock(CreepsPerMinDeltaControl.class);
        CreepsPerMinCalculator creepsPerMinCalculator = new CreepsPerMinCalculator(creepsPerMinDeltaControl);

        ArrayList<CreepsPerMinDeltas> mockResult = new ArrayList<>();
        CreepsPerMinDeltas creepsPerMinDeltas = new CreepsPerMinDeltas();
        creepsPerMinDeltas.setZeroToTen(7.1);
        creepsPerMinDeltas.setTenToTwenty(7.5);
        creepsPerMinDeltas.setTwentyToThirty(7.3);
        creepsPerMinDeltas.setThirtyToEnd(4.1);

        CreepsPerMinDeltas creepsPerMinDeltas2 = new CreepsPerMinDeltas();
        creepsPerMinDeltas2.setZeroToTen(7.1);
        creepsPerMinDeltas2.setTenToTwenty(7.5);
        creepsPerMinDeltas2.setTwentyToThirty(7.3);
        creepsPerMinDeltas2.setThirtyToEnd(4.1);

        CreepsPerMinDeltas creepsPerMinDeltas3 = new CreepsPerMinDeltas();
        creepsPerMinDeltas3.setZeroToTen(7.1);
        creepsPerMinDeltas3.setTenToTwenty(7.5);
        creepsPerMinDeltas3.setTwentyToThirty(7.3);
        creepsPerMinDeltas3.setThirtyToEnd(4.1);

        CreepsPerMinDeltas creepsPerMinDeltas4 = new CreepsPerMinDeltas();
        creepsPerMinDeltas4.setZeroToTen(7.1);
        creepsPerMinDeltas4.setTenToTwenty(7.5);
        creepsPerMinDeltas4.setTwentyToThirty(7.3);
        creepsPerMinDeltas4.setThirtyToEnd(4.1);

        mockResult.add(creepsPerMinDeltas);
        mockResult.add(creepsPerMinDeltas2);
        mockResult.add(creepsPerMinDeltas3);
        mockResult.add(creepsPerMinDeltas4);

        when(creepsPerMinDeltaControl.GetAllEarlyGameCreepsPerMinForASeason(anyLong(), anyString(), anyString(), anyString())).thenReturn(mockResult);

        double average = creepsPerMinCalculator.GetSeasonAverage(1, "season2017","TOP", "SOLO");
        Assert.assertTrue(average == 7.1);
    }

    @Test
    public void Ensure_RecentCreepsPerMin_CalculatesCorrectly() {
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = Mockito.mock(CreepsPerMinDeltaControl.class);
        CreepsPerMinCalculator creepsPerMinCalculator = new CreepsPerMinCalculator(creepsPerMinDeltaControl);

        ArrayList<CreepsPerMinDeltas> mockResult = new ArrayList<>();
        CreepsPerMinDeltas creepsPerMinDeltas = new CreepsPerMinDeltas();
        creepsPerMinDeltas.setZeroToTen(7.1);
        creepsPerMinDeltas.setTenToTwenty(7.5);
        creepsPerMinDeltas.setTwentyToThirty(7.3);
        creepsPerMinDeltas.setThirtyToEnd(4.1);

        CreepsPerMinDeltas creepsPerMinDeltas2 = new CreepsPerMinDeltas();
        creepsPerMinDeltas2.setZeroToTen(7.1);
        creepsPerMinDeltas2.setTenToTwenty(7.5);
        creepsPerMinDeltas2.setTwentyToThirty(7.3);
        creepsPerMinDeltas2.setThirtyToEnd(4.1);

        CreepsPerMinDeltas creepsPerMinDeltas3 = new CreepsPerMinDeltas();
        creepsPerMinDeltas3.setZeroToTen(7.1);
        creepsPerMinDeltas3.setTenToTwenty(7.5);
        creepsPerMinDeltas3.setTwentyToThirty(7.3);
        creepsPerMinDeltas3.setThirtyToEnd(4.1);

        CreepsPerMinDeltas creepsPerMinDeltas4 = new CreepsPerMinDeltas();
        creepsPerMinDeltas4.setZeroToTen(7.1);
        creepsPerMinDeltas4.setTenToTwenty(7.5);
        creepsPerMinDeltas4.setTwentyToThirty(7.3);
        creepsPerMinDeltas4.setThirtyToEnd(4.1);

        mockResult.add(creepsPerMinDeltas);
        mockResult.add(creepsPerMinDeltas2);
        mockResult.add(creepsPerMinDeltas3);
        mockResult.add(creepsPerMinDeltas4);

        when(creepsPerMinDeltaControl.GetMostRecent20EarlyGameCreepScoresForASummoner(anyLong(), anyInt(), anyString(), anyString())).thenReturn(mockResult);
        double result = creepsPerMinCalculator.GetRecentAverage(1, 1, "TOP", "SOLO");
        Assert.assertTrue(result == 7.1);
    }

    @Test
    public void Ensure_RecentCreepsPerMin_CalculatesCorrectly_WhenFilteringByChampion() {
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = Mockito.mock(CreepsPerMinDeltaControl.class);
        CreepsPerMinCalculator creepsPerMinCalculator = new CreepsPerMinCalculator(creepsPerMinDeltaControl);

        ArrayList<CreepsPerMinDeltas> mockResult = new ArrayList<>();
        CreepsPerMinDeltas creepsPerMinDeltas = new CreepsPerMinDeltas();
        creepsPerMinDeltas.setZeroToTen(7.1);
        creepsPerMinDeltas.setTenToTwenty(7.5);
        creepsPerMinDeltas.setTwentyToThirty(7.3);
        creepsPerMinDeltas.setThirtyToEnd(4.1);

        CreepsPerMinDeltas creepsPerMinDeltas2 = new CreepsPerMinDeltas();
        creepsPerMinDeltas2.setZeroToTen(7.1);
        creepsPerMinDeltas2.setTenToTwenty(7.5);
        creepsPerMinDeltas2.setTwentyToThirty(7.3);
        creepsPerMinDeltas2.setThirtyToEnd(4.1);

        CreepsPerMinDeltas creepsPerMinDeltas3 = new CreepsPerMinDeltas();
        creepsPerMinDeltas3.setZeroToTen(7.1);
        creepsPerMinDeltas3.setTenToTwenty(7.5);
        creepsPerMinDeltas3.setTwentyToThirty(7.3);
        creepsPerMinDeltas3.setThirtyToEnd(4.1);

        CreepsPerMinDeltas creepsPerMinDeltas4 = new CreepsPerMinDeltas();
        creepsPerMinDeltas4.setZeroToTen(7.1);
        creepsPerMinDeltas4.setTenToTwenty(7.5);
        creepsPerMinDeltas4.setTwentyToThirty(7.3);
        creepsPerMinDeltas4.setThirtyToEnd(4.1);

        mockResult.add(creepsPerMinDeltas);
        mockResult.add(creepsPerMinDeltas2);
        mockResult.add(creepsPerMinDeltas3);
        mockResult.add(creepsPerMinDeltas4);

        when(creepsPerMinDeltaControl.GetMostRecent20EarlyGameCreepScoresForASummoner(anyLong(), anyString(), anyString())).thenReturn(mockResult);
        double result = creepsPerMinCalculator.GetRecentAverage(1, "TOP", "SOLO");
        Assert.assertTrue(result == 7.1);
    }

    @Test
    public void Ensure_GetBest_ReturnsTheMax() {
        // This test is not really required because get max only returns the double
    }

    @Test
    public void Ensure_GetBest_ReturnsTheMin() {

    }


}

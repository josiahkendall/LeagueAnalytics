package com.teamunemployment.lolanalytics.data.TestStats;

import com.teamunemployment.lolanalytics.data.Role;
import com.teamunemployment.lolanalytics.models.AdapterPojo;
import com.teamunemployment.lolanalytics.models.Beans.*;
import com.teamunemployment.lolanalytics.models.Beans.Double;
import com.teamunemployment.lolanalytics.models.StringResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Josiah Kendall
 */
public class TestStats {
    public static final int TOP = 0;
    public static final int JUNGLE = 1;
    public static final int MID = 2;
    public static final int ADC = 3;
    public static final int SUPPORT = 4;

    // Tabs are 8 spaces even when set to 4 spaces is this ide retarded
    public List<AdapterPojo> FetchTopStats() {
        AdapterPojo adapterPojo = new AdapterPojo(32, "Creep Score: First Ten Minutes", 65.75, 71.46, TOP, -1);
        AdapterPojo adapterPojo1 = new AdapterPojo(33, "Creep Score: 10 - 20 Minutes", 71.65, 76.43, TOP, -1);
        AdapterPojo adapterPojo2 = new AdapterPojo(34, "Average Kills: First 10 Minutes", 1.94, 2.43, TOP, -1);
        AdapterPojo adapterPojo3 = new AdapterPojo(35, "Average Deaths : First 10 Minutes", 1.62, 2.31, TOP, -1);
        AdapterPojo adapterPojo4 = new AdapterPojo(36, "Average Assists : First 10 Minutes", 1.42, 1.43, TOP, -1);
        AdapterPojo adapterPojo5 = new AdapterPojo(37, "Average Kills: 10 - 20 Minutes", 1.94, 2.43, TOP, -1);
        AdapterPojo adapterPojo6 = new AdapterPojo(38, "Average Deaths : 10 - 20 Minutes", 1.62, 2.31, TOP, -1);
        AdapterPojo adapterPojo7 = new AdapterPojo(39, "Average Assists : 10 - 20 Minutes", 1.42, 1.43, TOP, -1);
        List<AdapterPojo> responseList = new ArrayList<>();
        responseList.add(adapterPojo);
        responseList.add(adapterPojo2);
        responseList.add(adapterPojo3);
        responseList.add(adapterPojo1);
        responseList.add(adapterPojo4);
        responseList.add(adapterPojo5);
        responseList.add(adapterPojo6);
        responseList.add(adapterPojo7);
        return responseList;
    }

    public List<AdapterPojo> FetchMidStats() {
        AdapterPojo adapterPojo = new AdapterPojo(24, "Creep Score: First Ten Minutes", 65.75, 71.46, MID, -1);
        AdapterPojo adapterPojo1 = new AdapterPojo(52, "Creep Score: 10 - 20 Minutes", 71.65, 76.43, MID, -1);
        AdapterPojo adapterPojo2 = new AdapterPojo(26, "Average Kills: First 10 Minutes", 1.94, 2.43, MID, -1);
        AdapterPojo adapterPojo3 = new AdapterPojo(27, "Average Deaths : First 10 Minutes", 1.62, 2.31, MID, -1);
        AdapterPojo adapterPojo4 = new AdapterPojo(28, "Average Assists : First 10 Minutes", 1.42, 1.43, MID, -1);
        AdapterPojo adapterPojo5 = new AdapterPojo(29, "Average Kills: 10 - 20 Minutes", 1.94, 2.43, MID, -1);
        AdapterPojo adapterPojo6 = new AdapterPojo(30, "Average Deaths : 10 - 20 Minutes", 1.62, 2.31, MID, -1);
        AdapterPojo adapterPojo7 = new AdapterPojo(31, "Average Assists : 10 - 20 Minutes", 1.42, 1.43, MID, -1);
        List<AdapterPojo> responseList = new ArrayList<>();
        responseList.add(adapterPojo);
        responseList.add(adapterPojo2);
        responseList.add(adapterPojo3);
        responseList.add(adapterPojo1);
        responseList.add(adapterPojo4);
        responseList.add(adapterPojo5);
        responseList.add(adapterPojo6);
        responseList.add(adapterPojo7);
        return responseList;
    }

    public List<AdapterPojo> FetchAdcStats() {
        AdapterPojo adapterPojo = new AdapterPojo(1, "Creep Score: First Ten Minutes", 65.75, 71.46,ADC, -1);
        AdapterPojo adapterPojo1 = new AdapterPojo(2, "Creep Score: 10 - 20 Minutes", 71.65, 76.43, ADC, -1);
        AdapterPojo adapterPojo2 = new AdapterPojo(3, "Average Kills: First 10 Minutes", 1.94, 2.43, ADC, -1);
        AdapterPojo adapterPojo3 = new AdapterPojo(4, "Average Deaths : First 10 Minutes", 1.62, 2.31, ADC, -1);
        AdapterPojo adapterPojo4 = new AdapterPojo(5, "Average Assists : First 10 Minutes", 1.42, 1.43, ADC, -1);
        AdapterPojo adapterPojo5 = new AdapterPojo(6, "Average Kills: 10 - 20 Minutes", 1.94, 2.43, ADC, -1);
        AdapterPojo adapterPojo6 = new AdapterPojo(7, "Average Deaths : 10 - 20 Minutes", 1.62, 2.31, ADC, -1);
        AdapterPojo adapterPojo7 = new AdapterPojo(8, "Average Assists : 10 - 20 Minutes", 1.42, 1.43, ADC, -1);
        List<AdapterPojo> responseList = new ArrayList<>();
        responseList.add(adapterPojo);
        responseList.add(adapterPojo2);
        responseList.add(adapterPojo3);
        responseList.add(adapterPojo1);
        responseList.add(adapterPojo4);
        responseList.add(adapterPojo5);
        responseList.add(adapterPojo6);
        responseList.add(adapterPojo7);
        return responseList;
    }

    public List<AdapterPojo> FetchJungleStats() {
        AdapterPojo adapterPojo = new AdapterPojo(9, "Creep Score: First Ten Minutes", 46.25, 37.56, JUNGLE, -1);
        AdapterPojo adapterPojo1 = new AdapterPojo(10, "Enemy Jungle Minions Killed", 34.67, 46.34, JUNGLE, -1);
        AdapterPojo adapterPojo2 = new AdapterPojo(11, "Average Kills: First 10 Minutes", 2.34, 2.83, JUNGLE, -1);
        AdapterPojo adapterPojo3 = new AdapterPojo(12, "Average Deaths : First 10 Minutes", 1.62, 2.31, JUNGLE, -1);
        AdapterPojo adapterPojo4 = new AdapterPojo(13, "Average Assists : First 10 Minutes", 1.42, 1.43, JUNGLE, -1);
        AdapterPojo adapterPojo5 = new AdapterPojo(14, "Average Kills: 10 - 20 Minutes", 1.94, 2.43, JUNGLE, -1);
        AdapterPojo adapterPojo6 = new AdapterPojo(15, "Average Deaths : 10 - 20 Minutes", 1.62, 2.31, JUNGLE, -1);
        AdapterPojo adapterPojo7 = new AdapterPojo(16, "Average Assists : 10 - 20 Minutes", 1.42, 1.43, JUNGLE, -1);

        List<AdapterPojo> responseList = new ArrayList<>();
        responseList.add(adapterPojo);
        responseList.add(adapterPojo2);
        responseList.add(adapterPojo3);
        responseList.add(adapterPojo1);
        responseList.add(adapterPojo4);
        responseList.add(adapterPojo5);
        responseList.add(adapterPojo6);
        responseList.add(adapterPojo7);
        return responseList;
    }

    public List<AdapterPojo> FetchSupportStats() {
        AdapterPojo adapterPojo = new AdapterPojo(17, "Crowd Control Dealt (Seconds) ", 3.41, 8.56, SUPPORT, -1);
        AdapterPojo adapterPojo2 = new AdapterPojo(18, "Average Kills: First 10 Minutes", 0.45, 0.83, SUPPORT, -1);
        AdapterPojo adapterPojo3 = new AdapterPojo(19, "Average Deaths : First 10 Minutes", 1.62, 2.31, SUPPORT, -1);
        AdapterPojo adapterPojo4 = new AdapterPojo(20, "Average Assists : First 10 Minutes", 1.42, 1.43, SUPPORT, -1);
        AdapterPojo adapterPojo5 = new AdapterPojo(21, "Average Kills: 10 - 20 Minutes", 1.94, 2.43, SUPPORT, -1);
        AdapterPojo adapterPojo6 = new AdapterPojo(22, "Average Deaths : 10 - 20 Minutes", 1.62, 2.31, SUPPORT, -1);
        AdapterPojo adapterPojo7 = new AdapterPojo(23, "Average Assists : 10 - 20 Minutes", 1.42, 1.43, SUPPORT, -1);

        List<AdapterPojo> responseList = new ArrayList<>();
        responseList.add(adapterPojo);
        responseList.add(adapterPojo2);
        responseList.add(adapterPojo3);
        responseList.add(adapterPojo4);
        responseList.add(adapterPojo5);
        responseList.add(adapterPojo6);
        responseList.add(adapterPojo7);
        return responseList;
    }

    public com.teamunemployment.lolanalytics.models.Beans.Double FetchWinRate(String role) {
        switch (role) {
            case Role.TOP:
                return new Double(54.72);
            case Role.JUNGLE:
                return new Double(48.72);
            case Role.MID:
                return new Double(50.00);
            case Role.PLAYMAKER:
                return new Double(55.00);
            case Role.MARKSMAN:
                return new Double(45.00);
        }
        return new Double(0.0);
    }
}

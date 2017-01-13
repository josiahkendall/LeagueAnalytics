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

    // Tabs are 8 spaces even when set to 4 spaces is this ide retarded
    public List<AdapterPojo> FetchTopStats() {
        AdapterPojo adapterPojo = new AdapterPojo("Creep Score: First Ten Minutes", 65.75, 71.46);
        AdapterPojo adapterPojo1 = new AdapterPojo("Creep Score: 10 - 20 Minutes", 71.65, 76.43);
        AdapterPojo adapterPojo2 = new AdapterPojo("Average Kills: First 10 Minutes", 1.94, 2.43);
        AdapterPojo adapterPojo3 = new AdapterPojo("Average Deaths : First 10 Minutes", 1.62, 2.31);
        AdapterPojo adapterPojo4 = new AdapterPojo("Average Assists : First 10 Minutes", 1.42, 1.43);
        AdapterPojo adapterPojo5 = new AdapterPojo("Average Kills: 10 - 20 Minutes", 1.94, 2.43);
        AdapterPojo adapterPojo6 = new AdapterPojo("Average Deaths : 10 - 20 Minutes", 1.62, 2.31);
        AdapterPojo adapterPojo7 = new AdapterPojo("Average Assists : 10 - 20 Minutes", 1.42, 1.43);
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
        AdapterPojo adapterPojo = new AdapterPojo("Creep Score: First Ten Minutes", 65.75, 71.46);
        AdapterPojo adapterPojo1 = new AdapterPojo("Creep Score: 10 - 20 Minutes", 71.65, 76.43);
        AdapterPojo adapterPojo2 = new AdapterPojo("Average Kills: First 10 Minutes", 1.94, 2.43);
        AdapterPojo adapterPojo3 = new AdapterPojo("Average Deaths : First 10 Minutes", 1.62, 2.31);
        AdapterPojo adapterPojo4 = new AdapterPojo("Average Assists : First 10 Minutes", 1.42, 1.43);
        AdapterPojo adapterPojo5 = new AdapterPojo("Average Kills: 10 - 20 Minutes", 1.94, 2.43);
        AdapterPojo adapterPojo6 = new AdapterPojo("Average Deaths : 10 - 20 Minutes", 1.62, 2.31);
        AdapterPojo adapterPojo7 = new AdapterPojo("Average Assists : 10 - 20 Minutes", 1.42, 1.43);
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
        AdapterPojo adapterPojo = new AdapterPojo("Creep Score: First Ten Minutes", 65.75, 71.46);
        AdapterPojo adapterPojo1 = new AdapterPojo("Creep Score: 10 - 20 Minutes", 71.65, 76.43);
        AdapterPojo adapterPojo2 = new AdapterPojo("Average Kills: First 10 Minutes", 1.94, 2.43);
        AdapterPojo adapterPojo3 = new AdapterPojo("Average Deaths : First 10 Minutes", 1.62, 2.31);
        AdapterPojo adapterPojo4 = new AdapterPojo("Average Assists : First 10 Minutes", 1.42, 1.43);
        AdapterPojo adapterPojo5 = new AdapterPojo("Average Kills: 10 - 20 Minutes", 1.94, 2.43);
        AdapterPojo adapterPojo6 = new AdapterPojo("Average Deaths : 10 - 20 Minutes", 1.62, 2.31);
        AdapterPojo adapterPojo7 = new AdapterPojo("Average Assists : 10 - 20 Minutes", 1.42, 1.43);
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
        AdapterPojo adapterPojo = new AdapterPojo("Creep Score: First Ten Minutes", 46.25, 37.56);
        AdapterPojo adapterPojo1 = new AdapterPojo("Enemy Jungle Minions Killed", 34.67, 46.34);
        AdapterPojo adapterPojo2 = new AdapterPojo("Average Kills: First 10 Minutes", 2.34, 2.83);
        AdapterPojo adapterPojo3 = new AdapterPojo("Average Deaths : First 10 Minutes", 1.62, 2.31);
        AdapterPojo adapterPojo4 = new AdapterPojo("Average Assists : First 10 Minutes", 1.42, 1.43);
        AdapterPojo adapterPojo5 = new AdapterPojo("Average Kills: 10 - 20 Minutes", 1.94, 2.43);
        AdapterPojo adapterPojo6 = new AdapterPojo("Average Deaths : 10 - 20 Minutes", 1.62, 2.31);
        AdapterPojo adapterPojo7 = new AdapterPojo("Average Assists : 10 - 20 Minutes", 1.42, 1.43);

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
        AdapterPojo adapterPojo = new AdapterPojo("Crowd Control Dealt (Seconds) ", 3.41, 8.56);
        AdapterPojo adapterPojo2 = new AdapterPojo("Average Kills: First 10 Minutes", 0.45, 0.83);
        AdapterPojo adapterPojo3 = new AdapterPojo("Average Deaths : First 10 Minutes", 1.62, 2.31);
        AdapterPojo adapterPojo4 = new AdapterPojo("Average Assists : First 10 Minutes", 1.42, 1.43);
        AdapterPojo adapterPojo5 = new AdapterPojo("Average Kills: 10 - 20 Minutes", 1.94, 2.43);
        AdapterPojo adapterPojo6 = new AdapterPojo("Average Deaths : 10 - 20 Minutes", 1.62, 2.31);
        AdapterPojo adapterPojo7 = new AdapterPojo("Average Assists : 10 - 20 Minutes", 1.42, 1.43);

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

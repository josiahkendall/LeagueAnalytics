package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.models.stats.*;

/**
 * @author Josiah Kendall
 *
 * This is the control class for each of the different deltas.
 */
public class DeltaControl {

    private BaseDeltaControl baseDeltaControl;

    public DeltaControl(BaseDeltaControl baseDeltaControl) {
        this.baseDeltaControl = baseDeltaControl;
    }

    /**
     * Save a instance ofxpPerMinDeltas for a player in a game.
     * @param xpPerMinDeltas The deltas that we want to save
     * @return The id of the saved row in the database.
     */
    public int SaveXpPerMinDeltas(BaseDeltas xpPerMinDeltas){
        return baseDeltaControl.saveDeltas(xpPerMinDeltas,"XpPerMinDeltas" );
    }

    public BaseDeltas GetXpPerMinDeltas(int id) {
        return baseDeltaControl.getDeltas("XpPerMinDeltas", id);
    }

    /**
     * Save the xpDiffPerMinDeltas.
     * @param xpDiffPerMinDeltas The delta to save.
     * @return The id of the saved row in the database.
     */
    public int SaveXpDiffPerMinDeltas(BaseDeltas xpDiffPerMinDeltas) {
        return baseDeltaControl.saveDeltas(xpDiffPerMinDeltas,"XpDiffPerMinDeltas");
    }

    public BaseDeltas GetXpDiffPerMinDeltas(int id) {
        return baseDeltaControl.getDeltas("XpDiffPerMinDeltas", id);
    }

    /**
     * Save the DamageTakenPerMinDeltas to the database.
     * @param damageTakenPerMinDeltas The deltas to save.
     * @return The row id of the saved delta.
     */
    public int SaveDamageTakenPerMinDeltas(BaseDeltas damageTakenPerMinDeltas) {

        return baseDeltaControl.saveDeltas(damageTakenPerMinDeltas,"DamageTakenPerMinDeltas");
    }

    public BaseDeltas GetDamageTakenPerMinDeltas(int id) {
        return baseDeltaControl.getDeltas("DamageTakenPerMinDeltas",id);
    }

    public int SaveDamageTakenDiffPerMinDeltas(BaseDeltas damageTakenDiffPerMinDeltas) {

        return baseDeltaControl.saveDeltas(damageTakenDiffPerMinDeltas,"DamageTakenDiffPerMinDeltas");
    }

    public BaseDeltas GetDamageTakenDiffPerMinDeltas(int id) {
        return baseDeltaControl.getDeltas("DamageTakenDiffPerMinDeltas", id);
    }

    public int SaveCreepsPerMinDeltas(BaseDeltas creepsPerMinDeltas) {
        return baseDeltaControl.saveDeltas(creepsPerMinDeltas, "CreepsPerMinDeltas");
    }

    public BaseDeltas GetCreepsPerMinDeltas(int id) {
        return baseDeltaControl.getDeltas("CreepsPerMinDeltas", id);
    }

    public int SaveCsDiffPerMinDeltas(BaseDeltas csDiffPerMinDeltas) {
        return baseDeltaControl.saveDeltas(csDiffPerMinDeltas, "CsDiffPerMinDeltas");
    }

    public BaseDeltas GetCsDiffPerMinDeltas(int id) {
        return baseDeltaControl.getDeltas("CsDiffPerMinDeltas", id);
    }

    public int SaveGoldPerMinDeltas(BaseDeltas goldPerMinDeltas) {
        return baseDeltaControl.saveDeltas(goldPerMinDeltas, "GoldPerMinDeltas");

    }

    public BaseDeltas GetGoldPerMinDeltas(int id) {
        return baseDeltaControl.getDeltas("GoldPerMInDeltas", id);
    }

    public int SaveGoldDiffPerMinDeltas(BaseDeltas baseDeltas) {
        return baseDeltaControl.saveDeltas(baseDeltas, "GoldDiffPerMinDeltas");
    }

    public BaseDeltas GetGoldDiffPerMinDeltas(int id) {
        return baseDeltaControl.getDeltas("GoldDiffPerMinDeltas", id);
    }





}

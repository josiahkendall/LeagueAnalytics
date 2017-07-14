package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.stats.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.DecimalMax;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author Josiah Kendall
 */
public class DeltaTests {

    private DeltaControl deltaControl;
    @Before
    public void  init() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        BaseDeltaControl baseDeltaControl = new BaseDeltaControl(dbHelper);
        deltaControl = new DeltaControl(baseDeltaControl);
    }

    @Test
    public void CanSave_XpPerMinDeltas() {
        Random random = new Random();
        BaseDeltas xpPerMinDeltas = new BaseDeltas();
        xpPerMinDeltas.setZeroToTen(random.nextDouble());
        xpPerMinDeltas.setTenToTwenty(random.nextDouble());
        xpPerMinDeltas.setTwentyToThirty(random.nextDouble());
        xpPerMinDeltas.setThirtyToEnd(random.nextDouble());
        int id = deltaControl.SaveXpPerMinDeltas(xpPerMinDeltas);
        BaseDeltas xpPerMinDeltas1 = deltaControl.GetXpPerMinDeltas(id);

        Assert.assertEquals(xpPerMinDeltas.getZeroToTen(), xpPerMinDeltas1.getZeroToTen(), 0.005);
        Assert.assertEquals(xpPerMinDeltas.getTenToTwenty(), xpPerMinDeltas1.getTenToTwenty(), 0.005);
        Assert.assertEquals(xpPerMinDeltas.getTwentyToThirty(), xpPerMinDeltas1.getTwentyToThirty(), 0.005);
        Assert.assertEquals(xpPerMinDeltas.getThirtyToEnd(), xpPerMinDeltas1.getThirtyToEnd(), 0.005);

    }

    @Test
    public void CanSave_DamageTakenPerMinDeltas() {
        Random random = new Random();
        BaseDeltas damageTakenPerMinDeltas = new BaseDeltas();
        damageTakenPerMinDeltas.setZeroToTen(random.nextDouble());
        damageTakenPerMinDeltas.setTenToTwenty(random.nextDouble());
        damageTakenPerMinDeltas.setTwentyToThirty(random.nextDouble());
        damageTakenPerMinDeltas.setThirtyToEnd(random.nextDouble());
        int id = deltaControl.SaveDamageTakenPerMinDeltas(damageTakenPerMinDeltas);
        BaseDeltas xpPerMinDeltas1 = deltaControl.GetDamageTakenPerMinDeltas(id);

        Assert.assertEquals(damageTakenPerMinDeltas.getZeroToTen(), xpPerMinDeltas1.getZeroToTen(), 0.005);
        Assert.assertEquals(damageTakenPerMinDeltas.getTenToTwenty(), xpPerMinDeltas1.getTenToTwenty(), 0.005);
        Assert.assertEquals(damageTakenPerMinDeltas.getTwentyToThirty(), xpPerMinDeltas1.getTwentyToThirty(), 0.005);
        Assert.assertEquals(damageTakenPerMinDeltas.getThirtyToEnd(), xpPerMinDeltas1.getThirtyToEnd(), 0.005);
    }

    @Test
    public void CanSave_DamageTakenDiffPerMinDeltas() {
        Random random = new Random();
        BaseDeltas damageTakenDiffPerMinDeltas = new BaseDeltas();
        damageTakenDiffPerMinDeltas.setZeroToTen(random.nextDouble());
        damageTakenDiffPerMinDeltas.setTenToTwenty(random.nextDouble());
        damageTakenDiffPerMinDeltas.setTwentyToThirty(random.nextDouble());
        damageTakenDiffPerMinDeltas.setThirtyToEnd(random.nextDouble());
        int id = deltaControl.SaveDamageTakenDiffPerMinDeltas(damageTakenDiffPerMinDeltas);
        BaseDeltas xpPerMinDeltas1 = deltaControl.GetDamageTakenDiffPerMinDeltas(id);

        Assert.assertEquals(damageTakenDiffPerMinDeltas.getZeroToTen(), xpPerMinDeltas1.getZeroToTen(), 0.005);
        Assert.assertEquals(damageTakenDiffPerMinDeltas.getTenToTwenty(), xpPerMinDeltas1.getTenToTwenty(), 0.005);
        Assert.assertEquals(damageTakenDiffPerMinDeltas.getTwentyToThirty(), xpPerMinDeltas1.getTwentyToThirty(), 0.005);
        Assert.assertEquals(damageTakenDiffPerMinDeltas.getThirtyToEnd(), xpPerMinDeltas1.getThirtyToEnd(), 0.005);
    }

    @Test
    public void CanSave_XpDiffPerMinDeltas() {
        Random random = new Random();
        BaseDeltas xpPerMinDeltas = new BaseDeltas();
        xpPerMinDeltas.setZeroToTen(random.nextDouble());
        xpPerMinDeltas.setTenToTwenty(random.nextDouble());
        xpPerMinDeltas.setTwentyToThirty(random.nextDouble());
        xpPerMinDeltas.setThirtyToEnd(random.nextDouble());
        int id = deltaControl.SaveXpDiffPerMinDeltas(xpPerMinDeltas);
        BaseDeltas xpPerMinDeltas1 = deltaControl.GetXpDiffPerMinDeltas(id);

        Assert.assertEquals(xpPerMinDeltas.getZeroToTen(), xpPerMinDeltas1.getZeroToTen(), 0.005);
        Assert.assertEquals(xpPerMinDeltas.getTenToTwenty(), xpPerMinDeltas1.getTenToTwenty(), 0.005);
        Assert.assertEquals(xpPerMinDeltas.getTwentyToThirty(), xpPerMinDeltas1.getTwentyToThirty(), 0.005);
        Assert.assertEquals(xpPerMinDeltas.getThirtyToEnd(), xpPerMinDeltas1.getThirtyToEnd(), 0.005);
    }

    @Test
    public void CanSave_CreepsPerMin() {
        Random random = new Random();
        BaseDeltas creepsPerMinDeltas = new BaseDeltas();
        creepsPerMinDeltas.setZeroToTen(random.nextDouble());
        creepsPerMinDeltas.setTenToTwenty(random.nextDouble());
        creepsPerMinDeltas.setTwentyToThirty(random.nextDouble());
        creepsPerMinDeltas.setThirtyToEnd(random.nextDouble());
        int id = deltaControl.SaveCreepsPerMinDeltas(creepsPerMinDeltas);
        BaseDeltas creepsPerMinDeltas1 = deltaControl.GetCreepsPerMinDeltas(id);

        Assert.assertEquals(creepsPerMinDeltas.getZeroToTen(), creepsPerMinDeltas1.getZeroToTen(), 0.005);
        Assert.assertEquals(creepsPerMinDeltas.getTenToTwenty(), creepsPerMinDeltas1.getTenToTwenty(), 0.005);
        Assert.assertEquals(creepsPerMinDeltas.getTwentyToThirty(), creepsPerMinDeltas1.getTwentyToThirty(), 0.005);
        Assert.assertEquals(creepsPerMinDeltas.getThirtyToEnd(), creepsPerMinDeltas1.getThirtyToEnd(), 0.005);
    }
    @Test
    public void CanSave_CsDiffPerMin() {
        Random random = new Random();
        BaseDeltas csDiffPerMinDeltas = new BaseDeltas();
        csDiffPerMinDeltas.setZeroToTen(random.nextDouble());
        csDiffPerMinDeltas.setTenToTwenty(random.nextDouble());
        csDiffPerMinDeltas.setTwentyToThirty(random.nextDouble());
        csDiffPerMinDeltas.setThirtyToEnd(random.nextDouble());
        int id = deltaControl.SaveCsDiffPerMinDeltas(csDiffPerMinDeltas);
        BaseDeltas csDiffPerMinDeltas1 = deltaControl.GetCsDiffPerMinDeltas(id);

        Assert.assertEquals(csDiffPerMinDeltas.getZeroToTen(), csDiffPerMinDeltas1.getZeroToTen(), 0.005);
        Assert.assertEquals(csDiffPerMinDeltas.getTenToTwenty(), csDiffPerMinDeltas1.getTenToTwenty(), 0.005);
        Assert.assertEquals(csDiffPerMinDeltas.getTwentyToThirty(), csDiffPerMinDeltas1.getTwentyToThirty(), 0.005);
        Assert.assertEquals(csDiffPerMinDeltas.getThirtyToEnd(), csDiffPerMinDeltas1.getThirtyToEnd(), 0.005);
    }

    @Test
    public void CanSave_GoldPerMin() {
        Random random = new Random();
        BaseDeltas goldPerMinDeltas = new BaseDeltas();
        goldPerMinDeltas.setZeroToTen(random.nextDouble());
        goldPerMinDeltas.setTenToTwenty(random.nextDouble());
        goldPerMinDeltas.setTwentyToThirty(random.nextDouble());
        goldPerMinDeltas.setThirtyToEnd(random.nextDouble());
        int id = deltaControl.SaveGoldPerMinDeltas(goldPerMinDeltas);
        BaseDeltas goldPerMinDeltas1 = deltaControl.GetGoldPerMinDeltas(id);

        Assert.assertEquals(goldPerMinDeltas.getZeroToTen(), goldPerMinDeltas1.getZeroToTen(), 0.005);
        Assert.assertEquals(goldPerMinDeltas.getTenToTwenty(), goldPerMinDeltas1.getTenToTwenty(), 0.005);
        Assert.assertEquals(goldPerMinDeltas.getTwentyToThirty(), goldPerMinDeltas1.getTwentyToThirty(), 0.005);
        Assert.assertEquals(goldPerMinDeltas.getThirtyToEnd(), goldPerMinDeltas1.getThirtyToEnd(), 0.005);
    }

    @Test
    public void CanSave_GoldDiffPerMin() {
        Random random = new Random();
        BaseDeltas goldPerMInDeltas2 = new BaseDeltas();
        goldPerMInDeltas2.setZeroToTen(random.nextDouble());
        goldPerMInDeltas2.setTenToTwenty(random.nextDouble());
        goldPerMInDeltas2.setTwentyToThirty(random.nextDouble());
        goldPerMInDeltas2.setThirtyToEnd(random.nextDouble());
        int id = deltaControl.SaveGoldDiffPerMinDeltas(goldPerMInDeltas2);
        BaseDeltas goldDiffPerMinDeltas = deltaControl.GetGoldDiffPerMinDeltas(id);

        Assert.assertEquals(goldPerMInDeltas2.getZeroToTen(), goldDiffPerMinDeltas.getZeroToTen(), 0.005);
        Assert.assertEquals(goldPerMInDeltas2.getTenToTwenty(), goldDiffPerMinDeltas.getTenToTwenty(), 0.005);
        Assert.assertEquals(goldPerMInDeltas2.getTwentyToThirty(), goldDiffPerMinDeltas.getTwentyToThirty(), 0.005);
        Assert.assertEquals(goldPerMInDeltas2.getThirtyToEnd(), goldDiffPerMinDeltas.getThirtyToEnd(), 0.005);
    }
}

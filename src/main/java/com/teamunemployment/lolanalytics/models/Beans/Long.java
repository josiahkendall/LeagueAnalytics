package com.teamunemployment.lolanalytics.models.Beans;

/**
 * @author Josiah Kendall
 */
public class Long {
    private long aLong;

    public Long(){}
    public Long(long aLong) {
        this.aLong = aLong;
    }

    public void setValue(long aLong) {
        this.aLong = aLong;
    }

    public long getValue() {
        return aLong;
    }
}

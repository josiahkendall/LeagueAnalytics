package com.teamunemployment.lolanalytics.models.stats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



/**
 * @author Josiah Kendall
 */
public class Timeline {

    private int id;
    @SerializedName("creepsPerMinDeltas")
    @Expose
    private BaseDeltas creepsPerMinDeltas;
    @SerializedName("xpPerMinDeltas")
    @Expose
    private BaseDeltas xpPerMinDeltas;
    @SerializedName("goldPerMinDeltas")
    @Expose
    private BaseDeltas goldPerMinDeltas;
    @SerializedName("csDiffPerMinDeltas")
    @Expose
    private BaseDeltas csDiffPerMinDeltas;
    @SerializedName("xpDiffPerMinDeltas")
    @Expose
    private BaseDeltas xpDiffPerMinDeltas;
    @SerializedName("damageTakenPerMinDeltas")
    @Expose
    private BaseDeltas damageTakenPerMinDeltas;
    @SerializedName("damageTakenDiffPerMinDeltas")
    @Expose
    private BaseDeltas damageTakenDiffPerMinDeltas;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("lane")
    @Expose
    private String lane;
    
    public int getId () {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    /**
     *
     * @return
     * The creepsPerMinDeltas
     */
    public BaseDeltas getCreepsPerMinDeltas() {
        return creepsPerMinDeltas;
    }

    /**
     *
     * @param creepsPerMinDeltas
     * The creepsPerMinDeltas
     */
    public void setCreepsPerMinDeltas(BaseDeltas creepsPerMinDeltas) {
        this.creepsPerMinDeltas = creepsPerMinDeltas;
    }

    /**
     *
     * @return
     * The xpPerMinDeltas
     */
    public BaseDeltas getXpPerMinDeltas() {
        return xpPerMinDeltas;
    }

    /**
     *
     * @param xpPerMinDeltas
     * The xpPerMinDeltas
     */
    public void setXpPerMinDeltas(BaseDeltas xpPerMinDeltas) {
        this.xpPerMinDeltas = xpPerMinDeltas;
    }

    /**
     *
     * @return
     * The goldPerMinDeltas
     */
    public BaseDeltas getGoldPerMinDeltas() {
        return goldPerMinDeltas;
    }

    /**
     *
     * @param goldPerMinDeltas
     * The goldPerMinDeltas
     */
    public void setGoldPerMinDeltas(BaseDeltas goldPerMinDeltas) {
        this.goldPerMinDeltas = goldPerMinDeltas;
    }

    /**
     *
     * @return
     * The csDiffPerMinDeltas
     */
    public BaseDeltas getCsDiffPerMinDeltas() {
        return csDiffPerMinDeltas;
    }

    /**
     *
     * @param csDiffPerMinDeltas
     * The csDiffPerMinDeltas
     */
    public void setCsDiffPerMinDeltas(BaseDeltas csDiffPerMinDeltas) {
        this.csDiffPerMinDeltas = csDiffPerMinDeltas;
    }

    /**
     *
     * @return
     * The xpDiffPerMinDeltas
     */
    public BaseDeltas getXpDiffPerMinDeltas() {
        return xpDiffPerMinDeltas;
    }

    /**
     *
     * @param xpDiffPerMinDeltas
     * The xpDiffPerMinDeltas
     */
    public void setXpDiffPerMinDeltas(BaseDeltas xpDiffPerMinDeltas) {
        this.xpDiffPerMinDeltas = xpDiffPerMinDeltas;
    }

    /**
     *
     * @return
     * The damageTakenPerMinDeltas
     */
    public BaseDeltas getDamageTakenPerMinDeltas() {
        return damageTakenPerMinDeltas;
    }

    /**
     *
     * @param damageTakenPerMinDeltas
     * The damageTakenPerMinDeltas
     */
    public void setDamageTakenPerMinDeltas(BaseDeltas damageTakenPerMinDeltas) {
        this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
    }

    /**
     *
     * @return
     * The damageTakenDiffPerMinDeltas
     */
    public BaseDeltas getDamageTakenDiffPerMinDeltas() {
        return damageTakenDiffPerMinDeltas;
    }

    /**
     *
     * @param damageTakenDiffPerMinDeltas
     * The damageTakenDiffPerMinDeltas
     */
    public void setDamageTakenDiffPerMinDeltas(BaseDeltas damageTakenDiffPerMinDeltas) {
        this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
    }

    /**
     *
     * @return
     * The role
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @param role
     * The role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     *
     * @return
     * The lane
     */
    public String getLane() {
        return lane;
    }

    /**
     *
     * @param lane
     * The lane
     */
    public void setLane(String lane) {
        this.lane = lane;
    }

}

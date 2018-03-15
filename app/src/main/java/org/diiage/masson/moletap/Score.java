package org.diiage.masson.moletap;

/**
 * Created by Victor Masson on 15/03/2018.
 */

public class Score {
    private int nombrePoint;
    private int nombreRate;
    private int tempsReactionMax;
    private int tempsReactionMin;
    private int tempsReactionMoy;

    public Score() {
        this.nombrePoint = 0;
        this.nombreRate = 0;
    }

    public Score(int nombrePoint, int nombreRate, int tempsReactionMax, int tempsReactionMin, int tempsReactionMoy) {
        this.nombrePoint = nombrePoint;
        this.nombreRate = nombreRate;
        this.tempsReactionMax = tempsReactionMax;
        this.tempsReactionMin = tempsReactionMin;
        this.tempsReactionMoy = tempsReactionMoy;
    }

    public int getNombrePoint() {
        return nombrePoint;
    }

    public void setNombrePoint(int nombrePoint) {
        this.nombrePoint = nombrePoint;
    }

    public int getNombreRate() {
        return nombreRate;
    }

    public void setNombreRate(int nombreRate) {
        this.nombreRate = nombreRate;
    }

    public int getTempsReactionMax() {
        return tempsReactionMax;
    }

    public void setTempsReactionMax(int tempsReactionMax) {
        this.tempsReactionMax = tempsReactionMax;
    }

    public int getTempsReactionMin() {
        return tempsReactionMin;
    }

    public void setTempsReactionMin(int tempsReactionMin) {
        this.tempsReactionMin = tempsReactionMin;
    }

    public int getTempsReactionMoy() {
        return tempsReactionMoy;
    }

    public void setTempsReactionMoy(int tempsReactionMoy) {
        this.tempsReactionMoy = tempsReactionMoy;
    }

    public void addToScore(){
        this.nombrePoint++;
    }
    public void addToRate(){ this.nombreRate++; }
}

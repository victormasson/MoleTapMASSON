package org.diiage.masson.moletap;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Victor Masson on 15/03/2018.
 */

public class Session implements Parcelable {
    private Date date;
    private String nomJoueur;
    private ArrayList<Score> scores;

    public Session() {
        this.date = new Date();
        this.scores = new ArrayList<Score>();
    }

    public Session(Date date, String nomJoueur, ArrayList<Score> scores) {
        this.date = date;
        this.nomJoueur = nomJoueur;
        this.scores = scores;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

    public void addScore(Score score){
        this.getScores().add(score);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(date.toString());
        dest.writeString(nomJoueur);
        dest.writeArray(scores.toArray());
    }

    public static final Parcelable.Creator<Session> CREATOR = new Parcelable.Creator<Session>()
    {
        @Override
        public Session createFromParcel(Parcel source)
        {
            return new Session(source);
        }

        @Override
        public Session[] newArray(int size)
        {
            return new Session[size];
        }
    };

    public Session (Parcel in) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(in.readString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.date = convertedDate;
        this.nomJoueur = in.readString();
        this.scores = in.readArrayList(Session.class.getClassLoader());
    }
}
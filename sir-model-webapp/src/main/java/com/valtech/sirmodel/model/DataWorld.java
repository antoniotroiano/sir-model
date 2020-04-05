package com.valtech.sirmodel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dataWorld")
public class DataWorld {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dataWorldId;
    private int confirmed;
    private int recovered;
    private int deaths;
    private String lastUpdate;

    public DataWorld() {
    }

    public DataWorld(DataWorld dataWorld) {
        this.dataWorldId = dataWorld.getDataWorldId();
        this.confirmed = dataWorld.getConfirmed();
        this.recovered = dataWorld.getRecovered();
        this.deaths = dataWorld.getDeaths();
        this.lastUpdate = dataWorld.getLastUpdate();
    }

    public long getDataWorldId() {
        return dataWorldId;
    }

    public void setDataWorldId(long dataWorldId) {
        this.dataWorldId = dataWorldId;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "DataWorld{" +
                "lastUpdate='" + lastUpdate + '\'' +
                '}';
    }
}
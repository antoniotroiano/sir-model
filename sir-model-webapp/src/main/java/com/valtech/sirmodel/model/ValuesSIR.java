package com.valtech.sirmodel.model;

public class ValuesSIR {

    Double transmissionRate;
    Double recoveryRate;

    public ValuesSIR() {
    }

    public ValuesSIR(Double transmissionRate, Double recoveryRate) {
        this.transmissionRate = transmissionRate;
        this.recoveryRate = recoveryRate;
    }

    public Double getTransmissionRate() {
        return transmissionRate;
    }

    public void setTransmissionRate(Double transmissionRate) {
        this.transmissionRate = transmissionRate;
    }

    public Double getRecoveryRate() {
        return recoveryRate;
    }

    public void setRecoveryRate(Double recoveryRate) {
        this.recoveryRate = recoveryRate;
    }
}
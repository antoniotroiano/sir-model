package com.valtech.sirmodel.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ValuesSIRTest {

    private ValuesSIR valuesSIR = new ValuesSIR();

    @BeforeEach
    public void setUp() {
        valuesSIR.setTransmissionRate(1.0);
        valuesSIR.setRecoveryRate(2.0);
    }

    @Test
    public void createdValuesSIR() {
        assertThat(valuesSIR).isInstanceOf(ValuesSIR.class);
    }

    @Test
    public void hasTransmissionRate() {
        assertThat(valuesSIR.getTransmissionRate()).isEqualTo(1.0);
    }

    @Test
    public void hasRecoveryRate() {
        assertThat(valuesSIR.getRecoveryRate()).isEqualTo(2.0);
    }

    @Test
    public void constructorTest() {
        ValuesSIR valuesSIRTwo = new ValuesSIR(1.0, 2.0);
        assertThat(valuesSIRTwo).isInstanceOf(ValuesSIR.class);
    }
}

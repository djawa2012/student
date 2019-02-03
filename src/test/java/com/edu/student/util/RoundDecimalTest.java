package com.edu.student.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoundDecimalTest {

    private RoundDecimal roundDecimal;

    @Before
    public void setup () {
        roundDecimal = new RoundDecimal();
    }

    @Test
    public void roundUp () {
        assertEquals(2.55d, roundDecimal.round2DecimalPoint(2.5455d), 0d);
    }

    @Test
    public void roundDown () {
        assertEquals(2.54d, roundDecimal.round2DecimalPoint(2.5445d), 0d);
    }

}
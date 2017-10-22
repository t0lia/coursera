package com.firefly.crypto.week3;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Problem3Test {

    @Test
    public void testGetFor() throws Exception {
        Problem3 problem3 = new Problem3();
        Assert.assertEquals(problem3.getFor("exp.bin"), "5b96aece304a1422224f9a41b228416028f9ba26b0d1058f400200f06a589949");
        Assert.assertEquals(problem3.getFor("act.bin"), "03c08f4ee0b576fe319338139c045c89c3e8e9409633bea29442e21425006ea8");
    }
}
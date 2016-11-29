package com.vishpat.projeuler;

import java.math.BigInteger;

/**
 * Created by vpati011 on 11/29/16.
 */
public class Problem25 {

    public void solve() {
        BigInteger f1 = BigInteger.valueOf(1);
        BigInteger f2 = BigInteger.valueOf(1);

        int index = 2;
        while (f2.toString().length() < 1000) {
            BigInteger oldf2 = f2;
            f2 = f2.add(f1);
            f1 = oldf2;
            index++;
        }

        System.out.println(index);
    }
}

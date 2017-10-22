package com.firefly.crypto;


import com.firefly.crypto.week1.Problem1;
import com.firefly.crypto.week2.Problem2;
import com.firefly.crypto.week3.Problem3;
import com.firefly.crypto.week4.Problem4;
import com.firefly.crypto.week5.Problem5;
import com.firefly.crypto.week6.Problem6;

class Crypto {
    String resolve(int number) {
        if (number < 1 || number > 6) {
            throw new IllegalArgumentException();
        }
        Problem problem;
        switch (number) {
            case 1:
                problem = new Problem1();
                break;
            case 2:
                problem = new Problem2();
                break;
            case 3:
                problem = new Problem3();
                break;
            case 4:
                problem = new Problem4();
                break;
            case 5:
                problem = new Problem5();
                break;
            case 6:
                problem = new Problem6();
                break;
            default:
                return "";
        }
        return problem.resolve();
    }
}

package com.firefly.crypto.week5;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class Problem5Test {
    private static BigInteger num(long number) {
        return new BigInteger(Long.toString(number));
    }

    private static BigInteger num(String number) {
        return new BigInteger(number);
    }

    private class Pair {
        public Pair(Function<Long, BigInteger> fun, Long b) {
            this.a = fun.apply(b);
            this.b = b;
        }

        public Pair(BigInteger a, Long b) {
            this.a = a;
            this.b = b;
        }

        private BigInteger a;
        private Long b;

        public BigInteger key() {
            return a;
        }

        public void setA(BigInteger a) {
            this.a = a;
        }

        public Long value() {
            return b;
        }

        public void setB(Long b) {
            this.b = b;
        }

    }

    @Test
    public void testResolve() throws Exception {
//
//        BigInteger i = num(97);
        System.out.println("\n==========================");
//        System.out.println("mod");
//        System.out.println(num(2).mod(num(11)));
//        System.out.println("modPow");
//        System.out.println(num(17).modPow(num(-1), num(11)));
//        System.out.println("modInverse");
//        System.out.println(num(17).modInverse(num(11)));
//

        BigInteger p = num("13407807929942597099574024998205846127479365820592393377723561443721764030073546976801874298166903427690031858186486050853753882811946569946433649006084171");
        BigInteger g = num("11717829880366207009516117596335367088558084999998952205599979459063929499736583746670572176471460312928594829675428279466566527115212748467589894601965568");
        BigInteger h = num("3239475104050450443565264378728065788649097520952449527834792452971981976143292558073856937958553180532878928001494706097394108577585732452307673444020333");
//
//        BigInteger p = num(11);
//        BigInteger g = num(13);
//        BigInteger h = num(17);
        final long b = 1024L * 1024L;
        final BigInteger bb = num(b);

        Function<Long, BigInteger> f1 = x -> h.multiply(g.modPow(num(x), p).modInverse(p)).mod(p);
        Function<Long, BigInteger> f2 = x -> g.modPow(bb.multiply(num(x)).mod(p), p);

        Map<BigInteger, Long> cache = Stream.iterate(1L, x -> x + 1)
                .limit(b)
                .collect(toMap(f1, identity()));

        Stream.iterate(1L, x -> x + 1)
                .limit(b)
                .map(x -> new Pair(f2, x))
                .filter(pair -> cache.containsKey(pair.key()))
                .forEach(pair -> {
                    Long x1 = cache.get(pair.key());
//                    System.out.println(num(pair.value()).multiply(bb).add(num(x1)));
                    System.out.println(pair.value() * b + x1);
                });

//        System.out.println(result);

//        cache.entrySet().forEach(x -> {
//            String s = x.getKey().toString() + " : " + x.getValue().stream().map(w -> Long.toString(w)).collect(joining(","));
//            System.out.println(s);
//        });
//        System.out.println(cache);
//        Stream.iterate(1, x -> x + 1)
//                .limit(b)


    }

}
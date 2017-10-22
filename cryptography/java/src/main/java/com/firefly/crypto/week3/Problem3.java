package com.firefly.crypto.week3;

import com.firefly.crypto.Problem;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;

public class Problem3 implements Problem {
    private static final int KB = 1024;

    String getFor(String file) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] bytes = Files.readAllBytes(Paths.get(file));
        int parts = bytes.length / KB;
        md.update(Arrays.copyOfRange(bytes, parts * KB, bytes.length));
        byte[] digest = md.digest();

        for (; parts > 0; parts--) {
            byte[] buf = Arrays.copyOfRange(bytes, (parts - 1) * KB, parts * KB);
            md.update(merge(buf, digest));
            digest = md.digest();
        }
        return String.format("%064x", new BigInteger(1, digest));
    }

    private byte[] merge(byte[] fst, byte[] snd) {
        byte[] result = new byte[fst.length + snd.length];
        System.arraycopy(fst, 0, result, 0, fst.length);
        System.arraycopy(snd, 0, result, fst.length, snd.length);
        return result;
    }

    public String resolve() {
        try {
            return getFor("act.bin");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "exception";
    }
}

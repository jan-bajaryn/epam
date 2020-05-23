package by.epam.cafe.service.my;


import org.apache.commons.codec.digest.DigestUtils;
import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;

import static org.testng.Assert.*;

public class Sha256Testing {
    @Test
    public void simple() throws NoSuchAlgorithmException {
        String hash = "e35089b2d968d2c00562279dd210847f3e156caa7c9affbaa45a25c6c0e75edf";
        String password = "ILoveJava";

        String s = DigestUtils.sha256Hex(password);
        assertEquals(s,hash);
    }
}

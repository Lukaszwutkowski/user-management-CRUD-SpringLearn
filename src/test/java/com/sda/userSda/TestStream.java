package com.sda.userSda;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestStream {

    @Test
    void test3() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("lukasz"));
        System.out.println(encoder.encode("kasia"));
    }
}

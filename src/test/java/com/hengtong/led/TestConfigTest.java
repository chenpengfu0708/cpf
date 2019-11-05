package com.hengtong.led;

import com.hengtong.led.utils.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestConfigTest {

    @Autowired
    private TestConfig testConfig;

    @Test
    public void test(){
        System.out.println(testConfig.getAddress());
    }
}

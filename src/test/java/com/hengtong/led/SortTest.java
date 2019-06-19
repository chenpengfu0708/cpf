package com.hengtong.led;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SortTest {



    @Test
    public void quickSort(){
        int[] arr  = {1,2,3,4,5,6,7,8,9};
        int a1,a2,a3;
        Random random = new Random();
        a1 = random.nextInt(8)%(9);
        do {
            a2 = random.nextInt(8)%(9);
        } while (a2 == a1);
        do {
            a3 = random.nextInt(8)%(9);
        } while (a3 == a1 || a3 == a2);
        System.out.println("三个随机数为：" + arr[a1] + "," + arr[a2] + "," + arr[a3]);
    }

    @Test
    public void one(){
        for (int i=1; i<100; i++){
            if (i%5 == 0 && i%7 == 0){
                System.out.println(i);
            }
        }
    }


    @Test
    public void t(){
        Integer a = 1;
        Integer b = 1;
        Integer c = 128;
        Integer d = 128;
        String t1 = "hello";
        String t2 = new String("hello");
        System.out.println(a == b);
        System.out.println(c == d);
        System.out.println(t1 == t2);
    }
}

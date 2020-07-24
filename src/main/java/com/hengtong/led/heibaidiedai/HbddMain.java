package com.hengtong.led.heibaidiedai;

import com.hengtong.led.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HbddMain {

    @Autowired
    private RedisUtils redisUtils;

    private static final int NUM = 8;

    private static int[][] main = new int[8][8];

    public static int[][] initMain() {
        for (int i = 0; i < NUM; i++) {
            for (int f = 0; f < NUM; f++) {
                main[i][f] = 0;
            }
        }
        return main;
    }

    public Map<String, String> sout(Integer a, Integer b, String token) {
        int[][] myHbdd = mapToArray(token);

        myHbdd = click(a, b, myHbdd);
        Map<String, String> map = new HashMap<>();
        int xy = 0;
        int numx = 0;
        for (int i = 0; i < NUM; i++) {
            for (int j = 0; j < NUM; j++) {
                if (myHbdd[i][j] != xy) {
                    numx++;
                }
                System.out.print(myHbdd[i][j] + " ");
                String mapKey = "" + i + "" + j;
                map.put(mapKey, ""+myHbdd[i][j]);
            }
            System.out.println();
        }
        if (numx == 0) {
            System.out.println("wow~完成了");
        }
        redisUtils.setMap(token, map);
        return map;
    }

    public static int[][] click(Integer x, Integer y, int[][] main) {
        main[x][y] = change(main[x][y]);
        if (x > 0) {
            main[x - 1][y] = change(main[x - 1][y]);
        }
        if (x < (NUM - 1)) {
            main[x + 1][y] = change(main[x + 1][y]);
        }
        if (y > 0) {
            main[x][y - 1] = change(main[x][y - 1]);
        }
        if (y < (NUM - 1)) {
            main[x][y + 1] = change(main[x][y + 1]);
        }
        return main;
    }


    public static int change(int num) {
        if (num == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public int[][] mapToArray(String key) {
        Map<Object, Object> map = redisUtils.getMap(key);
        if (map == null || map.size() == 0) {
            return main;
        } else {
            int[][] result = new int[NUM][NUM];
            for (int i = 0; i < NUM; i++) {
                for (int j = 0; j < NUM; j++) {
                    String mapKey = "" + i + "" + j;
                    result[i][j] = Integer.parseInt(map.get(mapKey).toString());
                }
            }
            return result;
        }
    }

}

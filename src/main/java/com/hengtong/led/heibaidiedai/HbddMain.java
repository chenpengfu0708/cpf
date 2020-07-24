package com.hengtong.led.heibaidiedai;

import com.hengtong.led.dto.CommonResponseDto;
import com.hengtong.led.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    public CommonResponseDto sout(Integer a, Integer b, String token, Map<String, String> map, int[][] myHbdd) {
        CommonResponseDto commonResponseDto = new CommonResponseDto();
        if (a != -1 && b != -1) {
            myHbdd = click(a, b, myHbdd);
        } else {
            sendTopic(8, token, map, myHbdd);
        }
        int xy = 0;
        int numx = 0;
        for (int i = 0; i < NUM; i++) {
            for (int j = 0; j < NUM; j++) {
                if (myHbdd[i][j] != xy) {
                    numx++;
                }
                String mapKey = "" + i + "" + j;
                map.put(mapKey, "" + myHbdd[i][j]);
            }
        }
        redisUtils.setMap(token, map);
        if (numx == 0 && a != -1) {
            redisUtils.setKey("finish_" + token, "finish", 20L, TimeUnit.MINUTES);
            redisUtils.remove(token);
        }
        return commonResponseDto.code(0).data(map);
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


    public void sendTopic(Integer click, String token, Map<String, String> map, int[][] myHbdd) {
        for (int i = 0; i < click; i++) {
            Integer x = (int)(Math.random()*8);
            Integer y = (int)(Math.random()*8);
            sout(x, y, token, map, myHbdd);
        }
    }

}

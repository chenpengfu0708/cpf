package com.hengtong.led.heibaidiedai;

import com.hengtong.led.dto.CommonResponseDto;
import com.hengtong.led.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class SWHbddMain {

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

    public CommonResponseDto sout(Integer num, Integer plane, Integer x, Integer y, String token, Map<String, int[][]> myHbddMap) {
        CommonResponseDto commonResponseDto = new CommonResponseDto();
        if (x != -1 && y != -1) {
            click(plane, x, y, myHbddMap);
        } else {
            //初始化出题
            sendTopic(num, token, myHbddMap);
        }
        return commonResponseDto.code(0).data(myHbddMap);
    }

    public static Map<String, int[][]> click(Integer plane, Integer x, Integer y, Map<String, int[][]> map) {
        String thisPlaneKey = "plane" + plane;
        int[][] thisPlane = map.get(thisPlaneKey);
        //处理当前平面
        thisPlane[x][y] = change(thisPlane[x][y]);
        if (x > 0) {
            thisPlane[x - 1][y] = change(thisPlane[x - 1][y]);
        }
        if (x < (NUM - 1)) {
            thisPlane[x + 1][y] = change(thisPlane[x + 1][y]);
        }
        if (y > 0) {
            thisPlane[x][y - 1] = change(thisPlane[x][y - 1]);
        }
        if (y < (NUM - 1)) {
            thisPlane[x][y + 1] = change(thisPlane[x][y + 1]);
        }

        //处理上下平面
        if (plane > 0) {
            String key = "plane" + (plane - 1);
            int[][] upMain = map.get(key);
            upMain[x][y] = change(upMain[x][y]);
        }
        if (plane < 5) {
            String key = "plane" + (plane + 1);
            int[][] downMain = map.get(key);
            downMain[x][y] = change(downMain[x][y]);
        }
        return map;
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


    /**
     * 出题
     */
    public void sendTopic(Integer click, String token, Map<String, int[][]> myHbddMap) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < click; j++) {
                Integer x = (int) (Math.random() * 8);
                Integer y = (int) (Math.random() * 8);
                sout(click, i, x, y, token, myHbddMap);
            }
        }

    }

}

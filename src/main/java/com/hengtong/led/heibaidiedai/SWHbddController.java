package com.hengtong.led.heibaidiedai;

import com.hengtong.led.dto.CommonResponseDto;
import com.hengtong.led.dto.HbddRequestDto;
import com.hengtong.led.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class SWHbddController {

    @Autowired
    private SWHbddMain swhbddMain;
    @Autowired
    private RedisUtils redisUtils;


    @PostMapping("/sw/con/hbdd")
    public CommonResponseDto hbdd(@RequestBody HbddRequestDto requestDto) {
        HbddMain.initMain();
        CommonResponseDto response = swhbddMain.sout(requestDto.getPlane(), requestDto.getX(), requestDto.getY(), requestDto.getToken(), getMap(requestDto));
        Map<String, int[][]> main = (Map<String, int[][]>)response.getData();
        HashMap<String, Object> data = new HashMap<>();
        sout(main, requestDto.getToken(), response, data);
        return response;
    }

    public CommonResponseDto sout(Map<String, int[][]> main, String token, CommonResponseDto response, HashMap<String, Object> data) {
        int num = 0;
        for (int a = 0; a < 6; a++) {
            int[][] plane = main.get("plane" + a);
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    String dataKey = "" + a + "" + i + "" + j;
                    data.put(dataKey, plane[i][j]);
                    String mapKey = "" + i + "" + j;
                    map.put(mapKey, "" + plane[i][j]);
                    if (plane[i][j] != 0) {
                        num ++;
                    }
                }
            }
            String key = token + ":plane" + a;
            redisUtils.setMap(key, map);
        }

        if (num == 0) {
            redisUtils.setKey("finish_" + token, "finish", 20L, TimeUnit.MINUTES);
            redisUtils.remove(token);
        }
        return response.data(data);
    }

    @PostMapping("/sw/con/checkStatus")
    public CommonResponseDto checkStatus(@RequestBody HbddRequestDto requestDto) {
        if (redisUtils.exitst("finish_" + requestDto.getToken())) {
            return new CommonResponseDto().code(0).finish(1);
        } else {
            return new CommonResponseDto().code(0).finish(0);
        }
    }

    private Map<String, int[][]> getMap(HbddRequestDto requestDto) {
        Map<String, int[][]> result = new HashMap<>();
        String token = requestDto.getToken();
        for (int i = 0; i < 6; i++) {
            String key = token + ":plane" + i;
            if (redisUtils.exitst(key)) {
                result.put("plane" + i, swhbddMain.mapToArray(key));
            } else {
                result.put("plane" + i, getInit());
            }
        }
//        sout(result);
        return result;
    }

    public int[][] getInit() {
        int[][] main = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int f = 0; f < 8; f++) {
                main[i][f] = 0;
            }
        }
        return main;
    }


}

package com.hengtong.led;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hengtong.led.entity.TestListToMap;
import com.hengtong.led.entity.User;
import com.hengtong.led.mapper.UserMapper;
import com.hengtong.led.service.TeacherService;
import com.hengtong.led.service.TestRun;
import com.hengtong.led.service.UserService;
import com.hengtong.led.service.Voice;
import com.hengtong.led.utils.AsyncUtils;
import com.hengtong.led.utils.Base64Util;
import com.hengtong.led.utils.Java8;
import com.hengtong.led.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LedApplicationTests {
    @Autowired
    private Voice voice;
    @Autowired
    private Java8 java8;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AsyncUtils asyncUtils;

    @Test
    public void testRedis(){
        String key = "test";
        String value = "测试";
        //插入缓存
        redisTemplate.opsForValue().set(key, value);
        //取缓存
        System.out.println(redisTemplate.opsForValue().get("test"));
    }

    @Test
    public void contextLoads() {
        //初始化一个list
        long first = new Date().getTime();
        List<TestListToMap> list = new ArrayList<>();
        for (int i=1; i<5000; i++){
            TestListToMap listToMap = new TestListToMap();
            listToMap.setId(i);
            listToMap.setName("name"+i);
            listToMap.setAge(i+20);
            list.add(listToMap);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("传统所用时间" + (new Date().getTime() - first));


        long startTime = new Date().getTime();
        list.stream().forEach(f ->{
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("串行所用时间" + (new Date().getTime() - startTime));


        long endTime = new Date().getTime();
        list.parallelStream().forEach(f ->{
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("并行所用时间" + (new Date().getTime() - endTime));


    }

    /**
     * 排序
     * */
    @Test
    public void testOrder(){
        List<TestListToMap> list = new ArrayList<>();
        TestListToMap listToMap = new TestListToMap();
        TestListToMap listToMap1 = new TestListToMap();
        TestListToMap listToMap2 = new TestListToMap();
        TestListToMap listToMap3 = new TestListToMap();
        listToMap.setId(5);
        listToMap.setName("第5");
        listToMap1.setId(2);
        listToMap1.setName("第2");
        listToMap2.setId(5);
        listToMap2.setName("第3");
        listToMap3.setId(6);
        listToMap3.setName("第6");
        list.add(listToMap);
        list.add(listToMap1);
        list.add(listToMap2);
        list.add(listToMap3);
        System.out.println(list);
        List<TestListToMap> orderList = list.stream()
                .sorted(Comparator.comparing(TestListToMap::getId))
                .collect(Collectors.toList());
        System.out.println(orderList);
//        list.sort(Comparator.comparing(TestListToMap::getId).reversed());//反排序 reversed()

        //根据对象属性去重
        List<TestListToMap> list1 = list.stream().filter(java8.distinctByKey(l ->l.getId())).collect(Collectors.toList());
        System.out.println(list1);
    }

    @Test
    public void testDistinct(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(1);
        list.add(0);
        System.out.println(list);
        List<Integer> list1 = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(list1);
        List<Integer> integerList = list.stream().distinct().collect(Collectors.toList());
        System.out.println(integerList);
    }

    @Test
    public void plateVoiceTest(){
        String plate = "京AF0128";
        List<String> plateList = new ArrayList<>();
        for (int i=0; i<plate.length();i++){
            char p = plate.charAt(i);
            plateList.add(String.valueOf(p));
        }
        System.out.println(voice.getVoiceList(plateList));
        float money = 21.1f;
        System.out.println(voice.getT(money));
    }

    @Test
    public void te(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(sdf.format(date));
        try {
            date = sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("date = "+sdf.format(date));
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(date);
        System.out.println("calender="+calendar);
    }

    @Test
    public void testRun(){
        TestRun testRun = new TestRun();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(() -> {
                String key = "name"+finalI;
                    map.put(key, "v"+finalI);
                    System.out.println(map.toString());
                }, String.valueOf(i)).start();
        }


    }


    @Test
    public void saveUser(){
//        userService.test();
        teacherService.test();
    }


    @Test
    public void tes(){
        Boolean again = true;
        List<Integer> a = new ArrayList<>();
        a.add(1);
        int times = 0;
        do {
            try {
                if (times == 0 || times == 1){
                    System.out.println(a.get(3));
                }
                log.info("发送成功!");
                again = false;
            }catch (Exception e){
                e.printStackTrace();
                times++;
                log.info("发送失败，正在重试");
            }
        } while (again && times<3);
    }



    @Test
    public void test(){
        List<Integer> ids = Arrays.asList(1,2,4,5);
        List<User> userList = userMapper.getByIds(ids);
        Map<Integer, User> userMap = userList.parallelStream().collect(Collectors.toMap(User :: getId, u -> u, (u1, u2) -> u1));
        for (Integer id : ids) {
            User user = userMap.get(id);
            /*
            * 这里写业务
            * */
        }
        ids.stream().forEach(id ->{
            User user = userMap.get(id);
            /*
             * 这里写业务
             * */
        });

    }


    @Test
    public void testBase(){
        String b = "测试加密";
        String jiami = "";
        try {
            jiami = Base64Util.encryptBASE64(b.getBytes());
            System.out.println("加密后：" + jiami);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] jm = jiami.getBytes();
        try {
            String jiemi = new String(Base64Util.decryptBASE64(jm), "UTF-8");
            System.out.println("解密后：" + jiemi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testRedis1(){
        User user = new User();
        user.setId(1);
        user.setName("redis");
        user.setAge(23);
        String json = JSONObject.toJSONString(user);
        redisUtils.setKey("test", json, 60L, TimeUnit.SECONDS);
        System.out.println("test="+redisUtils.getKey("test"));
        User user1 = JSON.parseObject(redisUtils.getKey("test"), User.class);
        System.out.println(user1);
    }


    @Test
    public void testIncrement(){
        System.out.println("num=" + redisUtils.increment("num", 1L));
    }


    @Test
    public void java8Test(){
        List<User> userList = new ArrayList<>();
        for(int i = 0; i<5; i++){
            User user = new User();
            user.setId(i);
            user.setName("测试"+i);
            user.setAge(20+i);
            userList.add(user);
        }
        System.out.println(userList);
        System.out.println(userList.stream().filter(f -> f.getAge() > 21 && f.getId() == 4).collect(Collectors.toList()));
        userList.stream().forEach(u ->{
            u.getId();

        });
   }


    @Test
    public void t(){
        System.out.println(userMapper.getByName("'1' or 1 = 1"));
    }

    @Test
    public void async(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "罗一");
        map.put("age", "16");
        List<User> user = userMapper.getByMap(map);
        System.out.println(user);
    }


}

package com.hengtong.led.service;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @date: 2019/4/25 16:35
 * @author: rain
 * @description: led
 */
@Component
public class Voice {

    //获取车牌语音组播参数数据
    public String getVoiceList(List<String> stringList){
        StringBuilder voices = new StringBuilder();
        for (String string:stringList){
            switch (string){
                case "0":
                    voices.append("0,");
                    break;
                case "1":
                    voices.append("1,");
                    break;
                case "2":
                    voices.append("2,");
                    break;
                case "3":
                    voices.append("3,");
                    break;
                case "4":
                    voices.append("4,");
                    break;
                case "5":
                    voices.append("5,");
                    break;
                case "6":
                    voices.append("6,");
                    break;
                case "7":
                    voices.append("7,");
                    break;
                case "8":
                    voices.append("8,");
                    break;
                case "9":
                    voices.append("9,");
                    break;
                case "A":
                    voices.append("76,");
                    break;
                case "B":
                    voices.append("77,");
                    break;
                case "C":
                    voices.append("78,");
                    break;
                case "D":
                    voices.append("79,");
                    break;
                case "E":
                    voices.append("80,");
                    break;
                case "F":
                    voices.append("81,");
                    break;
                case "G":
                    voices.append("82,");
                    break;
                case "H":
                    voices.append("83,");
                    break;
                case "J":
                    voices.append("84,");
                    break;
                case "K":
                    voices.append("85,");
                    break;
                case "L":
                    voices.append("86,");
                    break;
                case "M":
                    voices.append("87,");
                    break;
                case "N":
                    voices.append("88,");
                    break;
                case "O":
                    voices.append("89,");
                    break;
                case "P":
                    voices.append("90,");
                    break;
                case "Q":
                    voices.append("91,");
                    break;
                case "R":
                    voices.append("92,");
                    break;
                case "S":
                    voices.append("93,");
                    break;
                case "T":
                    voices.append("94,");
                    break;
                case "U":
                    voices.append("95,");
                    break;
                case "V":
                    voices.append("96,");
                    break;
                case "W":
                    voices.append("97,");
                    break;
                case "X":
                    voices.append("98,");
                    break;
                case "Y":
                    voices.append("99,");
                    break;
                case "Z":
                    voices.append("100,");
                    break;
                case "澳":
                    voices.append("101,");
                    break;
                case "川":
                    voices.append("102,");
                    break;
                case "鄂":
                    voices.append("103,");
                    break;
                case "甘":
                    voices.append("104,");
                    break;
                case "赣":
                    voices.append("105,");
                    break;
                case "港":
                    voices.append("106,");
                    break;
                case "挂":
                    voices.append("107,");
                    break;
                case "贵":
                    voices.append("108,");
                    break;
                case "黑":
                    voices.append("109,");
                    break;
                case "沪":
                    voices.append("110,");
                    break;
                case "吉":
                    voices.append("111,");
                    break;
                case "冀":
                    voices.append("112,");
                    break;
                case "津":
                    voices.append("113,");
                    break;
                case "晋":
                    voices.append("114,");
                    break;
                case "京":
                    voices.append("115,");
                    break;
                case "警":
                    voices.append("116,");
                    break;
                case "辽":
                    voices.append("117,");
                    break;
                case "领":
                    voices.append("118,");
                    break;
                case "鲁":
                    voices.append("119,");
                    break;
                case "蒙":
                    voices.append("120,");
                    break;
                case "闽":
                    voices.append("121,");
                    break;
                case "宁":
                    voices.append("122,");
                    break;
                case "青":
                    voices.append("123,");
                    break;
                case "琼":
                    voices.append("124,");
                    break;
                case "陕":
                    voices.append("125,");
                    break;
                case "使":
                    voices.append("126,");
                    break;
                case "苏":
                    voices.append("127,");
                    break;
                case "皖":
                    voices.append("128,");
                    break;
                case "湘":
                    voices.append("129,");
                    break;
                case "新":
                    voices.append("130,");
                    break;
                case "学":
                    voices.append("131,");
                    break;
                case "渝":
                    voices.append("132,");
                    break;
                case "豫":
                    voices.append("133,");
                    break;
                case "粤":
                    voices.append("134,");
                    break;
                case "云":
                    voices.append("135,");
                    break;
                case "藏":
                    voices.append("136,");
                    break;
                case "浙":
                    voices.append("137,");
                    break;
            }
        }
        return voices.toString().substring(0,voices.length()-1);
    }

    //获取金额语音
    public String getT(float money){
        StringBuilder numVoice = new StringBuilder();
        Integer num = (int)money;
        Integer fNum = (int)(money%1 * 10);
        getIntegerVoice(numVoice, num);
        //获取角的语音序号
        numVoice.append(fNum.toString()).append(",142,");//142表示 角 的语音序号
        return numVoice.toString().substring(0, numVoice.length()-1);
    }

    //获取整数金额语音序号
    public void getIntegerVoice(StringBuilder result, Integer integerNum){
        Integer iNum;
        Integer iNum1;
        if (integerNum > 999){
            iNum = integerNum/1000;
            iNum1 = integerNum%1000;
            result.append(iNum.toString()).append(",").append("12,");//12表示 千 的语音序号
            if (iNum1 < 100){//求余小于100块，添加 0 的语音包
                result.append("0,");
            }
            getIntegerVoice(result, iNum1);
        }else if (integerNum > 99){
            iNum = integerNum/100;
            iNum1 = integerNum%100;
            result.append(iNum.toString()).append(",").append("11,");//11表示 百 的语音序号
            if (iNum1 < 10){//求余小于10块，添加 0 的语音包
                result.append("0,");
            }
            getIntegerVoice(result, iNum1);
        }else if (integerNum > 9){
            iNum = integerNum/10;
            iNum1 = integerNum%10;
            result.append(iNum.toString()).append(",").append("10,");//10表示 十 的语音序号
            if (iNum1 > 0){
                result.append(iNum1.toString()).append(",19,");//19表示 元 的语音序号
            }
        }
    }


}

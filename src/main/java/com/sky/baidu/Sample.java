package com.sky.baidu;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @program: demo_tset
 * @description: Sample
 * @author: suznhg
 * @create: 2018-08-27
 **/
public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "11735521";
    public static final String API_KEY = "9ldZwCqt1dhlXxEsaND7qb4N";
    public static final String SECRET_KEY = "3bATrzhxdKN2O0j4L8gxYEhAzOPB16DU";

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        sample(client);

    }


    public static void sample(AipOcr client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("probability", "true");


        // 参数为本地图片路径
        String image = "D:\\1.jpg";
        JSONObject res = client.basicAccurateGeneral(image, options);
        System.out.println(res.toString());


    }

    public static void sampleppp(AipOcr client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");


        // 参数为本地图片路径
        String image = "D:\\4.png";
        JSONObject res = client.enhancedGeneral(image, options);
        System.out.println(res.toString());


    }

}

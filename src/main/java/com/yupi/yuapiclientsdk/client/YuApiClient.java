package com.yupi.yuapiclientsdk.client;

import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yupi.yuapiclientsdk.model.User;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.yupi.yuapiclientsdk.utils.SignUtils.getSign;

/**
 * 调用第三方接口的客户端
 * @author ccc
 * @create 2023-12-07 17:44
 */
public class YuApiClient {

    private static final String GATEWAY_HOST = "http://localhost:8090";
//    private static final String GATEWAY_HOST = "http://114.132.63.217:8090";

    private String accessKey;
    private String secretKey;

    public YuApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    //使用GET方法从服务器获取名称信息
    public String getNameByGet(String name){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result= HttpUtil.get(GATEWAY_HOST + "/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    //使用POST方法从服务器获取名称信息
    public String getNameByPost(String name){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result= HttpUtil.post(GATEWAY_HOST + "/api/name/", paramMap);
        System.out.println(result);
        return result;
    }


    /**
     * 创建一个私有方法，用于构造请求头
     *
     * @param body 请求体内容
     * @return 包含请求头参数的哈希映射
     */
    private Map<String,String> getHeaderMap(String body) {
        //创建一个新的HashMap对象
        Map<String, String> hashMap = new HashMap<>();

        //将“accessKey”和其对应的值放入map中
        hashMap.put("accessKey",accessKey);

        //注意：不能直接发送密钥
        //将“secretKey”和其对应的值放入map中
//        hashMap.put("secretKey",secretKey);

        //生成4位随机数
        hashMap.put("nonce",RandomUtil.randomNumbers(4));

        //请求体内容
        hashMap.put("body", body);

        //时间戳
        hashMap.put("timestamp",String.valueOf(System.currentTimeMillis() / 1000));

        hashMap.put("sign",getSign(body,secretKey));

        //返回构造的请求头map
        return hashMap;
    }

    //使用POST方法从服务器获取User对象
    public String getUserNameByPost(User user){
        String json = JSONUtil.toJsonStr(user);
        String result = HttpRequest.post(GATEWAY_HOST + "/api/name/user")
                //添加前面构造的请求头
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute().body();
        System.out.println(result);
        return result;
    }
}

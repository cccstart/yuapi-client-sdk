package com.yupi.yuapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * 签名工具
 * @author ccc
 * @create 2023-12-07 21:32
 */
public class SignUtils {
    /**
     * 生成签名
     * @param body 请求体内容
     * @param secretKey 密钥
     * @return 生成的签名字符串
     */
    public static String getSign(String body,String secretKey){
        //使用SHA256算法的Digester
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        //构建签名内容，将body转换为字符串并拼接密钥
        String content = body + "." + secretKey;
        // 计算签名的摘要并返回摘要的十六进制表示形式
        return md5.digestHex(content);
    }

}

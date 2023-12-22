package com.yupi.yuapiclientsdk;

import com.yupi.yuapiclientsdk.client.YuApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ccc
 * @create 2023-12-07 22:18
 */
@Configuration
@ConfigurationProperties("yuapi.client")
@Data
@ComponentScan
public class YuApiClientConfig {
    private String accessKey;
    private String secretKey;

    //创建bean
    @Bean
    public YuApiClient yuApiClient(){
        //使用ak和sk创建一个实例
        return new YuApiClient(accessKey,secretKey);
    }
}

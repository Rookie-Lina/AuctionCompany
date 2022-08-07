package com.sg.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-05-10 14:05
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss.file")
public class OssProperties {
    private String endpoint;

    private String keyId;

    private String keySecret;

    private String dirName;

    private String bucketName;
}

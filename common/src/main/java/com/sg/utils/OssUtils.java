package com.sg.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-05-10 14:09
 */
@Component
public class OssUtils {

    @Resource
    private OssProperties ossProperties;

    public void  saveFaceImg(int userId, String name, InputStream inputStream) {
        System.out.println(ossProperties.toString());
        OSS ossClient = new OSSClientBuilder().build(ossProperties.getEndpoint(), ossProperties.getKeyId(), ossProperties.getKeySecret());

        String path = ossProperties.getDirName() + "/" + userId + "/" + name;
        ossClient.putObject(ossProperties.getBucketName(), path, inputStream);
        ossClient.shutdown();
    }
}

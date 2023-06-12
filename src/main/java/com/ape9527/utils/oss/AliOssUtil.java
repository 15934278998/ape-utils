package com.ape9527.utils.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ape9527.utils.string.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 阿里云oss存储工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
@Component
public class AliOssUtil {

    /**
     * accessKeyId
     */
    private String keyId;

    /**
     * accessKeySecret
     */
    private String keySecret;

    /**
     * bucket名称
     */
    private String bucketName;

    /**
     * 地域节点
     */
    private String endpoint;

    /**
     * 上传文件到阿里云oss
     *
     * @param file 要上传的文件
     * @return 上传后的地址
     */
    public String uploadFile(MultipartFile file) {
        String res = "";
        OSS oss = createConnection();
        InputStream inputStream = null;
        try {
            //获取文件流
            inputStream = file.getInputStream();
            //获取上传的文件名
            String fileName = StringUtil.fileNameAddTimestamp(file.getOriginalFilename());
            oss.putObject(bucketName, fileName, inputStream);
            cleanConnection(oss);
            res = "https://" + bucketName + "." + endpoint + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            res = e.getMessage();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    /**
     * 创建oss连接
     *
     * @return OSS
     */
    private OSS createConnection() {
        return new OSSClientBuilder().build(endpoint, keyId, keySecret);
    }

    /**
     * 关闭oss连接
     *
     * @param oss
     */
    private void cleanConnection(OSS oss) {
        oss.shutdown();
    }

}

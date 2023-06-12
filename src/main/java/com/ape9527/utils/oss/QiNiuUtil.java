package com.ape9527.utils.oss;

import com.ape9527.utils.json.JsonUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 七牛云存储工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
@Component
public class QiNiuUtil {

    private String bucketName;

    private String accessKey;

    private String secretKey;

    /**
     * 服务器直接上传
     *
     * @param file 要上传的文件
     * @return 上传后返回的凭证信息
     */
    public Map<String, Object> update(MultipartFile file) {
        Map<String, Object> res = new HashMap<>();
        Configuration cfg = new Configuration(Region.region1());
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucketName);
        String[] split = file.getOriginalFilename().split("\\.");
        String key = UUID.randomUUID().toString() + "." + split[split.length - 1];
        try {
            Response response = uploadManager.put(file.getInputStream(), key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = JsonUtil.parseObject(response.bodyString(), DefaultPutRet.class);
            res.put("key", putRet.key);
            res.put("hash", putRet.hash);
            res.put("upToken", upToken);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * 生成前端上传所需令牌
     *
     * @param fileType 上传的文件类型
     * @return 令牌信息
     */
    public String getUpToken(String fileType) {
        String key = UUID.randomUUID().toString() + "." + fileType;
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucketName, null, 3600, new StringMap()
                .putNotEmpty("saveKey", key), true);
    }

}

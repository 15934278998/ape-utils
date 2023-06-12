package com.ape9527.utils.oss;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * minio存储工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
@Component
public class MinioUtil {

    /**
     * host
     */
    public static String HOST;

    /**
     * accessKey
     */
    public static String ACCESS_KEY;

    /**
     * secretKey
     */
    public static String SECRET_KEY;

    /**
     * bucket
     */
    public static String BUCKET;

    /**
     * minioClient
     */
    private static MinioClient minioClient;

    /**
     * 上传文件
     *
     * @param file     文件
     * @param fileName 文件名
     * @return 文件上传url
     */
    public static String upload(MultipartFile file, String fileName) {
        if (null != file) {
            try {
                MinioClient client = getClient();
                //得到文件流
                InputStream input = file.getInputStream();
                //类型
                String contentType = file.getContentType();
                //把文件放置Minio桶(文件夹)
                // 如需要将文件分成文件夹的形式，只需在文件名前加入对应的文件夹路径就行了
                // 如：fileName = "images/" + fileName;
                client.putObject(BUCKET, fileName, input, contentType);
                return HOST + "/" + BUCKET + "/" + fileName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取一个有时效性的链接
     *
     * @param fileName 文件名称
     * @param expires  过期时间，秒级
     * @return url
     */
    public static String getWithAgingUrl(String fileName, Integer expires) {
        try {
            return getClient().presignedGetObject(BUCKET, fileName, expires);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 文件下载
     *
     * @param response 响应
     * @param fileName 文件名
     */
    public static void download(HttpServletResponse response, String fileName) {
        InputStream inputStream;
        try {
            MinioClient minioClient = getClient();
            ObjectStat stat = minioClient.statObject(BUCKET, fileName);
            inputStream = minioClient.getObject(BUCKET, fileName);
            response.setContentType(stat.contentType());
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            IOUtils.copy(inputStream, response.getOutputStream());
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 文件下载
     *
     * @param fileName 文件名
     * @return 文件字节数组
     */
    public static byte[] getBytes(String fileName) {
        InputStream inputStream;
        byte[] bytes = new byte[0];
        try {
            inputStream = getClient().getObject(BUCKET, fileName);
            bytes = toByteArray(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 获取minio连接
     *
     * @return minio连接
     * @throws InvalidPortException     异常
     * @throws InvalidEndpointException 异常
     */
    public static MinioClient getClient() throws InvalidPortException, InvalidEndpointException {
        if (minioClient == null) {
            minioClient = new MinioClient(HOST, ACCESS_KEY, SECRET_KEY);
        }
        return minioClient;
    }

    /**
     * 删除文件
     *
     * @param objectName 文件名称
     * @return true or false
     */
    public static Boolean delete(String objectName) {
        try {
            MinioClient minioClient = getClient();
            minioClient.removeObject(BUCKET, objectName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }


}

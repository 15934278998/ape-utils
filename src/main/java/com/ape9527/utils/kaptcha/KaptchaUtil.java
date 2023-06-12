package com.ape9527.utils.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 图片验证码工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
public class KaptchaUtil {

    /**
     * 是否有边框
     */
    public static boolean BORDER = false;

    /**
     * 边框颜色
     */
    public static String BORDER_COLOR = "105,179,90";

    /**
     * 验证码字体颜色
     */
    public static String TEXT_FONT_COLOR = "blue";

    /**
     * 验证码字体大小
     */
    public static Integer TEXT_FONT_SIZE = 30;

    /**
     * 验证码字体样式
     */
    public static String TEXT_FONT_NAMES = "宋体,楷体";

    /**
     * 验证码字符长度
     */
    public static Integer TEXT_CHAR_LENGTH = 4;

    /**
     * 图片宽度
     */
    public static Integer IMAGE_WIDTH = 140;

    /**
     * 图片高度
     */
    public static Integer IMAGE_HEIGHT = 40;

    private static final DefaultKaptcha DEFAULT_KAPTCHA = new DefaultKaptcha();

    private static void setConfig() {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", BORDER ? "yes" : "no");
        properties.setProperty("kaptcha.border.color", BORDER_COLOR);
        properties.setProperty("kaptcha.textproducer.font.color", TEXT_FONT_COLOR);
        properties.setProperty("kaptcha.textproducer.font.size", TEXT_FONT_SIZE.toString());
        properties.setProperty("kaptcha.textproducer.font.names", TEXT_FONT_NAMES);
        properties.setProperty("kaptcha.textproducer.char.length", TEXT_CHAR_LENGTH.toString());
        properties.setProperty("kaptcha.image.width", IMAGE_WIDTH.toString());
        properties.setProperty("kaptcha.image.height", IMAGE_HEIGHT.toString());

        Config config = new Config(properties);
        DEFAULT_KAPTCHA.setConfig(config);
    }

    /**
     * 获取图片验证码
     *
     * @return 验证码文本信息和图片信息
     * @throws IOException IOException
     */
    public static Map<String, String> getImageCode() throws IOException {
        setConfig();
        // 生成文字验证码
        String text = DEFAULT_KAPTCHA.createText();
        // 生成图片验证码
        BufferedImage image = DEFAULT_KAPTCHA.createImage(text);
        // 使用redis缓存验证码的值，并设置过期时间为5分钟
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", stream);
        String base64 = Base64.getEncoder().encodeToString(stream.toByteArray());
        stream.flush();
        stream.close();

        Map<String, String> res = new HashMap<>();
        res.put("text", text);
        res.put("base64", "data:image/png;base64," + base64);
        return res;
    }

}

package com.ape9527.utils.sms;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;

/**
 * 腾讯云短信发送工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
public class TencentSmsUtil {

    /**
     * SecretId
     */
    public static String SECRET_ID;

    /**
     * SecretKey
     */
    public static String SECRET_KEY;

    /**
     * 地域信息
     */
    public static String REGION;

    /**
     * 短信应用ID
     */
    public static String SDK_APP_ID;

    /**
     * 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名
     */
    public static String SIGN_NAME;

    /**
     * 认证对象
     */
    private static Credential credential;

    /**
     * SmsClient对象
     */
    private static SmsClient smsClient;

    /**
     * 实例化认证对象
     *
     * @return 认证对象
     */
    private static Credential getCredential() {
        if (credential == null) {
            credential = new Credential(SECRET_ID, SECRET_KEY);
        }
        return credential;
    }

    /**
     * 实例化SmsClient对象
     *
     * @return SmsClient对象
     */
    private static SmsClient getSmsClient() {
        if (smsClient == null) {
            Credential credential = getCredential();
            smsClient = new SmsClient(credential, REGION);
        }
        return smsClient;
    }

    /**
     * 发送短信
     *
     * @param templateId 模板ID
     * @param params 模板参数：模板参数的个数需要与 TemplateId 对应模板的变量个数保持一致，若无模板参数，则设置为空
     * @param phoneNumbers 下发手机号码，采用 E.164 标准，+[国家或地区码][手机号]
     * @return SendSmsResponse
     * @throws TencentCloudSDKException TencentCloudSDKException
     */
    public static SendSmsResponse sendSms(String templateId, String[] params, String[] phoneNumbers) throws TencentCloudSDKException {
        SmsClient smsClient = getSmsClient();

        // 实例化一个请求对象
        SendSmsRequest req = new SendSmsRequest();
        req.setSmsSdkAppId(SDK_APP_ID);
        req.setSignName(SIGN_NAME);
        req.setTemplateId(templateId);
        req.setTemplateParamSet(params);
        req.setPhoneNumberSet(phoneNumbers);

        return smsClient.SendSms(req);
    }



}

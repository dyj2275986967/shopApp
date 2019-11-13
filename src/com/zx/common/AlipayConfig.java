package com.zx.common;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101200669969";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCrVm9mmjrfXaydsuR2rgnErOR/BJOJM8J+zic3GLT3D5jBXmiAESFuyi9V4i5YNe67Ootd0z2Vcja56mFRxUAAAGcU7V2htTBHSQ82NSvfqxa9ghENiqq3MRedfgxZN4nw3WaEnmpAMFObJh1V86FNUvKDbT+86la/hTzbHaXxL4N/92JbtYk8Zh54xuDl/8j8TzNDHdMuK2mGyG3Q7xCX3NEYGg4Xf+ZHaqfJ1t73J+j7JJEdco1M0mE/j2Hkwttxc5nhxUXhL8azQg1iCkK/Bo4Sn3nw28M+H609ID613mukV8LGAzq2jIneSowq4MdemitegKcZn7+AzCmVcL4nAgMBAAECggEAZPwt7rhqiKpqigwdcgHECiKpDmBBIPXb5edV37h1OMT9kMM3LnxqSyVp3uHP1Pf8vGeWKvfNbV8C+HM3Zv2uahHSr9fzftMPIITkLVwLfuoVHh8iM+wOEKkjtRTM7Gy6pbeoq/O+xhR1wpUlZzqQUMXZ4DKtBYjNFlbOdosz+kHCfsYJEPsy1XTQmjS8Zfa7UUtJdB49jaUJBdomFCulYJNe7jnDNRi4gsEuG4PznQKB/rESE/dnTNxjpjaEy7AvtKiiI/wqCkxnSgrY9+rmv7QMAx7F3i2l+RVsHJpe9Dx/0F2Tm6ET0AoOB2Pws+tE0nOSm6H/Igso8LTgcqUWMQKBgQDf399wRCbmFJxrp2lsDJriDe5UchdclD8f/Cy1nRdZHtMHWtn6Ie1bcHTdxMN2L/Lm1G9IAegmoYRGb/0Fz0EvD8g5fT8CwcXvXRl9XP7nCtKt67qbjVNyZ9yet1JP3+ZRzIY+dyIB5A41v8+UtLIZSqmq9CM5Rz0MRsHZBMicnwKBgQDD7JkGETFqknx0acuI9zJ1WV5ahcP4zI2YjhtAtO9HyO8s4e3Jx1pRPK5MoTgUDXbaO4ZPvB1nUtLUfu1lE33tHPyNJN2LykD/qXBFnUxsJ+0zsmrWOw4TzmLH/iZYuc5SKF96UbLTnPNsL+ksrmqxkNhZxOzy5RfPQVhRuK7peQKBgEmnBIKR2V5On5uHS1ah2v7N6H/dvTPXDeF+/FKPqRPH4VOFHzt8l/NCTyB60Hd8lOQ+NZIyFmdHMhZeV5bmtaI/Bfdr7iQBDhaOwYkN5Eqss91GNXFhkHeOFfYdh7YBimuU5wF7/jMzDT2/qhJZVFKfzqwQf9ojRsFYMgQs37cvAoGATULcBVx6VEB3+6BMtzJkH4qifqgJY++mVuaTMK0pPvGp/9UIZG3M3ARF58obxyyYFe8rQ/42yiwLH+j9zMInAn5PyYkkJKAZibhToeAp5uzHyX8uSlT3dDALHDrI/GwpRJTPMflNv81A4QTzur79LBCmgJ/7oL1e6kgmf/OIZmECgYEAxWFJfoFwUTjoJFwdNxuUx0aGn7PNVQa13vfReMizd1FQnYqF4FpdKMnKjRF8RaMKvZ2UWpTmvIH2T92css2DfKRkJiBpp/5UH4S7v8nJ5ng0CDT89mG2jDX9qh0A/D4wI8UeWhSBSzKNHRv67fRklmgy6Xju3pTja8BqUx1G+WY=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuh8pS0LsSzJnPjzaWskYNi82lWp2IZVfop6VI5hQmLtBAoWl6Xa6eMxKwbpvjT2VsPy4hO5knJ+s0YHa/kN+kfbhxSKW3zDvUzNYf1XVfDZqryhue11zZEEemrm4Fu9ZueUbJ0HYwS9TEMTB2rVVrjQtjTVdky5JHyOqton6BHzWxhcxyRpHD6Uz+OSLS/jbQgLA6HlA+zC3pfoMHDXpjVHpxzDNJ1sZIsrBAio8OrvMTWz3HHxlLDNKuWwaQiq5LOv/AozUlvqiicgFN9Jdq+7Q4sWuip7z+Hq/HtWeWSBH5scEfI7jBKC52ZGJ2B/qvrIjbCFMw3QI6xWpA17bAwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/shopApp_customer_ssm/order/aorder";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = " https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


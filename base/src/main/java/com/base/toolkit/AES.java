package com.base.toolkit;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private static Logger logger = LoggerFactory.getLogger(AES.class);

    //data为待解密的数据，keyBytes为加密时所使用的密钥，transform为加密时所采取的加密模式和填充模式
    public static byte[] decryptData(String data, byte[] keyBytes) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        try {
            //根据格式获取Cipher实例，需与加密时一致
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //初始化Cipher，注意这里变为解密模式
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            //先Base64解码
            byte[] temp = java.util.Base64.getDecoder().decode(data);

            //解密数据
            return cipher.doFinal(temp);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

}
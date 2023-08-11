package com.hellomaker.web.common.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @describe: AES加解密工具
 *
 * @author  xianzhikun
 * @date    2023/8/11 14:11
 */
public class AESUtil {
    /**
     * 编码
     */
    private static final String ENCODING = "UTF-8";
    /**
     * 算法定义
     */
    private static final String AES_ALGORITHM = "AES";
    /**
     * 指定填充方式
     */
    private static final String CIPHER_PADDING = "AES/ECB/PKCS5Padding";
    private static final String CIPHER_CBC_PADDING = "AES/CBC/PKCS5Padding";

    /**
     * 密钥
     */
    private static final String SECRET_KEY = "w8e10wh73se4omvf";

    /**
     * AES加密
     * @param content 待加密内容
     * @param aesKey  密码
     * @return
     */
    public static String encrypt(String content, String aesKey){
        if(StringUtils.isBlank(content)){
            //AES encrypt: the content is null!
            return null;
        }
        //判断秘钥是否为16位
        if(StringUtils.isNotBlank(aesKey) && aesKey.length() == 16){
            try {
                //对密码进行编码
                byte[] bytes = aesKey.getBytes(ENCODING);
                //设置加密算法，生成秘钥
                SecretKeySpec skeySpec = new SecretKeySpec(bytes, AES_ALGORITHM);
                // "算法/模式/补码方式"
                Cipher cipher = Cipher.getInstance(CIPHER_PADDING);
                //选择加密
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
                //根据待加密内容生成字节数组
                byte[] encrypted = cipher.doFinal(content.getBytes(ENCODING));
                //返回base64字符串
                return Base64Utils.encodeToString(encrypted);
            } catch (Exception e) {
                //AES encrypt exception
                throw new RuntimeException(e);
            }

        }else {
            //AES encrypt: the aesKey is null or error!
            return null;
        }
    }

    /**
     *
     * @describe: 根据秘钥加密数据
     *
     * @param content 需要加密的原始字符串
     * @return  {{@link String}} 返回加密后的base64字符串
     * @author  xianzhikun
     * @date    2023/8/9 9:29
     */
    public static String encrypt(String content) {
        return encrypt(content, SECRET_KEY);
    }

    /**
     * 解密
     * 
     * @param content 待解密内容
     * @param aesKey  密码
     * @return
     */
    public static String decrypt(String content, String aesKey){
        if(StringUtils.isBlank(content)){
            //AES decrypt: the content is null!
            return null;
        }
        //判断秘钥是否为16位
        if(StringUtils.isNotBlank(aesKey) && aesKey.length() == 16){
            try {
                //对密码进行编码
                byte[] bytes = aesKey.getBytes(ENCODING);
                //设置解密算法，生成秘钥
                SecretKeySpec skeySpec = new SecretKeySpec(bytes, AES_ALGORITHM);
                // "算法/模式/补码方式"
                Cipher cipher = Cipher.getInstance(CIPHER_PADDING);
                //选择解密
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);

                //先进行Base64解码
                byte[] decodeBase64 = Base64Utils.decodeFromString(content);

                //根据待解密内容进行解密
                byte[] decrypted = cipher.doFinal(decodeBase64);
                //将字节数组转成字符串
                return new String(decrypted, ENCODING);
            } catch (Exception e) {
                //AES decrypt exception
                throw new RuntimeException(e);
            }

        }else {
            //"AES decrypt: the aesKey is null or error!
            return null;
        }
    }

    /**
     *
     * @describe: 根据秘钥解密数据
     *
     * @param content 需要解密的base64字符串
     * @return  {{@link String}} 解密后的原始字符串数据
     * @author  xianzhikun
     * @date    2023/8/9 9:28
     */
    public static String decrypt(String content) {
        return decrypt(content, SECRET_KEY);
    }
}

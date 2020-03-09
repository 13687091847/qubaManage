package com.lhm.qubaManage.util;

import java.util.Base64;

/**  
 * base64加密工具类
 * @package: com.lhm.qubaManage.util
 * @author: liu huangming
 * @date: 2019年12月26日 上午9:16:25 
 */
public class Base64Utils {
	public static final Base64.Encoder encoder = Base64.getEncoder();
    public static final Base64.Decoder decoder = Base64.getDecoder();
 
    /**
     * base64加密
     *
     * @param encodeText 明文
     * @return
     */
    public static byte[] encoder(byte[] encodeText) {
        return encoder.encode(encodeText);
    }
 
    /**
     * base64加密
     *
     * @param decodeText 密文
     */
    public static byte[] decoder(byte[] decodeText) {
        return decoder.decode(decodeText);
    }
 
}

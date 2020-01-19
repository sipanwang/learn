package generate.utils;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.alibaba.fastjson.JSON;

/**
 * sm3加密算法工具类
 * @explain 加密与加密结果验证（不可逆算法）
 * @author Marydon
 * @creationTime 2018年7月5日上午10:01:24
 * @version 1.0
 * @since
 * @email marydon20170307@163.com
 */
public class Sm3Utils {

    private static final String ENCODING = "UTF-8";
    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    
    
    /**
     * sm3算法加密
     * @explain
     * @param paramStr
     *            待加密字符串
     * @return 返回加密后，固定长度=32的16进制字符串
     */
    public static String encrypt(String paramStr){
        // 将返回的hash值转换成16进制字符串
        String resultHexString = "";
        try {
            // 将字符串转换成byte数组
            byte[] srcData = paramStr.getBytes(ENCODING);
            // 调用hash()
            byte[] resultHash = hash(srcData);
            // 将返回的hash值转换成16进制字符串
            //resultHexString = ByteUtils.toHexString(resultHash);
            Encoder encoder = java.util.Base64.getEncoder();
            resultHexString = encoder.encodeToString(resultHash);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultHexString;
    }

    /**
     * 返回长度=32的byte数组
     * @explain 生成对应的hash值
     * @param srcData
     * @return
     */
    public static byte[] hash(byte[] srcData) {
        SM3Digest digest = new SM3Digest();
        digest.update(srcData, 0, srcData.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return hash;
    }
    
    /**
     * 通过密钥进行加密
     * @explain 指定密钥进行加密
     * @param key
     *            密钥
     * @param srcData
     *            被加密的byte数组
     * @return
     */
    public static byte[] hmac(byte[] key, byte[] srcData) {
        KeyParameter keyParameter = new KeyParameter(key);
        SM3Digest digest = new SM3Digest();
        HMac mac = new HMac(digest);
        mac.init(keyParameter);
        mac.update(srcData, 0, srcData.length);
        byte[] result = new byte[mac.getMacSize()];
        mac.doFinal(result, 0);
        return result;
    }
    
    /**
     * 判断源数据与加密数据是否一致
     * @explain 通过验证原数组和生成的hash数组是否为同一数组，验证2者是否为同一数据
     * @param srcStr
     *            原字符串
     * @param sm3HexString
     *            16进制字符串
     * @return 校验结果
     */
    public static boolean verify(String srcStr, String sm3HexString) {
        boolean flag = false;
        try {
            byte[] srcData = srcStr.getBytes(ENCODING);
            Decoder decoder = java.util.Base64.getDecoder();
            byte[] sm3Hash = decoder.decode(sm3HexString);
            byte[] newHash = hash(srcData);
            if (Arrays.equals(newHash, sm3Hash))
                flag = true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    
    public static void main(String[] args) {
//		String signStr = "version=1&timestamp=20190328162529&random=8d78cbfb28813572&data=%257B%2522s_service%2522%253A%2522%252Fv1%252FgetA80PolicyFlag%2522%252C%2522s_domain%2522%253A%2522hydra%2522%252C%2522token%2522%253A%25221d02c3af5764445abdc471212a64111c%2522%257D";
//		String sign = "Sn3KfXPXdaKh8kteS6nMFjuu13A+Yb9b4FoGvRgaZDc=";
//		
//		System.out.println(Sm3Utils.encrypt(signStr));
//		System.out.println(Sm3Utils.verify(signStr, sign));
		
		
		Map<Object, Object> map = new TreeMap<Object, Object>();
        // body的封装
        Map<Object, Object> bodyMap = new TreeMap<Object, Object>();
        //交易
//        bodyMap.put("terminalNo", "00032709");
//        bodyMap.put("operCardBin", "621214");
//        bodyMap.put("merchantCity", "310100");
//        bodyMap.put("bizCode", "021000");
//        bodyMap.put("colCustName", "泉州市鲤城区德友酒吧");
//        bodyMap.put("operCustType", "c");
//        bodyMap.put("mcc", "5311");
//        bodyMap.put("transAmount", 1);
//        bodyMap.put("merchantOpeningTime", "20190525092524000");
//        bodyMap.put("bizChannel", "pos");
//        bodyMap.put("posAddr", "111111");
//        bodyMap.put("serialId", 3184378);
//        bodyMap.put("colMerchantId", "819290053116222");
//        bodyMap.put("key", "fftbs31415");
//        bodyMap.put("operStatus", "92");
//        bodyMap.put("operCardNo", "6212142100000000036");
//        bodyMap.put("colCustType", "m");
//        bodyMap.put("colCustId", "819290053116222");
//        bodyMap.put("operCardType", "c");
//        bodyMap.put("operCustId", "6212142100000000036");
//        bodyMap.put("respCode", "92");
//        bodyMap.put("operTime", "20190909132656000");
        
        
        
        //商户
        bodyMap.put("merchantId", "21002211");
        bodyMap.put("mobile", "18862610980");
        bodyMap.put("settleAcctNo", "23102131");
        bodyMap.put("corporateNo", "321023199300221122");
        bodyMap.put("merchantOpeningNo", "321023199202021133");
        bodyMap.put("businessId", "3124121");
        bodyMap.put("key", "fftbs31415");
        bodyMap.put("bizChannel", "pos");
        bodyMap.put("bizCode", "exam");
        bodyMap.put("customerType", "01");
        bodyMap.put("busiScope", "1");
        bodyMap.put("settleAcctType", "01");
        bodyMap.put("corporateType", "00");
        bodyMap.put("corpIdentityExpDate", "20200322190000000");
        bodyMap.put("bizLicExpDate", "20200322190000000");
        bodyMap.put("customerProperty", "01");
        bodyMap.put("customerSource", "01");
        bodyMap.put("enrolledAt", "20190904102800000");
        
        
        String bodyValue = JSON.toJSONString(bodyMap);
        System.out.println(bodyValue);
        // 加签
        String signature = Sm3Utils.encrypt(bodyValue);
        System.out.println(signature);
        bodyMap.remove("key");
        String bodyStr = JSON.toJSONString(bodyMap);
        // header的封装
        Map<Object, Object> headerMap = new HashMap<Object, Object>();
        headerMap.put("signType", "sm3");
        headerMap.put("timestamp", System.currentTimeMillis());
        headerMap.put("sign", signature);
        map.put("header", headerMap);
        map.put("body", bodyStr);
        String json = JSON.toJSONString(map);
        
        System.out.println(json);
	}
    
}

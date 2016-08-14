/**
 * Project Name:crawl-app001
 * File Name:MD5.java
 * Package Name:com.shawn.utils
 * Date:2016-8-13下午2:18:27
 *
 */


package com.shawn.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * ClassName: MD5 <br/>
 * Date: 2016-8-13 下午2:18:27 <br/>
 * Description: MD5加密（url压缩算法） 
 *
 * @author luxf
 * @version 
 * @see
 */
public class MD5 {
    public static String getMD5(byte[] source){
        String s = null;
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest();
            char str[] = new char[16 * 2];
            int k = 0;
            for(int i=0;i<16;i++){
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            s = new String(str);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s;
    }
    /**
     * @throws UnsupportedEncodingException 
     * Created on 2016-8-13 下午2:18:28 
     * @description TODO 
     * @author luxf
     * @return void
     * @throws
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        String result = MD5.getMD5("http://www.cnblogs.com/loveyakamoz/archive/2011/07/27/2118937.html".getBytes("utf-8"));
        System.out.println(result);
    }

}


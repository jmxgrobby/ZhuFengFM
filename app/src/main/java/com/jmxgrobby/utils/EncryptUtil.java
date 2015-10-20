package com.jmxgrobby.utils;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-16
 */

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

/**
 * 常见加密工具类，包含
 * Hex 编码 DES加密
 */

public final class EncryptUtil{
    private EncryptUtil(){}

    // RSA 密钥生成

    /**
     * RSA 加密
     * @param data
     * @param key 可以是公钥，也可以是私钥
     * @return
     */
    public static byte[] rsaEncrypt(int mode,byte[] data,Key key){
        byte[] ret =null;
        if(data!=null&&data.length>0&&key!=null) {
            // 1.创建Clipher 使用RSA
            try {
                Cipher rsa = Cipher.getInstance("RSA");
               if(mode == EncryptUtil.ENCRYPT)
                rsa.init(Cipher.ENCRYPT_MODE,key);
                else if(mode==EncryptUtil.DECRYPT)
                   rsa.init(Cipher.DECRYPT_MODE,key);
                else
                    throw new IllegalArgumentException("非法的模式");
                ret = rsa.doFinal(data);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    /**
     * 通过指定的密钥长度，生成非堆成的密钥对
     * @param keySize 推荐使用1024 2048  不允许低于1024
     * @return
     */
    public static KeyPair generateRSAKeyPair(int keySize){
        KeyPair ret =null;
        try {
            //1 准备生成
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            //2 初始化，设置密钥长度
            generator.initialize(keySize);
            //3 生成，并且返回
            ret = generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     *
     * @param mode
     * @param data
     * @param keyData
     * @param ivData 用于AES/CBC/PKCSSPadding 这个带有加密模式的算法
     * @return
     */
    private static byte[] aesWithIv(int mode,byte[]data,byte[]keyData,byte[]ivData){
        byte[]ret=null;
        if(data!=null&&data.length>0
                &&keyData!=null&&keyData.length==16
                &&ivData!=null&&ivData.length==16){
            try {
                //支持 AES/CBC/PKCS5Padding
                //支持 AES/ECB/PKCS5Padding
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                SecretKeySpec keySpec = new SecretKeySpec(keyData,"AES");
                //准备IV参数用于支持CBC模式或者ECB模式
                IvParameterSpec iv = new IvParameterSpec(ivData);
                if(mode==EncryptUtil.ENCRYPT){
                    cipher.init(ENCRYPT,keySpec,iv);
                }
                else if(mode ==EncryptUtil.DECRYPT){
                    cipher.init(DECRYPT,keySpec,iv);
                }
                ret = cipher.doFinal(data);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }



        return ret;
    }


    public static byte[] aesSingle(int mode,byte []data,byte [] keyData){
        byte ret[]=null;
        if(data!=null&&data.length>0&&keyData!=null&&keyData.length==16){
            try {
                Cipher cipher = Cipher.getInstance("AES");
                //AES 方式1 单一密码的情况
                SecretKeySpec keySpec = new SecretKeySpec(keyData,"AES");
                if(mode==EncryptUtil.DECRYPT)
                    cipher.init(Cipher.DECRYPT_MODE,keySpec);
                else if(mode==EncryptUtil.ENCRYPT)
                    cipher.init(Cipher.ENCRYPT_MODE,keySpec);
                ret = cipher.doFinal(data);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        }



        return  ret;
    }


    /**
     * 一个字节形成两个字符，最终长度为原始数据的2倍
     * @param data 要进行编码的数据
     * @return 编码完成的数据
     */
    public static String toHex(byte[] data){
        String ret =null;
        // TODO 将字节数组转换为字符串
        if (data != null&&data.length>0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : data) {
                //获取高四位，低四位的内容，将两个数值，转换成字符
                int h = (b >> 4)& 0x0f;
                int l = b & 0x0f;
                char ch,cl;
                if(h>9){//0x0a~0x0f
                    ch= (char) ('A'+(h-10));
                }else {
                    ch= (char) ('0'+h);
                }

                if (l>9)
                    cl= (char) ('A'+(l-10));
                else
                    cl = (char) ('0'+l);
                stringBuilder.append(ch).append(cl);
            }
            ret = stringBuilder.toString();
        }

        return ret;
    }

    public static byte[]  fromHex(String str){
        byte ret[] = null;
        // TODO 将Hex编码的字符串，还原为原始的字节数组
        int len = str.length();

       if(len>0 && len%2==0){
           char[] chars = str.toCharArray();
           ret = new byte[len/2] ;
           int ih=0,il=0,v;
           char ch,cl;
           for (int i = 0; i < len/2; i++) {
               ch = chars[2*i];
               cl = chars[2*i+1];
               if(ch>='A'&&ch<='F'){
                   ih= 10+(ch-'A');
               }else if(ch>='a'&&ch<='f'){
                   ih = 10+(ch-'a');
               }else if(ch>='0'&&ch<='9')
                   ih = ch-'0';

               if(cl>='A'&&cl<='F'){
                   il= 10+(cl-'A');
               }else if(cl>='a'&&cl<='f'){
                   il = 10+(cl-'a');
               }else if(cl>='0'&&cl<='9')
                   il = cl-'0';
               v = ((ih&0x0f)<<4) |(il&0x0f);
               ret[i] = (byte) v;
           }
       }

        return ret;
    }



    public static final int DECRYPT = 0;
    public static final int ENCRYPT = 1;

    /**
     * 将网址映射为文件名
     * @param stringContent 源名字
     * @return 文件名
     */
    public static String MD5(String stringContent){
        String ret = null;
        if (stringContent != null) {
            try {
                //创建消息摘要的对象，使用MD5算法㷨
                MessageDigest digest = MessageDigest.getInstance("MD5");
                //计算URL对应的MD5数据，生成的数据是字节数组，内部包含不可显示的字节，需要进行编码才能转换成字符串
                //不要使用new String（bytes）！！！，需要转换成16进制内容
                byte[] data = digest.digest(stringContent.getBytes());
                //byte[]每一个字节转换为十六进制表示，拼接成一个字符串
                //0x3c ->3C 0x5-> 05
                ret = toHex(data);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
    public static byte[] desSingle(int mode, byte[] data, byte[] keyData){
        byte [] ret = null;
        if(data!=null&&data.length>0&&keyData!=null&& keyData.length==8){
            try {
                //创建加密引擎
                Cipher cipher = Cipher.getInstance("DES");
                //3 准备key对象
                // 3.1 DES 使用 DESKeySpec,构造的时候指定8个字节的密码即可
                DESKeySpec keySpec= new DESKeySpec(keyData);
                // 3.2 DES 需要转换成key对象，才可以继续使用
                // 需要使用SecretkeyFactory 来处理
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                // 3.3 生成key对象
                SecretKey secretKey = keyFactory.generateSecret(keySpec);
                //2设置Cipher引擎是加密还是解密
                //同时对于对称加密还需要设置key对象（密码）
                if(mode==EncryptUtil.DECRYPT)
                    cipher.init(Cipher.DECRYPT_MODE,secretKey);
                else if(mode == EncryptUtil.ENCRYPT)
                    cipher.init(Cipher.ENCRYPT_MODE,secretKey);
                // 4 加密
                // doFinal方法 可以设置字节数组 作为代价密的内容
                ret = cipher.doFinal(data);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }
        return  ret;
    }

}

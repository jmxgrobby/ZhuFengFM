package com.jmxgrobby.cache;
import android.content.Context;
import android.os.Environment;
import com.jmxgrobby.utils.EncryptUtil;
import com.jmxgrobby.utils.StreamUtils;


import java.io.*;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-12
 */
public final class FileCache {
    private static FileCache ourInstance ;

    private static Context context;
    public  static  FileCache newInstance(Context  context){
        if(ourInstance==null)
                ourInstance = new FileCache(context);
        else {
            throw new IllegalArgumentException("context must be not null");
        }
        return ourInstance;
    }

    public static FileCache getInstance(){
        if(ourInstance==null)
            throw  new IllegalArgumentException("newInstance invoke");
        return ourInstance;
    }
    private FileCache(Context context) {
        this.context = context;
    }

    /**
     * 根据url获取文件名从本地读取文件（如果存在）
     * @param url 图片地址，用于获取图片名字
     * @return 返回图片字节数组
     */
    public byte[] load(String url){
        // TODO 通过网址找文件
        byte[] ret = null;
        if(url!=null)
        {
            File cacheDir = null;
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                //获取存储卡上面应用程序的缓存目录
                cacheDir = context.getExternalCacheDir();

            } else {
                //获取存储缓存
                cacheDir = context.getCacheDir();
            }
            //映射文件名称
            String filename = EncryptUtil.MD5(url);
            File  targetFile = new File(cacheDir,filename);

            //读文件
            if(targetFile.exists()){
                FileInputStream fin = null;
                try {
                    fin = new FileInputStream(targetFile);
                     ret = StreamUtils.readStream(fin);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                        StreamUtils.close(fin);
                }
            }
        }
        return ret;
    }

    /**
     *保存对应地址的数据到文件中
     * @param url 源数据网址
     * @param date
     */
    public  void save(String url,byte[] date){
        // TODO 通过网址存文件
        if(url!=null&&date!=null)
        {
            File cacheDir = null;
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                //获取存储卡上面应用程序的缓存目录
                cacheDir = context.getExternalCacheDir();

            } else {
                //获取存储缓存
                cacheDir = context.getCacheDir();
            }
            //映射文件名称��
            String filename = EncryptUtil.MD5(url);
            File  targetFile = new File(cacheDir,filename);

            //IO操作
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(targetFile);
                fos.write(date);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                StreamUtils.close(fos);
            }
        }

    }
}

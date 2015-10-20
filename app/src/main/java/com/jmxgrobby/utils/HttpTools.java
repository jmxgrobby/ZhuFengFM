package com.jmxgrobby.utils;

import android.os.Build;
import com.jmxgrobby.cache.FileCache;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-12
 */
public final class HttpTools {

    public static final String USER_AGENT = "ting_4.1.7(" + Build.MODEL + "," + Build.VERSION.SDK_INT + ")";

    private HttpTools() {

    }

    public static final int CONNECT_TIMEOUT = 5000;
    public static final int READ_TIMEOUT = 10000;

    /**
     * @param url 请求网址
     * @return 返回结果集
     */
    public static byte[] doGet(String url) {
        byte[] ret = null;
        if (url != null) {
            try {
                URL u = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) u.openConnection();

                //设置连接的配置

                conn.setConnectTimeout(CONNECT_TIMEOUT);
                conn.setReadTimeout(READ_TIMEOUT);

                conn.setRequestMethod("GET");

                //完善 HTTP 请求 的 内容
                //1.设定通用的 Http 头部信息

                //设定 Accept 头信息，告诉服务器客户端能接受什么数据
                conn.setRequestProperty("Accept", "application/*,text/*,image/*,*/*");

                //设置接受的内容压缩算法
                conn.setRequestProperty("Accept-Encoding", "gzip");

                //设置 user-agent
                conn.setRequestProperty("User-Agent", USER_AGENT);

                conn.connect();

                int code = conn.getResponseCode();
                if (code == 200) {
                    // TODO 给ret赋值
                    {
                        InputStream fis = null;
                        try {
                            fis = conn.getInputStream();

                            // TODO 进行输入流的GZIP解压缩
                            String headerField = conn.getHeaderField("Content-Encoding");
                            if("gzip".equals(headerField)){
                                fis = new GZIPInputStream(fis);
                            }
                            ret = StreamUtils.readStream(fis);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            StreamUtils.close(fis);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}

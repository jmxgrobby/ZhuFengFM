package zhufengfm.jmxgrobby.com.zhufengfm.utils;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-12
 * Info:关闭IO流的工具类
 */
public final class StreamUtils {
    private StreamUtils(){}

    /**
     * 关闭传来的所有流
     * @param streams 需要关闭的流的数组
     */
    public static void close(Object... streams){
        if (streams != null) {
            for(int i=0;i<streams.length;i++){
                Object stream =streams[i];
                try{
                    if(stream instanceof  InputStream){
                        ((InputStream) stream) .close();
                    }else if(stream instanceof OutputStream){
                        ((OutputStream) stream).close();
                    }else if(stream instanceof Reader){
                        ((Reader) stream).close();
                    }else if(stream instanceof Writer){
                        ((Writer) stream).close();
                    }else if(stream instanceof HttpURLConnection){
                        ((HttpURLConnection) stream).disconnect();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] readStream(InputStream in) throws IOException {
        byte ret[] = null;
        if(in!=null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new  byte[128];
            int len ;
            while (true){
                    len = in.read(buf);
                    if (len==-1){
                        break;
                    }
                    baos.write(buf,0,len);
            }


            //注意：buf必须要进行==null操作
            //减少内存溢出的可能性���
            buf = null;
            ret = baos.toByteArray();
            baos.close();
        }
        return ret;
    }
}

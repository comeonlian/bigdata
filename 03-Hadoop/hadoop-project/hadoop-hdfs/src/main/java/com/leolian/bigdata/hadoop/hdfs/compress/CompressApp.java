package com.leolian.bigdata.hadoop.hdfs.compress;

import com.hadoop.compression.lzo.LzoCodec;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/1 15:27
 */
public class CompressApp {
    // F:/Java-Dev/taobao-it18/03-Hadoop/compress/comp.txt
    // /opt/dev/compress/file/comp.txt
    private static String file = "/opt/dev/compress/file/comp.txt";
    // F:/Java-Dev/taobao-it18/03-Hadoop/compress/comp
    // /opt/dev/compress/file/comp
    private static String file_prefix = "/opt/dev/compress/file/comp";
    
    public static void main(String[] args) throws Exception {
        Class[] clazzs = new Class[]{
                DeflateCodec.class,
                GzipCodec.class,
                BZip2Codec.class,
                LzoCodec.class
        };
        for (Class clazz : clazzs) {
            zip(clazz);
        }
        for (Class clazz : clazzs) {
            unzip(clazz);
        }
    }

    /**
     * 压缩
     * @param clazz
     */
    private static void zip(Class<CompressionCodec> clazz) throws Exception {
        CompressionCodec compressionCodec = ReflectionUtils.newInstance(clazz, new Configuration());
        FileInputStream inputStream = new FileInputStream(new File(file));
        FileOutputStream outputStream = new FileOutputStream(new File(file_prefix + compressionCodec.getDefaultExtension()));
        CompressionOutputStream compressionOutputStream = compressionCodec.createOutputStream(outputStream);
        IOUtils.copyBytes(inputStream, compressionOutputStream, 1024);
        compressionOutputStream.close();
    }

    /**
     * 解压缩
     * @param clazz
     */
    private static void unzip(Class<CompressionCodec> clazz) throws Exception {
        CompressionCodec compressionCodec = ReflectionUtils.newInstance(clazz, new Configuration());
        FileOutputStream outputStream = new FileOutputStream(new File(file_prefix + compressionCodec.getDefaultExtension() + ".txt"));
        FileInputStream inputStream = new FileInputStream(new File(file_prefix + compressionCodec.getDefaultExtension()));
        CompressionInputStream compressionInputStream = compressionCodec.createInputStream(inputStream);
        IOUtils.copyBytes(compressionInputStream, outputStream, 1024);
        compressionInputStream.close();
    }

}

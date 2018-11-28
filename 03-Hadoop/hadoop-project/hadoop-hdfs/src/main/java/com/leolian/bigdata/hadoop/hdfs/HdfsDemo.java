package com.leolian.bigdata.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * @description:
 * @author lianliang
 * @date 2018/11/28 21:13
 */
public class HdfsDemo {
    private FileSystem fileSystem = null;
    private String winRootDir = "windirs";
    
    @Before
    public void before() throws IOException {
        Configuration configuration = new Configuration();
        fileSystem = FileSystem.get(configuration);
    }
    
    @Test
    public void mkdir() throws IOException {
        fileSystem.mkdirs(new Path(winRootDir));
    }

    private String file = "RelaxYourEyesGreen.jar";
    private String path = "F:/Java-Dev/taobao-it18/03-Hadoop/file" ;
    private String targetFile = winRootDir + "/" + file;
    
    @Test
    public void upload() throws IOException {
        String localFile = path + File.separator + file;
        InputStream inputStream = new FileInputStream(localFile);
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path(targetFile));
        IOUtils.copyBytes(inputStream, fsDataOutputStream, 1024 * 5);
        inputStream.close();
        fsDataOutputStream.close();
    }
    
    @Test
    public void download() throws IOException {
        OutputStream outputStream = new FileOutputStream(new File(path + File.separator + "temp.jar"));
        FSDataInputStream fsDataInputStream = fileSystem.open(new Path(targetFile));
        IOUtils.copyBytes(fsDataInputStream, outputStream, 1024 * 5);
        outputStream.close();
        fsDataInputStream.close();
    }
    
    @Test
    public void delete() throws IOException {
        fileSystem.delete(new Path(targetFile), false);
    }
    
}

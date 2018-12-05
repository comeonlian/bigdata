package com.leolian.bigdata.hadoop.mapreduce.mapfile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/2 11:23
 */
public class MapFileTest {
    private Configuration configuration = null;
    private FileSystem fileSystem = null;
    private Path path = null;
    private String uri = "F:/Java-Dev/taobao-it18/03-Hadoop/mapfile";

    @Before
    public void before() {
        try {
            configuration = new Configuration();
            configuration.set("fs.defaultFS", "file:///");
            fileSystem = FileSystem.get(configuration);
            path = new Path(uri);
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("deprecation")
    @Test
    public void writeLow() throws Exception {
        IntWritable key = new IntWritable();
        Text value = new Text();
        MapFile.Writer writer = null;

        try {
            writer = new MapFile.Writer(configuration, fileSystem, uri,
                    key.getClass(), value.getClass());
            for (int i = 0; i < 10; i++) {
                key.set(i);
                value.set("Tom" + i);
                System.out.printf("%s\t%s\n", key, value);
                writer.append(key, value);
            }
        } finally {
            IOUtils.closeStream(writer);
        }
    }

    @SuppressWarnings("deprecation")
    @Test
    public void writeException() throws Exception {
        IntWritable key = new IntWritable();
        Text value = new Text();
        MapFile.Writer writer = null;

        try {
            writer = new MapFile.Writer(configuration, fileSystem, uri,
                    key.getClass(), value.getClass());
            for (int i = 10; i < 20; i++) {
                key.set(i);
                value.set("Tom" + i);
                System.out.printf("%s\t%s\n", key, value);
                writer.append(key, value);
            }
            for (int i = 0; i < 10; i++) {
                key.set(i);
                value.set("Tom" + i);
                System.out.printf("%s\t%s\n", key, value);
                writer.append(key, value);
            }
        } finally {
            IOUtils.closeStream(writer);
        }
    }

    @SuppressWarnings("deprecation")
    @Test
    public void read() throws Exception {
        MapFile.Reader reader = null;
        try {
            reader = new MapFile.Reader(fileSystem, uri, configuration);
            WritableComparable key = (WritableComparable)
                    ReflectionUtils.newInstance(reader.getKeyClass(), configuration);
            Writable value = (Writable)
                    ReflectionUtils.newInstance(reader.getValueClass(), configuration);
            while (reader.next(key, value)) {
                System.out.printf("%s\t%s\n", key, value);
            }
        } finally {
            IOUtils.closeStream(reader);
        }
    }

    @SuppressWarnings("deprecation")
    @Test
    public void readSeek() throws Exception {
        MapFile.Reader reader = null;
        try {
            reader = new MapFile.Reader(fileSystem, uri, configuration);
            WritableComparable key = (WritableComparable)
                    ReflectionUtils.newInstance(reader.getKeyClass(), configuration);
            Writable value = (Writable)
                    ReflectionUtils.newInstance(reader.getValueClass(), configuration);
            reader.seek(new IntWritable(5));
            while (reader.next(key, value)) {
                System.out.printf("%s\t%s\n", key, value);
            }
        } finally {
            IOUtils.closeStream(reader);
        }
    }
    
}

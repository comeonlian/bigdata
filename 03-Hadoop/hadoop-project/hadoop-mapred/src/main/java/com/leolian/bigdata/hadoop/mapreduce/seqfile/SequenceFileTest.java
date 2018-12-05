package com.leolian.bigdata.hadoop.mapreduce.seqfile;

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
public class SequenceFileTest {
    private Configuration configuration = null;
    private FileSystem fileSystem = null;
    private Path path = null;

    @Before
    public void before() {
        try {
            configuration = new Configuration();
            configuration.set("fs.defaultFS", "file:///");
            fileSystem = FileSystem.get(configuration);
            String uri = "F:/Java-Dev/taobao-it18/03-Hadoop/seqfile/test.seq";
            path = new Path(uri);
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("deprecation")
    @Test
    public void write() throws Exception {
        IntWritable key = new IntWritable();
        Text value = new Text();
        SequenceFile.Writer writer = null;
        try {
            writer = SequenceFile.createWriter(fileSystem, configuration, path,
                    key.getClass(), value.getClass());
            for (int i = 0; i < 10; i++) {
                key.set(i);
                value.set("Tom" + i);
                System.out.printf("[%s]\t%s\t%s\n", writer.getLength(), key, value);
                writer.append(key, value);
                writer.sync(); //手动写入同步点
            }
        } finally {
            IOUtils.closeStream(writer);
        }
    }
    /*
    * [128]	0	Tom0
    [173]	1	Tom1
    [218]	2	Tom2
    [263]	3	Tom3
    [308]	4	Tom4
    [353]	5	Tom5
    [398]	6	Tom6
    [443]	7	Tom7
    [488]	8	Tom8
    [533]	9	Tom9
    * */

    @SuppressWarnings("deprecation")
    @Test
    public void readSeek() throws Exception{
        SequenceFile.Reader reader = null;
        try {
            reader = new SequenceFile.Reader(fileSystem, path, configuration);
            Writable key = (Writable)
                    ReflectionUtils.newInstance(reader.getKeyClass(), configuration);
            Writable value = (Writable)
                    ReflectionUtils.newInstance(reader.getValueClass(), configuration);
            reader.seek(170);
            long position = reader.getPosition();
            while (reader.next(key, value)) {
                System.out.printf("[%s]\t%s\t%s\n", position, key, value);
                position = reader.getPosition(); // beginning of next record
            }
        } finally {
            IOUtils.closeStream(reader);
        }
    }

    @SuppressWarnings("deprecation")
    @Test
    public void readSync() throws Exception{
        SequenceFile.Reader reader = null;
        try {
            reader = new SequenceFile.Reader(fileSystem, path, configuration);
            Writable key = (Writable)
                    ReflectionUtils.newInstance(reader.getKeyClass(), configuration);
            Writable value = (Writable)
                    ReflectionUtils.newInstance(reader.getValueClass(), configuration);
            reader.sync(170);
            long position = reader.getPosition();
            while (reader.next(key, value)) {
                System.out.printf("[%s]\t%s\t%s\n", position, key, value);
                position = reader.getPosition(); // beginning of next record
            }
        } finally {
            IOUtils.closeStream(reader);
        }
    }
}

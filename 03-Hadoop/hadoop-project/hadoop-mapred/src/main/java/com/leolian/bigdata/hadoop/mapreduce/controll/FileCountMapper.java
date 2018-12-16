package com.leolian.bigdata.hadoop.mapreduce.controll;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description: 
 * @author lianliang
 * @date 2018/12/16 21:22
 */
public class FileCountMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString(); // hehe-haha.txt 3
        String[] strArr = line.split("-");
        String word = strArr[0];
        String info = strArr[1];
        context.write(new Text(word), new Text(info));
    }
}

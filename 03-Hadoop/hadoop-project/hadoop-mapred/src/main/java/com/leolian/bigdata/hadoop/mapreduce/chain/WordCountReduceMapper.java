package com.leolian.bigdata.hadoop.mapreduce.chain;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description: 
 * @author lianliang
 * @date 2018/12/9 11:48
 */
public class WordCountReduceMapper extends Mapper<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void map(Text key, IntWritable value, Context context) throws IOException, InterruptedException {
        int cnt = value.get();
        if (cnt > 5) {
            context.write(key, value);
        }
    }
}

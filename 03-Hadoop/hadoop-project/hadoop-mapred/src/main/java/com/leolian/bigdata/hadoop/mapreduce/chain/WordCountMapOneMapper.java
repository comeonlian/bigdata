package com.leolian.bigdata.hadoop.mapreduce.chain;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/9 11:45
 */
public class WordCountMapOneMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] arr = line.split("\\t");
        for (String str : arr) {
            context.write(new Text(str), new IntWritable(1));
        }
    }
}

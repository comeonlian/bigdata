package com.leolian.bigdata.hadoop.mapreduce.multiinput;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description:
 * @author lianliang
 * @date 2018/11/28 22:08
 */
public class WordCountTextMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] array = line.split("\\t");
        for (String arr : array) {
            context.write(new Text(arr), new IntWritable(1));
        }
    }

}

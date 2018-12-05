package com.leolian.bigdata.hadoop.mapreduce.multiinput;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/5 21:10
 */
public class WordCountSeqMapper extends Mapper<IntWritable, Text, Text, IntWritable> {

    @Override
    protected void map(IntWritable key, Text value, Context context) throws IOException, InterruptedException {
        String string = value.toString();
        if (string.contains("\t")) {
            String[] strings = string.split("\\t");
            for (String s : strings) {
                context.write(new Text(s), new IntWritable(1));
            }
        } else {
            context.write(value, new IntWritable(1));
        }
    }
}

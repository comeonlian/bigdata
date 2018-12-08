package com.leolian.bigdata.hadoop.mapreduce.skew.first;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/8 22:07
 */
public class WordCountSkewMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] split = line.split("\\t");
        for (String val : split) {
            context.write(new Text(val), new IntWritable(1));
        }
    }

}

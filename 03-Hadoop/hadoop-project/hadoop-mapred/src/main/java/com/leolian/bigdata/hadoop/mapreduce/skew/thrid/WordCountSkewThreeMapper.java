package com.leolian.bigdata.hadoop.mapreduce.skew.thrid;

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
public class WordCountSkewThreeMapper extends Mapper<Text, Text, Text, IntWritable> {

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        context.write(new Text(key), new IntWritable(Integer.valueOf(value.toString())));
    }
    
}

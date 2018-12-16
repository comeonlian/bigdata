package com.leolian.bigdata.hadoop.mapreduce.controll;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/16 21:21
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private String fileName;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        fileName = inputSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] array = line.split("\\t");
        for (String val : array) {
            context.write(new Text(val + "-" + fileName), new IntWritable(1));
        }
    }
}

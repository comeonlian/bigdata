package com.leolian.bigdata.hadoop.mapreduce.database;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/9 14:26
 */
public class DatabaseMapper extends Mapper<LongWritable, MyDBWritable, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, MyDBWritable value, Context context) throws IOException, InterruptedException {
        System.out.printf("Mapper key: " + key.get());
        System.out.println("Mapper value: " + value.toString());
        String words = value.getWords();
        String[] array = words.split(" ");
        for (String str : array) {
            context.write(new Text(str), new IntWritable(1));
        }
    }
}

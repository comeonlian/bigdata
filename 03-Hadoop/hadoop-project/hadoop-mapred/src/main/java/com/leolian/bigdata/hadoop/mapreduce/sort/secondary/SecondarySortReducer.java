package com.leolian.bigdata.hadoop.mapreduce.sort.secondary;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @description: 
 * @author lianliang
 * @date 2018/12/8 21:00
 */
public class SecondarySortReducer extends Reducer<CombineKey, NullWritable, IntWritable, IntWritable> {
    @Override
    protected void reduce(CombineKey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        int year = key.getYear();
        int temperature = key.getTemperature();
        context.write(new IntWritable(year), new IntWritable(temperature));
    }
}

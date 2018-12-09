package com.leolian.bigdata.hadoop.mapreduce.database;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @description: 
 * @author lianliang
 * @date 2018/12/9 14:26
 */
public class DatabaseReducer extends Reducer<Text, IntWritable, MyDBWritable, NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for (IntWritable intWritable : values) {
            count = count + intWritable.get();
        }
        MyDBWritable myDBWritable = new MyDBWritable();
        myDBWritable.setWord(key.toString());
        myDBWritable.setCount(count);
        context.write(myDBWritable, NullWritable.get());
    }
}

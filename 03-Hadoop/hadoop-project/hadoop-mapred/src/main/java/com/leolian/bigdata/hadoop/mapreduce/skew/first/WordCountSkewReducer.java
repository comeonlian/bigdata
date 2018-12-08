package com.leolian.bigdata.hadoop.mapreduce.skew.first;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @description: 
 * @author lianliang
 * @date 2018/12/8 22:09
 */
public class WordCountSkewReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for (IntWritable iw : values) {
            count = count + iw.get();
        }
        context.write(key, new IntWritable(count));
    }
    
}

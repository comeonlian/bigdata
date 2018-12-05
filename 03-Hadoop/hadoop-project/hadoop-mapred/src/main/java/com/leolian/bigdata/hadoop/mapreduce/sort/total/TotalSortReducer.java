package com.leolian.bigdata.hadoop.mapreduce.sort.total;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/5 21:53
 */
public class TotalSortReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

    @Override
    protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int max = Integer.MIN_VALUE;
        for (IntWritable iw : values) {
            if (iw.get() > max) {
                max = iw.get();
            }
        }
        context.write(key, new IntWritable(max));
    }

}

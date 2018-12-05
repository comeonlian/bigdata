package com.leolian.bigdata.hadoop.mapreduce.local;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @description:
 * @author lianliang
 * @date 2018/11/28 22:08
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private static final Log LOG = LogFactory.getLog(WordCountReducer.class);

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for (IntWritable intWritable : values) {
            count = count + intWritable.get();
        }
        LOG.info("//////// " + Thread.currentThread().getName() + ": Reducer : " + key.toString() + "=" + count);
        context.write(key, new IntWritable(count));
    }

}

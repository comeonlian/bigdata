package com.leolian.bigdata.hadoop.mapreduce.chain;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 敏感词过滤
 * @author lianliang
 * @date 2018/12/9 11:45
 */
public class WordCountMapTwoMapper extends Mapper<Text, IntWritable, Text, IntWritable> {
    private static Set<String> set = new HashSet<String>() {
        {
            add("falungong");
            add("dupin");
        }
    };

    @Override
    protected void map(Text key, IntWritable value, Context context) throws IOException, InterruptedException {
        String text = key.toString();
        if (!set.contains(text)) {
            context.write(key, value);
        }
    }
}

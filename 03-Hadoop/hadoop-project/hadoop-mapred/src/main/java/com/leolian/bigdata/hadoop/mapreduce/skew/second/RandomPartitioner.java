package com.leolian.bigdata.hadoop.mapreduce.skew.second;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.Random;

/**
 * @description: 随机分区
 * @author lianliang
 * @date 2018/12/8 22:24
 */
public class RandomPartitioner extends Partitioner<Text, IntWritable> {

    @Override
    public int getPartition(Text text, IntWritable intWritable, int i) {
        Random random = new Random();
        int nextInt = random.nextInt(i);
        return nextInt % i;
    }
    
}

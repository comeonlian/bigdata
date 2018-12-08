package com.leolian.bigdata.hadoop.mapreduce.sort.secondary;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @description: 
 * @author lianliang
 * @date 2018/12/8 20:59
 */
public class SecondarySortPartitioner extends Partitioner<CombineKey, NullWritable> {

    @Override
    public int getPartition(CombineKey combineKey, NullWritable nullWritable, int i) {
        // 同一个年分到同一个分区
        int year = combineKey.getYear();
        return year % i;
    }
    
}

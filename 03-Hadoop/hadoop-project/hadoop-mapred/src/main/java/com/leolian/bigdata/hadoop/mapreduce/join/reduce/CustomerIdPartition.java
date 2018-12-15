package com.leolian.bigdata.hadoop.mapreduce.join.reduce;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @description: 
 * @author lianliang
 * @date 2018/12/15 21:31
 */
public class CustomerIdPartition extends Partitioner<CombineKey, NullWritable> {
    @Override
    public int getPartition(CombineKey combineKey, NullWritable nullWritable, int numPartitions) {
        return combineKey.getCustomerId() % numPartitions;
    }
}

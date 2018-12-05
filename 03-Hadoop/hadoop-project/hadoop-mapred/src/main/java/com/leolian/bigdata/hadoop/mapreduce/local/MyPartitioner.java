package com.leolian.bigdata.hadoop.mapreduce.local;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/2 10:59
 */
public class MyPartitioner extends Partitioner<Text, IntWritable> {
    private static final Log LOG = LogFactory.getLog(MyPartitioner.class);
    
    @Override
    public int getPartition(Text text, IntWritable intWritable, int i) {
        LOG.info("============= MyPartitioner =============");
        return 0;
    }

}

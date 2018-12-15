package com.leolian.bigdata.hadoop.mapreduce.join.reduce;

import com.leolian.bigdata.hadoop.mapreduce.join.map.MapJoinMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/15 20:53
 */
public class ReduceJoinApp {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "file:///");

        Job job = Job.getInstance(configuration);
        // 设置Job属性
        job.setJobName("ReduceJoinApp");
        job.setJarByClass(ReduceJoinApp.class);

        FileInputFormat.addInputPath(job, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\join\\reduce"));
        FileOutputFormat.setOutputPath(job, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\join\\reduce\\out"));

        job.setMapperClass(ReduceJoinMapper.class);
        job.setReducerClass(ReduceJoinReducer.class);

        job.setMapOutputKeyClass(CombineKey.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(2);
        
        job.setPartitionerClass(CustomerIdPartition.class);
        job.setSortComparatorClass(CustomerOrderComparator.class);
        job.setGroupingComparatorClass(CustomerOrderGroupComparator.class);
        
        job.waitForCompletion(true);
    }
}

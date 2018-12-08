package com.leolian.bigdata.hadoop.mapreduce.sort.secondary;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/8 21:38
 */
public class SecondarySortApp {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "file:///");

        Job job = Job.getInstance(configuration);
        // 设置Job属性
        job.setJobName("SecondarySortApp");
        job.setJarByClass(SecondarySortApp.class);
        // 设置Key-Value输入格式
        job.setInputFormatClass(KeyValueTextInputFormat.class);

        FileInputFormat.addInputPath(job, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\secondary"));
        FileOutputFormat.setOutputPath(job, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\secondary\\out"));

        job.setMapperClass(SecondarySortMapper.class);
        job.setReducerClass(SecondarySortReducer.class);

        job.setMapOutputKeyClass(CombineKey.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        job.setPartitionerClass(SecondarySortPartitioner.class);
        job.setSortComparatorClass(CombineKeyComparator.class);
        job.setGroupingComparatorClass(YearGroupComparator.class);

        job.setNumReduceTasks(3);

        job.waitForCompletion(true);
    }

}

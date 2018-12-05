package com.leolian.bigdata.hadoop.mapreduce.sort.total;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/5 21:54
 */
public class TotalSortApp {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "file:///");

        Job job = Job.getInstance(configuration);
        // 设置Job属性
        job.setJobName("TotalSortApp");
        job.setJarByClass(TotalSortApp.class);
        job.setInputFormatClass(SequenceFileInputFormat.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(TotalSortMapper.class);
        job.setReducerClass(TotalSortReducer.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        job.setNumReduceTasks(3);
        
        // 设置采样器
        InputSampler.Sampler<IntWritable, IntWritable> sampler = 
                new InputSampler.RandomSampler<IntWritable, IntWritable>(0.5, 6000, 2);

        TotalOrderPartitioner.setPartitionFile(job.getConfiguration(),
                new Path("file:///F:/Java-Dev/taobao-it18/03-Hadoop/mr/partitionfile/part.lst"));
        job.setPartitionerClass(TotalOrderPartitioner.class);
        
        InputSampler.writePartitionFile(job, sampler);
        
        job.waitForCompletion(true);
    }

}

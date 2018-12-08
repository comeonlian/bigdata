package com.leolian.bigdata.hadoop.mapreduce.skew.first;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/8 22:12
 */
public class WordCountSkewApp {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "file:///");

        Job job = Job.getInstance(configuration);
        // 设置Job属性
        job.setJobName("WordCountSkewApp");
        job.setJarByClass(WordCountSkewApp.class);
        // 设置Key-Value输入格式
        job.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.addInputPath(job, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\skew\\skew01"));
        FileOutputFormat.setOutputPath(job, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\skew\\skew01\\out"));

        job.setMapperClass(WordCountSkewMapper.class);
        job.setReducerClass(WordCountSkewReducer.class);

        // 即使设置combiner也不能解决数据倾斜问题
        // 如果某个分区内的数据非常多，在Map端仍然存在问题
        job.setCombinerClass(WordCountSkewReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        job.setNumReduceTasks(3);

        job.waitForCompletion(true);
    }

}

package com.leolian.bigdata.hadoop.mapreduce.skew.second;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
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
public class WordCountSkewTwoApp {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "file:///");

        Job job = Job.getInstance(configuration);
        // 设置Job属性
        job.setJobName("WordCountSkewTwoApp");
        job.setJarByClass(WordCountSkewTwoApp.class);
        // 设置Key-Value输入格式
        job.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.addInputPath(job, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\skew\\skew02"));
        FileOutputFormat.setOutputPath(job, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\skew\\skew02\\out"));

        job.setMapperClass(WordCountSkewTwoMapper.class);
        job.setReducerClass(WordCountSkewTwoReducer.class);

        job.setPartitionerClass(RandomPartitioner.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setNumReduceTasks(3);

        job.waitForCompletion(true);
    }

}

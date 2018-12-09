package com.leolian.bigdata.hadoop.mapreduce.chain;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.chain.ChainReducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/8 22:12
 */
public class WordCountChainApp {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "file:///");

        Job job = Job.getInstance(configuration);
        // 设置Job属性
        job.setJobName("WordCountChainApp");
        job.setJarByClass(WordCountChainApp.class);
        // 设置Key-Value输入格式
        job.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.addInputPath(job, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\chain"));
        FileOutputFormat.setOutputPath(job, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\chain\\out"));

        // 设置MapReduce链
        ChainMapper.addMapper(job, WordCountMapOneMapper.class, LongWritable.class, Text.class, Text.class, IntWritable.class, configuration);
        ChainMapper.addMapper(job, WordCountMapTwoMapper.class, Text.class, IntWritable.class, Text.class, IntWritable.class, configuration);

        ChainReducer.setReducer(job, WordCountChainReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class, configuration);
        ChainReducer.addMapper(job, WordCountReduceMapper.class, Text.class, IntWritable.class, Text.class, IntWritable.class, configuration);

        job.setNumReduceTasks(3);

        job.waitForCompletion(true);
    }

}

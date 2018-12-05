package com.leolian.bigdata.hadoop.mapreduce.local;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

/**
 * @description:
 * @author lianliang
 * @date 2018/11/28 21:15
 */
public class WordCountAppPartitioner {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "file:///");

        Job job = Job.getInstance(configuration);
        // 设置Job属性
        job.setJobName("WordCountAppPartitioner");
        job.setJarByClass(WordCountAppPartitioner.class);
        job.setInputFormatClass(TextInputFormat.class);
        
        // 设置结果文件输出格式
//        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        // 设置最大切片数
//        FileInputFormat.setMaxInputSplitSize(job, 13);
        // 设置最小切片数
//        FileInputFormat.setMinInputSplitSize(job, 1);
        
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
    
        // 设置分区
        job.setPartitionerClass(MyPartitioner.class);
        // 设置合成类
        job.setCombinerClass(WordCountReducer.class);
        
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setNumReduceTasks(3);

        job.waitForCompletion(true);
    }

}

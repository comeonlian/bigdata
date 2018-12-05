package com.leolian.bigdata.hadoop.mapreduce.multiinput;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @description:
 * @author lianliang
 * @date 2018/11/28 21:15
 */
public class WordCountMultiFileApp {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "file:///");

        Job job = Job.getInstance(configuration);
        // 设置Job属性
        job.setJobName("WordCountMultiFileApp");
        job.setJarByClass(WordCountMultiFileApp.class);

        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, WordCountTextMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[1]), SequenceFileInputFormat.class, WordCountSeqMapper.class);

        FileOutputFormat.setOutputPath(job, new Path(args[2]));

        job.setReducerClass(WordCountReducer.class);
        job.setNumReduceTasks(2);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.waitForCompletion(true);
    }

}

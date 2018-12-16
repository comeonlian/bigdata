package com.leolian.bigdata.hadoop.mapreduce.controll;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.util.concurrent.TimeUnit;

/**
 * @description: 多Job串联
 * @author lianliang
 * @date 2018/12/16 21:23
 */
public class JobControlApp {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "file:///");
        // 第一个job
        Job jobFirst = Job.getInstance(configuration);
        jobFirst.setJarByClass(JobControlApp.class);
        jobFirst.setMapperClass(WordCountMapper.class);
        jobFirst.setReducerClass(WordCountReducer.class);
        jobFirst.setMapOutputKeyClass(Text.class);
        jobFirst.setMapOutputValueClass(IntWritable.class);
        jobFirst.setOutputKeyClass(Text.class);
        jobFirst.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(jobFirst, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\controll\\jobfirst"));
        FileOutputFormat.setOutputPath(jobFirst, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\controll\\firstout"));
        // 第二个job
        Job jobSecond = Job.getInstance(configuration);
        jobSecond.setJarByClass(JobControlApp.class);
        jobSecond.setMapperClass(FileCountMapper.class);
        jobSecond.setReducerClass(FileCountReducer.class);
        jobSecond.setMapOutputKeyClass(Text.class);
        jobSecond.setMapOutputValueClass(Text.class);
        jobSecond.setOutputKeyClass(Text.class);
        jobSecond.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(jobSecond, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\controll\\firstout"));
        FileOutputFormat.setOutputPath(jobSecond, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\controll\\secondout"));

        ControlledJob controlledJobFirst = new ControlledJob(jobFirst.getConfiguration());
        ControlledJob controlledJobSecond = new ControlledJob(jobSecond.getConfiguration());

        controlledJobFirst.setJob(jobFirst);
        controlledJobSecond.setJob(jobSecond);
        controlledJobSecond.addDependingJob(controlledJobFirst);

        JobControl jobControl = new JobControl("job controlled");
        jobControl.addJob(controlledJobFirst);
        jobControl.addJob(controlledJobSecond);

        Thread thread = new Thread(jobControl);
        thread.start();

        while (!jobControl.allFinished()) {
            TimeUnit.SECONDS.sleep(1);
        }
        jobControl.stop();
    }
}

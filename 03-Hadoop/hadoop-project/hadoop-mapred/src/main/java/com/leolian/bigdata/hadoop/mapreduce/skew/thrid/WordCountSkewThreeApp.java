package com.leolian.bigdata.hadoop.mapreduce.skew.thrid;

import com.leolian.bigdata.hadoop.mapreduce.skew.second.RandomPartitioner;
import com.leolian.bigdata.hadoop.mapreduce.skew.second.WordCountSkewTwoMapper;
import com.leolian.bigdata.hadoop.mapreduce.skew.second.WordCountSkewTwoReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/8 22:12
 */
public class WordCountSkewThreeApp {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "file:///");

        Job job = Job.getInstance(configuration);
        // 设置Job属性
        job.setJobName("WordCountSkewThreeApp");
        job.setJarByClass(WordCountSkewThreeApp.class);
        // 设置Key-Value输入格式
        job.setInputFormatClass(KeyValueTextInputFormat.class);

        FileInputFormat.addInputPath(job, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\skew\\skew02\\out\\part-r-00000"));
        FileInputFormat.addInputPath(job, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\skew\\skew02\\out\\part-r-00001"));
        FileInputFormat.addInputPath(job, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\skew\\skew02\\out\\part-r-00002"));
        FileOutputFormat.setOutputPath(job, new Path("F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\skew\\skew03\\out"));

        job.setMapperClass(WordCountSkewThreeMapper.class);
        job.setReducerClass(WordCountSkewThreeReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setNumReduceTasks(3);

        job.waitForCompletion(true);
    }

}

package com.leolian.bigdata.hadoop.mapreduce.database;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/8 22:12
 */
public class DatabaseApp {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://192.168.100.130:3306/lian";
    private static String username = "root";
    private static String password = "123456";

    // TODO: 提交集群测试
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
//        configuration.set("fs.defaultFS", "file:///");

        Job job = Job.getInstance(configuration);
        // 设置Job属性
        job.setJobName("DatabaseApp");
        job.setJarByClass(DatabaseApp.class);
        
        // 配置数据库连接信息
        DBConfiguration.configureDB(job.getConfiguration(), driver, url, username, password);
        // 设置数据库输入
        DBInputFormat.setInput(job, MyDBWritable.class, "select id, words from words", "select count(1) from words");
        // 设置将结果输出到数据库
        DBOutputFormat.setOutput(job, "results", "word", "count");
        
        job.setMapperClass(DatabaseMapper.class);
        job.setReducerClass(DatabaseReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(MyDBWritable.class);
        job.setOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(3);

        job.waitForCompletion(true);
    }

}

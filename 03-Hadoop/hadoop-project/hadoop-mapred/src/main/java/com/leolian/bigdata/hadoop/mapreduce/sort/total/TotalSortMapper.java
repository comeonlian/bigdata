package com.leolian.bigdata.hadoop.mapreduce.sort.total;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description: 全排序示例是对Key排序，所以选择读取seq文件
 * @author lianliang
 * @date 2018/12/5 21:52
 */
public class TotalSortMapper extends Mapper<IntWritable, IntWritable, IntWritable, IntWritable> {

    @Override
    protected void map(IntWritable key, IntWritable value, Context context) throws IOException, InterruptedException {
        context.write(key, value);
    }

}

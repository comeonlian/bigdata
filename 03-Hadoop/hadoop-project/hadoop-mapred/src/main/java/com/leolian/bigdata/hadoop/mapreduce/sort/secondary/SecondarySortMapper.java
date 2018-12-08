package com.leolian.bigdata.hadoop.mapreduce.sort.secondary;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/8 20:57
 */
public class SecondarySortMapper extends Mapper<Text, Text, CombineKey, NullWritable> {

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        CombineKey combineKey = new CombineKey();
        combineKey.setYear(Integer.valueOf(key.toString()));
        combineKey.setTemperature(Integer.valueOf(value.toString()));
        context.write(combineKey, NullWritable.get());
    }

}

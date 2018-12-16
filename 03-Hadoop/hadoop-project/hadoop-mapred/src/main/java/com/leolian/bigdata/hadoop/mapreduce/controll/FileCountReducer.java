package com.leolian.bigdata.hadoop.mapreduce.controll;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/16 21:22
 */
public class FileCountReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        for (Text text : values) {
            sb.append(text.toString() + "\t");
        }
        context.write(key, new Text(sb.toString()));
    }
}

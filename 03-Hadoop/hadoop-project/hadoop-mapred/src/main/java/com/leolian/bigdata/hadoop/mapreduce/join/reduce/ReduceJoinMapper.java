package com.leolian.bigdata.hadoop.mapreduce.join.reduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/15 21:32
 */
public class ReduceJoinMapper extends Mapper<LongWritable, Text, CombineKey, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        String filePath = fileSplit.getPath().toString();
        CombineKey combineKey = new CombineKey();
        if (filePath.contains("customers")) { // 用户数据
            combineKey.setType(0);
            int index = line.indexOf(",");
            int customerId = Integer.parseInt(line.substring(0, index));
            combineKey.setCustomerId(customerId);
            combineKey.setCustomerInfo(line);
        } else {
            combineKey.setType(1);
            int index = line.indexOf(",");
            int lastIndexOf = line.lastIndexOf(",");
            int orderId = Integer.parseInt(line.substring(0, index));
            int customerId = Integer.parseInt(line.substring(lastIndexOf + 1));
            String orderInfo = line.substring(0, lastIndexOf);
            combineKey.setCustomerId(customerId);
            combineKey.setOrderId(orderId);
            combineKey.setOrderInfo(orderInfo);
        }
        context.write(combineKey, NullWritable.get());
    }
}

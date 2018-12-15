package com.leolian.bigdata.hadoop.mapreduce.join.reduce;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/15 21:47
 */
public class ReduceJoinReducer extends Reducer<CombineKey, NullWritable, Text, NullWritable> {
    @Override
    protected void reduce(CombineKey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<NullWritable> iterator = values.iterator();
        iterator.next();
        String customerInfo = key.getCustomerInfo();
        while (iterator.hasNext()) {
            iterator.next();
            String orderInfo = key.getOrderInfo();
            context.write(new Text(customerInfo + "," + orderInfo), NullWritable.get());
        }
    }
}

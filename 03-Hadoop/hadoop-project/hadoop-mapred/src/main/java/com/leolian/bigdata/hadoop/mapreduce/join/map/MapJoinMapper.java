package com.leolian.bigdata.hadoop.mapreduce.join.map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/15 19:42
 */
public class MapJoinMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    private static String customerFile = "file:///F:/Java-Dev/taobao-it18/03-Hadoop/mr/join/customers.txt";
    private Map<String, String> customers = new HashMap<>();

    /**
     * 加载客户数据到内存
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException {
        Configuration configuration = context.getConfiguration();
        FileSystem fileSystem = FileSystem.get(configuration);
        FSDataInputStream fsDataInputStream = fileSystem.open(new Path(customerFile));
        BufferedReader reader = new BufferedReader(new InputStreamReader(fsDataInputStream));
        String line = null;
        while ((line = reader.readLine()) != null) {
            int index = line.indexOf(",");
            String id = line.substring(0, index);
            customers.put(id, line);
        }
        reader.close();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        int index = line.lastIndexOf(",");
        String cid = line.substring(index + 1);
        String orderInfo = line.substring(0, index);
        String customerInfo = customers.get(cid);
        context.write(new Text(customerInfo + "," + orderInfo), NullWritable.get());
    }

}

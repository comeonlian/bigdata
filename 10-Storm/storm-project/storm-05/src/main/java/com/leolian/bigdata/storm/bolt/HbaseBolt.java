package com.leolian.bigdata.storm.bolt;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import java.io.IOException;
import java.util.Map;

/**
 * @description:
 * @author lianliang
 * @date 2019/9/8 22:04
 */
public class HbaseBolt implements IRichBolt {

    private Table table;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        try {
            Configuration configuration = HBaseConfiguration.create();
            Connection connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(TableName.valueOf("ns1:t3"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute(Tuple tuple) {
        String word = tuple.getString(0);
        Integer count = tuple.getInteger(1);
        byte[] rowKey = Bytes.toBytes(word);
        byte[] colFamily = Bytes.toBytes("cf1");
        byte[] col = Bytes.toBytes("count");
        try {
            table.incrementColumnValue(rowKey, colFamily, col, count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cleanup() {

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}

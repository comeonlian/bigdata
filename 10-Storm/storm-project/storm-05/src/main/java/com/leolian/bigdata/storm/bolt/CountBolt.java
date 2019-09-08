package com.leolian.bigdata.storm.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/1.
 */
public class CountBolt implements IRichBolt {

    private TopologyContext context;
    private OutputCollector collector;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.context = context;
        this.collector = collector;
    }

    public void execute(Tuple tuple) {
        String word = tuple.getString(0);
        Integer count = tuple.getInteger(1);
        System.out.println(word + " : " + count);
        collector.emit(new Values(word, count));
    }

    public void cleanup() {

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word", "count"));
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}

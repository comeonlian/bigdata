package com.leolian.bigdata.storm.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.*;

/**
 * Spout
 */
public class WordCountSpout implements IRichSpout {
    private TopologyContext context;
    private SpoutOutputCollector collector;

    private List<String> states;

    private Random r = new Random();

    private int index = 0;

    private Map<Long, String> map = new HashMap<Long, String>();

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.context = context;
        this.collector = collector;
        states = new ArrayList<String>();
        states.add("hello world tom");
        states.add("hello world tomas");
        states.add("hello world tomasLee");
        states.add("hello world tomson");
    }

    public void close() {

    }

    public void activate() {

    }

    public void deactivate() {

    }

    public void nextTuple() {
        if (index < 5) {
            String line = states.get(r.nextInt(4));
            long millis = System.currentTimeMillis();
            collector.emit(new Values(line), millis);
            map.put(millis, line);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            index++;
        }
    }

    public void ack(Object msgId) {
        map.remove((Long) msgId);
    }

    public void fail(Object msgId) {
        String line = map.get((Long) msgId);
        collector.emit(new Values(line), msgId);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("line"));
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}

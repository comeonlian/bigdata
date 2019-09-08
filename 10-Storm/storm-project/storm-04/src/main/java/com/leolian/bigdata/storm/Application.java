package com.leolian.bigdata.storm;

import com.leolian.bigdata.storm.bolt.SplitBolt;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;

import java.util.UUID;

/**
 * Application
 */
public class Application {

    public static void main(String[] args) throws Exception {
        TopologyBuilder builder = new TopologyBuilder();

        String topic = "storm_test";
        String zkConnectInfo = "127.0.0.1:2181";
        BrokerHosts brokerHosts = new ZkHosts(zkConnectInfo);
        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, topic, "/storm_test", UUID.randomUUID().toString());
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);

        //设置Spout
        builder.setSpout("kafkaSpout", kafkaSpout).setNumTasks(2);
        //设置creator-Bolt
        builder.setBolt("split-bolt", new SplitBolt(), 2).shuffleGrouping("kafkaSpout").setNumTasks(2);

        Config conf = new Config();
        conf.setNumWorkers(2);
        conf.setDebug(true);

        /**
         * 本地模式storm
         */
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("wc", conf, builder.createTopology());
//        Thread.sleep(100000);
//        cluster.shutdown();

//        StormSubmitter.submitTopology("wordcount", conf, builder.createTopology());
    }

}

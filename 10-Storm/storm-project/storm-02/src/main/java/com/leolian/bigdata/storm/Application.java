package com.leolian.bigdata.storm;

import com.leolian.bigdata.storm.bolt.CustomGroupSplitBolt;
import com.leolian.bigdata.storm.group.MyGrouping;
import com.leolian.bigdata.storm.spout.CustomGroupSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

/**
 * Application
 */
public class Application {

    public static void main(String[] args) throws Exception {
        TopologyBuilder builder = new TopologyBuilder();
        //设置Spout
        builder.setSpout("customGroupSpout", new CustomGroupSpout()).setNumTasks(2);
        //设置creator-Bolt
        builder.setBolt("customGroupSplitBolt", new CustomGroupSplitBolt(), 4)
                .customGrouping("customGroupSpout", new MyGrouping())
                .setNumTasks(4);

        Config conf = new Config();
        conf.setNumWorkers(2);
        conf.setDebug(true);

        /**
         * 本地模式storm
         */
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("custom", conf, builder.createTopology());
        Thread.sleep(10000);
//        cluster.shutdown();

//        StormSubmitter.submitTopology("wordcount", conf, builder.createTopology());
    }

}

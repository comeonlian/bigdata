package com.leolian.bigdata.storm;

import com.leolian.bigdata.storm.bolt.CallLogCounterBolt;
import com.leolian.bigdata.storm.bolt.CallLogCreatorBolt;
import com.leolian.bigdata.storm.spout.CallLogSpout;
import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * App
 */
public class Application {

    public static void main(String[] args) throws InterruptedException, InvalidTopologyException, AuthorizationException, AlreadyAliveException {
        TopologyBuilder builder = new TopologyBuilder();
        //设置Spout
        builder.setSpout("spout", new CallLogSpout());
        //设置creator-Bolt
        builder.setBolt("creator-bolt", new CallLogCreatorBolt()).shuffleGrouping("spout");
        //设置counter-Bolt
        builder.setBolt("counter-bolt", new CallLogCounterBolt()).fieldsGrouping("creator-bolt", new Fields("call"));

        Config conf = new Config();
        conf.setDebug(true);
        
        // 本地模式
//        LocalCluster cluster = new LocalCluster();
//        cluster.submitTopology("LogAnalyserStorm", conf, builder.createTopology());
//        Thread.sleep(10000);
//        //停止集群
//        cluster.shutdown();
        
        // 集群模式
        StormSubmitter.submitTopology("myTopology", conf, builder.createTopology());
    }

}
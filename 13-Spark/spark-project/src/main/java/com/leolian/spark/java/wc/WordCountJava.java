package com.leolian.spark.java.wc;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Lian
 * @description:
 * @date 2020/8/23 16:28
 */
public class WordCountJava {

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf();
        // 设置名称与Master
        // sparkConf.setAppName("WordCountJava");
        // sparkConf.setMaster("local");

        // new sc
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
        String filePath = "E:/Java-Dev/IT18/spark/helloworld.txt";
        JavaRDD<String> rdd1 = javaSparkContext.textFile(args[0]);
        JavaRDD<String> rdd2 = rdd1.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) throws Exception {
                String[] array = s.split(" ");
                List<String> list = Arrays.asList(array);
                return list.iterator();
            }
        });
        //映射,word -> (word,1)
        JavaPairRDD<String, Integer> rdd3 = rdd2.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s, 1);
            }
        });
        //reduce化简
        JavaPairRDD<String, Integer> rdd4 = rdd3.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });
        //
        List<Tuple2<String, Integer>> list = rdd4.collect();
        for (Tuple2<String, Integer> t : list) {
            System.out.println(t._1() + " : " + t._2());
        }
    }

}

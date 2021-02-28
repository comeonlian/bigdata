package com.leolian.spark.scala.wc

import org.apache.spark.{SparkConf, SparkContext}

object WordCountAction01 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf();
    sparkConf.setAppName("WordCountScala")
    sparkConf.setMaster("local[4]");
    val sparkContext = new SparkContext(sparkConf);
    val filePath = "D:/Java/Dev/Spark/line.txt";
    val rdd1 = sparkContext.textFile(filePath).flatMap(_.split(" ")).map((_, 3))
    // redeuce by key
    var rdd2 = rdd1.reduceByKey(_ + _)
    rdd2.collect().foreach(println)
    println("========================")
    // count by key
    rdd1.countByKey().foreach(println)
  }
}

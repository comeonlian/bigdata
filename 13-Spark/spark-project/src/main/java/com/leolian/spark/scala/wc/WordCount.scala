package com.leolian.spark.scala.wc

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf();
    sparkConf.setAppName("WordCountScala")
    sparkConf.setMaster("local[4]");
    val sparkContext = new SparkContext(sparkConf);
    val filePath = "D:/Java/Dev/Spark/line.txt";
    val rdd1 = sparkContext.textFile(filePath)
    val rdd2 = rdd1.map(_.split(" ").length)
    println(rdd2.reduce(_ + _))
  }
}

package com.leolian.spark.scala.wc

import org.apache.spark.{SparkConf, SparkContext}

object WordCountAction {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf();
    sparkConf.setAppName("WordCountScala")
    sparkConf.setMaster("local[4]");
    val sparkContext = new SparkContext(sparkConf);
    val filePath = "D:/Java/Dev/Spark/line.txt";
    val rdd1 = sparkContext.textFile(filePath).flatMap(_.split(" ")).map((_, 1))
    val rdd2 = rdd1.reduceByKey(_ + _, 2)
    rdd2.saveAsTextFile("D:\\Java\\Dev\\Spark\\out")
  }
}

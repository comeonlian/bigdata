package com.leolian.spark.scala.wc

import org.apache.spark.{SparkConf, SparkContext}

object WordCountScala {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf();
    // 设置名称与Master
    // sparkConf.setAppName("WordCountScala");
    // sparkConf.setMaster("local");

    // new sc
    val sparkContext = new SparkContext(sparkConf);
    // Text File
    val filePath = "E:/Java-Dev/IT18/spark/helloworld.txt";
    val result = sparkContext.textFile(args(0))
      .flatMap(line => line.split(" "))
      .map(key => (key, 1))
      .reduceByKey(_ + _)
      .collect;
    result.foreach(println);
  }

}

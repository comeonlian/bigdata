package com.leolian.spark.scala.demo

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

object MapDemo {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf();
    // 设置名称与Master
    sparkConf.setAppName("SparkDemo");
    sparkConf.setMaster("local[2]");

    // new sc
    val sparkContext = new SparkContext(sparkConf);
    // Text File
    val filePath = "D:/Java/Dev/Spark/line.txt";
    val rdd1 = sparkContext.textFile(filePath, 2)
    val rdd2 = rdd1.flatMap(line => line.split(" "))
    //    val rdd3 = rdd2.map(word => {
    //      val t = (word, 1)
    //      println("tuple: " + t);
    //      t
    //    })

    def func(iter: Iterator[String]): Iterator[(String)] = {
      var res = ArrayBuffer[String]()
      println(Thread.currentThread().getName + " : mapPartitions start")
      for (it <- iter) {
        res.+=("_" + it)
      }
      res.iterator
    }

    val rdd3 = rdd2.mapPartitions(func)

    val rdd5 = rdd3.map((_, 1))
    val rdd4 = rdd5.reduceByKey(_ + _)
    val r = rdd4.collect();
    r.foreach(println);
  }


}

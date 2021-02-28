package com.leolian.spark.scala.demo

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 */
object DistinctDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("WordCountScala")
    conf.setMaster("local[4]");
    val sc = new SparkContext(conf)
    val rdd1 = sc.textFile("D:/Java/Dev/Spark/line.txt", 4)
    val rdd2 = rdd1.flatMap(_.split(" "))
    val rdd3 = rdd2.distinct()

    rdd3.collect().foreach(println)
  }

}

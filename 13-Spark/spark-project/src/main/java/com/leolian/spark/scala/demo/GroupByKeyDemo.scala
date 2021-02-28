package com.leolian.spark.scala.demo

import org.apache.spark.{SparkConf, SparkContext}

object GroupByKeyDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("GroupByKeyDemo")
    conf.setMaster("local[2]");
    val sc = new SparkContext(conf)
    val rdd1 = sc.textFile("D:/Java/Dev/Spark/stus.txt")
    val rdd2 = rdd1.map(line => {
      var key = line.split(" ")(3)
      (key, line)
    })
    val rdd3 = rdd2.groupByKey()
    rdd3.collect().foreach(t => {
      var key = t._1
      print(key + ": ==== ")
      for (e <- t._2) {
        print(e + " ")
      }
      println()
    })

  }

}

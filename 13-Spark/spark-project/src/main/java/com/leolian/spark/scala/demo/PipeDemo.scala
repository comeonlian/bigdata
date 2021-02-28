package com.leolian.spark.scala.demo

import org.apache.spark.{SparkConf, SparkContext}

object PipeDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("CartesianDemo")
    conf.setMaster("local[2]");
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(Array("file:///d:", "file:///e:"))

    val rdd3 = rdd1.pipe("file:///d: dir")
    rdd3.collect().foreach(println)
  }

}

package com.leolian.spark.scala.demo

import org.apache.spark.{SparkConf, SparkContext}

object CartesianDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("CartesianDemo")
    conf.setMaster("local[2]");
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(Array("tom", "tomas", "tomasLee", "tomson"))
    val rdd2 = sc.parallelize(Array("1234", "5678", "9876", "4321"))

    val rdd3 = rdd1.cartesian(rdd2)
    rdd3.collect().foreach(t => {
      println(t)
    })
  }

}

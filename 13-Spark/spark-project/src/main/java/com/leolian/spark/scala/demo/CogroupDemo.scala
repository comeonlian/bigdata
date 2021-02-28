package com.leolian.spark.scala.demo

import org.apache.spark.{SparkConf, SparkContext}

object CogroupDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("CogroupDemo")
    conf.setMaster("local[2]");
    val sc = new SparkContext(conf)
    val nameRDD1 = sc.textFile("D:/Java/Dev/Spark/cogroup-1.txt")
    val nameRDD2 = nameRDD1.map(line => {
      val arr = line.split(" ")
      (arr(0), arr(1))
    })
    val scoreRDD1 = sc.textFile("D:/Java/Dev/Spark/cogroup-2.txt")
    val scoreRDD2 = scoreRDD1.map(line => {
      val arr = line.split(" ")
      (arr(0), arr(1))
    })
    val rdd3 = nameRDD2.cogroup(scoreRDD2)
    rdd3.collect().foreach(t => {
      println(t._1 + " ============== ")
      for (e <- t._2._1) {
        print(e + " ")
      }
      println()
      for (e <- t._2._2) {
        print(e + " ")
      }
      println()
    })

  }

}

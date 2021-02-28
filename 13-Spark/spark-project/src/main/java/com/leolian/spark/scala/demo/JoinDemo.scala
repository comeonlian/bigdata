package com.leolian.spark.scala.demo

import org.apache.spark.{SparkConf, SparkContext}

object JoinDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("JoinDemo")
    conf.setMaster("local[2]");
    val sc = new SparkContext(conf)
    val nameRDD1 = sc.textFile("D:/Java/Dev/Spark/name.txt")
    val nameRDD2 = nameRDD1.map(line => {
      val arr = line.split(" ")
      (arr(0).toInt, arr(1))
    })
    val scoreRDD1 = sc.textFile("D:/Java/Dev/Spark/score.txt")
    val scoreRDD2 = scoreRDD1.map(line => {
      val arr = line.split(" ")
      (arr(0).toInt, arr(1))
    })

    val rdd3 = nameRDD2.join(scoreRDD2).sortByKey()
    rdd3.collect().foreach(t => {
      println(t._1 + " : " + t._2)
    })
  }

}

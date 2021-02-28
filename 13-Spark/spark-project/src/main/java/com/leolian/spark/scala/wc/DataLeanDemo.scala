package com.leolian.spark.scala.wc

import org.apache.spark.{SparkConf, SparkContext}

import scala.util.Random

/**
 * 数据倾斜处理
 */
object DataLeanDemo {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf();
    sparkConf.setAppName("WordCountScala")
    sparkConf.setMaster("local[4]");
    val sparkContext = new SparkContext(sparkConf);
    val filePath = "D:/Java/Dev/Spark/line.txt";
    val rdd1 = sparkContext.textFile(filePath, 4)
      .flatMap(_.split(" "))
      .map((_, 1)).map(t => {
      var word = t._1
      val rand = Random.nextInt(100)
      (word + "_" + rand, t._2)
    }).reduceByKey(_ + _, 4)
      .map(t => {
        val word = t._1.split("_")(0)
        val count = t._2
        (word, count)
      }).reduceByKey(_ + _, 2)
      .saveAsTextFile("D:\\Java\\Dev\\Spark\\lean")
  }
}

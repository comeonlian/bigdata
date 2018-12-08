package com.leolian.bigdata.hadoop.mapreduce;

import org.apache.hadoop.io.IntWritable;

import java.io.*;
import java.util.Random;

/**
 * @description: 
 * @author lianliang
 * @date 2018/12/8 21:23
 */
public class WriteData {

    public static void main(String[] args) throws Exception {
        String file = "F:\\Java-Dev\\taobao-it18\\03-Hadoop\\mr\\secondary\\temperature.txt";
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter((new FileOutputStream(new File(file)))));
        for (int i = 0; i < 1000; i++) {
            int year = 1970 + new Random().nextInt(100);
            int temp = -30 + new Random().nextInt(100);
            writer.write(year + "\t" + temp);
            writer.newLine();
        }
        writer.close();
    }
    
}

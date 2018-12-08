package com.leolian.bigdata.hadoop.mapreduce.sort.secondary;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @description: 组合key
 * @author lianliang
 * @date 2018/12/8 20:43
 */
public class CombineKey implements WritableComparable<CombineKey> {
    private int year;
    private int temperature;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public int compareTo(CombineKey combineKey) {
        int yearOther = combineKey.getYear();
        int temperatureOther = combineKey.getTemperature();
        if (this.year == yearOther) {
            // 返回值大于0，即为升序，返回值小于0，即为降序
            return -(temperature - temperatureOther);
        } else {
            return year - yearOther;
        }
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(year);
        dataOutput.writeInt(temperature);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.year = dataInput.readInt();
        this.temperature = dataInput.readInt();
    }

}

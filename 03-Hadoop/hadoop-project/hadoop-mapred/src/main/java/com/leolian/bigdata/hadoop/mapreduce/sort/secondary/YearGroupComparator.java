package com.leolian.bigdata.hadoop.mapreduce.sort.secondary;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @description: 
 * @author lianliang
 * @date 2018/12/8 21:14
 */
public class YearGroupComparator extends WritableComparator {

    public YearGroupComparator() {
        super(CombineKey.class, true);
    }

    @Override
    public int compare(WritableComparable comparable1, WritableComparable comparable2) {
        CombineKey combineKey1 = (CombineKey) comparable1;
        CombineKey combineKey2 = (CombineKey) comparable2;
        return combineKey1.getYear() - combineKey2.getYear();
    }
}

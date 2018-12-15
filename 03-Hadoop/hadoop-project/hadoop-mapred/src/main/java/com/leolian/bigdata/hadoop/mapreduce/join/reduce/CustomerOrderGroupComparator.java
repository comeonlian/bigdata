package com.leolian.bigdata.hadoop.mapreduce.join.reduce;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @description: 
 * @author lianliang
 * @date 2018/12/15 21:53
 */
public class CustomerOrderGroupComparator extends WritableComparator {
    public CustomerOrderGroupComparator() {
        super(CombineKey.class, true);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        CombineKey combineKey1 = (CombineKey) a;
        CombineKey combineKey2 = (CombineKey) b;
        return combineKey1.getCustomerId() - combineKey2.getCustomerId();
    }
}

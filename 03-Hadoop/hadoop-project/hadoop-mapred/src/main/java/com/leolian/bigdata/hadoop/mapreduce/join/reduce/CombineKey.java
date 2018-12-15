package com.leolian.bigdata.hadoop.mapreduce.join.reduce;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @description: 
 * @author lianliang
 * @date 2018/12/15 21:18
 */
public class CombineKey implements WritableComparable<CombineKey> {
    private int type; // 记录的类型 0-用户 1-订单
    private int customerId;
    private int orderId;
    private String customerInfo = "";
    private String orderInfo = "";

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    @Override
    public int compareTo(CombineKey obj) {
        int objType = obj.type;
        int objCustomerId = obj.customerId;
        int objOrderId = obj.orderId;
        if (customerId == objCustomerId) { // 同一个用户的数据(包括用户信息和订单信息)
            if (type == objType) {
                // 同一个用户的订单数据
                return -(orderId - objOrderId);
            } else {
                if (type == 0) { //用户数据
                    return -1;
                } else { //订单数据
                    return 1;
                }
            }
        } else { // 不同用户的数据
            return customerId - objCustomerId;
        }
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(type);
        dataOutput.writeInt(customerId);
        dataOutput.writeInt(orderId);
        dataOutput.writeUTF(customerInfo);
        dataOutput.writeUTF(orderInfo);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.type = dataInput.readInt();
        this.customerId = dataInput.readInt();
        this.orderId = dataInput.readInt();
        this.customerInfo = dataInput.readUTF();
        this.orderInfo = dataInput.readUTF();
    }
}

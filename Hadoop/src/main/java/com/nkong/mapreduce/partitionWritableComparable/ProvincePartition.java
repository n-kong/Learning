package com.nkong.mapreduce.partitionWritableComparable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/6/12 14:19
 */
public class ProvincePartition extends Partitioner<FlowBean, Text> {


    @Override
    public int getPartition(FlowBean flowBean, Text text, int i) {
        String phone = text.toString().substring(0, 3);
        if ("136".equals(phone)) {
            return 0;
        } else if ("137".equals(phone)) {
            return 1;
        } else if ("138".equals(phone)) {
            return 2;
        } else if ("139".equals(phone)) {
            return 3;
        } else {
            return 4;
        }
    }
}

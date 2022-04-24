package com.nkong.mapreduce.writable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/6/3 22:04
 */
public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

    private Text text = new Text();

    private FlowBean flowBean = new FlowBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String s = value.toString();

        String[] split = s.split("\t");

        String phone = split[0];

        String upFlow = split[1];

        String downFlow = split[2];

        text.set(phone);

        flowBean.setUpload(Long.parseLong(upFlow));
        flowBean.setDownload(Long.parseLong(downFlow));
        flowBean.setSumFlow();

        context.write(text, flowBean);

    }
}

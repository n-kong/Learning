package com.nkong.mapreduce.partitionWritableComparable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/6/3 22:04
 */
public class FlowMapper extends Mapper<LongWritable, Text, FlowBean, Text> {

    private FlowBean outK = new FlowBean();
    private Text outV = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] split = value.toString().split("\t");

        outV.set(split[0]);
        outK.setUpload(Long.parseLong(split[1]));
        outK.setDownload(Long.parseLong(split[2]));
        outK.setSumFlow();

        context.write(outK, outV);

    }
}

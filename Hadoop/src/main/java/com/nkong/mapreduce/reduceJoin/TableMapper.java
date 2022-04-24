package com.nkong.mapreduce.reduceJoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/6/14 12:47
 */
public class TableMapper extends Mapper<LongWritable, Text, Text, TableBean> {

    private String fileName;
    private Text text = new Text();
    private TableBean tableBean = new TableBean();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        FileSplit split = (FileSplit) context.getInputSplit();
        fileName = split.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();

        String[] split = line.split("\t");

        if (fileName.contains("order")) {
            text.set(split[1]);
            tableBean.setId(split[0]);
            tableBean.setPid(split[1]);
            tableBean.setAmount(Integer.parseInt(split[2]));
            tableBean.setpName("");
            tableBean.setFlag("order");
        } else {
            text.set(split[0]);
            tableBean.setId("");
            tableBean.setPid(split[0]);
            tableBean.setAmount(0);
            tableBean.setpName(split[1]);
            tableBean.setFlag("pd");
        }
        context.write(text, tableBean);
    }
}

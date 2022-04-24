package com.nkong.mapreduce.writableComparable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/6/3 22:15
 */
public class FlowDriver  {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        // 获取job
        Configuration entries = new Configuration();
        Job job = Job.getInstance(entries);
        // 设置jar包路径
        job.setJarByClass(FlowDriver.class);
        // 关联mapper和reducer
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);
        // 设置map输出的kv类型
        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);
        // 设置最终的kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        // 如果不设置，默认的是TextInputFormat.class
        job.setInputFormatClass(CombineTextInputFormat.class);

        // 虚拟存储最大值设为4m
        CombineTextInputFormat.setMaxInputSplitSize(job, 4194304);

        // 设置输入路径和输出路劲
        FileInputFormat.setInputPaths(job, new Path("file_out/flow_out"));
        FileOutputFormat.setOutputPath(job, new Path("file_out/flow_out2"));
        // 提交作业
        boolean b = job.waitForCompletion(true);

        System.exit(b ? 0 : 1);

    }

}

package com.nkong.mapreduce.reduceJoin;

import com.nkong.mapreduce.outputformat.LogDriver;
import com.nkong.mapreduce.outputformat.LogMapper;
import com.nkong.mapreduce.outputformat.LogOutputFormat;
import com.nkong.mapreduce.outputformat.LogReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/6/14 13:13
 */
public class TableDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 获取job
        Configuration entries = new Configuration();
        Job job = Job.getInstance(entries);
        // 设置jar包路径
        job.setJarByClass(TableDriver.class);
        // 关联mapper和reducer
        job.setMapperClass(TableMapper.class);
        job.setReducerClass(TableReducer.class);
        // 设置map输出的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(TableBean.class);
        // 设置最终的kv类型
        job.setOutputKeyClass(TableBean.class);
        job.setOutputValueClass(NullWritable.class);

        // 设置输入路径和输出路劲
        FileInputFormat.setInputPaths(job, new Path("file/reduceJoin"));
        FileOutputFormat.setOutputPath(job, new Path("file_out/reduceJoin"));
        // 提交作业
        boolean b = job.waitForCompletion(true);

        System.exit(b ? 0 : 1);
    }

}

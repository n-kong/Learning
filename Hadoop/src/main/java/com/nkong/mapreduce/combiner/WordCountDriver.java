package com.nkong.mapreduce.combiner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/5/26 23:04
 */
public class WordCountDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        // 获取job
        Configuration entries = new Configuration();
        Job job = Job.getInstance(entries);
        // 设置jar包路径
        job.setJarByClass(WordCountDriver.class);
        // 关联mapper和reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        // 设置map输出的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        // 设置最终的kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 设置Combiner
        job.setCombinerClass(WordCountCombiner.class);

        // 设置输入路径和输出路劲
        FileInputFormat.setInputPaths(job, new Path("file/wc"));
        FileOutputFormat.setOutputPath(job, new Path("file_out/hadoop_out"));
        // 提交作业
        boolean b = job.waitForCompletion(true);

        System.exit(b ? 0 : 1);
    }

}

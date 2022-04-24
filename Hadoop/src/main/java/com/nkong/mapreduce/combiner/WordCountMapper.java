package com.nkong.mapreduce.combiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/5/26 23:04
 *
 * KEYIN： map阶段输入的key的类型
 * VALUEIN：map阶段输入的value的类型
 * KEYOUT：map阶段输出的key的类型
 * VALUEOUT：map阶段输出的value的类型
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private Text text = new Text();
    private IntWritable intWritable = new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String str = value.toString();

        String[] strs = str.split(" ");

        for (String s : strs) {
            text.set(s);
            context.write(text, intWritable);
        }

    }
}

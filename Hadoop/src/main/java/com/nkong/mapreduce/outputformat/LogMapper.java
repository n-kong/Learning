package com.nkong.mapreduce.outputformat;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/6/13 17:10
 */
public class LogMapper extends Mapper<LongWritable, Text, Text
        , NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        context.write(value, NullWritable.get());
    }
}

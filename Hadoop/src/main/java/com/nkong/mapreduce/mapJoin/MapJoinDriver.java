package com.nkong.mapreduce.mapJoin;

import com.nkong.mapreduce.reduceJoin.TableBean;
import com.nkong.mapreduce.reduceJoin.TableDriver;
import com.nkong.mapreduce.reduceJoin.TableMapper;
import com.nkong.mapreduce.reduceJoin.TableReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/6/14 20:42
 */
public class MapJoinDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        // 获取job
        Configuration entries = new Configuration();
        Job job = Job.getInstance(entries);
        // 设置jar包路径
        job.setJarByClass(MapJoinDriver.class);
        // 关联mapper和reducer
        job.setMapperClass(MapJoinMapper.class);
        // 设置map输出的kv类型ss
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        // 设置最终的kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        // 加载缓存数据
        job.addCacheFile(new URI("file/reduceJoin/pd.txt"));

        // 设置输入路径和输出路劲
        FileInputFormat.setInputPaths(job, new Path("file/mapJoin"));
        FileOutputFormat.setOutputPath(job, new Path("file_out/mapJoin"));
        // 提交作业
        boolean b = job.waitForCompletion(true);

        System.exit(b ? 0 : 1);
    }

}

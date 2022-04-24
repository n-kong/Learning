package com.nkong.mapreduce.outputformat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/6/13 17:16
 */
public class LogRecordWriter extends RecordWriter<Text, NullWritable> {

    private FSDataOutputStream stream1;
    private FSDataOutputStream stream2;

    public LogRecordWriter(TaskAttemptContext taskAttemptContext) {
        try {
            FileSystem fileSystem = FileSystem.get(taskAttemptContext.getConfiguration());
            stream1 = fileSystem.create(new Path("file_out/flow_out/nkong.log"));
            stream2 = fileSystem.create(new Path("file_out/flow_out/other.log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Text text, NullWritable nullWritable) throws IOException, InterruptedException {
        String data = text.toString();
        if (data.contains("nkong")){
            stream1.writeBytes(data + "\n");
        } else {
            stream2.writeBytes(data + "\n");
        }
    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        IOUtils.closeStreams(stream1, stream2);
    }
}

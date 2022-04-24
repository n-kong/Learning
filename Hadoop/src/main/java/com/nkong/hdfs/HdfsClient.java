package com.nkong.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/4/26 22:41
 */
public class HdfsClient {

    private FileSystem fs;

    @Before
    public void init() {
        URI uri = null;
        try {
            uri = new URI("hdfs://localhost:9000");
            Configuration configuration = new Configuration();
            fs = FileSystem.get(uri, configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() {
        try {
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mkdir() throws IOException {
        String user = "ZYT";
        boolean mkdirs = fs.mkdirs(new Path("/test_3", user));
        if (mkdirs) {
            System.out.println(true);
        }
    }

    @Test
    public void put() throws IOException {
        fs.copyFromLocalFile(new Path("D:\\workspace\\Learning\\file\\file.zip"), new Path("/test_1"));
    }

    @Test
    public void get() throws IOException {
        fs.copyToLocalFile(false, new Path("/test_1/file.zip"), new Path("D:\\workspace\\Learning\\file\\file_1.zip"), true);
    }

    @Test
    public void see() throws IOException {
        RemoteIterator<LocatedFileStatus> iterator = fs.listFiles(new Path("/"), true);
        while (iterator.hasNext()) {
            LocatedFileStatus fileStatus = iterator.next();
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getOwner());
            System.out.println(fileStatus.getPath().toString());
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            System.out.println(Arrays.toString(blockLocations));
        }

    }

}

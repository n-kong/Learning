package com.nkong.yarn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.Arrays;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/6/22 22:48
 */
public class WordCountDriver {

    private static Tool tool;
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        switch (args[0]) {
            case "wordcount":
                tool = new WordCount();
                break;
            default:
                throw new RuntimeException("no such tool " + args[0]);
        }

        int run = ToolRunner.run(conf, tool, Arrays.copyOfRange(args, 1, args.length));

        System.exit(run);

    }

}

package com.nkong.mapreduce.writable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/6/3 22:11
 */
public class FlowReducer extends Reducer<Text, FlowBean, Text, FlowBean> {

    private static Logger log = LoggerFactory.getLogger(FlowReducer.class);
    private FlowBean flowBean = new FlowBean();
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {

        log.info("Key is:{}", key);

        long upTotal = 0;
        long downTotal = 0;
        for (FlowBean value : values) {
            upTotal += value.getUpload();
            downTotal += value.getDownload();
        }

        flowBean.setUpload(upTotal);
        flowBean.setDownload(downTotal);
        flowBean.setSumFlow();

        context.write(key, flowBean);
    }
}

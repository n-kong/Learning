package com.nkong.mapreduce.reduceJoin;

import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/6/14 13:04
 */
public class TableReducer extends Reducer<Text, TableBean, TableBean, NullWritable> {

    @SneakyThrows
    @Override
    protected void reduce(Text key, Iterable<TableBean> values, Context context) throws IOException, InterruptedException {

        List<TableBean> orderBeans = new ArrayList<>();
        TableBean pdBean = new TableBean();

        for (TableBean value : values) {
            if ("order".equals(value.getFlag())) {
                TableBean tmpTableBean = new TableBean();
                BeanUtils.copyProperties(tmpTableBean, value);
                System.out.println("order==>" + value.hashCode());
                System.out.println("tmpTableBean==>" + tmpTableBean.hashCode());
                orderBeans.add(tmpTableBean);
            } else {
                BeanUtils.copyProperties(pdBean, value);
            }
        }

        for (TableBean orderBean : orderBeans) {
            orderBean.setpName(pdBean.getpName());
            context.write(orderBean, NullWritable.get());
        }


    }
}

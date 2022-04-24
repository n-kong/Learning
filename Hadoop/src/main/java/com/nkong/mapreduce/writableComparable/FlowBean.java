package com.nkong.mapreduce.writableComparable;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/6/1 23:41
 */
public class FlowBean implements WritableComparable<FlowBean> {

    private long upload;
    private long download;
    private long sumFlow;

    public FlowBean() {
    }

    public long getUpload() {
        return upload;
    }

    public void setUpload(long upload) {
        this.upload = upload;
    }

    public long getDownload() {
        return download;
    }

    public void setDownload(long download) {
        this.download = download;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    public void setSumFlow() {
        this.sumFlow = this.upload + this.download;
    }

    @Override
    public String toString() {
        return upload + "\t" + download + "\t" + sumFlow;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upload);
        dataOutput.writeLong(download);
        dataOutput.writeLong(sumFlow);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.upload = dataInput.readLong();
        this.download = dataInput.readLong();
        this.sumFlow = dataInput.readLong();
    }

    @Override
    public int compareTo(FlowBean o) {
        // 按总流量倒序排
        if (this.sumFlow > o.getSumFlow()) {
            return -1;
        } else if (this.sumFlow < o.getSumFlow()) {
            return 1;
        } else {
            // 按上行流量正序排
            if (this.upload > o.getUpload()) {
                return 1;
            } else if (this.upload < o.getUpload()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}

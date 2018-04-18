package com.hadoop.weblog.cleaning;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CleanMapper extends Mapper<LongWritable, Text, LongWritable, Text> {
	
	Text outputValue = new Text();
	
	protected void map(LongWritable key, Text value,Context context)throws IOException, InterruptedException {
		LogParser lp=new LogParser();
		final String[] parsed = lp.parse(value.toString());

        // step1.���˵���̬��Դ��������
        if (parsed[2].startsWith("GET /static/")
                || parsed[2].startsWith("GET /uc_server")) {
            return;
        }
        // step2.���˵���ͷ��ָ���ַ���
        if (parsed[2].startsWith("GET /")) {
            parsed[2] = parsed[2].substring("GET /".length());
        } else if (parsed[2].startsWith("POST /")) {
            parsed[2] = parsed[2].substring("POST /".length());
        }
        // step3.���˵���β���ض��ַ���
        if (parsed[2].endsWith(" HTTP/1.1")) {
            parsed[2] = parsed[2].substring(0, parsed[2].length()
                    - " HTTP/1.1".length());
        }
        // step4.ֻд��ǰ������¼������
        outputValue.set(parsed[0] + "\t" + parsed[1] + "\t" + parsed[2]);
        context.write(key, outputValue);
	
	}

}

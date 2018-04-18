package com.hadoop.weblog.pv;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TestMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	 protected void map(LongWritable key, Text value,Context context)throws IOException, InterruptedException {
		 
		 String line=value.toString();
		 String ip=line.split(" ")[0];
		 
		 context.write(new Text(ip), new IntWritable(1));
		
	}

}

package com.hadoop.weblog.pv;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TestReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	
	protected void reduce(Text ip, Iterable<IntWritable> values,Context context)throws IOException, InterruptedException {
		int count=0;
		for(IntWritable value : values){
			count +=value.get();
			
		}
		context.write(ip, new IntWritable(count));
	}

}

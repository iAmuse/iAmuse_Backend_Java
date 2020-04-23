package com.iamuse.kafka;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticSearchConsumer {
	
	public static RestHighLevelClient createClient()
	{
		String hostname ="localhost";
				
		RestClientBuilder builder = RestClient.builder(new HttpHost(hostname,9200,"http"));
	
	    RestHighLevelClient client = new RestHighLevelClient(builder);
		return client;
	}
    
	public static void main( String[] args ) throws IOException
    {
    	Logger logger = LoggerFactory.getLogger(ElasticSearchConsumer.class.getName());
    	RestHighLevelClient client = createClient();
    	String jsonString = "{\"foo\":\"bar\"}";
    	IndexRequest indexRequest = new IndexRequest("twitter","tweets").source(jsonString,XContentType.JSON);
    	IndexResponse indexResponse  = client.index(indexRequest,RequestOptions.DEFAULT);
    	String id = indexResponse.getId();
    	logger.info( "ID="+id);
    	client.close();
    }

}

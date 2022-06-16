package com.br.lis.util;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticSearchModule {
	//http://152.67.196.32:9200/calendar_board/_search
	private String hostname = "152.67.196.32";
	private int port = 9200;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public ElasticSearchModule() {
	}
	
	
	public void insertElasticMap(Map<String, Object> data,String index, String id) throws IOException {
        IndexRequest insertRequest = new IndexRequest(index);
        insertRequest.id(id);
        insertRequest.source(data);
        IndexResponse response = this.getClient().index(insertRequest, RequestOptions.DEFAULT);
        
        String result_data = response.getId();
        logger.info("ElasticSearchModule insertElasticMap index: {} , id : {} result : {}" , index, id, result_data);
        
    }
	public void updateElasticMap(Map<String, Object> data, String index, String id) throws IOException {
		UpdateRequest updateRequest = new UpdateRequest(index, id);
		updateRequest.doc(data);
		
		UpdateResponse response = this.getClient().update(updateRequest, RequestOptions.DEFAULT);
		
		String result_data = response.getId();
		logger.info("ElasticSearchModule updateElasticMap index: {} , id : {} result : {}" , index, id, result_data);
		
	}
	
	public void deleteElastic(String index, String id) throws IOException {
		DeleteRequest deleteRequest = new DeleteRequest(index, id);
		
		DeleteResponse deleteResponse = this.getClient().delete(deleteRequest, RequestOptions.DEFAULT);
		
		String result_data = deleteResponse.getId();
		
		logger.info("ElasticSearchModule deleteElastic index: {} , id : {} result : {}" , index, id, result_data);
	}
	

    public RestHighLevelClient getClient() throws UnknownHostException {
    	HttpHost host = new HttpHost(hostname,port);
		RestClientBuilder restClientBuilder = RestClient.builder(host);
		RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
        return client;
    }

}

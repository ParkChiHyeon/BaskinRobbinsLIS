package com.br.lis.util;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticSearchModule {
	//http://152.67.196.32:9200/calendar_board/_search
	private String hostname = "152.67.196.32";
	private int port = 9200;
	private String index;
	private String id;
	
	public ElasticSearchModule(String index,String id) {
		this.index=index;
		this.id=id;
	}
	
	
	public void insertElasticMap(Map<String, Object> data) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index);
        indexRequest.id(id);
        indexRequest.source(data);
        IndexResponse response = this.getClient().index(indexRequest, RequestOptions.DEFAULT);
        
        String result_data = response.getId();
        System.out.println(result_data);
              
    }
	

    public RestHighLevelClient getClient() throws UnknownHostException {
    	HttpHost host = new HttpHost(hostname,port);
		RestClientBuilder restClientBuilder = RestClient.builder(host);
		RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
        return client;
    }

}

package com.hzcf.platform.mgr.sys.solr;





import java.net.MalformedURLException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 获得solr服务器
 * @author ping.jiang 
 */
public  class SolrServerFactory{

	private static SolrServer server = null;

	private static CloudSolrServer cloudSolrServer = null;
	
	private static final Logger logger = LoggerFactory.getLogger(SolrServerFactory.class);	

	

	public static SolrServer getCloudSolrServer(String iscloud,String zkHost,String solrServerUrl,String defaultCollection) {
    
		if (server == null) {
			if ("on".equals(iscloud)) {
				try {
					cloudSolrServer = getCloudSolrServer(zkHost,defaultCollection);
					server = (SolrServer) cloudSolrServer;

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {

				server = new HttpSolrServer(solrServerUrl);

				((HttpSolrServer) server).setConnectionTimeout(1000);
				((HttpSolrServer) server).setDefaultMaxConnectionsPerHost(1000);
				((HttpSolrServer) server).setMaxTotalConnections(30);
				((HttpSolrServer) server).setFollowRedirects(false);
				((HttpSolrServer) server).setAllowCompression(true);
				((HttpSolrServer) server).setMaxRetries(3);
			}
		}

		return server;

	}

	private static synchronized CloudSolrServer getCloudSolrServer(String zkHost,String defaultCollection) {
		if (cloudSolrServer == null) {
			try {
				cloudSolrServer = new CloudSolrServer(zkHost);
				cloudSolrServer.setDefaultCollection(defaultCollection); 
			} catch (MalformedURLException e) {
				System.out.println("The URL of zkHost is not correct!! Its form must as below:\n zkHost:port");
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return cloudSolrServer;
	}
	/**
     * 增量/全量建立索引 。
     * 
     * @param delta ture，增量建立索引；false，重建所有索引
     */
	public static void buildIndex(SolrServer cloudSolrServer, boolean delta) {
		SolrQuery query = new SolrQuery();
		// 指定RequestHandler，默认使用/select
		query.setRequestHandler("/dataimport");

		String command = delta ? "delta-import" : "full-import";
		String clean = delta ? "false" : "true";
		String optimize = delta ? "false" : "true";
		query.setParam("command", command).setParam("clean", clean).setParam("commit", "true")
				.setParam("entity", "esin").setParam("optimize", optimize);
		query.set("shards.tolerant", true); // 参数的功用,只查询存活的分片

		try {
			QueryResponse response=cloudSolrServer.query(query);
			if (response.getResults() != null) {
				logger.info("############新建立索引 数据返回结果状态  ：" + response.getResponseHeader());
				logger.info("############新建立索引  数据返回结果   ：" + response.getResponse());
				logger.info("############新建立索引 数据  ：" + response.getResults().getNumFound() + "行");
			} else {
				logger.info("############新建立索引 数据返回结果状态   ：" + response.getResponse());
				logger.info("############新建立索引 数据返回结果 ：" + response.getResponseHeader());

			}
			} catch (SolrServerException e) {
			logger.error("建立索引时遇到错误，delta:" + delta, e);
		}
	}

}

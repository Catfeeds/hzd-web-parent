package com.hzcf.platform.mgr.sys.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.util.EntityUtils;
import org.apache.solr.client.solrj.SolrServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.common.util.response.ResponseBuilder;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.mgr.sys.util.SolrConfigConstants;
import com.google.common.base.Charsets;
import com.google.common.base.Stopwatch;


@RestController
public class SolrIndexController {
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private int maxConnections = 20;
	private int retryTimes = 1;
	private int timeout = 60000;

	private CloseableHttpClient httpclient;
	
	@RequestMapping(value = "/solr/index/{type}", method = RequestMethod.POST)
	public ResponseEntity<Object> solrindex(HttpServletRequest request, @PathVariable String type) {
		
		Result<Boolean> result=new Result<Boolean>(StatusCodes.OK, true);
		httpclient = HttpClients.custom().setRetryHandler(new StandardHttpRequestRetryHandler(retryTimes, false))
				.setMaxConnTotal(maxConnections).setMaxConnPerRoute(maxConnections).build();
		
		Map<String, String> param = new HashMap<>();
		param.put("type", type);
		String requestUrl=SolrConfigConstants.requestUrl;
		try {
			String response = sendData(requestUrl, param);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			result.setStatus(400);
			e.printStackTrace();
		}

		/*SolrServer solrServer=SolrServerFactory.getCloudSolrServer(SolrConfigConstants.IS_CLOUD, SolrConfigConstants.ZKHOST, null, SolrConfigConstants.DEFAULT_COLLECTION);
		if(type==null||type.equals(("0"))||type.equals((""))){
		    SolrServerFactory.buildIndex(solrServer, false);
		}else{
			SolrServerFactory.buildIndex(solrServer, true);
		}*/
		return ResponseBuilder.instance().body(result).build();

	}
	
	
	private String sendData(String url, Map<String, String> param) throws URISyntaxException {
		URIBuilder URIBuilder = new URIBuilder(url);
		if (param != null && !param.isEmpty()) {
			for (String key : param.keySet()) {
				URIBuilder.addParameter(key, param.get(key));
			}
		}
		HttpPost getRequest = new HttpPost(URIBuilder.build());
		getRequest.setConfig(RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout)
				.setConnectionRequestTimeout(timeout).build());
		Stopwatch stopwatch = Stopwatch.createStarted();
		String body = null;
		getRequest.setConfig(RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout)
				.setConnectionRequestTimeout(timeout).build());

		try (CloseableHttpResponse response = httpclient.execute(getRequest)) {
			if (response != null) {
				int status = response.getStatusLine().getStatusCode();
				if (status != 200) {
					logger.error(" has wrong response code, code={}, param={}", status, param);
					// return StringUtils.EMPTY;
				}
				body = EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
				return body;
			}
		} catch (IOException e) {
			logger.error("product pic syn has exception, exception={}, param={}", e, param);
		}

		stopwatch.stop();
		logger.info("Use time: {}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
		return "";
	}
	
}

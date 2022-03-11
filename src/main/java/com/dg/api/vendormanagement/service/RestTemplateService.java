package com.dg.api.vendormanagement.service;

import java.net.http.HttpTimeoutException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dg.api.vendormanagement.exception.ApplicationException;
import com.dg.api.vendormanagement.util.Constants;


@Component
@Scope("prototype")
public class RestTemplateService {

	Logger logger = LoggerFactory.getLogger(RestTemplateService.class);

	@Autowired
	RestTemplate restTemplate;

	@Retryable(maxAttempts = 3, include = { HttpTimeoutException.class,
			RestClientException.class }, backoff = @Backoff(delay = 100))
	public <R, T> T invokePostApi(R request, String url, HttpHeaders headers, Class<T> responseClass)
			throws ApplicationException {
		T response = null;
		if (request != null && headers != null) {
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<?> entity = new HttpEntity<>(request, headers);
			try {
				ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, responseClass);
				if (responseEntity.getStatusCode() == HttpStatus.OK
						|| responseEntity.getStatusCode() == HttpStatus.CREATED) {
					response = responseEntity.getBody();
					return response;
				} else {
					logger.error("InvokePostApi call failed for url: {} with request: {}", url, request.toString());
					logger.error("InvokePostApi response: {}, statusCode:{}",
							responseEntity.getBody() != null ? responseEntity.getBody().toString() : null,
							responseEntity.getStatusCode());
					return responseEntity.getBody();
				}	
			} catch (Exception e) {
				logger.error("InvokePostApi: Exception :{}", e.getMessage());
				throw new ApplicationException(Constants.UNKNOWN_EXCEPTION,
						"Failed to Trigger Internal Service");
			}
		}

		return response;
	}

	@Retryable(maxAttempts = 3, include = { HttpTimeoutException.class,
			RestClientException.class }, backoff = @Backoff(delay = 100))
	public <R, T> T invokeGetApi(String url, HttpHeaders headers, Class<T> responseClass) throws ApplicationException {
		T response = null;

		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		// headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		try {
			ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, responseClass);
			if (responseEntity.getStatusCode() == HttpStatus.OK
					|| responseEntity.getStatusCode() == HttpStatus.CREATED) {
				response = responseEntity.getBody();
				return response;
			} else {
				logger.error("InvokeGetApi call failed for url: {} ", url);
				logger.error("InvokeGetApi response: {}, statusCode:{}",
						responseEntity.getBody() != null ? responseEntity.getBody().toString() : null,
						responseEntity.getStatusCode());
				return responseEntity.getBody();
			}
		} catch (Exception e) {
			logger.error("InvokePostApi: Exception :{}", e.getMessage());
			throw new ApplicationException(Constants.EXTERNAL_SERVICE_CALL_FAILED,
					"Failed to Trigger Internal Service");

		}

	}

	@Async
	public <T, R> Future<T> invokePostApiAsync(R request, String url, HttpHeaders headers, Class<T> responseClass)
			throws ApplicationException {
		Future<T> response = null;
		if (request != null) {
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<?> entity = new HttpEntity<>(request, headers);
			try {
				ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, responseClass);
				if (responseEntity.getStatusCode() == HttpStatus.OK
						|| responseEntity.getStatusCode() == HttpStatus.CREATED) {
					return CompletableFuture.completedFuture(responseEntity.getBody());

				} else {
					logger.error("InvokePostApiAsync call failed for url: {} with request: {}", url,
							request.toString());
					logger.error("InvokePostApiAsync response: {}, statusCode:{}",
							responseEntity.getBody() != null ? responseEntity.getBody().toString() : null,
							responseEntity.getStatusCode());
					return CompletableFuture.completedFuture(responseEntity.getBody());
				}

			} catch (Exception e) {
				logger.error("InvokePostApi: Exception :{}", e.getMessage());
				throw new ApplicationException(Constants.EXTERNAL_SERVICE_CALL_FAILED,
						"Failed to Trigger Internal Service");
			}

		}
		return response;
	}

	@Async
	public <R, T> Future<T> invokeGetApiAsyc(R request, String url, HttpHeaders headers, Class<T> responseClass)
			throws ApplicationException {
		Future<T> response = null;
		if (request != null) {
			// headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			try {
				ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, responseClass);
				if (responseEntity.getStatusCode() == HttpStatus.OK
						|| responseEntity.getStatusCode() == HttpStatus.CREATED) {
					response = CompletableFuture.completedFuture(responseEntity.getBody());
					return response;
				} else {
					logger.error("InvokeGetApiAsyc call failed for url: {} with request: {}", url, request.toString());
					logger.error("InvokeGetApiAsync response: {}, statusCode:{}",
							responseEntity.getBody() != null ? responseEntity.getBody().toString() : null,
							responseEntity.getStatusCode());
					return CompletableFuture.completedFuture(responseEntity.getBody());
				}
			} catch (Exception e) {
				logger.error("InvokePostApi: Exception :{}", e.getMessage());
				throw new ApplicationException(Constants.EXTERNAL_SERVICE_CALL_FAILED,
						"Failed to Trigger Internal Service");
			}

		}
		return response;
	}

}

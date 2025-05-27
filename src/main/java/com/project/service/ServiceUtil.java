package com.project.service;

import java.net.URI;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

public class ServiceUtil {

	public static <T> PageImpl<T> getPage(
	        URI uri,
	        RestClient restClient,
	        ParameterizedTypeReference<PageImpl<T>> responseType
	) {
		String response = restClient.get()
			    .uri(uri.toString())
			    .retrieve()
			    .body(String.class);
			System.out.println("Response: " + response);
	    return restClient.get()
	            .uri(uri)
	            .retrieve()
	            .body(responseType);
	}
    public static URI getURI(String resourcePath, Pageable pageable) {
        return getUriComponent(resourcePath)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("sort", ServiceUtil.getSortParams(pageable.getSort()))
                .build()
                .toUri();
    }

    public static UriComponentsBuilder getUriComponent(String resourcePath, Pageable pageable) {
        return getUriComponent(resourcePath)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("sort", ServiceUtil.getSortParams(pageable.getSort()));
    }

    public static UriComponentsBuilder getUriComponent(String resourcePath) {
        return UriComponentsBuilder.fromUriString(resourcePath);
    }

    public static String getSortParams(Sort sort) {
        StringBuilder builder = new StringBuilder();
        if (sort != null) {
            String sep = "";
            for (Sort.Order order : sort) {
                builder.append(sep)
                        .append(order.getProperty())
                        .append(",")
                        .append(order.getDirection());
                sep = "&sort=";
            }
        }
        return builder.toString();
    }
}
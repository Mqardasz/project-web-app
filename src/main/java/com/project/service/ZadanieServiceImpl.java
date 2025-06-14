package com.project.service;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.project.exception.HttpException;
import com.project.model.Zadanie;

@Service
public class ZadanieServiceImpl implements ZadanieService {

    private static final Logger logger = LoggerFactory.getLogger(ZadanieServiceImpl.class);
    private final RestClient restClient;

    public ZadanieServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    private String getResourcePath() {
        return "/api/zadania";
    }

    private String getResourcePath(Integer id) {
        return String.format("%s/%d", getResourcePath(), id);
    }

    @Override
    public Optional<Zadanie> getZadanieById(Integer zadanieId) {
        String resourcePath = getResourcePath(zadanieId);
        logger.info("REQUEST -> GET {}", resourcePath);
        try {
            Zadanie zadanie = restClient
                .get()
                .uri(resourcePath)
                .retrieve()
                .body(Zadanie.class);
            return Optional.ofNullable(zadanie);
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound ex) {
            // 404 - nie znaleziono zadania
            return Optional.empty();
        } catch (org.springframework.web.client.HttpClientErrorException ex) {
            // inne błędy HTTP
            throw ex;
        }
    }

    @Override
    public Zadanie setZadanie(Zadanie zadanie) {
        if (zadanie.getZadanieId() != null) {
            String resourcePath = getResourcePath(zadanie.getZadanieId());
            logger.info("REQUEST -> PUT {}", resourcePath);
            restClient
                    .put()
                    .uri(resourcePath)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(zadanie)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (req, res) -> {
                        throw new HttpException(res.getStatusCode(), res.getHeaders());
                    })
                    .toBodilessEntity();
            return zadanie;
        } else {
            String resourcePath = getResourcePath();
            logger.info("REQUEST -> POST {}", resourcePath);
            ResponseEntity<Void> response = restClient
                    .post()
                    .uri(resourcePath)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(zadanie)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (req, res) -> {
                        throw new HttpException(res.getStatusCode(), res.getHeaders());
                    })
                    .toBodilessEntity();
            URI location = response.getHeaders().getLocation();
            logger.info("REQUEST (location) -> GET {}", location);
            return restClient
                    .get()
                    .uri(location)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (req, res) -> {
                        throw new HttpException(res.getStatusCode(), res.getHeaders());
                    })
                    .body(Zadanie.class);
        }
    }

    @Override
    public void deleteZadanieById(Integer zadanieId) {
        String resourcePath = getResourcePath(zadanieId);
        logger.info("REQUEST -> DELETE {}", resourcePath);
        restClient
                .delete()
                .uri(resourcePath)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    throw new HttpException(res.getStatusCode(), res.getHeaders());
                })
                .toBodilessEntity();
    }

    @Override
    public Page<Zadanie> getAllZadania(Pageable pageable) {
        URI uri = ServiceUtil.getURI(getResourcePath(), pageable);
        logger.info("REQUEST -> GET {}", uri);
        return getPage(uri);
    }

    public Page<Zadanie> findZadaniaByNazwa(String nazwa, Pageable pageable) {
        URI uri = ServiceUtil
                .getUriComponent(getResourcePath(), pageable)
                .queryParam("nazwa", nazwa)
                .build().toUri();
        logger.info("REQUEST -> GET {}", uri);
        return getPage(uri);
    }

    private Page<Zadanie> getPage(URI uri) {
        return restClient.get()
                .uri(uri.toString())
                .retrieve()
                .body(new ParameterizedTypeReference<RestResponsePage<Zadanie>>() {});
    }

    @Override
    public Zadanie updateZadanie(Integer id, Zadanie updatedZadanie) {
        String resourcePath = getResourcePath(id);
        logger.info("REQUEST -> PUT {}", resourcePath);
        restClient
            .put()
            .uri(resourcePath)
            .accept(MediaType.APPLICATION_JSON)
            .body(updatedZadanie)
            .retrieve()
            .onStatus(HttpStatusCode::isError, (req, res) -> {
                throw new HttpException(res.getStatusCode(), res.getHeaders());
            })
            .toBodilessEntity();
        return updatedZadanie;
    }

	@Override
	public Page<Zadanie> getZadaniaByProjektId(Integer projektId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
}
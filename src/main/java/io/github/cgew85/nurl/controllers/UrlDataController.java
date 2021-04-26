package io.github.cgew85.nurl.controllers;

import io.github.cgew85.nurl.model.dtos.UrlDataResponse;
import io.github.cgew85.nurl.services.UrlDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("urls")
public class UrlDataController implements UrlDataControllerInterface {

    private final UrlDataService urlDataService;

    @Autowired
    public UrlDataController(UrlDataService urlDataService) {
        this.urlDataService = urlDataService;
    }

    @PostMapping
    public ResponseEntity<UrlDataResponse> saveUrl(@RequestBody String url) {
        var urlDataResponse = new UrlDataResponse();

        urlDataResponse.setUrl(url);
        urlDataResponse.setKey(urlDataService.saveUrl(url));
        urlDataResponse.setCreatedDate(LocalDateTime.now());

        return ResponseEntity.created(URI.create(urlDataResponse.getKey())).build();
    }

    @GetMapping(value = "/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUrl(@PathVariable String key) {
        return urlDataService.getUrlByKey(key);
    }
}

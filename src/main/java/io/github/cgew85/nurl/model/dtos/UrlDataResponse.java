package io.github.cgew85.nurl.model.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlDataResponse {

    private String key;
    private LocalDateTime createdDate;
    private String url;
}

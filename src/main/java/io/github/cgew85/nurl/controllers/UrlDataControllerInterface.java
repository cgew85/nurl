package io.github.cgew85.nurl.controllers;

import io.github.cgew85.nurl.model.dtos.UrlDataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public interface UrlDataControllerInterface {

    @Operation(summary = "Post url to be converted",
            responses = {
                    @ApiResponse(responseCode = "201", description = "UrlDataResponse object",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = URI.class)))
            })
    ResponseEntity<UrlDataResponse> saveUrl(String url);

    @Operation(summary = "Get a converted url by its key",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Gets the converted url via key")}
    )
    String getUrl(String key);
}

package com.synopsis.mc_api.mc_api.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.synopsis.mc_api.mc_api.dtos.ExternalProductDto;
import com.synopsis.mc_api.mc_api.services.IWebClient;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class WebClientExternalProduct implements IWebClient {
    @Autowired
    private Environment env;
    private final WebClient.Builder webClientBuilder;

    public WebClientExternalProduct(WebClient.Builder webClientBuilder) {

        this.webClientBuilder = webClientBuilder;
    }

    HttpClient client = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .option(EpollChannelOption.TCP_KEEPIDLE, 300)
            .option(EpollChannelOption.TCP_KEEPINTVL,60)
            .responseTimeout(Duration.ofSeconds(1))
            .doOnConnected(connection -> {
                connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
            });

    @Override
    public JsonNode createExternalProduct(ExternalProductDto externalProduct) {

        var webClient = webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(client))
                .baseUrl(env.getProperty("external.api.base.url"))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient
                .post()
                .bodyValue(externalProduct)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .doOnError(e -> {
                    System.err.println("Error al crear el producto: " + e.getMessage());
                })
                .block();
    }
}

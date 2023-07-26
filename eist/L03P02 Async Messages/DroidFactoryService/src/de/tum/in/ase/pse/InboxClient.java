package de.tum.in.ase.pse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

public final class InboxClient {

    private RestTemplate rest;
    private static final String BASE_URL = "http://localhost:8080/messages/";
    private static final String R_2 = "r2";
    private static final String THREE_PO = "3po";

    private final List<String> messages = new ArrayList<>();


    public InboxClient() {
        this.rest = new RestTemplate();
    }

    public void droidReadyR2(String droid) {
        messages.add(rest.postForEntity(BASE_URL + R_2, createHttpEntity(droid), String.class).getBody());
    }
    public void droidReady3PO(String droid) {
        messages.add(rest.postForEntity(BASE_URL + THREE_PO, createHttpEntity(droid), String.class).getBody());
    }

    public void printMessages() {
        this.messages.forEach(System.out::println);
    }
    public List<String> getMessages() {
        return messages;
    }
    private HttpEntity<String> createHttpEntity(String droid) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(droid, headers);
    }
}

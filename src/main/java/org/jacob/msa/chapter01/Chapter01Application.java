package org.jacob.msa.chapter01;

import com.sun.xml.internal.ws.encoding.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RequestMapping(value = "hello")
@RestController
@Slf4j
public class Chapter01Application {


    public static void main(String[] args) {
        SpringApplication.run(Chapter01Application.class, args);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public void helloPost() {

        log.info("XXX");
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    @ResponseBody
    public Person hhhh() {

        Person p = new Person("nn", 11);
        return p;
    }

    @RequestMapping(value = "/{firstname}/{lastname}", method = RequestMethod.GET)
    public ResponseEntity<String> hello(@RequestHeader HttpHeaders headers,
                                        @PathVariable("firstname") String firstname,
                                        @PathVariable("lastname") String lastname) {
        log.info("accept-language ={}", headers.getContentType());


        MediaType ss = headers.getContentType();

        assert ss != null;
        if (ss.equals(MediaType.APPLICATION_JSON)) {
            log.info("SSS");
        }

        RestTemplate restTemplate = new RestTemplate();
        String createPersonUrl = "http://localhost:8080/hello/test";

        Object request = null;
        restTemplate.postForObject(createPersonUrl, request, String.class);
        return new ResponseEntity<String>(String.format("message %s, %s", firstname, lastname), HttpStatus.OK);

    }
}

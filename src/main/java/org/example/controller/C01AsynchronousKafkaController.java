package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.Entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RequestMapping("/api/kafka")
@RestController
@Slf4j
public class C01AsynchronousKafkaController {


    private KafkaTemplate<String, Product> kafkaTemplate;

    public C01AsynchronousKafkaController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }



    @PostMapping
    public ResponseEntity<String> sentMessage(@RequestBody Product product) throws ExecutionException, InterruptedException {
        System.out.println("senddddddd");
        CompletableFuture<SendResult<String,Product>> product1 = this.kafkaTemplate
                .send("product4",String.valueOf(product.getId()), new Product(product.getId(), product.getName(), product.getDescription(), product.getPrice()));
       /* Object o = product1.get();
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhh"+o);
        return ResponseEntity.status(HttpStatus.CREATED).body("Hello World!");*/

        product1.whenComplete((result, exception) -> {
            if (exception!=null)
            {
                log.error("@@@@@@@@failed to send message :{}",exception.getMessage());
            }
            else {
                log.info("@@@@@@@@Message sent successfully  :{}",result.getRecordMetadata());
            }
        });
    //      product1.join();
        return ResponseEntity.status(HttpStatus.CREATED).body("Hello World!");
    }

}

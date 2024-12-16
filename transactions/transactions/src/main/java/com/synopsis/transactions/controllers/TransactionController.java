package com.synopsis.transactions.controllers;

import com.synopsis.transactions.entities.Transactions;
import com.synopsis.transactions.repositories.TransactionRepository;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/{iban}")
    public List<Transactions> get(@PathVariable(name = "iban") String iban) {
        return transactionRepository.getTransactionsByIban(iban);
    }

    @GetMapping
    public List<Transactions> getAll() {
        return transactionRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addTransaction(@RequestBody Transactions transaction) {
        transactionRepository.insertTransaction(
                transaction.getReference(),
                transaction.getAccountIban(),
                transaction.getAmmount(),
                transaction.getDescription(),
                transaction.getStatus(),
                transaction.getChannel()
        );

        return ResponseEntity.ok().build();
    }
}

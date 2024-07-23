package org.example.services;

import org.example.models.Wallet;
import org.example.repository.WalletRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    //if it was controller then @PostMapping would have been used to createWallet but
    //in our case we have to get data from kafka topic so we are using @KafkaListener
    @KafkaListener(topics = {"user_created"},groupId = "kafka01")       //add groupId="jbdl58"
    public void createWallet(String input) throws ParseException {
        JSONObject obj= (JSONObject) new JSONParser().parse(input);

        //String walletId=(String) obj.get("phone");
        String walletId=UUID.randomUUID().toString();

        Wallet wallet= Wallet.builder().walletId(walletId).currency("INR").balance(100L).build();
        walletRepository.save(wallet);
    }
}

package com.client.controller;

import com.client.entitiy.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")  //访问方法链接后面要加api
public class accountController {
    private List<Account> accounts =new ArrayList<>();

    @PostMapping("/account")   // api/account
    public ResponseEntity<List<Account>> addAccount(@RequestBody Account acc){
        accounts.add(acc);
        return ResponseEntity.ok(accounts);
    }
    @DeleteMapping("/account/{id}")
    public ResponseEntity deleteAccountById(@PathVariable("id") int id){
        accounts.remove(id);
        return ResponseEntity.ok(accounts);
    }
    @GetMapping("/account")
    public ResponseEntity getAccountByName(@RequestParam("id") String id){
        List<Account> results= accounts.stream().filter(acc -> acc.getId().equals(id)).collect(Collectors.toList());
        return ResponseEntity.ok(results);
    }

}
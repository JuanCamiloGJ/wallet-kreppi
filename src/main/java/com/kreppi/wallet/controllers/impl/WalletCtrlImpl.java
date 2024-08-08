package com.kreppi.wallet.controllers.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.kreppi.wallet.controllers.WalletController;
import com.kreppi.wallet.models.dto.User;
import com.kreppi.wallet.services.WalletService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class WalletCtrlImpl implements WalletController {

    private final WalletService walletService;

    @Override
    public Mono<ResponseEntity<Object>> getBalance(User userData) {
        log.info("Obteniendo balance del usuario: {}", userData);

        return walletService.getBalance(userData)
                .flatMap(wallet -> Mono.just(ResponseEntity.ok((Object) wallet)))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body("datos no encontrados")));
    }
}
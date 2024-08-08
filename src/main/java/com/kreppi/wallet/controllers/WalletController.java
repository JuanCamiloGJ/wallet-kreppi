package com.kreppi.wallet.controllers;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kreppi.wallet.models.dto.User;

@RequestMapping("/wallet")
@Validated
public interface WalletController {
    /**
     * Obtiene el balance del usuario
     *
     * @param userData información del usuario como documento y número de teléfono
     * @return el saldo del usuario
     */
    @GetMapping(value = "/balance", produces = "application/stream+json")
    @ResponseBody
    Mono<ResponseEntity<Object>> getBalance(@Valid @ModelAttribute User userData);

}

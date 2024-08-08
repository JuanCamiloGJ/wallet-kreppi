package com.kreppi.wallet.services;

import reactor.core.publisher.Mono;

import com.kreppi.wallet.models.dto.User;
import com.kreppi.wallet.models.dto.Wallet;

public interface WalletService {

    /**
     * Obtiene el balance del usuario
     * @param userData información del usuario como documento y número de teléfono
     * @return el saldo del usuario
     */
    Mono<Wallet> getBalance(User userData);
}

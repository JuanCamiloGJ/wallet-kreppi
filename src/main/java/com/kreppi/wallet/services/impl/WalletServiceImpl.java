package com.kreppi.wallet.services.impl;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

import com.kreppi.wallet.models.dto.User;
import com.kreppi.wallet.models.dto.Wallet;
import com.kreppi.wallet.repository.WalletRepository;
import com.kreppi.wallet.services.WalletService;
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    public Mono<Wallet> getBalance(@NotNull User userData) {

        return walletRepository.getBalance(userData.getDocument(), userData.getNPhone())
                .map(Wallet::new)
                .switchIfEmpty(Mono.empty());
    }
}

package com.kreppi.wallet.repository;

import java.math.BigDecimal;

import reactor.core.publisher.Mono;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.kreppi.wallet.models.dto.User;
@Repository
public interface WalletRepository extends R2dbcRepository<User, Long> {
    @Query("SELECT balance FROM WALLET WHERE document = :document AND phone = :phoneNumber")
    Mono<BigDecimal> getBalance(String document, String phoneNumber);

}

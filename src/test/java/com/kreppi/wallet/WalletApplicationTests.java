package com.kreppi.wallet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Objects;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kreppi.wallet.controllers.impl.WalletCtrlImpl;
import com.kreppi.wallet.models.dto.User;
import com.kreppi.wallet.models.dto.Wallet;
import com.kreppi.wallet.repository.WalletRepository;
import com.kreppi.wallet.services.WalletService;
import com.kreppi.wallet.services.impl.WalletServiceImpl;

@SpringBootTest
class WalletApplicationTests {

	@Test
	public void returns_wallet_object_when_balance_is_found() {
		WalletRepository walletRepository = mock(WalletRepository.class);
		WalletServiceImpl walletService = new WalletServiceImpl(walletRepository);

		User user = new User();
		user.setDocument("123456789");
		user.setNPhone("987654321");

		BigDecimal balance = new BigDecimal("100.00");
		when(walletRepository.getBalance(user.getDocument(), user.getNPhone())).thenReturn(Mono.just(balance));

		Mono<Wallet> result = walletService.getBalance(user);

        assertEquals(0, Objects.requireNonNull(result.block()).balance().compareTo(balance));
	}

	@Test
	public void test_returns_empty_mono_when_no_balance_found() {
		WalletRepository walletRepository = mock(WalletRepository.class);
		WalletServiceImpl walletService = new WalletServiceImpl(walletRepository);
		User userData = new User();
		userData.setDocument("123456789");
		userData.setNPhone("987654321");
		when(walletRepository.getBalance(anyString(), anyString())).thenReturn(Mono.empty());

		Mono<Wallet> result = walletService.getBalance(userData);

		StepVerifier.create(result)
				.expectNextCount(0)
				.verifyComplete();
	}

	@Test
	public void returns_http_404_status_when_balance_is_not_found() {
		WalletService walletService = mock(WalletService.class);
		WalletCtrlImpl walletCtrl = new WalletCtrlImpl(walletService);

		User user = new User();
		user.setDocument("123456789");
		user.setNPhone("987654321");

		when(walletService.getBalance(user)).thenReturn(Mono.empty());

		ResponseEntity<Object> response = walletCtrl.getBalance(user).block();

		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("datos no encontrados", response.getBody());
	}
	@Test
	public void returns_balance_when_user_data_is_valid_and_balance_exists() {
		WalletService walletService = mock(WalletService.class);
		WalletCtrlImpl walletCtrl = new WalletCtrlImpl(walletService);

		User user = new User();
		user.setDocument("123456789");
		user.setNPhone("987654321");

		Wallet wallet = new Wallet(BigDecimal.valueOf(100.0));

		when(walletService.getBalance(user)).thenReturn(Mono.just(wallet));

		ResponseEntity<Object> response = walletCtrl.getBalance(user).block();

		assertNotNull(response);
		assertEquals(ResponseEntity.ok(wallet), response);
	}
}

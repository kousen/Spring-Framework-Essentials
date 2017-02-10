package com.oreilly.repositories;

import com.oreilly.config.AppConfig;
import com.oreilly.entities.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.Assert.assertThat;

@SuppressWarnings("Duplicates")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
// @Transactional
@ActiveProfiles("dev")
public class InMemoryAccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Test
    public void testGetAccounts() throws Exception {
        List<Account> accounts = repository.getAccounts();
        assertThat(accounts.size(), is(3));
    }

    @Test
    public void testGetAccount() throws Exception {
        Account account = repository.getAccount(1L);
        assertThat(account.getId(), is(1L));
        assertThat(new BigDecimal("100.0"),
                is(closeTo(account.getBalance(), new BigDecimal("0.01"))));
    }

    @Test
    public void testGetNumberOfAccounts() throws Exception {
        assertThat(repository.getNumberOfAccounts(), is(3));
    }

    @Test
    public void testCreateAccount() throws Exception {
        // Make a new account and add it to DB
        Long id = repository.createAccount(new BigDecimal("250.00"));
        assertThat(id, is(notNullValue()));

        // Retrieve the new account and check its properties
        Account account = repository.getAccount(id);
        assertThat(account.getId(), is(id));
        assertThat(account.getBalance(), is(closeTo(new BigDecimal("250.0"),
                new BigDecimal("0.01"))));

        // Delete the account
        repository.deleteAccount(id);
    }

    @Test
    public void testUpdateAccount() throws Exception {
        Account account = repository.getAccount(1L);
        BigDecimal current = account.getBalance();
        BigDecimal amount = new BigDecimal("50.0");
        account.setBalance(current.add(amount));
        repository.updateAccount(account);

        Account again = repository.getAccount(1L);
        assertThat(again.getBalance(), is(closeTo(current.add(amount),
                new BigDecimal("0.01"))));
    }

    @Test
    public void testDeleteAccount() throws Exception {
        // Get the number of accounts before deleting
        int beforeCount = repository.getNumberOfAccounts();
        Account account = repository.getAccount(3L);

        // Delete an account and check that num is one less
        repository.deleteAccount(3L);
        int afterCount = repository.getNumberOfAccounts();
        assertThat(afterCount, is(beforeCount - 1));

        // Re-add the deleted account
        repository.createAccount(account.getBalance());
    }
}
package com.oreilly.repositories;

import com.oreilly.entities.Account;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile("dev")
public class InMemoryAccountRepository implements AccountRepository {
    private static Map<Long, Account> accountMap =
            new ConcurrentHashMap<Long, Account>();

    private static Long nextId = 4L;

    static {
        accountMap.put(1L, new Account(1L, new BigDecimal("100.0")));
        accountMap.put(2L, new Account(2L, new BigDecimal("120.0")));
        accountMap.put(3L, new Account(3L, new BigDecimal("150.0")));
    }

    @Override
    public List<Account> getAccounts() {
        Collection<Account> accountCollection = accountMap.values();
        List<Account> accountList = new ArrayList<Account>();
        for (Account account : accountCollection) {
            accountList.add(account);
        }
        return accountList;
    }

    @Override
    public Account getAccount(Long id) {
        return accountMap.get(id);
    }

    @Override
    public int getNumberOfAccounts() {
        return accountMap.size();
    }

    @Override
    public Long createAccount(BigDecimal initialBalance) {
        Long id = nextId++;
        accountMap.put(id, new Account(id, initialBalance));
        return id;
    }

    @Override
    public int deleteAccount(Long id) {
        accountMap.remove(id);
        return 1;
    }

    @Override
    public void updateAccount(Account account) {
        accountMap.put(account.getId(), account);
    }
}

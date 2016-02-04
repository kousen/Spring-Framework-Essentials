package com.oreilly.repositories;

import com.oreilly.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Profile({"prod", "test"})
public class JdbcAccountRepository implements AccountRepository {
    private JdbcTemplate template;
    private static long nextId = 4;

    @Autowired
    public JdbcAccountRepository(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Account> getAccounts() {
        String sqlTxt = "select * from account";
        return template.query(sqlTxt, new AccountMapper());
    }

    @Override
    public Account getAccount(Long id) {
        String sqlTxt = "select * from account where id=?";
        return template.queryForObject(sqlTxt, new AccountMapper(), id);
    }


    @Override
    public int getNumberOfAccounts() {
        String sqlTxt = "select count(*) from account";
        return template.queryForObject(sqlTxt, Integer.class);
    }

    @Override
    public Long createAccount(BigDecimal initialBalance) {
        String sqlTxt = "insert into account(id,balance) values(?,?)";
        long id = nextId++;
        int uc = template.update(sqlTxt, id, initialBalance);
        return id;
    }

    @Override
    public void updateAccount(Account account) {
        String sqlTxt = "update account set balance = ? where id = ?";
        template.update(sqlTxt, account.getBalance(), account.getId());
    }

    @Override
    public int deleteAccount(Long id) {
        String sqlTxt = "delete from account where id=?";
        return template.update(sqlTxt, id);
    }

    private class AccountMapper implements RowMapper<Account> {
        @Override
        public Account mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Account(resultSet.getLong("id"),
                    resultSet.getBigDecimal("balance"));
        }
    }
}

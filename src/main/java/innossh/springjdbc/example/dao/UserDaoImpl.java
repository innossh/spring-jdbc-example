package innossh.springjdbc.example.dao;

import innossh.springjdbc.example.entity.User;
import innossh.springjdbc.example.errors.TransactionRollbackTestRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component(value = "userDaoImpl")
public class UserDaoImpl implements UserDao {

    @Autowired
    @Qualifier(value = "jdbcTemplate")
    JdbcTemplate jdbcTemplate;

//    @Override
//    public User findOne(Long id) {
//        return jdbcTemplate.query("SELECT * FROM users WHERE user_id = ?", rs -> {
//            if (rs.next()) {
//                return new User(rs.getLong("id"), rs.getString("name"));
//            }
//            return null;
//        }, id);
//    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", rs -> {
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(new User(rs.getLong("id"), rs.getString("name")));
            }
            return users;
        });
    }

//    @Override
    public int save(User user) {
        return jdbcTemplate.update("REPLACE INTO users VALUES (?, ?)", user.getId(), user.getName());
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public boolean saves(List<User> users) {
        save(users.get(0));
        save(users.get(1));

        if (users.size() > 2) {
            throw new TransactionRollbackTestRuntimeException("Transaction rollback test");
        }

        save(users.get(2));
        return true;
    }

}

package innossh.springjdbc.example.dao;

import innossh.springjdbc.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component(value = "userDaoImpl2")
public class UserDaoImpl2 implements UserDao {

    @Autowired
    @Qualifier(value = "jdbcTemplate2")
    JdbcTemplate jdbcTemplate2;

//    @Override
//    public User findOne(Long id) {
//        return jdbcTemplate2.query("SELECT * FROM users WHERE user_id = ?", rs -> {
//            if (rs.next()) {
//                return new User(rs.getLong("id"), rs.getString("name"));
//            }
//            return null;
//        }, id);
//    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate2.query("SELECT * FROM users", rs -> {
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(new User(rs.getLong("id"), rs.getString("name")));
            }
            return users;
        });
    }

//    @Override
//    public int save(User user) {
//        return jdbcTemplate2.update("REPLACE INTO users VALUES (?, ?)", user.getId(), user.getName());
//    }

    @Transactional(value = "transactionManager2", propagation = Propagation.REQUIRED)
    @Override
    public boolean saves(List<User> users) {
        jdbcTemplate2.update("CREATE TABLE IF NOT EXISTS `temp` (`id` bigint(20) not null, `name` varchar(255) default null, primary key (`id`));");
        jdbcTemplate2.update("REPLACE INTO users VALUES (?, ?)", users.get(0).getId(), users.get(0).getName());
        jdbcTemplate2.update("REPLACE INTO users VALUES (?, ?)", users.get(1).getId(), users.get(1).getName());

        // exception
        jdbcTemplate2.update("REPLACE INTO users1 VALUES (?, ?)", users.get(2).getId(), users.get(2).getName());

        return true;
    }

}

package innossh.springjdbc.example.service;

import innossh.springjdbc.example.bean.UsersGetResponse;
import innossh.springjdbc.example.entity.User;
import innossh.springjdbc.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    @Qualifier(value = "userDaoImpl")
    UserDao userDao;

    @Autowired
    @Qualifier(value = "userDaoImpl2")
    UserDao userDao2;

//    public User getUser(Long id) {
//        return userDao.findOne(id);
//    }

    public UsersGetResponse getUsers(String db) {
        List<User> users = ("example".equals(db)) ? userDao.findAll() : userDao2.findAll();
        UsersGetResponse response = new UsersGetResponse();
        response.setTotal(users.size());
        response.setUsers(users);
        return response;
    }

//    public boolean saveUser(User user) {
//        if (userDao.save(user) != 1) {
//            throw new RuntimeException("Failed to save user :" + user);
//        }
//        return true;
//    }

    public boolean saveUsers(String db) throws Exception {
        Long tempId = new Date().getTime();
        String tempName = "hoge";
        List<User> users = new ArrayList<>(Arrays.asList(new User(tempId, tempName), new User(tempId + 1L, tempName + "1"), new User(tempId + 2L, tempName + "2")));
        boolean isSuccess = ("example".equals(db)) ? userDao.saves(users) : userDao2.saves(users);
        if (!isSuccess) {
            throw new RuntimeException("Failed to save users :" + users);
        }
        return true;

    }

}
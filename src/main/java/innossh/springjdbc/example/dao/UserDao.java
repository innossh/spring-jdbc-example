package innossh.springjdbc.example.dao;

import innossh.springjdbc.example.entity.User;

import java.util.List;

public interface UserDao {

//    public User findOne(Long id);

    public List<User> findAll();

//    public int save(User user);

    public boolean saves(List<User> users) throws Exception;
}

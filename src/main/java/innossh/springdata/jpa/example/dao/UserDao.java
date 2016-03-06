package innossh.springdata.jpa.example.dao;

import innossh.springdata.jpa.example.entity.User;

import java.util.List;

public interface UserDao {

//    public User findOne(Long id);

    public List<User> findAll();

//    public int save(User user);

    public boolean saves(List<User> users) throws Exception;
}

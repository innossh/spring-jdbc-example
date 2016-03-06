package innossh.springdata.jpa.example.bean;

import innossh.springdata.jpa.example.entity.User;

import java.util.List;

public class UsersGetResponse {

    private int total;
    private List<User> users;

    public long getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UsersGetResponse{" +
                "total=" + total +
                ", users=" + users +
                '}';
    }
}

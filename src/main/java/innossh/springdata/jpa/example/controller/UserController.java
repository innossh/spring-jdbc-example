package innossh.springdata.jpa.example.controller;

import innossh.springdata.jpa.example.bean.UsersGetResponse;
import innossh.springdata.jpa.example.entity.User;
import innossh.springdata.jpa.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UsersGetResponse getUsers(@RequestParam(required = false) String db) {
        return userService.getUsers(db);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String saveUsers(@RequestParam(required = false) String db) throws Exception {
        return Boolean.toString(userService.saveUsers(db));
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public String saveUser(@PathVariable Long id, @RequestParam String name) {
//        return Boolean.toString(userService.saveUser(new User(id, name)));
//    }

}
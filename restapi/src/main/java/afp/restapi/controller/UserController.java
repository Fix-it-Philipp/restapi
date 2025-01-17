package afp.restapi.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import afp.restapi.models.Debug;
import afp.restapi.models.IUsers;
import afp.restapi.models.Users;
import afp.restapi.services.UserService;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping(path="/v1")
@RestController
public class UserController {
    private final UserService USERSERVICE;

    public UserController(UserService USERSERVICE){
        this.USERSERVICE = USERSERVICE;
    }

    @GetMapping(path="/user/findAll")
    private ResponseEntity<List<Users>> findAllUsers() {
        return new ResponseEntity<>(USERSERVICE.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping(path="/user")
    private ResponseEntity<Users> saveUser(@RequestBody Users user) {
        return new ResponseEntity<Users>(USERSERVICE.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping(path="/user/{id}")
    private ResponseEntity<Optional<Users>> findUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<Optional<Users>>(USERSERVICE.findUserById(id), HttpStatus.OK);
    }

    @PutMapping(path="/user")
    private ResponseEntity<?> updateUser(@RequestBody Users user) {
        USERSERVICE.updateUser(user);
        Debug.log("User upgedatet: " + user.getUserId() + ", " + user.getEmail());
        return new ResponseEntity<>(USERSERVICE.getIUserByEmail(user.getEmail()), HttpStatus.OK);
    }

    @DeleteMapping(path="/user/delete")
    private ResponseEntity<Users> deleteUser(@RequestBody Users user) {
        USERSERVICE.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping(path="/user/find/{role}")
    private ResponseEntity<List<Users>> getUserByRole(@PathVariable("role") String roleName) {
        return new ResponseEntity<List<Users>>(USERSERVICE.getUserByRole(roleName), HttpStatus.OK);
    }
    
    @PostMapping(path="/user/login")
    private ResponseEntity<Boolean> logIn(@RequestBody Map<String, String> login, HttpServletRequest request){
        return new ResponseEntity<>(USERSERVICE.logIn(login.get("password"), login.get("email"), request), HttpStatus.OK);
    }

    @GetMapping(path="/miniuser/{email}")
    private ResponseEntity<Optional<IUsers>> getIUserByEmail(@PathVariable("email") String email){
        return new ResponseEntity<>(USERSERVICE.getIUserByEmail(email), HttpStatus.OK);
    }
}

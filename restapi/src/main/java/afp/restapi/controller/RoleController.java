package afp.restapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import afp.restapi.models.Roles;
import afp.restapi.services.RoleService;

@RequestMapping(path="/v1")
@RestController
public class RoleController {
    private final RoleService ROLESERVICE;

    public RoleController(RoleService ROLESERVICE){
        this.ROLESERVICE = ROLESERVICE;
    }

    @GetMapping(path = "/role/findAll")
    private ResponseEntity<List<Roles>> findAllRoles() {
        return new ResponseEntity<>(ROLESERVICE.findAllRoles(), HttpStatus.OK);
    }

    @PostMapping(path = "/role")
    private ResponseEntity<Roles> saveRole(@RequestBody Roles role) {
        return new ResponseEntity<Roles>(ROLESERVICE.saveRole(role), HttpStatus.CREATED);
    }

    @PutMapping(path="/role")
    private ResponseEntity<?> updateRole(@RequestBody Roles role) {
        return new ResponseEntity<>(ROLESERVICE.updateRole(role), HttpStatus.OK);
    }

    @DeleteMapping(path="/role")
    private ResponseEntity<Roles> deleteRole(@RequestBody Roles role) {
        ROLESERVICE.deleteRole(role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @GetMapping(path = "/role/{name}")
    // private ResponseEntity<Optional<List<Roles>>> findRolesByName(@RequestParam("name") String roleName) {
    //     return new ResponseEntity<Optional<List<Roles>>>(ROLESERVICE.findRolesByName(roleName), HttpStatus.OK);
    // }

    // @GetMapping(path = "/role/{role}")
    // private ResponseEntity<Optional<List<Roles>>> findUsersByRole(@RequestParam("role") String roleName) {
    //     return new ResponseEntity<Optional<List<Roles>>>(ROLESERVICE.findUsersByRole(roleName), HttpStatus.OK);
    // }
}

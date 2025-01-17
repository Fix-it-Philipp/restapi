package afp.restapi.controller;

import java.util.List;
import java.util.Optional;

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

import afp.restapi.models.Subforum;
import afp.restapi.services.SubforumService;

@RequestMapping(path="/v1")
@RestController
public class SubforumController {

    private final SubforumService SUBFORUMSERVICE;

    public SubforumController(SubforumService SUBFORUMSERVICE){
        this.SUBFORUMSERVICE = SUBFORUMSERVICE;
    }

    @GetMapping(value="/subforum/findAll")
    private ResponseEntity<List<Subforum>> findSubforums(){
        return new ResponseEntity<>(SUBFORUMSERVICE.getSubforums(),HttpStatus.OK);
    }

    @GetMapping(value="/subforum/{id}")
    private ResponseEntity<Optional<Subforum>> findSubforum(@PathVariable("id") Long id){
        return new ResponseEntity<>(SUBFORUMSERVICE.getSubforum(id), HttpStatus.OK);
    }

    @PostMapping(value="/subforum")
    private ResponseEntity<Subforum> saveSubforum(@RequestBody Subforum Subforum){
        return new ResponseEntity<>(SUBFORUMSERVICE.saveSubforum(Subforum), HttpStatus.CREATED);
    }

    @PostMapping(value="/subforum/all")
    private ResponseEntity<List<Subforum>> saveSubforums(@RequestBody List<Subforum> Subforums){
        return new ResponseEntity<>(SUBFORUMSERVICE.saveSubforums(Subforums),HttpStatus.CREATED);
    }

    @PutMapping(value="/subforum/update")
    private ResponseEntity<Subforum> updateSubforum(@RequestBody Subforum Subforum){
        return new ResponseEntity<>(SUBFORUMSERVICE.updateSubforum(Subforum), HttpStatus.OK);
    }

    @DeleteMapping(value="/subforum/{id}")
    private void deleteSubforum(@PathVariable("id") Long id) {
        SUBFORUMSERVICE.deleteSubforum(id);
    }
}

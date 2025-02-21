package com.a.demo.controller;

/*import com.a.demo.model.Message;
import com.a.demo.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@RestController
public class MessageController {
      @Autowired
    MessageService obj;
    @PostMapping("/postdata")
    public ResponseEntity<Message> addMessage(@RequestBody Message a)
    {
        return new ResponseEntity<>(obj.add(a),HttpStatus.ACCEPTED);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return new ResponseEntity<>(obj.getAllMessages(), HttpStatus.OK);
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int id) {
        return obj.getMessageById(id)
                  .map(message -> new ResponseEntity<>(message, HttpStatus.OK))
                  .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/messages/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message newMessage) {
        try {
            return new ResponseEntity<>(obj.updateMessage(id, newMessage), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int id) {
        try {
            obj.deleteMessage(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
     }

}*/


/*import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.a.demo.model.Children;
import com.a.demo.service.ChildrenService;



@RestController
public class ChildrenController {
    @Autowired
    ChildrenService ser;

    @PostMapping("/api/children")
    public ResponseEntity<Children> pos(@RequestBody Children c)
    {
        return  ResponseEntity.status(201).body(ser.post(c));
    }

    @GetMapping("/api/children/sortBy/{field}")
    public List<Children> g(@PathVariable String field)
    {
        return ser.sort(field);
    }

    @GetMapping("/api/children/{offset}/{pagesize}")
    public List<Children> get(@PathVariable int offset,@PathVariable int pagesize)
    {
        return ser.page(pagesize, offset);
    }

    @GetMapping("/api/children/{offset}/{pagesize}/{field}")
    public List<Children> pagesorting(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
    {
        return ser.pagesort(pagesize,offset, field);
    }
}*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.a.demo.model.Pet;
import com.a.demo.service.PetService;

@RestController
@RequestMapping("/api/pets")
public class PetController {
    
    @Autowired
    PetService petService;

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        return ResponseEntity.status(201).body(petService.createPet(pet));
    }

    // Get a pet by ID
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable int id) {
        Pet pet = petService.getPetById(id);
        if (pet != null) {
            return ResponseEntity.ok(pet);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/sortBy/{field}")
    public List<Pet> sortPets(@PathVariable String field) {
        return petService.sortPets(field);
    }

    @GetMapping("/paginate")
    public List<Pet> getPaginatedPets(@RequestParam int page, @RequestParam int size) {
        return petService.getPaginatedPets(page, size);
    }

    @GetMapping("/paginate/sort")
    public List<Pet> getPaginatedSortedPets(@RequestParam int page, @RequestParam int size, @RequestParam String field) {
        return petService.getPaginatedSortedPets(page, size, field);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePet(@PathVariable int id) {
        boolean deleted = petService.deletePet(id);
        if (deleted) {
            return ResponseEntity.ok("Pet deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Pet not found");
        }
    }
}

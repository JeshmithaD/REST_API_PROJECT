package com.a.demo.service;

/*import com.a.demo.model.Message;
import com.a.demo.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
 @Autowired
    MessageRepository obj;
    public Message add(Message a)
    {
        return obj.save(a);
    }
    public List<Message> getAllMessages()
    {
        return obj.findAll();
    }
    public Optional<Message> getMessageById(int id)
    {
        return obj.findById(id);

    }
    public Message updateMessage(int id, Message newMessage) 
    {
        return obj.findById(id).map(existingMessage -> {
            existingMessage.setId(newMessage.getId()); 
            existingMessage.setFirstname(newMessage.getFirstname());  
            return obj.save(existingMessage);
        }).orElseThrow(() -> new RuntimeException("Message not found with id " + id));
    }    
    public void deleteMessage(int id)
    {
        if(obj.existsById(id))
        {
            obj.deleteById(id);
        }
        else
        {
            throw new RuntimeException("Message not found with id "+id);
        }
    }
}*/

// Service





import com.a.demo.model.Pet;
import com.a.demo.repository.PetRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PetService {
    
    @Autowired
    private PetRepo petRepository;

    // Create a new pet
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

     // Get a pet by ID
     public Pet getPetById(int id) {
        return petRepository.findById(id).orElse(null);
    }

    // Get all pets sorted by a specific field
    public List<Pet> sortPets(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return petRepository.findAll(sort);
    }

    // Paginate pets
    public List<Pet> getPaginatedPets(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return petRepository.findAll(pageable).getContent();
    }

    // Paginate and sort pets
    public List<Pet> getPaginatedSortedPets(int page, int size, String field) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, field));
        return petRepository.findAll(pageable).getContent();
    }

    // Delete a pet
    public boolean deletePet(int id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            petRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

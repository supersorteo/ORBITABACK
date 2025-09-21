package com.example.orbita.services;

import com.example.orbita.entity.Contact;
import com.example.orbita.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Contact saveContact(Contact contact) {
        contact.setFecha(LocalDate.now()); // Establecer fecha actual
        return contactRepository.save(contact);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public Contact updateContact(Long id, Contact contactDetails) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        contact.setNombre(contactDetails.getNombre());
        contact.setEmail(contactDetails.getEmail());
        contact.setWhatsapp(contactDetails.getWhatsapp());
        contact.setMensaje(contactDetails.getMensaje());
        return contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

}

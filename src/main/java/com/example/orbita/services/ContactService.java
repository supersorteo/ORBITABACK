package com.example.orbita.services;

import com.example.orbita.entity.Contact;
import com.example.orbita.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public Contact saveContact(Contact contact) {
        contact.setFecha(LocalDate.now());
        Contact saved = contactRepository.save(contact);
        log.info("Nuevo contacto guardado: id={}, email={}", saved.getId(), saved.getEmail());
        return saved;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public Contact updateContact(Long id, Contact contactDetails) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contacto no encontrado con id: " + id));
        contact.setNombre(contactDetails.getNombre());
        contact.setEmail(contactDetails.getEmail());
        contact.setWhatsapp(contactDetails.getWhatsapp());
        contact.setMensaje(contactDetails.getMensaje());
        log.info("Contacto actualizado: id={}", id);
        return contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new EntityNotFoundException("Contacto no encontrado con id: " + id);
        }
        contactRepository.deleteById(id);
        log.info("Contacto eliminado: id={}", id);
    }
}

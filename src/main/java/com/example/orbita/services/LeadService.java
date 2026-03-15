package com.example.orbita.services;

import com.example.orbita.entity.Lead;
import com.example.orbita.repository.LeadRepository;
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
public class LeadService {

    private final LeadRepository leadRepository;

    public Lead saveLead(Lead lead) {
        if (lead.getFecha() == null) {
            lead.setFecha(LocalDate.now());
        }
        Lead saved = leadRepository.save(lead);
        log.info("Nuevo lead guardado: id={}, tipo={}, contacto={}", saved.getId(), saved.getTipo(), saved.getContacto());
        return saved;
    }

    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    public Optional<Lead> getLeadById(Long id) {
        return leadRepository.findById(id);
    }

    public Lead updateLead(Long id, Lead leadDetails) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lead no encontrado con id: " + id));
        lead.setNombre(leadDetails.getNombre());
        lead.setFecha(leadDetails.getFecha());
        lead.setTipo(leadDetails.getTipo());
        lead.setAlcance(leadDetails.getAlcance());
        lead.setMensaje(leadDetails.getMensaje());
        lead.setContacto(leadDetails.getContacto());
        lead.setHits(leadDetails.getHits());
        lead.setEstimado(leadDetails.getEstimado());
        log.info("Lead actualizado: id={}", id);
        return leadRepository.save(lead);
    }

    public void deleteLead(Long id) {
        if (!leadRepository.existsById(id)) {
            throw new EntityNotFoundException("Lead no encontrado con id: " + id);
        }
        leadRepository.deleteById(id);
        log.info("Lead eliminado: id={}", id);
    }
}

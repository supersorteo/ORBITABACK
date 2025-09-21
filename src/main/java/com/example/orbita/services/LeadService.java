package com.example.orbita.services;

import com.example.orbita.entity.Lead;
import com.example.orbita.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadService {
    @Autowired
    private LeadRepository leadRepository;

    public Lead saveLead(Lead lead) {
        // Opcional: Agregar lógica de negocio, e.g., validaciones o cálculos adicionales
        return leadRepository.save(lead);
    }

    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    public Optional<Lead> getLeadById(Long id) {
        return leadRepository.findById(id);
    }

    public Lead updateLead(Long id, Lead leadDetails) {
        Lead lead = leadRepository.findById(id).orElseThrow(() -> new RuntimeException("Lead not found"));
        //lead.setT(leadDetails.getT());
        lead.setNombre(leadDetails.getNombre());
        lead.setFecha(leadDetails.getFecha());
        lead.setTipo(leadDetails.getTipo());
        lead.setAlcance(leadDetails.getAlcance());
        lead.setMensaje(leadDetails.getMensaje());
        lead.setContacto(leadDetails.getContacto());
        lead.setHits(leadDetails.getHits());
        lead.setEstimado(leadDetails.getEstimado());
        return leadRepository.save(lead);
    }

    public void deleteLead(Long id) {
        leadRepository.deleteById(id);
    }

}

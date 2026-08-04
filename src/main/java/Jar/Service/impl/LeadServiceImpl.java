package Jar.Service.impl;

import Jar.Dto.LeadRequestDTO;
import Jar.Dto.LeadResponseDTO;
import Jar.Model.Lead;
import Jar.Repository.LeadRepository;
import Jar.Service.LeadService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;

    public LeadServiceImpl(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    @Override
    public List<LeadResponseDTO> listAll() {
        return leadRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public LeadResponseDTO create(LeadRequestDTO dto) {
        Lead lead = new Lead();
        lead.setName(dto.name());
        lead.setContact(dto.contact());
        lead.setMessage(dto.message());

        Lead saved = leadRepository.save(lead);
        return toResponseDTO(saved);
    }

    @Override
    public void delete(Long id) {
        leadRepository.deleteById(id);
    }

    private LeadResponseDTO toResponseDTO(Lead lead) {
        return new LeadResponseDTO(
                lead.getId(),
                lead.getName(),
                lead.getContact(),
                lead.getMessage(),
                lead.getCreatedAt()
        );
    }
}
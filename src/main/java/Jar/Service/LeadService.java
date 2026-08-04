package Jar.Service;

import Jar.Dto.LeadRequestDTO;
import Jar.Dto.LeadResponseDTO;
import java.util.List;

public interface LeadService {
    List<LeadResponseDTO> listAll();
    LeadResponseDTO create(LeadRequestDTO dto);
    void delete(Long id);
}
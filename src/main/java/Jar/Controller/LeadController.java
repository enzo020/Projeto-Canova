package Jar.Controller;

import Jar.Dto.LeadRequestDTO;
import Jar.Dto.LeadResponseDTO;
import Jar.Service.LeadService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @GetMapping
    public ResponseEntity<List<LeadResponseDTO>> listAll() {
        return ResponseEntity.ok(leadService.listAll());
    }

    @PostMapping
    public ResponseEntity<LeadResponseDTO> create(@Valid @RequestBody LeadRequestDTO dto) {
        return ResponseEntity.ok(leadService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        leadService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
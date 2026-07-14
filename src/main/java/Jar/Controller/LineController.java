package Jar.Controller;

import Jar.Dto.LineResponseDTO;
import Jar.Dto.LineRequestDTO;
import Jar.Service.LineService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/line")
public class LineController {
    private final LineService lineService;

    public LineController(LineService lineService){
        this.lineService = lineService;
    }

    @GetMapping
    public ResponseEntity<List<LineResponseDTO>> listAll(){
        return ResponseEntity.ok(lineService.listAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<LineResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(lineService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LineResponseDTO> create(@Valid @RequestBody LineRequestDTO dto) {
        return ResponseEntity.ok(lineService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LineResponseDTO> update(@PathVariable Long id, @Valid @RequestBody LineRequestDTO dto) {
        return ResponseEntity.ok(lineService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lineService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


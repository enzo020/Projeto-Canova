package Jar.Service.impl;

import Jar.Exception.ResourceNotFoundException;
import Jar.Service.LineService;
import Jar.Dto.LineRequestDTO;
import Jar.Dto.LineResponseDTO;
import Jar.Model.Line;
import Jar.Repository.LineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineServiceIMPL implements LineService {

    private final LineRepository lineRepository;

    public LineServiceIMPL(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    @Override
    public List<LineResponseDTO> listAll() {
        return lineRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public LineResponseDTO findById(Long id) {
        Line line = lineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        return toResponseDTO(line);
    }

    @Override
    public LineResponseDTO create(LineRequestDTO dto) {
        Line line = new Line();
        line.setName(dto.name());

        Line saved = lineRepository.save(line);
        return toResponseDTO(saved);
    }

    @Override
    public LineResponseDTO update(Long id, LineRequestDTO dto) {
        Line line = lineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        line.setName(dto.name());

        Line updated = lineRepository.save(line);
        return toResponseDTO(updated);
    }

    @Override
    public void delete(Long id) {
        lineRepository.deleteById(id);
    }

    private LineResponseDTO toResponseDTO(Line line) {
        return new LineResponseDTO(line.getId(), line.getName());
    }


}

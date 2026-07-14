package Jar.Service;

import Jar.Dto.LineRequestDTO;
import Jar.Dto.LineResponseDTO;
import Jar.Dto.ProductRequestDTO;
import Jar.Model.Line;

import java.util.List;

public interface LineService {
    List<LineResponseDTO> listAll();
    LineResponseDTO findById(Long Id);
    LineResponseDTO create(LineRequestDTO dto);
    LineResponseDTO update(Long Id, LineRequestDTO name);
    void delete(Long Id);
}

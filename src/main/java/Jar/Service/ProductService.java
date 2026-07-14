package Jar.Service;

import Jar.Dto.*;
import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> listAll();
    ProductResponseDTO findById(Long id);
    ProductResponseDTO create(ProductRequestDTO dto);
    ProductResponseDTO update(Long id, ProductRequestDTO dto);
    void delete(Long id);
}

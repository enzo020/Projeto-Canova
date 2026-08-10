package Jar.Service.impl;

import Jar.Dto.*;
import Jar.Exception.ResourceNotFoundException;
import Jar.Model.*;
import Jar.Repository.*;
import Jar.Service.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final LineRepository lineRepository;

    public ProductServiceImpl(ProductRepository productRepository, LineRepository lineRepository) {
        this.productRepository = productRepository;
        this.lineRepository = lineRepository;
    }

    @Override
    public List<ProductResponseDTO> listAll() {
        return productRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public ProductResponseDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));;
        return toResponseDTO(product);
    }

    @Override
    public ProductResponseDTO create(ProductRequestDTO dto) {
        Line line = lineRepository.findById(dto.lineId())
                .orElseThrow(() -> new ResourceNotFoundException("Line não encontrado"));

        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setImageUrl(dto.imageUrl());

        return toResponseDTO(productRepository.save(product));
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        Line line = lineRepository.findById(dto.lineId())
                .orElseThrow(() -> new ResourceNotFoundException("Line não encontrado"));

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setLine(line);
        product.setImageUrl(dto.imageUrl());

        return toResponseDTO(productRepository.save(product));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImageUrl(),
                product.getLine().getName()
        );
    }
}
package Jar.Controller;

import Jar.Dto.LoginRequestDTO;
import Jar.Dto.LoginResponseDTO;
import Jar.Dto.RegisterRequestDTO;
import Jar.Exception.InvalidCredentialsException;
import Jar.Exception.ResourceAlreadyExistsException;
import Jar.Model.User;
import Jar.Repository.UserRepository;
import Jar.Security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(() -> new InvalidCredentialsException("Usuário ou senha inválidos"));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Usuário ou senha inválidos");
        }

        String token = jwtService.generateToken(user.getUsername(), user.getRole());

        return ResponseEntity.ok(new LoginResponseDTO(token, user.getUsername(), user.getRole()));
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO dto) {
        if (userRepository.findByUsername(dto.username()).isPresent()) {
                throw new ResourceAlreadyExistsException("Usuário já existe");
        }

        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(dto.role());

        userRepository.save(user);

        return ResponseEntity.ok("Usuário criado com sucesso");
    }
}
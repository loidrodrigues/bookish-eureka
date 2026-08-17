package br.com.loidpadre.segundo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import br.com.loidpadre.segundo.dto.UserRequestDto;
import br.com.loidpadre.segundo.dto.UserResponseDto;
import br.com.loidpadre.segundo.model.User;
import br.com.loidpadre.segundo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto saveUser(UserRequestDto request) {

        if (!request.email().contains("@")) {
            throw new IllegalArgumentException("O e-mail informado é inválido.");
        }

        // 2. CONVERSÃO DE ENTRADA (Request DTO -> Entidade)
        // Transformamos o DTO da internet no objeto que o banco entende
        User user = new User(request.nome(), request.email(), request.senha());

        // 3. Salva no banco de dados e recebe o objeto com o ID gerado
        User usuarioSalvo = userRepository.save(user);

        // 4. CONVERSÃO DE SAÍDA (Entidade -> Response DTO)
        // Pegamos o usuário salvo, tiramos a senha, e montamos o crachá de saída
        return new UserResponseDto(usuarioSalvo.getId(), usuarioSalvo.getName(), usuarioSalvo.getEmail());
    }

    public List<UserResponseDto> buscarTodos() {
        List<User> listaDeUsuarios = userRepository.findAll();

        // faço um varredura na lista que veio do banco de dados, e passo o DTO, para
        // mostrar so o necessario para o front
        return listaDeUsuarios.stream().map(user -> new UserResponseDto(user.getId(), user.getName(),
                user.getEmail())).toList();
    }

    public UserResponseDto getOnUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário nao encontrado"));
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail());

    }

    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }

    public UserResponseDto EditeUser(Long id, UserRequestDto request) {
        User usuarioExistente = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        usuarioExistente.setName(request.nome());
        usuarioExistente.setEmail(request.email());
        usuarioExistente.setSenha(request.senha());

        User usuarioAtualizado = userRepository.save(usuarioExistente);
        return new UserResponseDto(usuarioAtualizado.getId(), usuarioAtualizado.getName(),
                usuarioAtualizado.getEmail());
    }
}

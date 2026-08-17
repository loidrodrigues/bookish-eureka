package br.com.loidpadre.segundo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import br.com.loidpadre.segundo.model.User;
import br.com.loidpadre.segundo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(String name, String email, String senha) {

        if (!email.contains("@")) {
            throw new IllegalArgumentException("O email informado é invalido");
        }
        User user = new User(name, email, senha);
        return userRepository.save(user);
    }

    public List<User> buscarTodos() {
        return userRepository.findAll();
    }

    public User getOnUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario nao encontrado"));
    }

    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }
}

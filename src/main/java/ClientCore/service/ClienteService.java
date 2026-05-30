package ClientCore.service;

import ClientCore.dto.ClienteDTO;
import ClientCore.model.Cliente;
import ClientCore.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteDTO criar(ClienteDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado: " + dto.getEmail());
        }
        return toDTO(repository.save(toEntity(dto)));
    }

    public List<ClienteDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO buscarPorId(Long id) {
        return toDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado: " + id)));
    }

    public ClienteDTO atualizar(Long id, ClienteDTO dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado: " + id));

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setCpf(dto.getCpf());

        return toDTO(repository.save(cliente));
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado: " + id);
        }
        repository.deleteById(id);
    }

    private ClienteDTO toDTO(Cliente c) {
        return new ClienteDTO(c.getId(), c.getNome(), c.getEmail(), c.getTelefone(), c.getCpf());
    }

    private Cliente toEntity(ClienteDTO dto) {
        return Cliente.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .cpf(dto.getCpf())
                .build();
    }
}

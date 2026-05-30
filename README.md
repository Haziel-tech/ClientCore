## 🗃️ Modelo de dados

| Campo | Tipo | Obrigatório | Observação |
|---|---|---|---|
| `id` | Long | — | Gerado automaticamente |
| `nome` | String | ✅ | Máx. 100 caracteres |
| `email` | String | ✅ | Único, formato válido |
| `telefone` | String | ❌ | Máx. 15 caracteres |
| `cpf` | String | ❌ | Único |
| `dataCadastro` | DateTime | — | Gerado automaticamente |


---

## 🧠 Conceitos aplicados

- **MVC** — separação em Controller, Service e Repository
- **JPA / Hibernate** — mapeamento objeto-relacional
- **DTO Pattern** — separação entre a entidade do banco e os dados da API
- **Bean Validation** — validação declarativa com `@NotBlank`, `@Email`
- **Injeção de dependência** — via construtor com `@RequiredArgsConstructor`
- **ResponseEntity** — controle explícito dos status HTTP

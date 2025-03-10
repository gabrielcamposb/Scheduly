O **Scheduly** é um sistema de agendamento de consultas desenvolvido com **Spring Boot**, que facilita a gestão de horários disponíveis e o agendamento de consultas entre pacientes e funcionários. A API oferece funcionalidades para consultar horários disponíveis e agendar consultas de forma eficiente e sem conflitos.

### Funcionalidades

- **Buscar horários disponíveis**: Consultar os horários disponíveis para um determinado funcionário dentro de um intervalo de tempo.
- **Agendar consulta**: Agendar uma consulta para um paciente com um funcionário, verificando se o horário desejado está disponível.

### Endpoints

#### `GET /appointments/availability`

- **Descrição**: Retorna os horários disponíveis para um funcionário dentro do intervalo de tempo fornecido.
- **Parâmetros**:
  - `employeeId` (Long): ID do funcionário.
  - `start` (String, formato: "yyyy-MM-dd'T'HH:mm:ss"): Data e hora de início do intervalo.
  - `end` (String, formato: "yyyy-MM-dd'T'HH:mm:ss"): Data e hora de término do intervalo.
  
- **Resposta**:
  - **200 OK**: Lista de horários disponíveis.
  - **400 Bad Request**: Erro de formato de dados ou parâmetros inválidos.

#### `POST /appointments/schedule`

- **Descrição**: Agenda uma consulta para o paciente com um funcionário.
- **Parâmetros**:
  - `patientId` (Long): ID do paciente.
  - `employeeId` (Long): ID do funcionário.
  - `dateHour` (String, formato: "yyyy-MM-dd'T'HH:mm:ss"): Data e hora do agendamento.

- **Resposta**:
  - **201 Created**: Consulta agendada com sucesso.
  - **400 Bad Request**: Horário indisponível ou dados inválidos.
  - **500 Internal Server Error**: Erro interno ao tentar agendar a consulta.

## Tecnologias Usadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para construção da API REST.
- **JPA / Hibernate**: Para a interação com o banco de dados.
- **LocalDateTime**: Para o gerenciamento de datas e horários.
- **Maven**: Gerenciador de dependências e construção do projeto.

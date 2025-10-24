# 🗓️ Sistema Gerenciador de Eventos

Um projeto desenvolvido em **Java puro**, com foco em aplicar conceitos fundamentais de **Programação Orientada a Objetos (POO)**, **validação de dados** e **estrutura de armazenamento em memória** utilizando coleções (`HashSet`, `Array` e `ArrayList`).  

O sistema permite **criar, validar, armazenar, pesquisar, analisar e gerar relatórios** de eventos, simulando o funcionamento básico de um gerenciador de agenda.

---

## 🚀 Funcionalidades Principais

- **Criação de eventos** com nome, data, duração e outras propriedades.
- **Validação de dados** (nome vazio, duração inválida, duplicidade, etc).
- **Armazenamento em memória** usando `Array`, `ArrayList` e `HashSet`.
- **Pesquisa de eventos** por nome ou outros critérios.
- **Análise de agendamento**, verificando conflitos de horários.
- **Geração de relatórios** dos eventos cadastrados.
- **Mensagens de erro e feedback** no console para interação com o usuário.

---

## ⚙️ Tecnologias Utilizadas

- ☕ **Java 21 LTS**
- 📦 **Pacotes padrão (`java.util`, `java.time`, `java.io`, etc.)**
- 🧩 **Coleções:** `HashSet`, `ArrayList`, `Array`
- ⏰ **Manipulação de datas:** `LocalDateTime`, `Duration`

---

## 🧱 Estrutura do Projeto

```bash
SistemaGerenciadorEventos/
├── src/               
│   ├── domain/
│   │   └── Event.java           # Classe que representa o evento (entidade principal).
│   ├── service/
│   │   └── EventUtils.java      # Geração de relatórios e análises, Contém regras de validação, Gerencia criação, remoção e busca de eventos.
│   └── Main.java                # Classe principal: ponto de entrada do programa.
└── README.md

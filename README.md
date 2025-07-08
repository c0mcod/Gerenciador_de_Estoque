# 🧾 Gerenciador de Estoque

**Status do Projeto:** 🚧 Em desenvolvimento  
**Tipo:** Projeto pessoal para fins de estudo  

Este é um sistema de **gerenciamento de estoque** desenvolvido em **Java**, com estrutura em camadas e uso das bibliotecas **Swing** para interface gráfica e **JDBC** para integração com banco de dados MySQL.

---

## 🎯 Objetivo

Criar um sistema desktop que permita o gerenciamento de produtos, fornecedores e vendas em estoque, aplicando boas práticas de orientação a objetos, separação de camadas e persistência de dados.

---

## 📁 Estrutura do Projeto

```
src/
├── br.com.estoque.connection
│   └── ConnectionFactory.java              # Gerencia a conexão com o banco
│
├── br.com.estoque.controller
│   ├── EstoqueFornecedorController.java    # Lógica de fornecedores
│   ├── EstoqueProdutoController.java       # Lógica de produtos
│   └── EstoqueVendaController.java         # Lógica de vendas
│
├── br.com.estoque.fornecedor.dao
│   └── FornecedorDAO.java                  # DAO de Fornecedor
│
├── br.com.estoque.fornecedor.model
│   └── Fornecedor.java                     # Modelo de Fornecedor
│
├── br.com.estoque.produto.dao
│   └── ProdutoDAO.java                     # DAO de Produto
│
├── br.com.estoque.produto.model
│   └── Produto.java                        # Modelo de Produto
│
├── br.com.estoque.vendas.controller
│   ├── ItemVenda.java                      # Modelo de item vendido
│   └── Venda.java                          # Modelo de venda
│
├── br.com.estoque.view
│   ├── Interface.java                      # Inicialização da interface
│   ├── PainelGraficoEstoque.java           # Interface principal com Swing
│   └── info.java                           # Dados auxiliares
│
└── module-info.java
```

---

## 🛠️ Tecnologias Utilizadas

- **Java** (JDK 17)
- **Swing** (interface gráfica)
- **JDBC** (acesso ao banco de dados)
- **MySQL** (banco de dados)
- **IDE:** Eclipse

---

## 🚀 Como Executar

> ⚠️ O projeto ainda está em construção. Algumas partes podem não estar finalizadas ou conectadas corretamente.

1. **Clone este repositório:**
   ```bash
   git clone https://github.com/c0mcod/Gerenciador_de_Estoque.git
   ```

2. **Importe o projeto** no Eclipse ou outra IDE compatível com Java.

3. **Configure o banco de dados MySQL** com as tabelas necessárias (modelo ainda em construção).

4. **Configure as credenciais** de acesso ao banco no arquivo:
   ```
   br.com.estoque.connection.ConnectionFactory.java
   ```

5. **Execute a classe** `Interface.java` para abrir a interface gráfica.

---

## 🚧 Andamento do Projeto

- [x] Estrutura inicial dos pacotes e classes
- [x] CRUD de Produto (em progresso)
- [x] CRUD de Fornecedor (em progresso)
- [ ] CRUD de Vendas
- [ ] Interface completa com tabelas e eventos
- [ ] Testes unitários
- [ ] Documentação do banco de dados

---

## 📋 Funcionalidades Planejadas

- ✅ Cadastro, edição e exclusão de produtos
- ✅ Cadastro, edição e exclusão de fornecedores
- 🔄 Sistema de vendas e controle de estoque
- 🔄 Relatórios de estoque
- 🔄 Interface gráfica completa e intuitiva

---

## 📌 Observações

- Este é um projeto **pessoal** com foco em **aprendizado**.
- Pode conter trechos incompletos ou em refatoração.
- O código segue padrões de arquitetura em camadas (MVC + DAO).
- Não há licença definida até o momento.

---

## 🤝 Contribuindo

Se você quiser sugerir algo, sinta-se à vontade para abrir uma **Issue** ou entrar em contato.

---

## 📬 Contato

Desenvolvido por **Antônio Carlos (Neto)**  
🔗 GitHub: [@c0mcod](https://github.com/c0mcod)
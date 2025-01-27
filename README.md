# API de Gerenciamento de Produtos

Esta API permite gerenciar produtos, incluindo criação, listagem, atualização e exclusão. Abaixo estão os detalhes de cada endpoint.

---

## **1. Executando o Sistema**

### **Pré-requisitos**
Certifique-se de que você possui as seguintes ferramentas instaladas:
- **Java JDK 17+**
- **Banco de Dados** H2 (banco em memória)
- **Postman** ou outra ferramenta para testar APIs REST 

### **Passos para Executar**

1. **Clone o Repositório**:
   ```bash
   git clone git@github.com:joaocandidozup/retornosAPI.git

2. **Configure o Banco de Dados**:
    - Verifique o arquivo `application.properties` ou `application.yml` para configurar as variáveis de ambiente DB_USERNAME e DB_PASSWORD..

3. **Compile e Execute o Projeto**:
    - Compile o projeto:
      ```bash
      mvn clean install
      ```
    - Execute o projeto:
      ```bash
      mvn spring-boot:run
      ```
    - O sistema estará disponível em: `http://localhost:8080`.

---

## **2. Testando os Endpoints**

### **Ferramentas Recomendadas**
- **Postman**: Para testar manualmente os endpoints.

### **1. Cadastrar produto**
1. **Inicie o Postman** e crie uma nova requisição.
2. **Configure a URL do Endpoint**:
    - Exemplo: `http://localhost:8080/api/products`
3. **Escolha o Método HTTP**:
    -  `POST`

    - Exemplo de JSON para criar um produto:
      ```json
      {
        "name": "Camisa",
        "description": "Camisa de algodão",
        "price": 50.0,
        "quantityStock": 10,
        "category": "Roupas"
      }
      ```
4. **Envie a Requisição** e verifique a resposta.

### **2. Buscar todos os produtos**
1. **Inicie o Postman** e crie uma nova requisição.
2. **Configure a URL do Endpoint**:
   - Exemplo: `http://localhost:8080/api/products`
3. **Escolha o Método HTTP**:
   -  `GET`
4. **Envie a Requisição** e verifique a resposta.

### **3. Buscar produtos pelo nome**
1. **Inicie o Postman** e crie uma nova requisição.
2. **Configure a URL do Endpoint**:
   - Exemplo: `http://localhost:8080/api/products/search?name=Camisa`
3. **Escolha o Método HTTP**:
   -  `GET`
4. **Envie a Requisição** e verifique a resposta.

### **4. Buscar produto PELO ID**
1. **Inicie o Postman** e crie uma nova requisição.
2. **Configure a URL do Endpoint**:
   - Exemplo: `http://localhost:8080/api/products/1`
3. **Escolha o Método HTTP**:
   -  `GET`
4. **Envie a Requisição** e verifique a resposta.

### **5. Atualisar produto**
1. **Inicie o Postman** e crie uma nova requisição.
2. **Configure a URL do Endpoint**:
   - Exemplo: `http://localhost:8080/api/products`
3. **Escolha o Método HTTP**:
   -  `PUT`

   - Exemplo de JSON para atualizar um produto:
     ```json
     {
       "name": "Camisa ",
       "description": "Camisa de algodão",
       "price": 50.0,
       "quantityStock": 10,
       "category": "Roupas"
     }
     ```
4. **Envie a Requisição** e verifique a resposta.

### **6. Deletar produto**
1. **Inicie o Postman** e crie uma nova requisição.
2. **Configure a URL do Endpoint**:
   - Exemplo: `http://localhost:8080/api/products/1`
3. **Escolha o Método HTTP**:
   -  `DELETE`
4. **Envie a Requisição** e verifique a resposta.




# API DistriCommerce

## Autenticação

A API utiliza autenticação JWT para proteger as rotas. Apenas as rotas de cadastro e login são públicas. Para acessar as demais rotas, é necessário obter um token JWT e enviá-lo no header `Authorization`.

---

## 1. Cadastro de Usuário (Público)

**Endpoint:**
```
POST /api/usuarios/cadastro
```
**Body:**
```json
{
  "nome": "Seu Nome",
  "email": "seu@email.com",
  "senha": "suaSenha"
}
```
**Resposta:**
- 201 Created (usuário criado)
- 400 Bad Request (e-mail já cadastrado)

---

## 2. Login (Público)

**Endpoint:**
```
POST /api/usuarios/login
```
**Body:**
```json
{
  "email": "seu@email.com",
  "senha": "suaSenha"
}
```
**Resposta:**
- 200 OK (retorna o token JWT como string)
- 401 Unauthorized (credenciais inválidas)

**Exemplo de resposta:**
```
"eyJhbGciOiJIUzI1NiJ9..."
```

---

## 3. Acesso às rotas protegidas

Para acessar qualquer rota protegida, envie o token JWT no header:

```
Authorization: Bearer SEU_TOKEN_AQUI
```

### Exemplo usando curl:

```
curl -H "Authorization: Bearer SEU_TOKEN_AQUI" http://localhost:8080/api/produtos
```

---

## 4. Exemplos de rotas protegidas

### Listar produtos
```
GET /api/produtos
```

### Criar produto
```
POST /api/produtos
Body:
{
    "nome":"celular",
    "descricao":"iphone 15",
    "preco":5000,
    "estoque":10
}
```

### Listar pedidos do usuário
```
GET /api/pedidos/usuario/{usuarioId}
```

### Criar pedido
```
POST /api/pedidos
Body:
{
  "usuarioId": 1,
  "itens": [
    { "produtoId": 1, "quantidade": 2 }
  ]
}
```

---

## Observações
- Sempre envie o token JWT no header para rotas protegidas.
- Se receber 403 ou 401, verifique se o token está correto e não expirou.
- As rotas de cadastro e login não exigem token.

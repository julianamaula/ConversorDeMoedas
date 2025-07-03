
# Conversor de Moedas em Java

Este projeto em Java realiza a conversão de moedas utilizando a API pública [ExchangeRate-API](https://www.exchangerate-api.com/). Ele usa a biblioteca **Gson** para manipulação de JSON, **HttpClient** para requisições HTTP, e implementa recursos como retries automáticos e logs detalhados das requisições.

---

## Funcionalidades

- Conversão entre moedas: USD, BRL, EUR
- Menu interativo via console para escolher moedas e informar valores
- Requisições HTTP robustas com retries automáticos (até 3 tentativas)
- Análise da resposta JSON com mapeamento direto para objetos Java usando Gson
- Logs das requisições e respostas salvos em arquivo `log.txt`
- Tratamento de erros com mensagens amigáveis ao usuário

---

## Estrutura do Projeto

- `ApiCurrencyFreaks.java`: Classe que faz a requisição à API e obtém a taxa de câmbio
- `ApiResponse.java`: Classe POJO para mapeamento do JSON retornado pela API
- `MenuConversao.java`: Classe responsável pelo menu interativo no console
- `Main.java`: Classe principal que inicializa o programa e chama o menu

---

## Pré-requisitos

- Java 11 ou superior (para suporte ao `HttpClient` nativo)
- Biblioteca Gson (`gson-2.10.1.jar` ou superior)

---

## Como rodar

1. Clone ou baixe o projeto
2. Adicione a biblioteca Gson ao seu classpath (exemplo para compilação manual):
   ```bash
   javac -cp gson-2.10.1.jar *.java
````

3. Execute a aplicação:

   ```bash
   java -cp .;gson-2.10.1.jar Main
   ```
4. No menu que aparece, escolha a conversão desejada e informe o valor.

---

## Configuração da API Key

No arquivo `Main.java`, substitua a string da chave da API na criação do objeto `ApiCurrencyFreaks`:

```java
ApiCurrencyFreaks api = new ApiCurrencyFreaks("SUA_API_KEY_AQUI");
```

Você pode obter uma chave gratuita em [https://www.exchangerate-api.com/](https://www.exchangerate-api.com/).

---

## Logs

As requisições e respostas da API são salvas no arquivo `log.txt` no diretório do projeto. Ele contém:

* Data e hora da requisição
* URL chamada
* Código HTTP retornado
* Cabeçalhos da resposta
* Corpo JSON da resposta

---

## Exemplo de uso

```
--- Menu de Conversão de Moedas ---
1 - USD para BRL (Dólar para Real)
2 - BRL para USD (Real para Dólar)
3 - EUR para USD (Euro para Dólar)
4 - USD para EUR (Dólar para Euro)
5 - BRL para EUR (Real para Euro)
6 - EUR para BRL (Euro para Real)
0 - Sair
Escolha uma opção: 6
Informe o valor para converter: 25
25,00 EUR equivalem a 160,80 BRL
```

---

## Melhorias Futuras

* Criar interface gráfica com JavaFX ou Swing
* Salvar histórico de conversões em CSV
* Suporte a mais moedas e APIs diferentes
* Testes automatizados unitários e de integração

---

## Autor

**Seu Nome** — \[Seu GitHub ou LinkedIn]

---

## Licença

Este projeto está licenciado sob a MIT License - veja o arquivo [LICENSE](LICENSE) para detalhes.

```

---

Quer que eu gere também um modelo de arquivo `LICENSE` ou um guia para estruturar esse projeto em Maven/Gradle?
```


# Requisitos

## Requisitos funcionais

| ID | Requisito |
| --- | --- |
| RF01 | Consultar cardápio | O cliente deve obter os produtos disponíveis, com ID, nome e preço, por meio do Gateway. | `GET /cardapio` retorna HTTP 200 e lista de produtos em JSON. |
| RF02 | Cadastrar pedido | O cliente deve enviar nome e uma lista não vazia de produtos com quantidades inteiras positivas. | `POST /pedidos` recebe JSON; dados inválidos retornam HTTP 400. |
| RF03 | Validar produtos e calcular total | Pedidos deve consultar Cardápio, verificar os IDs e calcular subtotal e total com os preços cadastrados. | Produto inexistente retorna HTTP 400; pedido válido retorna itens detalhados e total correto. |
| RF04 | Autorizar pagamento simulado e confirmar | Pedidos deve chamar Pagamentos via HTTP e confirmar o pedido somente se a autorização simulada for aprovada. | `POST /autorizar` retorna `aprovado`; `POST /pedidos` retorna HTTP 201 e status `confirmado`. Não há cobrança real. |
| RF05 | Consultar pedidos registrados | O cliente deve listar os pedidos e consultar um pedido pelo ID através do Gateway. | `GET /pedidos` retorna a lista; `GET /pedidos/{id}` retorna o pedido ou HTTP 404. |

## Requisitos não funcionais

| ID | Requisito |
| --- | --- |
| RNF01 | Modularidade e comunicação | Gateway, Cardápio, Pedidos e Pagamentos devem executar como processos/contêineres independentes, comunicando-se por HTTP com dados JSON. | Há quatro serviços definidos no Compose, com pastas e Dockerfiles próprios. |
| RNF02 | Implantação reproduzível e acesso controlado | O sistema deve iniciar com um único comando Docker Compose e expor externamente apenas a porta 8000 do Gateway. | `docker compose up --build -d` inicia os quatro contêineres e apenas o Gateway publica `8000:8000`. |
| RNF03 | Observabilidade básica | Os serviços devem registrar em logs eventos relevantes do processamento, incluindo horário, origem e resultado, para inspeção por Docker Compose. | `docker compose logs --timestamps` apresenta registros do Gateway, Pedidos, Cardápio e Pagamentos após um pedido. |

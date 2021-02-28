# PROGRAMA DE GERENCIAMENTO DE TREINAMENTO

## Descrição do treinamento
O treinamento será realizado em 2 etapas e as pessoas serão divididas em salas com lotação variável. 
Serão realizados também dois intervalos de café em 2 espaços distintos. 
Você precisa criar o sistema que gerenciará este evento.

### O sistema precisa permitir que:
- O cadastro de pessoas, com nome e sobrenome;
- O cadastro das salas do evento, com nome e lotação;
- O cadastro dos espaços de café pelo nome;

- A diferença de pessoas em cada sala deverá ser de no máximo 1 pessoa. Para estimular a troca de conhecimentos, metade das pessoas precisam trocar de sala entre as duas etapas do treinamento.
- Ao consultar uma pessoa cadastrada no treinamento, o sistema deverá retornar à sala em que a pessoa ficará em cada etapa e o espaço onde ela realizará cada intervalo de café.
- Ao consultar uma sala cadastrada ou um espaço de café, o sistema deverá retornar uma lista das pessoas que estarão naquela sala ou espaço em cada etapa do evento.

### Requisitos obrigatórios:
Crie uma interface que permita: 
- O cadastro de pessoas, com nome e sobrenome;
- O cadastro das salas do evento, com nome e lotação;
- O cadastro dos espaços de café com lotação;
- A consulta de cada pessoa;
- A consulta de cada sala e espaço;

### Requisitos desejáveis:
- Persistência de dados;
- Testes unitários;
	
## Tecnologias
Projeto criado com:
* Java version: 11.0.10
* ItelliJ IDEA version: 2020.2.3
	
## Setup
Para rodar este projeto:
- Instale as tecnologias descritas no tópico acima;
- Abra a pasta "PROGRAMA DE GERENCIAMENTO" utilizando o IntelliJ
- Compilar e rodar a classe "Sistema".

## Instruções de utilização


## Persistencia de Dados
Os dados são salvos em 3 arquivos diferentes: "Espacos.txt", "Pessoas.txt" e "Salas.txt", onde cada linha representa um objeto dessas classes, com os atributos sendo separados por ","

#### Espacos
Nome, lotacao
#### Pessoas
Nome, sobrenome
#### Salas
Nome, lotacao

## Support
Qualquer dúvida, entrar em contato via e-mail: kaiquee.f@hotmail.com

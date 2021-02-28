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
Ao executar o programa, será exibida uma lista de possíveis ações à serem tomadas, como: Cadastro/Consulta de pessoas, salas e espaços de café, além de sair, que irá printar a lista completa de pessoas, salas e espaços de café em cada etapa do treinamento e não deixará realizar mais modificações.
A cada opção selecionada, o sistema mostrará o que deverá ser feito.
Vale ressaltar que ao iniciar, o programa irá consultar o banco de dados para carregar as listas existentes.
A seguir, uma lista de possíveis impedimentos de cadastramentos.
- Caso a pessoa/sala/espaço já esteja cadastrada, o sistema impedirá o recadastramento.
- Caso não haja espaço nas salas e espaços de café para uma nova pessoa, o sistema impedirá o cadastramento da pessoa.
- Caso a sala não tenha espaço o suficiente para abrigar todas as pessoas cadastradas e obedecer o requisito "A diferença de pessoas em cada sala deverá ser de no máximo 1 pessoa.", o sistema impedirá o cadastramento da nova sala.


## Persistencia de Dados
Os dados são salvos em 3 arquivos diferentes: "Espacos.txt", "Pessoas.txt" e "Salas.txt", onde cada linha representa um objeto dessas classes, com os atributos sendo separados por ",". Caso algum desses arquivos esteja faltando, o programa reiniciará o banco de dados.

- Espacos: Nome, lotacao
- Pessoas: Nome, sobrenome
- Salas: Nome, lotacao

## Support
Qualquer dúvida, entrar em contato via e-mail: kaiquee.f@hotmail.com

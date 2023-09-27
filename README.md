# Spring_Angular
CRUD com Spring e Angular 

Foi realizado criação do CRUD.
Possui processos simples, com uma lista de tarefas gravadas, 
na listagem possui o botão '+' para adicionar uma nova tarefa. Tambem temos o botão 'lapis' para edição de um tarefa gravada e tambem
o botao 'lixeira', para a remoção de uma tarefa. Esta remoção é uma exclusão lógica, setando no registro do banco uma informação
indicando que o item foi excluído, inserindo uma data para registrar o momento da exclusão.
O item excluído nao apresenta na listagem.


Para utilizar a aplicação necessario executar o Spring e o Angular,
Para inciar a aplicação frontEnd com conexão a backend, necessario utilizar o comando: "npm run start" (dentro da pasta da aplicação frontEnd)

Estrutura dos Projetos
Backend -> 
Utilizando o Spring Boot na versão 3.1.4 com Java na versão 17, com banco postgresql e algumas dependencias como JPA.
Iniciado testes unitários, para cobertura dos códigos (necessitando finalização).
A comunicação passando por API para comunicação com o FrontEnd.
No arquivo 'application.properties está a configuração para a comunicação com o postgresql.
Executando o spring é gerado a tabela 'Tarefas'.

Frontend ->  
Utilizado o Angular na versão 14, em conjunto com o Angular Material, inserido dependencias necessárias.
Em 'proxy.cong.js' possui as configurações para a conexão com a aplicação Backend.


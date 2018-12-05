# Documentação sobre a base de pagamentos

* Link para baixar os arquivos de pagamentos: 
    - `http://www.portaltransparencia.gov.br/download-de-dados/despesas-execucao`
* Comando para o linux para baixar todos os arquivos de 2014 a 2018:
    - `wget http://www.portaltransparencia.gov.br/download-de-dados/despesas-execucao/201{4..8}{01..12}.zip`
    - 
## Passos para a criação das tabelas e inserção dos dados:

1. Após baixar os dados, é necessário executar 2 scripts.
2. É necessário utilizar do ambiente Jupyter Notebook.
3. Abrir o arquivo `etl/pagamento/Pagamento_Script_Create_Tables.ipynb`.
 - Lembre-se de alterar o nome do arquivo e de colocar o caminho correto e as alterações do banco e outros!
4. O script irá:
 - Criar schemas, bancos e funções.
5. Abrir o aqruivo `etl/pagamento/Pagamento_Script_Dados.ipynb`.
6. O script irá:
 - Extrair os .zip.
 - Realizar um pré-processamento para remoção ou alteração de dados.
 - Inserir os dados no banco.

## Observações sobre as tabelas:

* Foi inserido o valor de -100 para valores nulos, pois os .csv já possuem os valores -1, -2, -3.
* O script:
    - Primeiro insere em uma tabela com todos os dados
    - Depois insere em tabelas menores (ação, programa...)
    - Depois ele insere em uma tabela pagamento com as foreign key para as outras tabelas menores
    - Depois usa o Truncate na tabela primeira tabela com todos os dados

## Script para a criação da tabela pagamentos_codigos
* Esta tabela foi criada para o autocomplete, com o objetivo de reduzir o tamanho da consulta e melhorar o tempo de processamento
* O script se encontra em `etl/pagamento/scritp_pagamento_codigos.sql`
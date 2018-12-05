# Documentação sobre a base de servidores

* Ao baixar uma base de dados dos servidores, ele irá conter os seguintes arquivos no formato csv: 
 * Afastamentos.csv
 * Cadastro.csv
 * Honorarios.csv
 * Observacoes.csv
 * Remuneracao.csv
* Os arquivos mais importantes para a base de servidores são o **Cadastro.csv** e o **Remuneracao.csv**, existe um notebook que faz uma conversão para a criação das tabelas

## Passos para a criação das tabelas cadastro e remuneração, alteração e inserção dos dados:

1. Baixe no portal da transparência (http://www.portaltransparencia.gov.br/download-de-dados/servidores) o arquivo representando a base de servidores de um mês de um ano.
2. É necessário utilizar da ambiente Jupyter Notebook.
3. Abrir o arquivo `etl/servidor/Servidor_Script_Create_Tables.ipynb` para a criação das tabelas (executar apenas 1x).
4. Abrir o arquivo `etl/servidor/Servidor_Script_Dados.ipynb` para a extração e inserção dos dados nas tabelas.
 - Lembre-se de alterar o nome do arquivo e de colocar o caminho correto e as alterações do banco e outros!
5. Os scripts irão:
 - Criar schemas, bancos e funções.
 - Extrair os .zip.
 - Realizar um pré-processamento para remoção ou alteração de dados e colunas.
 - Inserir os dados no banco.

## Passos para a inserção dos dados de maneira unificada na tabela fato_cargo_funcao_remuneracao (explicação das últimas etapas do script):

1. Para realizar a inserção dos dados da tabela "fato_cargo_funcao_remuneracao", é necessário as tabelas cadastro e remuneracao já estarem preenchidas.
2. O notebook vai fazer essa inserção dos dados com o atributo tipo, que pode ser 'F' de Função, 'C' de Cargo, 'N' de Nenhum.

## Observações sobre as tabelas:

* Um servidor pode ter apenas função ou cargo, ou nenhum dos dois.
* O atributo **Atividade** em Cadastro é tratado como sendo **Função**.
* Os atributos para identificar se aquela tupla é relacionada com Função são, codigo_atividade e atividade, ambos não podem ser null
* O atributo para identificar se aquela tupla é relacionada com Cargo é descricao_cargo, ele não pode ser null.
* A consulta abaixo mostra que é possível existir uma pessoa que não tenha função ou cargo
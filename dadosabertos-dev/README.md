# Dados abertos
Projeto de consultas e gráficos sobre Dados Abertos sobre Pagamentos e Remunerações do Poder Executivo Federal.

## Componentes
- server: camada da API Rest desenvolvida em Sprint Boot
- webapp: camada do single-page application desenvolvida em HTML / CSS / JavaScript

## Objetivos
Responder algumas questões referentes a base de dados do Portal da transparência, como:
- U_ORG, ORG, ORG_SUP pode estar em uma tabela ORG?
- Pode existir algum servidor COM cargo e SEM VÍNCULO?
- Quando COD_UORG_LOTACAO é nulo? 
- Pessoas sem Cargo, sem Função e sem Vínculo.
- Pessoas sem Cargo, sem Função e com Vínculo.
- Pessoas sem Cargo, sem Função e sem Vínculo recebem remuneração ?.
- Relação de Função e Cargo
- Verificar se ID_SERVIDOR_PORTAL é mantido para o mesmo servidor ao longo dos meses

## Fluxo de Desenvolvimento

O modelo de desenvolvimento é baseado no artigo **[a successful Git branching model](http://nvie.com/posts/a-successful-git-branching-model)** do Vincent Driessen.

O repositório central possui duas **branchs** principais com vida útil infinita:

- **master**
- **dev**

Consideramos **origin/master** como a **branchs** principal em que o código-fonte da HEAD sempre reflete um estado *production-ready*.

Consideramos **origin/dev** como a **branchs** principal em que o código-fonte da HEAD sempre reflete um estado com as últimas alterações de desenvolvimento entregues para a próxima versão.

Quando o código-fonte da **branchs** de desenvolvimento atinge um ponto estável e está pronto para ser liberado, todas as alterações devem sofrer o *merge* de volta a **master** e, em seguida, marcadas com um número de release. Portanto, cada vez que as alterações são mergeadas de volta a **master**, isso é uma nova liberação de produção por definição.

A gestão do projeto será realizada pelo *[board de issues](http://git.quixada.ufc.br/npi/dadosabertos/boards)* do próprio repositório. As atividades serão especificadas por *issues* e cada *issue* deverá gerar uma **feature branch**.

As **feature branch** devem ser utilizadas para desenvolver novas funcionalidades. A essência de uma branch desse tipo é que ela existe enquanto a funcionalidade estiver em desenvolvimento, mas acabará sendo mergeada de volta para a **dev**. As **feature branch** normalmente existem apenas nos repositórios locais dos desenvolvedores, não no **origin**.

#### Criando uma *feature branch*

Ao iniciar o desenvolvimento de uma nova funcionalidade, ramifique-a a partir da branch **dev**.

```
# Acessa a dev
$ git checkout dev

# Puxa as atualizaçãoes
$ git pull origin dev

# Cria a branch de desenvolvimento
$ git checkout -b [issue-##] dev
```

**Obs.:** o nome da nova branch deverá seguir o padrão "issue-" + código da issue especificada no **[board](http://git.quixada.ufc.br/npi/dadosabertos/boards)** do projeto e atrelada ao desenvolvedor.

#### Padrão dos *commits*

Os *commits* devem ser o mais atômico (não acumular muitas alterações em um mesmo commit) possível e sempre [referenciar](https://about.gitlab.com/2016/03/08/gitlab-tutorial-its-all-connected/) a **issue** específica. É possível realizar [atualizações](https://docs.gitlab.com/ee/user/project/issues/automatic_issue_closing.html) na **issue** a partir das mensagens declaradas nos *commits*, mas isso não é obrigado, vai de cada desenvolvedor. O mínimo obrigatório é sempre referenciar a **issue** conforme o padrão a seguir:

```
$ git commit -m "[#123] Mensagem clara e direta do que se trata o commit"
```

Note que a referencia é realizada dentro dos colchetes através da **#** + o código da **issue**.

#### Incorporando uma funcionalidade finalizada na *dev branch*

Primeiro passo é garantir que a sua branch de desenvolvimento esteja alinhada com a **dev**.

```
# Acessa a dev e puxa todas as atualizações existentes
$ git checkout dev
$ git pull origin dev


# Acessa a branch de desenvolvimento, realiza o merge das atualizações da dev e trata possíveis conflitos
$ git checkout [issue-##]

$ git merge --no-ff dev
```

O passo final **(somente para Admin/Master do Repositório)** é enviar as atualizações para a **dev** e depois para o **origin**.

```
# Volta para a dev e envia a nova funcionalidade para ela 
$ git checkout dev
$ git merge [issue-##]

# Deleta a branch de desenvolvimento e envia as atualizações para o repositório remoto
$ git branch -d [issue-##]
$ git push origin dev
```

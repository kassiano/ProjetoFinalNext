# App - Meus investimentos

Projeto final do Movile Next segunda edição sob a supervisão do Prof. Paulo Salvatore.

O App criado se chama "Meus investimentos" e é um App para controle de carteira de investimentos. Através do App o usuário consegue cadastrar um ativo que esteja em sua carteira e cada ativo está dentro de uma Categoria e Produto - As categorias são Renda Fixa, Renda Variável, fundos de investimentos e COE e os produtos são CDBs, LCI, LCa, Debentures, Ações, Opções, fundos multimercados etc.


## Tecnologias utilizadas

Para o desenvolvimento desse projeto foi utilizados tecnologias vistas durante o curso. 


### Arquitetura MVVM e Clean Architecture

Segui uma arquitetura baseada em Clean Architecture visando um desacoplamento de camadas. Dividi da seguinte maneira: 

* "data": Essa Camada é responsavel por prover dados. Aqui tem os DAOs, assim como as classes de entidades do banco e também os acesso a APIs externas.

* "repository": Camada para abstração de dados, para quem acessa o reposiório não importa se a informação está vindo de uma banco de dados Sqlite ou de uma Api da internet.

* "dashboard": Camada com a lógica de negócio e de apresentação do App. Aqui você encontra as Activities, os ViewModels bem como as classes de dominio da aplicação.


Obs: Observe que nessa arquitetura a camada de apresentação não conhece as classes anotadas como `@Entity` por isso a criação das classes que eu chamei de `Summary`. No meu entendimento, se eu levo um classe `Entity` para a camada de apresentação eu estou quebrando o Clean Architecture porque eu estaria prendendo a aplicação ao Room. 

### Bando de dados - Room 

Para estruturação do banco de dados foi utilizado a lib Room, explorei um pouco mais a parte de relacionamentos de tabelas: Neste projeto existem 3 tabelas principais de banco de dados: `Category`, `Product` e `Asset`. Assim, cada ativo (`Asset`) que o usuário cadastra faz parte de um produto (`Product`) e cada produto faz parte de uma categoria (`Category`).


### RxJava 

Utilizei RXJava para todas as interações entre a camada de ViewModel e Repository.


### Dependency Injection - Dagger 2

Utilizei o Dagger 2 para injeção de dependencia nos ViewModels e também nos repositorios.


### ViewModel e LiveData

Utilizei um ViewModel para cada Activity do projeto utilizei LiveDatas para receber os valores e atualizar a UI.


### Retrofit 

Implementei o Retrofit para acesso a uma API externa para buscar preços de ações, implementei seu repositório, mas não está em uso no APP. O App trabalha hje 100% OffLine. 


### DataBinding

Utilizei o dataBinding na tela de Atualizar um Ativo. Para atualizar um item você precisa de um longclick no item.
    

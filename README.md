# API de Gerenciamento de Pessoas

Esta API permite o gerenciamento de pessoas, incluindo a criação, edição, consulta e listagem de pessoas, bem como o gerenciamento de endereços para essas pessoas.

## Funcionalidades
  * Criar uma pessoa
  * Editar uma pessoa
  * Consultar uma pessoa 
  * Listar pessoas
  * Criar endereço para pessoa
  * Listar endereços da pessoa
  * Informar qual endereço é o principal da pessoa

## Tecnologias Utilizadas
 * Java
 * Spring Boot
 * Spring Data JPA
 * JUnit
 * H2 (como banco de dados)
 * Maven (gerenciamento de dependências)

## Uso
  Essa api funciona localmente na porta 8080, então você deve compilá-la em sua máquina e fazer as requisições desejadas para <http://localhost:8080/>"endpoint desejado". 
 <p> Estou disponibilizando uma collection de testes no postman com todos os endpoints e os respectivos bodys, mas sinta-se livre para fazer mais testes!
  
  postman test collection: https://elements.getpostman.com/redirect?entityId=25774227-2338f783-be10-448b-b39a-e169abcc849b&entityType=collection
  
## Endpoints
  
  ### POST:
  * criar pessoa: http://localhost:8080/create/person
  
  Recebe um body tipo json com os campos: nome (name), data de nascimento (date) e uma lista de endereços (address). Os endereços precisam conter: rua (street), número (number), cidada (city) e cep (postalCode).
  <p> Caso alguma dessas informações não seja passada, retornará uma mensagem de erro com status code 400 (bad request) informando qual campo está faltando.
  <p> Se o body passado for correto, criará um registro no banco de dados e retornará status code 200.
  
  ### GET
  
  * listar todas as pessoas: http://localhost:8080/list/person/all
  
  Não precisa de body ou parâmetro. Retornará a lista de pessoas que foram criadas. Caso nenhuma pessoa tenha sido criada, retornará uma lista vazia.
  
  * listar pessoa por id: http://localhost:8080/list/person/"id"
  
  É necessário informar o id da pessoa que deseja consultar. Caso esse id não corresponda a nenhum id presente no banco de dados, será retornado uma mensagem de erro com status code 404 (not found) informando que nenhuma pessoa foi encontrada para este id. Caso contrário será retornado todas as informações dessa pessoa.
  
  * listar todos os endereço de uma pessoa: http://localhost:8080/list/address/"id" 
  
  É necessário informar o id da pessoa que deseja consultar os endereços. Caso esse id não corresponda a nenhum id presente no banco de dados, será retornado uma mensagem de erro com status code 404 (not found) informando que nenhuma pessoa foi encontrada para este id. Caso contrário será retornado todos os endereços cadastrados dessa pessoa.
   
   ### PUT
   
   * adiciona um endereço para uma pessoa: http://localhost:8080/create/address/"id"
   
   É necessário informar o id da pessoa que deseja cadastrar o novo endereço. Caso esse id não corresponda a nenhum id presente no banco de dados, será retornado uma mensagem de erro com status code 404 (not found) informando que nenhuma pessoa foi encontrada para este id. 
   <p> Além disso, recebe um body tipo json com os campos: rua (street), número (number), cidada (city) e cep (postalCode).
   <p> Caso alguma dessas informações não seja passada, retornará uma mensagem de erro com status code 400 (bad request) informando qual campo está faltando.
   <p> Se o body passado for correto, criará um registro no banco de dados e retornará status code 200.
  
  * atualizar informações de uma pessoa: http://localhost:8080/update/person/"id"
  
  É necessário informar o id da pessoa que deseja atualizar. Caso esse id não corresponda a nenhum id presente no banco de dados, será retornado uma mensagem de erro com status code 404 (not found) informando que nenhuma pessoa foi encontrada para este id. 
   <p> Além disso, recebe um body tipo json exatamente igual ao do método post. 
   <p> Caso alguma dessas informações não seja passada, retornará uma mensagem de erro com status code 400 (bad request) informando qual campo está faltando.
   <p> Se o body passado for correto, fará a atualização do registro no banco de dados e retornará status code 200.
  
  * informar o endereço principal: http://localhost:8080/update/person/"id da pessoa"/main-address/"id do endereço"
  
   É necessário informar o id da pessoa que deseja atualizar o endereço principal. Caso esse id não corresponda a nenhum id presente no banco de dados, será retornado uma mensagem de erro com status code 404 (not found) informando que nenhuma pessoa foi encontrada para este id. 
  <p> Além disso, deve-se informar o id do endereço cadastrado dessa pessoa que deseja tornar principal. Se esse id não corresponder a nenhum id dos endereços cadastrados para essa pessoa, será retornado uma mensagem de erro com status code 404 (not found) informando que nenhuma endereço foi encontrada para este id.
  Se os ids forem corretos, fará a atualização do registro no banco de dados e retornará status code 200.
  
## Testes

Foram realizados testes unitários para os adapters utilizando o jUnit e testes dos endpoints utilizando o postman.
  
  
  
  
  

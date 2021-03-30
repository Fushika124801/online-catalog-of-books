Run project:

#Docker

if postgres doesn't exist on PC, please download it  
docker pull postgres

run image with parameters   
docker run --name postgres -e POSTGRES_PASSWORD=sa -e POSTGRES_USER=sa -p 5432:5432 -d postgres

it will be on default 5432 port

#Online catalog of books project

in online.catalog.books:    
use command mvn spring-boot:run

java project on port 8081

in online.catalog.books/frontend:   
use command npm install  
after installing    
use command npm start   
first page localhost:3000/login

#users:

    username:super password:1111

    username:puper password:1111
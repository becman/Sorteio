# Sistema web para realização de sorteio

Esse projeto foi criado utilizando Spring Boot, nele ja tem o apache TomCat embutido.

## Sitema Sorteio

# Pré Requisitos
Para que seja possível rodar essa aplicação é necessário atender alguns requisitos básicos.
  
- Java 11
- Maven 3.3

# Compilando e inicializando
Assim como todo projeto *Maven*, é necessário primeiramente realizarmos a geração dos fontes. Conforme o exemplo abaixo:
  
Abra o cmd na raiz do projeto e execute o comando abaixo.
```bash
mvn clean install 
```

**Se estiver usando o `Intellij`, no canto superior Direito tem um `M` clique nele e clique no botão de atualização.**
Para validar se a aplicação inicializou com sucesso é necessario chamar o endpoint do *actuator* através do *link* abaixo:

```
http://localhost:8080/
```


# Base de dados
Antes de subir o projeto é importante ter o mysql instalado na maquina, o banco é criado automaticamente para fins de teste.


# Inclusão do Administrador 

```
INSERT INTO `dbtestenetbr`.`usuario` (`ativo`, `codigo_verificador`, `email`, `senha`) VALUES ('1', '', 'admin@sorteio.netbr.com', '$2a$12$ISYE/ds5M6nQYz4TNLbCUe/Qb23XFIi/X5n9gkYOzE6INOQNjsOim');
INSERT INTO `dbtestenetbr`.`perfil` (`descricao`) VALUES ('ADMIN');
INSERT INTO `dbtestenetbr`.`usuarios_tem_perfis` (`usuario_id`, `perfil_id`) values (1, 1);
```

# Primeiro acesso 
Utilizar o usuário cadastrado nos scripts acima com a senha root
```
Usuario: admin@sorteio.netbr.com
Senha: root
```


# Referências

## Arquitetural


## Padrões


## Capacidades adicionais


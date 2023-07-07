# O que é

Apenas um teste para uma aplicação em *QUARKUS*

# Como buildar
É buildado simplesmente rodando o comando:

        ./mvnw clean package

Como o application.properties já possui as anotações:

        quarkus.container-image.build=true
        quarkus.container-image.group=touch
        quarkus.container-image.name=projeto-quarkus
        quarkus.container-image.tag=latest

Automaticamente já geraremos a imagem docker da aplicação apenas com o comando de build

A imagem gerada será:

        touch/projeto-quarkus:latest

# Como rodar a aplicação:
Na mesma pasta em que se encontrar o arquivo docker-compose rodar o camando:

        docker compose -f docker-compose.yml up -d

E pronto nosso sistema estará de pé.

# Versão do JAVA
O quarkus está rodando atualmente no máximo com a versão 17 do java.

# Keycloak
Rodar o seguinte comando:

        docker run --name keycloak -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin -p 8180:8080 quay.io/keycloak/keycloak:17.0.0 start-dev

# Adicionar o tema customizado ao keycloak
Com o terminal em recursos-externos/keycloak/themes, digite o comando:

        docker cp quarkus/. {containerIdKeycloak}:/opt/jboss/keycloak/themes/quarkus
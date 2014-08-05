PlaceChat
=========

Como instalar
====

O projeto foi desenvolvido na IDE IntelliJ Ultimate. Para executar, deve-se ter uma instalação do Tomcat 8.0+

Inicialmente o projeto executa sem persistência, mas ela pode ser facilmente habilitada no arquivo [repo.properties](https://github.com/fmca/PlaceChat/blob/master/web/WEB-INF/classes/repo.properties).

Caso deseje trocar para persistência Hibernate, deve-se executar o script [mysql](https://github.com/fmca/PlaceChat/blob/master/src/conf/mysql_script.sql).

O servidor MySQL deve executar na porta 3306


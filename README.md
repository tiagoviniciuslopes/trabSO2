# Simulador

## Alunos: 
    - Tiago vinicius Lopes Pereira
    - Victor Hugo Cardoso
    - Natália Teixeira

## Referencias:
    Sites:
        - https://www.devmedia.com.br/java-threads-utilizando-wait-notify-e-notifyall/29545

## Sistema Operacional: Windows 10

## Java version "1.8.0_181"
### Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
### Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)
### Javac 1.8.0_161

## Instrucoes:
###    É necessário criar os objetos dos componentes que se deseja simular:
        1: Criar objeto da classe Componente, passando como parametro para o construtor o nome do componente (processador, disco ou teclado)
        2: Caso o objeto seja do tipo "disco", é necessário criar um objeto processador e atribuir esse objeto a disco.processador
        3: É necessário utilizar o metodo start() dos objetos de componentes criados.
        4: O processador executa a cada 1 centésimo de segundo, o teclado executa sempre que uma tecla é pressionada, o disco pode executar a cada 10 centésimos de segundo porém só executa quando é chamado pelo processador
        5: O processador le no máximo 100 dados da memória
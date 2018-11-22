import java.util.Scanner;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

// Esta classe contem os comportamentos de disco, teclado e processador, o modo assumido deve ser especificado no construtor da classe
public class Componente extends Thread
{
    String nome; // diz se eh processador disco ou teclado
    String mensagem; // armazena a mensagem que vai ser impressa, a mensagem muda com base no nome
    int intervalo; // intervalo de tempo em centesimos de segundos que o componente fica parado apos executar uma acao
    String[] dados = new String[100]; // vetor de dados que o processador acessa
    Componente processador; // componente utilizado pelo disco para receber sinais de execucao

    //construtor da classe, aqui passamos o nome do componente e com base nisso sao adicionados mensagem e intervalo
    //caso for um processador o vetor de dados eh inicializado tambem
    public Componente(String nome) throws Exception 
    {
        this.nome = nome;

        if(this.nome.equals("processador"))
        {
            this.mensagem = "executei uma instrucao";
            this.intervalo = 1;

           for(int i = 0; i<100 ; ++i) // inicializando vetor de dados
           {
               dados[i] = "dado " + Integer.toString(i);
           }
        }
        else if(this.nome.equals("teclado"))
        {
            this.mensagem = "usuario digitou algo";
            this.intervalo = 0;
        }
        else
        {
            this.mensagem = "dei uma volta";
            this.intervalo = 10;
        }

    }

    //metodo que vai executar quando iniciarmos a thread
    public void run() 
    { 

        if(this.nome.equals("disco")) // caso for um disco eh feito o link com o processador que vai sinalizar quando o disco deve executar 
        {
            synchronized(processador) // o processador deve estar settado no disco
            {
                try
                {
                    processador.wait(); // o disco aguarda o processador enviar um notify para continuar a execucao
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        Scanner scanner = new Scanner(System.in);
        int contador = 0;
        while(true)
        {
            try 
            { 
                if(this.nome.equals("processador")) // o processador executa a acao comum e le um dado da memoria
                {
                    acaoComum();

                    if(contador < 100)
                    {
                        System.out.println("dado lido da memoria: " + dados[contador]);
                        ++contador;

                        synchronized(this)
                        {
                            if( contador % 10 == 0) notifyAll(); // caso o dado na memoria for divisivel por 10 o processador nitifica o disco que passa a executar
                        }
                    }

                }
                else if(this.nome.equals("teclado")) // o teclado executa a acao comum quando uma tecla eh pressionada
                {
                    scanner.nextLine(); // metodo getchar() do java
                    acaoComum();
                }
                else // o disco executa a acao comum, grava um valor aleatorio em um arquivo txt e volta a modo de espera, aguardando o processador
                {
                    acaoComum();

                    Random rand = new Random();
                    int n = rand.nextInt(50) + 1;

                    BufferedWriter writer = new BufferedWriter( new FileWriter( "random.txt"));
                    writer.write(Integer.toString(n));
                    writer.close();
                    
                    synchronized(processador)
                    {
                        processador.wait(); // aqui o disco volta ao estado wait aguardando o processador
                    }

                }
            } 
            catch (Exception e) 
            { 
                System.out.println ("Exception is caught"); 
            } 
        }
    } 

    //acao que todos os componentes executam
    public void acaoComum() throws Exception 
    {
        System.out.println(""); 
        System.out.println(nome.toUpperCase()+ ": "); // imprime o nome do componente
        System.out.println (mensagem); // imprime a mensagem do componente
        Thread.sleep(intervalo*100); // aguarda o numero de centesimos segundos especificado em intervalo para executar novamente
    }

    
}
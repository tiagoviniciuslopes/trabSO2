import java.util.Scanner;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Componente extends Thread
{
    String nome;
    String mensagem;
    int intervalo;
    String[] dados = new String[100];

    public Componente(String nome)
    {
        this.nome = nome;

        if(this.nome.equals("processador"))
        {
            this.mensagem = "executei uma instrucao";
            this.intervalo = 1;

           for(int i = 0; i<100 ; ++i)
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

    public void run() 
    { 
        Scanner scanner = new Scanner(System.in);
        int contador = 0;
        while(true)
        {
            try 
            { 
                if(this.nome.equals("processador"))
                {
                    acaoComum();

                    if(contador >= 100) contador = 0;
                    System.out.println("dado lido da memoria: " + dados[contador]);
                    ++contador;
                }
                else if(this.nome.equals("teclado"))
                {
                    scanner.nextLine();
                    acaoComum();
                }
                else
                {
                    acaoComum();

                    Random rand = new Random();
                    int n = rand.nextInt(50) + 1;

                    BufferedWriter writer = new BufferedWriter( new FileWriter( "random.txt"));
                    writer.write(Integer.toString(n));
                    writer.close();
                }
            } 
            catch (Exception e) 
            { 
                System.out.println ("Exception is caught"); 
            } 
        }
    } 

    public void acaoComum() throws Exception
    {
        System.out.println("");
        System.out.println(nome.toUpperCase()+ ": ");
        System.out.println (mensagem);
        Thread.sleep(intervalo*100);
    }
}
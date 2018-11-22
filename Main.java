public class Main
{
    public static void main(String args[]) throws Exception
    {
        Componente teclado = new Componente("teclado");
        Componente processador = new Componente("processador");
        Componente disco = new Componente("disco");

        disco.processador = processador;

        teclado.start();
        disco.start();
        processador.start();
    }
}
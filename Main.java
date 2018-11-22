public class Main
{
    public static void main(String args[]) 
    {
        Componente teclado = new Componente("teclado");
        Componente processador = new Componente("processador");
        Componente disco = new Componente("disco");

        teclado.start();
        processador.start();
        disco.start();
    }
}
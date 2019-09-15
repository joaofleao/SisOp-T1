// import static java.lang.Thread.currentThread;
import java.util.concurrent.TimeUnit;



public class Canibal {
//passar canibais e porcoes por parametro
//Programa roda para sempre.
//Logica: Cozinheiro enche a travessa com M porcoes, enquanto isso os canibais esperam a comida ficar pronta (t1.join), apos isso os canibais comem,
//se nem todos estiverem satisfeitos uma outra Thread é criada para encher a travessa e alimentar o restante até zerar o numero de canibais.
    public static void main(String args[]) throws InterruptedException {
        while(true){
            int canibais = 30;
            Cozinheiro cozinheiro = new Cozinheiro(canibais, 3);
            Thread t1 = new Thread(cozinheiro, "T1");
            t1.start();

            try {
                t1.join();
            } catch(Exception ex) {
            }
            comer(cozinheiro);

            while(cozinheiro.getCanibais() != 0){
                try {
                    System.out.println("Canibais não alimentados: " + cozinheiro.getCanibais());
                    Thread t2 = new Thread(cozinheiro, "T2");
                    t2.start();
                    TimeUnit.MILLISECONDS.sleep(200);
                    comer(cozinheiro);
                } catch(Exception ex) {
                }
            }

        }
    }


    public static void comer(Cozinheiro c) throws InterruptedException{
        c.alimentarCanibais();
    }
}

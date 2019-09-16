import java.util.concurrent.*;
import java.util.Scanner;

public class Ex1 {
	static int porcoes;
	static volatile int porcao = 0;

	static class Canibal implements Runnable {
		Semaphore mutex;
		Semaphore cheia;
        Semaphore enchendo;
        Semaphore comida;
		private int tid;

		public Canibal(int id, Semaphore mtx, Semaphore ch, Semaphore en, Semaphore cm){
			this.tid = id;
			mutex = mtx;
			cheia = ch;
            enchendo = en;
            comida = cm;
		}

		public void run(){
			while (true) {
				try {
                    comida.acquire();
					enchendo.acquire();
					mutex.acquire();
					System.out.println("Canibal " + tid + " come a porção: " + porcao);
					porcao--;
					if (porcao == 0)
						cheia.release();
                    TimeUnit.MILLISECONDS.sleep(300);
					mutex.release();
				}catch (InterruptedException e) {
				}
			}
		}
	}

	static class Cozinheiro implements Runnable {
		Semaphore mutex;
		Semaphore cheia;
        Semaphore enchendo;
        Semaphore comida;
		private int tid;

		public Cozinheiro(int id, Semaphore mtx, Semaphore ch, Semaphore en, Semaphore cm){
			this.tid = id;
			mutex = mtx;
			cheia = ch;
            enchendo = en;
            comida = cm;
		}

		public void run(){
			while (true) {
				try {
					cheia.acquire();
					System.out.println("Cozinhando " + porcoes + " porções");
                    for (int i = 0; i < porcoes; i++){
                        enchendo.release();
                        porcao++;
                        System.out.println("Porção: " + porcao);
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                    for(int i = 0; i < porcoes; i++){
                        comida.release();
                    }
				}catch (InterruptedException e) {
				}
			}
		}
	}

	public static void main(String[] args) {
		
        //Controle binario.
        Semaphore mutex = new Semaphore(1);
		Semaphore cheia = new Semaphore(1);
        
        //Controle n.
        Semaphore enchendo = new Semaphore(0);
        Semaphore comida = new Semaphore(0);
		int i;
        Scanner In = new Scanner(System.in);



        System.out.println("Informe o número de canibais: ");
        int canibais = In.nextInt();
        System.out.println("Informe o número de porções: ");
        porcoes = In.nextInt();




		Thread p = new Thread(new Cozinheiro(0, mutex, cheia, enchendo,comida));
		p.start();
		for (i = 1; i <= canibais; i++){
			Thread t = new Thread(new Canibal(i, mutex, cheia, enchendo, comida));
			t.start();
		}
	}
}
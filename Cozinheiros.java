import java.io.*;

public class Cozinheiros extends Thread{    
    private int porcoes;
    public int travessa;    
    
    public Cozinheiros(int porcoes){
        this.porcoes = porcoes;
    }
    
    
    public void comer(){
        travessa--;
    }

    @Override
    public void run(){
        synchronized(this){
            System.out.println("Porcoes " + porcoes);
                for(int i=0; i<porcoes ; i++){
                    travessa++;
                }
            notify();
        }
    }
}
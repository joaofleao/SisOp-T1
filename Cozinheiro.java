import java.util.concurrent.TimeUnit;


public class Cozinheiro implements Runnable{
    private int travessa = 0;
    private int porcoes;
    private int canibais;

    
    public Cozinheiro(int canibais, int porcoes){
        this.porcoes = porcoes;
        this.canibais = canibais;
    }

    public void run() {
        while(travessa!=porcoes){
            travessa++;
            System.out.println("Quantidade de porcoes cozinhadas: " + travessa);
        }
        // notify();
        
    }
    
    public int getTravessa(){
        return travessa;
    }
    public int getPorcoes(){
        return porcoes;
    }
    
    public int getCanibais(){
        return canibais;
    }

    public void alimentarCanibais() throws InterruptedException{
        for(int i=porcoes;i!=0;i--){
            travessa--;
            canibais--;
            System.out.println("Quantidade de porcoes comidas: " + (travessa+1));
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }
}
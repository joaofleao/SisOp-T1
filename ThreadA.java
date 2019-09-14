import java.io.*;

public class ThreadA {


     public static void main(String[] args){
         Cozinheiros b = new Cozinheiros(2000);
    
        
        b.start();
        while(true){
            synchronized(b){
                try{
                    System.out.println("TRAVESSA " + b.travessa);
                    if(b.travessa==0){
                        System.out.println("Aguardando o b completar...");
                        b.wait();
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                
                b.comer();
                System.out.println("Total é igual a: " + b.travessa);
            }
        }
    }


}
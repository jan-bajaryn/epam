package by.epam.demo_threads.example06;

import java.util.concurrent.TimeUnit;

class CommonResource{

    int x;
    synchronized void increment(){
        x=1;
        for (int i = 1; i < 5; i++){
            System.out.printf("%s %d \n", Thread.currentThread().getName(), x);
            x++;
            try{
                TimeUnit.MILLISECONDS.sleep(100);
            }
            catch(InterruptedException e){}
        }
    }
}


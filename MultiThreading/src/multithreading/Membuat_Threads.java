/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multithreading;

/**
 *
 * @author kimi9
 */
public class Membuat_Threads {
    }  

// Class PrintNameThread yang memperluas Thread  
class PrintNameThread extends Thread {  
    // Konstruktor yang menerima nama dan memanggil super untuk set nama thread  
    PrintNameThread(String name) {  
        super(name);  
        // Memulai thread saat objek dibuat  
        start();  
    }  

    // Override method run() untuk menjalankan tugas thread  
    public void run() {  
        // Mendapatkan nama thread  
        String name = getName();  
        // Loop untuk mencetak nama thread sebanyak 100 kali  
        for (int i = 0; i < 100; i++) {  
            System.out.print(name);  
        }  
    }  
}  

// Class TestThread untuk menjalankan beberapa instance PrintNameThread  
class TestThread {  
    public static void main(String args[]) {  
        // Membuat beberapa instance PrintNameThread dengan nama berbeda  
        PrintNameThread pnt1 = new PrintNameThread("1");  
        PrintNameThread pnt2 = new PrintNameThread("2");  
        PrintNameThread pnt3 = new PrintNameThread("3");  
        PrintNameThread pnt4 = new PrintNameThread("4");  
    }  
}

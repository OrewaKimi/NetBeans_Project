y/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multithreading;

/**
 *
 * @author kimi9
 */
public class Sinkronisasi {
    } 
// Class TwoStrings untuk mencetak dua string 
class TwoStrings { 
// Method sinkronisasi untuk mencetak string agar thread tidak saling mengganggu 
static synchronized void print(String str1, String str2) { 
System.out.print(str1); 
try { 
// Tidur selama 500 milidetik untuk mensimulasikan jeda 
Thread.sleep(500); 
} catch (InterruptedException ie) { 
ie.printStackTrace(); 
} 
// Cetak string kedua setelah jeda 
System.out.println(str2); 
} 
} 
// Class PrintStringsThread yang mengimplementasikan Runnable 
class PrintStringsThread implements Runnable { 
Thread thread; 
String str1, str2; 
// Konstruktor untuk menerima string dan memulai thread 
PrintStringsThread(String str1, String str2) { 
this.str1 = str1; 
this.str2 = str2; 
thread = new Thread(this); 
thread.start(); 
} 
// Method run() yang memanggil TwoStrings.print() 
public void run() { 
TwoStrings.print(str1, str2); 
} 
} 
// Class TestThread untuk menjalankan beberapa instance PrintStringsThread 
class TestThread { 
public static void main(String[] args) { 
// Membuat beberapa instance PrintStringsThread dengan string berbeda 
new PrintStringsThread("Hello ", "there."); 
new PrintStringsThread("How are ", "you?"); 
new PrintStringsThread("Thank you ", "very much!"); 
}
}

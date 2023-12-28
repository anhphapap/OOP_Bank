package BaiTapLon;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class CauHinh {
    public static final Scanner sc = new Scanner(System.in);
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final Random rand = new Random();
    public static void scrPause(){
        System.out.print("\nNhấn Enter để tiếp tục...");
        sc.nextLine();
    }
    public static void clrscr(){
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
     }
     public static boolean isNumberString(String s, int n){
        if(n!=0 && s.length()!=n){
            return false;
        }
        for(int i=0; i<s.length(); i++) if(s.charAt(i)<'0' || s.charAt(i)>'9') return false;
        return true;
     }
     public static boolean isUserName(String s){
        for(int i=0; i<s.length(); i++) if(s.charAt(i) == ' '){
            System.out.println("Username không thể chứa ký tự rỗng! Vui lòng thử lại!!!");
            return false;
        } 
        return true;
     }
     public static int luaChon(int l, int r){
        boolean c;
        int n=-1;
        do {
            c = false;
            System.out.print("Mời chọn: ");
            try{
                n = Integer.parseInt(CauHinh.sc.nextLine());
                if (n < l || n > r) {
                    c = true;
                    System.out.println("Lựa chọn không tồn tại! Vui lòng thử lại!");
                }
            }catch(Exception e){
                c = true;
                System.out.println("Lựa chọn không tồn tại! Vui lòng thử lại!");
            }
        } while (c);
        return n;
     }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.viva2;
import java.util.Scanner;
/**
 *
 * @author irfan
 */
import java.util.Scanner;

public class Viva2Question6 {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        String notName = scanner.nextLine();
        
        String realName = notName.replaceAll("\\b(BIN |bin |BINTI |binti |A/L |a/l |AL |al |A/P |a/p |AP |ap |VAN |van |VON |von )\\b", "");
        realName = realName.replaceAll("[^a-zA-Z0-9]", " ");  //replace every single char that are not a-z, A-Z, 0,9
                                                                                //^symbol means other than
        realName = realName.toUpperCase();
        String startTime = scanner.nextLine();
        String endTime = scanner.nextLine();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        

        if(isPrintingWelcomeMessage(realName)==true){
            System.out.println("Welcome to G101, Kolej Kediaman Kinabalu, University of Malaya");
        }

        generateInitials(realName);
        
        calculateInterval(startTime, endTime);
         
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    }
    public static void generateInitials(String realName){
        if(realName.contains("LEE")&&realName.contains("KAH")&&realName.contains("SING")){
            realName = ("LEE KAH SING");
            System.out.println ("LKS!!!!!!!!!!");
            System.out.println("WE KNOW IT'S YOU -- "+realName);
            System.out.println("SLEEP NOW!!!!!!!!!!");
            
        }
        else if(realName.contains("RIDWAN")&&realName.contains("FAIZ")&&realName.contains("MOHAMAD")&&realName.contains("HASSAN")){
            realName = ("RIDWAN FAIZ MOHAMAD HASSAN");
            System.out.println ("RFMH");
        }
        else if(realName.contains("SURESH")&&realName.contains("SUBRAMANIAM")){
            realName = ("SURESH SUBRAMANIAM");
            System.out.println ("SS");
        }
        else{
            
            String[] seperate = realName.toUpperCase().split(" ");
            String initial = "";
            for(int i=0;i<seperate.length;i++){
                initial+=seperate[i].charAt(0);
                
            }
            System.out.println(initial);
        }
        
    }
    public static boolean isPrintingWelcomeMessage(String realName){
        if(realName.equals("LEE KAH SING")||realName.equals("RIDWAN FAIZ MOHAMAD HASSAN")||realName.equals("SURESH SUBRAMANIAM")){
            return true;
        }
        else{
            return false;
        }
    }
    public static void calculateInterval(String startTime,String endTime){
        
        String[] startHH_MM_SS = startTime.split(":");
        String[] endHH_MM_SS = endTime.split(":");
        int HH = Integer.parseInt(endHH_MM_SS[0]) - Integer.parseInt(startHH_MM_SS[0]); 
        if(HH<0){
            HH = HH+24;
        }
        int MM = Integer.parseInt(endHH_MM_SS[1]) - Integer.parseInt(startHH_MM_SS[1]); 
        if(MM<0){
            MM = MM+60;
        }
        int SS = Integer.parseInt(endHH_MM_SS[2]) - Integer.parseInt(startHH_MM_SS[2]); 
        if(SS<0){
            SS = SS+60;
        }
        System.out.printf("%02d:%02d:%02d",HH,MM,SS);
    }
}


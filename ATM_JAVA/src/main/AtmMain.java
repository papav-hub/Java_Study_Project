package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import bank.ATMachine;

public class AtmMain {

   public static void main(String[] args) {
      // 초기 설정 - 개설 가능한 최대 계좌수 (1000계좌), 
      // ATM 잔금 (50만원), 관리자 암호
      ATMachine atm = new ATMachine(1000, 500000, "admin");
      
      Scanner scan = new Scanner(System.in);
      while(true) {
         atm.displayMenu();
         System.out.print("메뉴를 선택하세요 >> ");
         try {
            int select = scan.nextInt();
            switch (select) {
               case 1:
                  atm.createAccount();
                  break;
               case 2:
                  atm.checkMoney();
                  break;
               case 3:
                  atm.depositMoney();
                  break;
               case 4:
                  atm.widrawMoney();
                  break;
               case 5:
                  atm.transfer();
                  break;
               case 6:
                  atm.requestSvc();
                  break;
               case 7:
                  atm.managerMode();
                  break;
               case 9:
                  System.out.println("안녕히 가세요!");
                  System.exit(0);
            }
         }catch (InputMismatchException e) {
            System.out.println("정확하게 입력하주세요. ");
            continue;
         }
      }

   }

}
package helpdesk;

import java.util.Scanner;

import bank.ATMachine;
import bank.Account;

public class CustomerSvc {
   
   private Account [] accountArray;
   private int nCurrentAccountNum;
   
   public CustomerSvc (Account [] acctArray, int nCurrentAcctNum) {   // 생성자
      accountArray = acctArray;
      nCurrentAccountNum = nCurrentAcctNum;
   }
   
   public void updatePasswdReq() {   // 비밀번호 변경

      Scanner scan = new Scanner(System.in);
      System.out.println("--------암호변경--------");      
      System.out.print("계좌 번호 입력: ");
      int NID = scan.nextInt();
      System.out.print("기존 비밀번호 입력: ");
      String STRPASSWORD = scan.next();
      System.out.print("신규 비밀번호 입력: ");
      String NEWSTRPASSWORD = scan.next();
      
      if(NID > nCurrentAccountNum + ATMachine.BASE_ACCOUNT_ID) { // 입력한 계좌번호가 현재 개설된 계좌번호보다 클 때
         System.out.println("존재하지 않는 계좌 번호 입니다.");
      }else {

         boolean check = accountArray[NID - ATMachine.BASE_ACCOUNT_ID].updatePasswd(STRPASSWORD, NEWSTRPASSWORD);//BASE_ACCOUNT_ID
            
         if(check) { // 비밀번호 변경 성공
            System.out.println("비밀번호가 정상적으로 변경되었습니다.");
         }else { // 기존 비밀번호가 틀리면 비밀번호 변경 실패
            System.out.println("비밀번호 변경이 실패하였습니다.");
         }
      
      }
   }
}
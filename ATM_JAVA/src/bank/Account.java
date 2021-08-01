package bank;

import bank.ATMachine;
import helpdesk.CustomerSvc;

public class Account {
   private int nID;   //계좌 번호
   private int nBalance;   //계좌 잔고
   private String strAccountName;   // 고객명
   private String strPassword;      // 계좌 비밀번호
   
   public Account(int id, int money, String name, String password) {   // 생성자   
      nID = id;
      nBalance = money;
      strAccountName = name;
      strPassword = password;
   }
   
   
   boolean authenticate(int id, String password) {   // 계정 확인
      boolean b = false;
      if ((password.equals(strPassword))) {
         b = true;
      }
      return b;
   }
   
   int deposit (int money) { // 입금
      nBalance += money;
      return nBalance;
   }
   
   public int widraw (int money) { // 출금
      nBalance -= money;
      return nBalance;
   }
   
   public boolean updatePasswd(String oldPasswd, String newPasswd) {   // 비밀번호 변경
      boolean b = false;
      if (strPassword.equals(oldPasswd)) {
         b = true;
         strPassword = newPasswd;
         if(oldPasswd.equals(newPasswd)) {
            b = false;
            System.out.println("기존 비밀번호와 같은 비밀번호 입니다.");
         }
      }else {
         System.out.println("계좌번호 혹은 기존 비밀번호가 잘못되었습니다.");
      }
      return b;
   }
   
   public String getAccountName() {   // 고객 명 읽기
      return strAccountName;
   }
   
   public int getnID() {
      return nID;
   }
   
   public int getnBalance() {
      return nBalance;
   }
}
package bank;

import java.util.Scanner;

import bank.Account;
import helpdesk.CustomerSvc;
import util.Statistics;

public class ATMachine {
   
   private Account [] accountArray;   // 고객 계좌 배열 참조 변수
   private int nMachineBalance;   // ATM 잔고
   private int nMaxAccountNum;      // 고객 계좌 참조 변수 배열 크기
   private int nCurrentAccountNum;   // 개설된 고객 계좌 수
   private String strManagerPassword;   // 관리자 비밀번호

   public static final int BASE_ACCOUNT_ID  = 100; // 고객 계좌 발급 시 시작 번호
   
   public ATMachine(int size, int balance, String password) { // 생성자
      nMaxAccountNum = size; // 개설 가능한 계좌 수
      accountArray = new Account [nMaxAccountNum]; 
      nMachineBalance = balance; // ATM 잔고
      nCurrentAccountNum = 0; // 개설된 고객 계좌 수
      strManagerPassword = password; // 관리자 비밀번호
   }
   
   public void createAccount() {   // 계좌 개설
      Scanner scan = new Scanner(System.in);
      System.out.println("---------개설---------");
      System.out.print("이름 입력: ");
      String NAME = scan.next();      
      System.out.print("암호 입력: ");
      String STRPASSWORD = scan.next();
      
      accountArray[nCurrentAccountNum] = new Account(BASE_ACCOUNT_ID + nCurrentAccountNum, 0, NAME, STRPASSWORD);
      int n = BASE_ACCOUNT_ID + nCurrentAccountNum;
      System.out.println(NAME + "님 " + n + "번 계좌번호가 정상적으로 개설되었읍니다. 감사합니다.");
      nCurrentAccountNum++;
   }
   

   public void checkMoney() {   // 계좌 조회
      Scanner scan = new Scanner(System.in);
      System.out.println("---------조회---------");
      System.out.print("계좌 번호 입력: ");
      int NID = scan.nextInt();
      System.out.print("비밀번호 입력: ");
      String STRPASSWORD = scan.next();      
            
      boolean check = accountArray[NID - BASE_ACCOUNT_ID].authenticate(NID, STRPASSWORD);      
      
      if(check) {
         System.out.println("계좌 잔액 : " + accountArray[NID - BASE_ACCOUNT_ID].getnBalance());
      }else {
         System.out.println("정보가 잘못되었습니다.");
      }
      
   }
   
   public void depositMoney() { // 계좌 입금
      Scanner scan = new Scanner(System.in);
      System.out.println("---------입금---------");
      System.out.print("계좌 번호 입력: ");
      int NID = scan.nextInt();
      System.out.print("비밀번호 입력: ");
      String STRPASSWORD = scan.next();
      
      boolean check = accountArray[NID - BASE_ACCOUNT_ID].authenticate(NID, STRPASSWORD);
      
      if(check) {
         System.out.print("입금 액 입력 : ");
         int PLUSMONEY = scan.nextInt();
         nMachineBalance += PLUSMONEY;
         int FINALMONEY = accountArray[NID - BASE_ACCOUNT_ID].deposit(PLUSMONEY);   
         System.out.println("입금 후 잔액 : " + FINALMONEY);
      }else {
         System.out.println("정보가 잘못되었습니다.");
      }
   }
   
   public void widrawMoney() {  // 계좌 출금
      Scanner scan = new Scanner(System.in);
      System.out.println("---------출금---------");
      System.out.print("계좌 번호 입력: ");
      int NID = scan.nextInt();
      System.out.print("비밀번호 입력: ");
      String STRPASSWORD = scan.next();
      
      boolean check = accountArray[NID - BASE_ACCOUNT_ID].authenticate(NID, STRPASSWORD);
      
      if(check) {
         System.out.print("출금 액 입력 : ");
         int MINUSMONEY = scan.nextInt();
         nMachineBalance -= MINUSMONEY;
         if (accountArray[NID - BASE_ACCOUNT_ID].getnBalance() >= MINUSMONEY) { // 이체 금액이 계좌 금액보다 클 경우
            int FINALMONEY = accountArray[NID - BASE_ACCOUNT_ID].widraw(MINUSMONEY);   
            System.out.println("출금 후 잔액 : " + FINALMONEY);
            
         }else {
            System.out.println("출금 금액이 계좌 잔액보다 큽니다.");
         }
         
      }else {
         System.out.println("정보가 잘못되었습니다.");
      }
   }
   
   public void transfer() {
      Scanner scan = new Scanner(System.in);
      System.out.println("---------이체---------");
      System.out.print("계좌 번호 입력: ");
      int NID = scan.nextInt();
      System.out.print("비밀번호 입력: ");
      String STRPASSWORD = scan.next();
      
      boolean check = accountArray[NID - BASE_ACCOUNT_ID].authenticate(NID, STRPASSWORD);
      
      if(check) { // 계좌 확인      
         System.out.print("이체계좌 입력: ");
         int TRANSNID = scan.nextInt();
         
         if(TRANSNID <= BASE_ACCOUNT_ID + nCurrentAccountNum) { // 이체 계좌가 존재하지 않을 경우      
            System.out.print("이체금액 입력: ");
            int TRANSMONEY = scan.nextInt();
            
            if (accountArray[NID - BASE_ACCOUNT_ID].getnBalance() >= TRANSMONEY) { // 이체 금액이 계좌 금액보다 클 경우
            
            
               int FINALMONEY = accountArray[NID - BASE_ACCOUNT_ID].widraw(TRANSMONEY); // 출금
               int TRANSFINALMONEY = accountArray[TRANSNID - BASE_ACCOUNT_ID].deposit(TRANSMONEY); // 입금
            
               System.out.println("현재 잔액 : " + FINALMONEY);
               System.out.println("계좌 이체를 완료하였습니다.");
               
            }else {
               System.out.println("이체 금액이 계좌 잔액보다 큽니다.");
            }
         
         }else {
            System.out.println("이체 계좌를 다시 확인하세요.");
         }
      
      }else {
         System.out.println("정보가 잘못되었습니다.");
      }
   }
   
   public void requestSvc() {   // 비밀번호 변경 요청   
      
      CustomerSvc svc = new CustomerSvc(accountArray, nCurrentAccountNum);
      svc.updatePasswdReq();
      
   }
   
   
   
   public void managerMode() {   // 고객 관리
      Scanner scan = new Scanner(System.in);
      System.out.println("--------고객관리--------");
      System.out.print("관리자 비밀번호 입력: ");
      String ADMINPASSWORD = scan.next();
      
      if(ADMINPASSWORD.equals(strManagerPassword)) {
         Statistics stat = new Statistics();
         System.out.println("ATM 현금 잔고: " + nMachineBalance);
         
         int ALLMONEY = stat.num(accountArray, nCurrentAccountNum); // 고객 잔고 총액
         System.out.println("고객 잔고 총액: " + ALLMONEY + "원 (" + nCurrentAccountNum + "명)");
         
         double AVERAGE = stat.average(accountArray, nCurrentAccountNum); // 고객 잔고 평균
         System.out.println("고객 잔고 평균: " + (int)AVERAGE + "원");
         
         int MAXMONEY = stat.max(accountArray, nCurrentAccountNum); // 고객 잔고 최고
         System.out.println("고객 잔고 최고: " + MAXMONEY + "원");
         
         System.out.println("고객 계좌 현황 (고객 잔고 내림 차순 정렬)");
         accountArray = stat.sort(accountArray, nCurrentAccountNum);
         for (int i=0 ; i<nCurrentAccountNum ; i++) {
            System.out.println(accountArray[i].getAccountName() + "\t" + accountArray[i].getnID() + "\t" + accountArray[i].getnBalance());
         }
   
      }else {
         System.out.println("관리자 비밀번호가 아닙니다.");
      }
   }
   
   public void displayMenu() { // 메인 메뉴 표시
      System.out.println("--------------------");
      System.out.println("-     KPU bank     -");
      System.out.println("--------------------");
      System.out.println("1. 계좌 개설");
      System.out.println("2. 계좌 조회");
      System.out.println("3. 계좌 입금");
      System.out.println("4. 계좌 출금");
      System.out.println("5. 계좌 이체");
      System.out.println("6. 고객 센터");
      System.out.println("7. 고객 관리");
      System.out.println("9. 업무 종료");
   }

}
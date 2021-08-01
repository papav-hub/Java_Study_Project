package bank;

import java.util.Scanner;

import bank.Account;
import helpdesk.CustomerSvc;
import util.Statistics;

public class ATMachine {
   
   private Account [] accountArray;   // �� ���� �迭 ���� ����
   private int nMachineBalance;   // ATM �ܰ�
   private int nMaxAccountNum;      // �� ���� ���� ���� �迭 ũ��
   private int nCurrentAccountNum;   // ������ �� ���� ��
   private String strManagerPassword;   // ������ ��й�ȣ

   public static final int BASE_ACCOUNT_ID  = 100; // �� ���� �߱� �� ���� ��ȣ
   
   public ATMachine(int size, int balance, String password) { // ������
      nMaxAccountNum = size; // ���� ������ ���� ��
      accountArray = new Account [nMaxAccountNum]; 
      nMachineBalance = balance; // ATM �ܰ�
      nCurrentAccountNum = 0; // ������ �� ���� ��
      strManagerPassword = password; // ������ ��й�ȣ
   }
   
   public void createAccount() {   // ���� ����
      Scanner scan = new Scanner(System.in);
      System.out.println("---------����---------");
      System.out.print("�̸� �Է�: ");
      String NAME = scan.next();      
      System.out.print("��ȣ �Է�: ");
      String STRPASSWORD = scan.next();
      
      accountArray[nCurrentAccountNum] = new Account(BASE_ACCOUNT_ID + nCurrentAccountNum, 0, NAME, STRPASSWORD);
      int n = BASE_ACCOUNT_ID + nCurrentAccountNum;
      System.out.println(NAME + "�� " + n + "�� ���¹�ȣ�� ���������� �����Ǿ����ϴ�. �����մϴ�.");
      nCurrentAccountNum++;
   }
   

   public void checkMoney() {   // ���� ��ȸ
      Scanner scan = new Scanner(System.in);
      System.out.println("---------��ȸ---------");
      System.out.print("���� ��ȣ �Է�: ");
      int NID = scan.nextInt();
      System.out.print("��й�ȣ �Է�: ");
      String STRPASSWORD = scan.next();      
            
      boolean check = accountArray[NID - BASE_ACCOUNT_ID].authenticate(NID, STRPASSWORD);      
      
      if(check) {
         System.out.println("���� �ܾ� : " + accountArray[NID - BASE_ACCOUNT_ID].getnBalance());
      }else {
         System.out.println("������ �߸��Ǿ����ϴ�.");
      }
      
   }
   
   public void depositMoney() { // ���� �Ա�
      Scanner scan = new Scanner(System.in);
      System.out.println("---------�Ա�---------");
      System.out.print("���� ��ȣ �Է�: ");
      int NID = scan.nextInt();
      System.out.print("��й�ȣ �Է�: ");
      String STRPASSWORD = scan.next();
      
      boolean check = accountArray[NID - BASE_ACCOUNT_ID].authenticate(NID, STRPASSWORD);
      
      if(check) {
         System.out.print("�Ա� �� �Է� : ");
         int PLUSMONEY = scan.nextInt();
         nMachineBalance += PLUSMONEY;
         int FINALMONEY = accountArray[NID - BASE_ACCOUNT_ID].deposit(PLUSMONEY);   
         System.out.println("�Ա� �� �ܾ� : " + FINALMONEY);
      }else {
         System.out.println("������ �߸��Ǿ����ϴ�.");
      }
   }
   
   public void widrawMoney() {  // ���� ���
      Scanner scan = new Scanner(System.in);
      System.out.println("---------���---------");
      System.out.print("���� ��ȣ �Է�: ");
      int NID = scan.nextInt();
      System.out.print("��й�ȣ �Է�: ");
      String STRPASSWORD = scan.next();
      
      boolean check = accountArray[NID - BASE_ACCOUNT_ID].authenticate(NID, STRPASSWORD);
      
      if(check) {
         System.out.print("��� �� �Է� : ");
         int MINUSMONEY = scan.nextInt();
         nMachineBalance -= MINUSMONEY;
         if (accountArray[NID - BASE_ACCOUNT_ID].getnBalance() >= MINUSMONEY) { // ��ü �ݾ��� ���� �ݾ׺��� Ŭ ���
            int FINALMONEY = accountArray[NID - BASE_ACCOUNT_ID].widraw(MINUSMONEY);   
            System.out.println("��� �� �ܾ� : " + FINALMONEY);
            
         }else {
            System.out.println("��� �ݾ��� ���� �ܾ׺��� Ů�ϴ�.");
         }
         
      }else {
         System.out.println("������ �߸��Ǿ����ϴ�.");
      }
   }
   
   public void transfer() {
      Scanner scan = new Scanner(System.in);
      System.out.println("---------��ü---------");
      System.out.print("���� ��ȣ �Է�: ");
      int NID = scan.nextInt();
      System.out.print("��й�ȣ �Է�: ");
      String STRPASSWORD = scan.next();
      
      boolean check = accountArray[NID - BASE_ACCOUNT_ID].authenticate(NID, STRPASSWORD);
      
      if(check) { // ���� Ȯ��      
         System.out.print("��ü���� �Է�: ");
         int TRANSNID = scan.nextInt();
         
         if(TRANSNID <= BASE_ACCOUNT_ID + nCurrentAccountNum) { // ��ü ���°� �������� ���� ���      
            System.out.print("��ü�ݾ� �Է�: ");
            int TRANSMONEY = scan.nextInt();
            
            if (accountArray[NID - BASE_ACCOUNT_ID].getnBalance() >= TRANSMONEY) { // ��ü �ݾ��� ���� �ݾ׺��� Ŭ ���
            
            
               int FINALMONEY = accountArray[NID - BASE_ACCOUNT_ID].widraw(TRANSMONEY); // ���
               int TRANSFINALMONEY = accountArray[TRANSNID - BASE_ACCOUNT_ID].deposit(TRANSMONEY); // �Ա�
            
               System.out.println("���� �ܾ� : " + FINALMONEY);
               System.out.println("���� ��ü�� �Ϸ��Ͽ����ϴ�.");
               
            }else {
               System.out.println("��ü �ݾ��� ���� �ܾ׺��� Ů�ϴ�.");
            }
         
         }else {
            System.out.println("��ü ���¸� �ٽ� Ȯ���ϼ���.");
         }
      
      }else {
         System.out.println("������ �߸��Ǿ����ϴ�.");
      }
   }
   
   public void requestSvc() {   // ��й�ȣ ���� ��û   
      
      CustomerSvc svc = new CustomerSvc(accountArray, nCurrentAccountNum);
      svc.updatePasswdReq();
      
   }
   
   
   
   public void managerMode() {   // �� ����
      Scanner scan = new Scanner(System.in);
      System.out.println("--------������--------");
      System.out.print("������ ��й�ȣ �Է�: ");
      String ADMINPASSWORD = scan.next();
      
      if(ADMINPASSWORD.equals(strManagerPassword)) {
         Statistics stat = new Statistics();
         System.out.println("ATM ���� �ܰ�: " + nMachineBalance);
         
         int ALLMONEY = stat.num(accountArray, nCurrentAccountNum); // �� �ܰ� �Ѿ�
         System.out.println("�� �ܰ� �Ѿ�: " + ALLMONEY + "�� (" + nCurrentAccountNum + "��)");
         
         double AVERAGE = stat.average(accountArray, nCurrentAccountNum); // �� �ܰ� ���
         System.out.println("�� �ܰ� ���: " + (int)AVERAGE + "��");
         
         int MAXMONEY = stat.max(accountArray, nCurrentAccountNum); // �� �ܰ� �ְ�
         System.out.println("�� �ܰ� �ְ�: " + MAXMONEY + "��");
         
         System.out.println("�� ���� ��Ȳ (�� �ܰ� ���� ���� ����)");
         accountArray = stat.sort(accountArray, nCurrentAccountNum);
         for (int i=0 ; i<nCurrentAccountNum ; i++) {
            System.out.println(accountArray[i].getAccountName() + "\t" + accountArray[i].getnID() + "\t" + accountArray[i].getnBalance());
         }
   
      }else {
         System.out.println("������ ��й�ȣ�� �ƴմϴ�.");
      }
   }
   
   public void displayMenu() { // ���� �޴� ǥ��
      System.out.println("--------------------");
      System.out.println("-     KPU bank     -");
      System.out.println("--------------------");
      System.out.println("1. ���� ����");
      System.out.println("2. ���� ��ȸ");
      System.out.println("3. ���� �Ա�");
      System.out.println("4. ���� ���");
      System.out.println("5. ���� ��ü");
      System.out.println("6. �� ����");
      System.out.println("7. �� ����");
      System.out.println("9. ���� ����");
   }

}
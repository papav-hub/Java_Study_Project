package helpdesk;

import java.util.Scanner;

import bank.ATMachine;
import bank.Account;

public class CustomerSvc {
   
   private Account [] accountArray;
   private int nCurrentAccountNum;
   
   public CustomerSvc (Account [] acctArray, int nCurrentAcctNum) {   // ������
      accountArray = acctArray;
      nCurrentAccountNum = nCurrentAcctNum;
   }
   
   public void updatePasswdReq() {   // ��й�ȣ ����

      Scanner scan = new Scanner(System.in);
      System.out.println("--------��ȣ����--------");      
      System.out.print("���� ��ȣ �Է�: ");
      int NID = scan.nextInt();
      System.out.print("���� ��й�ȣ �Է�: ");
      String STRPASSWORD = scan.next();
      System.out.print("�ű� ��й�ȣ �Է�: ");
      String NEWSTRPASSWORD = scan.next();
      
      if(NID > nCurrentAccountNum + ATMachine.BASE_ACCOUNT_ID) { // �Է��� ���¹�ȣ�� ���� ������ ���¹�ȣ���� Ŭ ��
         System.out.println("�������� �ʴ� ���� ��ȣ �Դϴ�.");
      }else {

         boolean check = accountArray[NID - ATMachine.BASE_ACCOUNT_ID].updatePasswd(STRPASSWORD, NEWSTRPASSWORD);//BASE_ACCOUNT_ID
            
         if(check) { // ��й�ȣ ���� ����
            System.out.println("��й�ȣ�� ���������� ����Ǿ����ϴ�.");
         }else { // ���� ��й�ȣ�� Ʋ���� ��й�ȣ ���� ����
            System.out.println("��й�ȣ ������ �����Ͽ����ϴ�.");
         }
      
      }
   }
}
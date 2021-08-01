package bank;

import bank.ATMachine;
import helpdesk.CustomerSvc;

public class Account {
   private int nID;   //���� ��ȣ
   private int nBalance;   //���� �ܰ�
   private String strAccountName;   // ����
   private String strPassword;      // ���� ��й�ȣ
   
   public Account(int id, int money, String name, String password) {   // ������   
      nID = id;
      nBalance = money;
      strAccountName = name;
      strPassword = password;
   }
   
   
   boolean authenticate(int id, String password) {   // ���� Ȯ��
      boolean b = false;
      if ((password.equals(strPassword))) {
         b = true;
      }
      return b;
   }
   
   int deposit (int money) { // �Ա�
      nBalance += money;
      return nBalance;
   }
   
   public int widraw (int money) { // ���
      nBalance -= money;
      return nBalance;
   }
   
   public boolean updatePasswd(String oldPasswd, String newPasswd) {   // ��й�ȣ ����
      boolean b = false;
      if (strPassword.equals(oldPasswd)) {
         b = true;
         strPassword = newPasswd;
         if(oldPasswd.equals(newPasswd)) {
            b = false;
            System.out.println("���� ��й�ȣ�� ���� ��й�ȣ �Դϴ�.");
         }
      }else {
         System.out.println("���¹�ȣ Ȥ�� ���� ��й�ȣ�� �߸��Ǿ����ϴ�.");
      }
      return b;
   }
   
   public String getAccountName() {   // �� �� �б�
      return strAccountName;
   }
   
   public int getnID() {
      return nID;
   }
   
   public int getnBalance() {
      return nBalance;
   }
}
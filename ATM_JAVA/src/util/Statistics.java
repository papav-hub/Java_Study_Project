package util;

import bank.Account;

public class Statistics {
   
   public Statistics() {
      
   }
   
   public static int num(Account [] account, int size) { // �� �ܰ� �Ѿ�
      int ALLMONEY = 0;
      for (int i=0 ; i<size ; i++) {
         ALLMONEY += account[i].getnBalance();
      }
      return ALLMONEY;
   }
   
   public static double average(Account [] account, int size) { // �� �ܰ� ���
      int ALLMONEY = 0;
      for (int i=0 ; i<size ; i++) {
         ALLMONEY += account[i].getnBalance();
      }
      double AVERAGE = (double)ALLMONEY/size;
      return AVERAGE;
   }
   
   public static int max(Account [] account, int size) { // �� �ܰ� �ְ�
      int MAXMONEY = 0;
      for (int i=0 ; i<size ; i++) {
         if(account[i].getnBalance() > MAXMONEY) {
            MAXMONEY = account[i].getnBalance();
         }
      }
      return MAXMONEY;
   }
   
   public static Account [] sort (Account [] account, int size) { // �� ���� ��Ȳ (�� �ܰ� ���� ���� ����)
      
      for(int i=0 ; i<size-1 ; i++) {
         for (int j=0 ; j<size-1 ; j++) {
            if(account[j].getnBalance() < account[j+1].getnBalance()) {
               Account n = account[j];
               account[j] = account[j+1];
               account[j+1] = n;
            }
         }
      }      
      
      return account;
   }
}
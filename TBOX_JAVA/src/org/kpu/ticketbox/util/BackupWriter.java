package org.kpu.ticketbox.util;

import java.io.*;
import java.util.*;

import org.kpu.ticketbox.payment.Receipt;

public class BackupWriter {
	
	public static void backupFile(String filename, HashMap<Integer, Receipt> map) throws IOException {
		
		Set <Integer> keys = map.keySet();
		Iterator<Integer> it = keys.iterator();
		
		File f = new File(filename);
		FileWriter fout = new FileWriter(f, true);
		
		try {
		
			while(it.hasNext()) {
				int a = it.next();
				Receipt aa  = map.get(a);
				String line = aa.toBackupString();
				
				if(line.length() == 0) {
					break;
				}
				fout.write(line);
				fout.write("\r\n", 0, 2); // 한 줄 띄우기
			}
			fout.close();
		}catch(IOException e) {
			System.out.println("오류");
		}	
	}

}

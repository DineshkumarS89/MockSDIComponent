package com.bc.mocksdicomponents.reader;


import com.bc.mocksdicomponents.BO.EventsBO;
import com.bc.mocksdicomponents.Rest.RestUtility;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class SimpleFileReader {

	private FileReader fileReader;
	private BufferedReader br;
	EventsBO events = null;
	RestUtility restUtility = null;
	
	
	public void readFile(String inputpath) throws IOException {
		
		fileReader = new FileReader(inputpath);
		
		events = new EventsBO();
		
		restUtility = new RestUtility();
		
		br = new BufferedReader(fileReader);
		
		String line = br.readLine();
		
		while(line!=null)
		{
			String tokens[] = line.split(",");
			
			events.setMerchantName(tokens[0]);
			events.setMerchantNumber(tokens[1]);
			events.setCrdNumber(tokens[2]);
			events.setTxnType(tokens[3]);
			events.setTxnAmount(tokens[4]);
			events.setWindowId(tokens[5]);
			events.setBusiness(tokens[6]);
			
			restUtility.sendEvents(events);
			
			line = br.readLine();
			
		}
		
	}
	
}

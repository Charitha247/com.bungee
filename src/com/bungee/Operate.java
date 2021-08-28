package com.bungee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Operate implements csv {
	
	@Override
	public void readCsv() {
		// TODO Auto-generated method stub
		String record = "";
		List<String> list = new ArrayList<>();
		int readCount = 0;
		int filterCount = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/input/main.csv")); 
			PrintWriter writer = new PrintWriter(new File("src/output/filteredCountry.csv"));
			StringBuilder header = new StringBuilder();
			header.append("SKU,DESCRIPTION,YEAR,CAPACITY,URL,PRICE,SELLER_INFORMATION,OFFER_DESCRIPTION,COUNTRY\n");
			writer.write(header.toString());
			writer.flush();
			while ((record = br.readLine()) != null) {
				String[] line = record.split(",");
				if (line[8].length() >=3) {
					String countryCode = line[8];
					if (countryCode.substring(0, 3).equals("USA")){
						list = Arrays.asList(line);
						StringBuilder row = new StringBuilder();
						for (String col:list) {
							row.append(col);
							row.append(",");
						}
						row.append("\n");
						writer.write(row.toString());
						filterCount = filterCount+1;
					}
				}
				readCount = readCount+1;
			}
			System.out.println("Total records count is "+ (readCount-1));
			System.out.println("Filtered records count is "+ filterCount);
			System.out.println("filteredCountry.csv is created in output folder!");
			br.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

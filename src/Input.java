
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.io.File;


public class Input{
		
		// NOTE: Have already been added into main (testing purpose only, delete when done)
	static ArrayList<char[]> forcedPartial = null;
	static ArrayList<char[]> forbiddenMachine = null;
	static ArrayList<char[]> tooNear = null;
	static char[][] machinePen = new char[8][8]; // Creating it as an 8 by 8 
	static ArrayList<char[]> tooNearPen = null;
	
	public static void inputFile(String[] args) throws IOException{
		BufferedReader br = null;
		BufferedWriter bw = null;
		PrintWriter writer = null;
			
		// Given through args
		File InputFile = null;
		File outputFile = null;
		
		String Name = "";
			
		// If there is less than 2 files given in args then create an error 
		
		// NOTE: Change the error message
		if(args.length < 2){
			// NOTE: This is assuming that the single output file will only be the input file
				writer = new PrintWriter("outputfile.txt", "UTF-8");
				writer.println("error");
				System.exit(1);
			}
			

		// NOTE: What is it if we dont get the input file?
			
		try {	
			InputFile = new File(args[0]);
			outputFile = new File(args[1]);
			writer = new PrintWriter(outputFile, "UTF-8");
			br = new BufferedReader(new FileReader(InputFile));
		}
		catch(IOException ioException){
			outputFile = new File(args[1]);
			writer = new PrintWriter(outputFile, "UTF-8");
			writer.println("Cannot open file");
			System.err.println("Cannot open file"); 
		}

		// Reading input file line by line, if the line contains : then that would be the start of a new variable. 
		String line = br.readLine();
			while(line != null) {
				while(line.contains(":")) {
					
					// Taking the name and putting it into the output file
					// This is the only thing this class needs to put into the output file
					if(line.contains("Name")) {
						Name = line;
						while(!line.isEmpty()) {
							line = br.readLine();
							Name = Name + ": /n" + line;
							line = br.readLine();
		
						}
						writer.println(Name);
						writer.close();
					}
					else if(line.contains("forced partial assignment")) {
						line = br.readLine();
						while(!line.isEmpty()) {
							String[] fix = line.split(" " + "(" + ")" + ",");
							for(int i = 0; i< fix.length; i++) {
								if (fix[i] == "(") {
									char x  = fix[i+1].charAt(0);
									char[] y = fix[i+3].toCharArray();
									forcedPartial.add(x, y);
								}
								i++;
						line = br.readLine();
							}
							 
						}
					}
					else if(line.contains("forbidden machine")) {
						line = br.readLine();
						while(!line.isEmpty()) {
							String[] fix = line.split(" " + "(" + ")" + ",");
							for(int i = 0; i< fix.length; i++) {
								if (fix[i] == "(") {
									char x  = fix[i+1].charAt(0);
									char[] y = fix[i+3].toCharArray();
									forbiddenMachine.add(x, y);
								}
								i++;
						line = br.readLine();
							}
						}
					}
					
					else if(line.contains("too-near tasks")) {
						line = br.readLine();
						while(!line.isEmpty()) {
							String[] fix = line.split(" " + "(" + ")" + ",");
							for(int i = 0; i< fix.length; i++) {
								if (fix[i] == "(") {
									char x  = fix[i+1].charAt(0);
									char[] y = fix[i+3].toCharArray();
									tooNear.add(x, y);
								}
								i++;
						line = br.readLine();
							}
						}
					}
					
					else if (line.contains("machine penalties")) {
						line = br.readLine();
						while(!line.isEmpty()) {
							String[] fix = line.split(" ");
							
							for(int i = 0; i <= 7; i++) {
								for(int j = 0; i <= 7; i++) {
									char n = fix[i].charAt(0);
									machinePen[i][j] = n;
									j++;
								}
								i++;
							}
							
						}
					}
					else if(line.contains("too-near penalities")) {
						line = br.readLine();
						while(!line.isEmpty()) {
							String[] fix = line.split(" " + "(" + ")" + ",");
							for(int i = 0; i< fix.length; i++) {
								if (fix[i] == "(") {
									char x  = fix[i+1].charAt(0);
									char[] y = fix[i+3].toCharArray();
									tooNearPen.add(x, y);
								}
								i++;
						line = br.readLine();
							}
						}
				
					}
			
				}
		}
	}
}
			




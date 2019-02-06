
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.io.File;


public class Input{
		
		// Creating the variables that will be used for the soft and hard constraints
	public static ArrayList<char[]> forcedPartial = null;
	public static ArrayList<char[]> forbiddenMachine = null;
	public static ArrayList<char[]> tooNear = null;
	public static ArrayList<char[]> tooNearPen = null;
	public static char[][] machinePen = new char[8][8]; // Since size is already known make double array
	
	public static void inputFile(String[] args) throws IOException{
		BufferedReader br = null;
		BufferedWriter bw = null;
		PrintWriter writer = null;
			
		// Given through args
		File InputFile = null;
		File outputFile = null;
		
		String Name = "";

			
		// If there is less than 2 files given in args then an output file for error message 	
		if(args.length < 2){
			// NOTE: This is assuming that the single output file will only be the input file
				writer = new PrintWriter("outputfile.txt", "UTF-8");
				writer.println("Error, Missing File");
				System.out.println("Error, Missing File");
				writer.close();
				System.exit(1);
			}
			

		// Try to make two variables for the files else 
		// make a new output file with an error message
			
		try {	
			InputFile = new File(args[0]);
			outputFile = new File(args[1]);
			writer = new PrintWriter(outputFile, "UTF-8");
			br = new BufferedReader(new FileReader(InputFile));
		}
		catch(IOException ioException){
			writer = new PrintWriter("outputfile.txt", "UTF-8");
			writer.println("Error, Cannot open file");
			System.err.println("Error, Cannot open file"); 
			writer.close();
			System.exit(1);
		}

		// Reading input file line by line, if the line contains : then that would be the start of a new variable. 
		String line = br.readLine();
			while(line != null) {
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
						line = br.readLine();
					}
					else if(line.contains("forced partial assignment")) {
						line = br.readLine();
						while(!line.isEmpty()) {
							String[] fix = line.split(" " + "(" + ")" + ",");
							for(int i = 0; i < fix.length; i++) {
								if (fix[i] == "(") {
									char x  = fix[i+1].charAt(0);
									char[] y = fix[i+3].toCharArray();
									forcedPartial.add(x, y);
								}
								i++;
							}
							line = br.readLine(); 
						}
						line = br.readLine();
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
						
							}
							line = br.readLine();
						}
						line = br.readLine();
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
	
							}
							line = br.readLine();
						}
						line = br.readLine();
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
						line = br.readLine();
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
							}
							line = br.readLine();
						}
						line = br.readLine();
					}
			
				}
		}
	
}
			




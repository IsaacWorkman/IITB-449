
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.io.File;


public class Input{
		
		// NOTE: Have already been added into main (testing purpose only, delete when done)
	ArrayList<Character[]> forcedPartial = null;
	ArrayList<Character[]> forbiddenMachine = null;
	ArrayList<Character[]> tooNear = null;
	char[][] machinePen = new char[8][8]; // Creating it as an 8 by 8 
	ArrayList<Character[]> tooNearPen = null;
	
	public static void inputFile(String[] args) throws IOException{
		BufferedReader br = null;
		BufferedWriter bw = null;
		PrintWriter writer = null;
			
		// Given through args
		File InputFile = null;
		File outputFile = null;
		
			
		String Inlines = "";
		char task = ' ';
		char machine = ' ';
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
							Name = Name + line;
							line = br.readLine();
		
						}
						writer.println(Name);
						writer.close();
					}
					else if(line.contains("forced partial assignment")) {
						Name = line;
						while(!line.isEmpty()) {
							line = br.readLine();
							Name = Name + line;
							line = br.readLine();
		
						}
						writer.println(Name);
						writer.close();
					}
					else if(line.contains("forbidden machine")) {
						
					}
				}
				
			}
			
	}
			

}


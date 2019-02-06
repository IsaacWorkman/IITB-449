
import java.io.*;
import java.util.*;
import java.io.File;


public class Input{
		
		// Creating the variables that will be used for the soft and hard constraints
	public ArrayList<char[]> forcedPartial = null;
	public ArrayList<char[]> forbiddenMachine = null;
	public ArrayList<char[]> tooNear = null;
	public ArrayList<char[]> tooNearPen = null;
	public char[][] machinePen = new char[8][8]; // Since size is already known make double array

	public Input(String[] args) throws IOException{
		BufferedReader br = null;
		BufferedWriter bw = null;
		PrintWriter writer = null;
			
		// Given through args
		File InputFile = null;
		File outputFile = null;
		
		String Name = "";
		//char[] pTasks = new char[] {'1','2','3','4','5','6','7','8'};
		//char[] pMachines = new char[] {'A','B','C','D','E','F','G','H'};
		String pmachine = "ABCDEFGH";
		String ptasks = "12345678";
			
		// If there is less than 2 files given in args then an output file for error message 	
		if(args.length < 2){
			// NOTE: This is assuming that the single output file will only be the input file
				writer = new PrintWriter("outputfile.txt", "UTF-8");
				writer.println("Error, Missing File");
				System.out.println("Error, Missing File");
				writer.close();
				System.exit(1);
			}
		
		if(args.length > 2){
			// NOTE: This is assuming that the single output file will only be the input file
				writer = new PrintWriter(outputFile, "UTF-8");
				writer.println("Error, Too many Files given");
				System.out.println("Error, Too many Files given");
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
							Name = Name + "\n" + line;
							line = br.readLine();
						}
						writer.println(Name);
						line = br.readLine();
					}
					// Splitting the read line and adding 2 variables into the character array if the character is (
					else if(line.contains("forced partial assignment")) {
						line = br.readLine();
						while(!line.isEmpty()) {
							char[] splitline = line.toCharArray();
							char tasks  = splitline[1];
							char machines = splitline[3];
							int taskin = ptasks.indexOf(tasks); 
							int taskout = pmachine.indexOf(machines); 
							if(taskin == -1 || taskout == -1) {
								writer.println("Error: there are no such Characters");
								System.exit(1);
							}
							line = br.readLine(); 
						}
						line = br.readLine();
					}
					// Splitting the read line and adding 2 variables into the character array if the character is (
					else if(line.contains("forbidden machine")) {
						line = br.readLine();
						while(!line.isEmpty()) {
							char[] splitline = line.toCharArray();
							char tasks  = splitline[1];
							char machines = splitline[3];
							int taskin = ptasks.indexOf(tasks); 
							int taskout = pmachine.indexOf(machines); 
							if(taskin == -1 || taskout == -1) {
								writer.println("Error: there are no such Characters");
								System.exit(1);
							}
							line = br.readLine(); 
						}
						line = br.readLine();
					}
					// Splitting the read line and adding 2 variables into the character array if the character is (
					else if(line.contains("too-near tasks")) {
						line = br.readLine();
						while(!line.isEmpty()) {
							char[] splitline = line.toCharArray();
							char tasks  = splitline[1];
							char machines = splitline[3];
							int taskin = ptasks.indexOf(tasks); 
							int taskout = ptasks.indexOf(machines); 
							if(taskin == -1 || taskout == -1) {
								writer.println("Error: there are no such Characters");
								System.exit(1);
							}
							tooNearPen.add(tasks,machines);
							
							line = br.readLine(); 
						}
						line = br.readLine();
					}
					
					// Splitting each line and adding all the characters into the array at point ij. 
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
					// Splitting the read line and adding 2 variables into the character array if the character is (
					else if(line.contains("too-near penalities")) {
						line = br.readLine();
						while(!line.isEmpty()) {
							char[] splitline = line.toCharArray();
							char tasks  = splitline[1];
							char machines = splitline[3];
							int taskin = ptasks.indexOf(tasks); 
							int taskout = pmachine.indexOf(machines); 
							if(taskin == -1 || taskout == -1) {
								writer.println("Error: there are no such Characters");
								System.exit(1);
							}
							line = br.readLine(); 
						}
						line = br.readLine();
					}
			
				}
		}
	
}
			




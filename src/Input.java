import java.io.*;
import java.util.*;
import java.io.File;


public class Input{
		
		// Creating the variables that will be used for the soft and hard constraints
	public ArrayList<char[]> forcedPartial = new ArrayList<char[]>();
	public ArrayList<char[]> forbiddenMachine = new ArrayList<char[]>() ;
	public ArrayList<char[]> tooNear = new ArrayList<char[]>();
	public ArrayList<String[]> tooNearPen = new ArrayList<String[]>();
	public char machinePen[][] = new char[8][8]; // Since size is already known make double array

	public Input(String[] args) throws IOException{
		BufferedReader br = null;
		BufferedWriter bw = null;
		PrintWriter writer = null;
			
		// Given through args
		File InputFile = null;
		File outputFile = null;
		
		String Name = "";
		String ptasks = "ABCDEFGH";
		String pmachine = "12345678";
			
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
						System.out.println(Name);
						System.out.println(line);
					} 
					// Splitting the read line and adding 2 variables into the character array if the character is (
					else if(line.contains("forced partial assignment")) {
						line = br.readLine();
						//System.out.println(line);
						int  i=0;
						while(!line.isEmpty()) {
							//System.out.println(line);
							char[] splitline = line.toCharArray();
							char machines  = splitline[1];
							System.out.println(machines);
							char tasks = splitline[4];
							System.out.println(tasks);
							int taskin = pmachine.indexOf(machines); 
							System.out.println(taskin);
							int taskout = ptasks.indexOf(tasks); 
							System.out.println(taskout);
							if(taskin == -1 || taskout == -1) {
								writer.println("Error: there are no such Characters");
								System.exit(1);
							}
							//System.out.print("a\n" );
							char[] charArray = {machines,tasks};
							//forcedPartial[forcedPartial.length] = charArray; 
							//System.out.print("b\n" );
							forcedPartial.add(charArray);
							//System.out.print("c\n" );
							System.out.println(charArray);
							line = br.readLine(); 
							//System.out.println(line);
						}
						line = br.readLine();
						i++;
						
					}
					// Splitting the read line and adding 2 variables into the character array if the character is (
					else if(line.contains("forbidden machine")) {
						line = br.readLine();
						System.out.println("forbidden machine");
						while(!line.isEmpty()) {
							char[] splitline = line.toCharArray();
							char machines  = splitline[1];
							char tasks = splitline[4];
							int taskin = pmachine.indexOf(machines); 
							int taskout = ptasks.indexOf(tasks); 
							if(taskin == -1 || taskout == -1) {
								writer.println("Error: there are no such Characters");
								System.exit(1);
							}
							char[] charArray = {machines,tasks};
							forbiddenMachine.add(charArray);
							
							line = br.readLine(); 
							System.out.println(charArray);
						}
						line = br.readLine();
					}
					// Splitting the read line and adding 2 variables into the character array if the character is (
					else if(line.contains("too-near tasks")) {
						line = br.readLine();
						System.out.println("too-near tasks");
						while(!line.isEmpty()) {
							char[] splitline = line.toCharArray();
							char machines  = splitline[1];
							//System.out.println(machines);
							char tasks = splitline[4];
							//System.out.println(machines);
							int taskin =ptasks.indexOf(machines); 
							int taskout = ptasks.indexOf(tasks); 
							if(taskin == -1 || taskout == -1) {
								writer.println("Error: there are no such Characters");
								System.exit(1);
							}
							char[] charArray = {machines,tasks};
							tooNear.add(charArray);
							//System.out.println("a");
							line = br.readLine();
							System.out.println(charArray); 
						}
						line = br.readLine();
						

					}
			
					// Splitting each line and adding all the characters into the array at point ij. 
					else if (line.contains("machine penalties")) {
						System.out.println("Machine penalties");
						line = br.readLine();
						String[] splited = line.split("\\s+");
						int length = splited.length;

						for(int i = 0; i < length; i++){
							if (splited[i] == "(" || splited[i] == ")" || splited[i] == ","){

							}
							try{
								int num = Integer.parseInt(splited[i]);
							}
							catch(Exception e) {
								System.out.println("Error in Machine Penalties:");
								System.out.println(e);
							}


						}


						char[] charArrays = new char[line.length()];
						//System.out.println(line.length());
						line = line.replaceAll("\\s+", "");
						charArrays = line.toCharArray();
				
						//System.out.println(line.length());
						//System.out.println(charArrays[7]);
						int count = 0;
						while(!line.isEmpty()) {
							for( int i = 0; i < 1; i++) {	
								for( int j = 0; j <= 7; j++) {
									char c = charArrays[j];
									machinePen[j][count] = c;
									//System.out.print(machinePen[i][j]);
									//System.out.println(i+"!!!!");
									//System.out.println(j);
								}
								count++;
							} 
							line = br.readLine();
						//System.out.println(line.length());
						line = line.replaceAll("\\s+", "");
						charArrays = line.toCharArray();
						//System.out.println(charArrays);
							
							//System.out.print(machinePen[0][7]);
							//System.out.println(line);		
						}
						/*line = br.readLine();
						//System.out.println("error1");
						line1 = line.replaceAll("\\s+", "");
						//System.out.println("error2");
						//System.out.println("error3");
						splitline = line1.toCharArray();
						//System.out.println("error4");
						for(int i = 0; i<8; i++){
						//	System.out.println("error");
							char machines  = splitline[i];
						//	System.out.println("error1");
							int taskin = kmachine.indexOf(machines); 
						//	System.out.println("error2");
							if(taskin == -1) {
								writer.println("Error: there are no such Characters");
								System.out.println("error");
								System.exit(1);
							}
						}*/
		

						//System.out.print(machinePen[2][1]);
						/*for (int k=0; k<=7; k++){
								for (int h=0; h<=7; h++){
									System.out.print(machinePen[k][h]);
								}
						}*/

						//System.out.println(machinePen[0][0]);
						System.out.println(machinePen[0][0]);
						System.out.println(machinePen[1][1]);
						System.out.println(machinePen[2][2]);
						System.out.println(machinePen[3][3]);
						System.out.println(machinePen[4][4]);
						line = br.readLine();

					}
					// Splitting the read line and adding 2 variables into the character array if the character is (
					else if(line.contains("too-near penalties")) {
						System.out.println("too-near penalties");
						line = br.readLine();
						while(line != null) {
							//System.out.println(line);
							line = line.replace(")", "");
							line = line.replace("(", "");
							line = line.replace(",", "");
							String[] splitLine = line.split("\\s+");
							//char[] splitline = line.toCharArray();
							String machines  = splitLine[0];
							String tasks = splitLine[1];
							String num = splitLine[2];
							int taskin = ptasks.indexOf(machines); 
							int taskout = ptasks.indexOf(tasks); 
							int theNum = Integer.parseInt(num);
							if(taskin == -1 || taskout == -1 || theNum == -1) {
								writer.println("Error: there are no such Characters");
								System.exit(1);
							}
							String[] charArray = {machines,tasks,num};
							tooNearPen.add(charArray);
							line = br.readLine(); 
							System.out.println(charArray);
							//System.out.println("a");
						}
						//System.out.println("c");
						line = br.readLine();
						if (line == null){
							//	System.out.println("f");
								br.close();
							}
					}

			
				}
				
		}	

}
	

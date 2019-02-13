import java.io.*;
import java.util.*;
import java.io.File;


public class Input{

    // Creating the variables that will be used for the soft and hard constraints
    public ArrayList<char[]> forcedPartial = new ArrayList<char[]>();
    public ArrayList<char[]> forbiddenMachine = new ArrayList<char[]>() ;
    public ArrayList<char[]> tooNear = new ArrayList<char[]>();
    public ArrayList<String[]> tooNearPen = new ArrayList<String[]>();
    public int machinePen[][] = new int[8][8]; // Since size is already known make double array

    public Input(String[] args) throws IOException{
        BufferedReader br = null;
        BufferedWriter bw = null;
        PrintWriter writer = null;

        // Given through args
        File InputFile = null;
        File outputFile = null;

        String Name = "";
        String Previous = "";
        String ptasks = "ABCDEFGH";
        String pmachine = "12345678";

        // If there is less than 2 files given in args then an output file for error message    
        if(args.length < 2){
            // NOTE: This is assuming that the single output file will only be the input file
            writer = new PrintWriter("outputfile.txt", "UTF-8");
            writer.println("Error, Missing File");
            writer.close();
            System.exit(1);
        }

        if(args.length > 2){
            // NOTE: This is assuming that the single output file will only be the input file
            writer = new PrintWriter(outputFile, "UTF-8");
            writer.println("Error, Too many Files given");
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
            writer.close();
            System.exit(1);
        }

        // Reading input file line by line, if the line contains : then that would be the start of a new variable. 
        String line = br.readLine();
        // Taking the name and putting it into the output file
        // This is the only thing this class needs to put into the output file
        if (line == null) {
            return;
        }
        while (!line.contains("Name")) {
            line = br.readLine();
        }
        line = br.readLine();
        while (line.isEmpty()) {
            line = br.readLine();
        }
        Name = line;
        Previous = "Name";
        line = br.readLine();
        while(line.isEmpty()) {
            line = br.readLine();
        }
        if (line.contains("forced partial assignment:") && (line.replace(" ", "").equals("forcedpartialassignment:"))) {
            if(Previous != "Name") {
                writer.println("Error: The name of previous not in file");
                writer.close();
                System.exit(1);
            }
        	Previous = "forced partial assignment";
            line = br.readLine();
            
            while(!line.contains("forbidden")) {
            	if (line.isEmpty()) {
            		line = br.readLine();
            		continue;
            	}
                char[] splitline = line.toCharArray();
                char machines  = splitline[1];
                char tasks = splitline[3];
                int taskin = pmachine.indexOf(machines); 
                int taskout = ptasks.indexOf(tasks); 
                if(taskin == -1 || taskout == -1) {
                    writer.println("Error: there are no such Characters");
                    writer.close();
                    System.exit(1);
                }
                if(splitline[0] != '(' || splitline[2] == ',' || splitline[4] == ')') {
                	 writer.println("Error: There are Characters missing");
                	 writer.close();
                     System.exit(1);
                }
                char[] charArray = {machines,tasks};
                forcedPartial.add(charArray);
                line = br.readLine(); 
            }
        }
        if (line.contains("forbidden machine:") && (line.replace(" ", "").equals("forbiddenmachine:"))) {
            if(Previous != "forced partial assignment" ) {
                writer.println("Error: The name of previous not in file");
                writer.close();
                System.exit(1);
            }
        	Previous = "forbidden machine";
            line = br.readLine();
             
            while(!line.contains("too-near tasks:")) {
            	if (line.isEmpty()) {
            		line = br.readLine();
            		continue;
            	}
                char[] splitline = line.toCharArray();
                char machines  = splitline[1];
                char tasks = splitline[3];
                int taskin = pmachine.indexOf(machines); 
                int taskout = ptasks.indexOf(tasks); 
                if(taskin == -1 || taskout == -1) {
                    writer.println("Error: there are no such Characters");
                    writer.close();
                    System.exit(1);
                }
                if(splitline[0] != '(' || splitline[2] != ',' || splitline[4] != ')') {
               	 writer.println("Error: There are Characters missing");
               	 writer.close();
                 System.exit(1);
               }
                char[] charArray = {machines,tasks};
                forbiddenMachine.add(charArray);
                line = br.readLine(); 
            }
            if (line.contains("too-near tasks:") && (line.replace(" ", "").equals("too-neartasks:"))) {
                if(Previous != "forbidden machine" ) {
                    writer.println("Error: The name of previous not in file");
                    writer.close();
                    System.exit(1);
                }
            	line = br.readLine();
                Previous = "too-near tasks";

                while(!line.contains("machine penalties:")) {
                	if (line.isEmpty()) {
                		line = br.readLine();
                		continue;
                	}
                    char[] splitline = line.toCharArray();
                    char machines  = splitline[1];
                    char tasks = splitline[3];
                    int taskin = ptasks.indexOf(machines); 
                    int taskout = ptasks.indexOf(tasks); 

                    if(taskin == -1 || taskout == -1) {
                        writer.println("Error: there are no such Characters");
                        writer.close();
                        System.exit(1);
                    }
                    if(splitline[0] != '(' || splitline[2] != ',' || splitline[4] != ')') {
                   	 	writer.println("Error: There are Characters missing");
                   	 	writer.close();
                   	 	System.exit(1);
                        
                   }
                    char[] charArray = {machines,tasks};
                    tooNear.add(charArray);
                    line = br.readLine();
                }
                if (line.contains("machine penalties:") && (line.replace(" ", "").equals("machinepenalties:"))) {
                    if(Previous != "too-near tasks" ) {
                        writer.println("Error: The name of previous not in file");
                        writer.close();
                        System.exit(1);
                    }
                	line = br.readLine();
                    int count = 0;
                    Previous = "machine penalties";
                    
                    while(!line.contains("too-near penalties")) {
                    	if (line.isEmpty()) {
                    		line = br.readLine();
                    		continue;
                    	}
                        String[] splitted = line.split("\\s+");
                        int length = splitted.length;
                        if (splitted.length != 8) {
                          	 writer.println("Error: There are Characters missing");
                          	 writer.close();
                             System.exit(1);        	
                        }
                        for( int i = 0; i < 8; i++) {
                            machinePen[count][i] = Integer.parseInt(splitted[i]);
                        }
                        line = br.readLine();
                        count++;
                    }
                    if (line.contains("too-near penalties") && (line.replace(" ", "").equals("too-nearpenalties"))) {
                        if(Previous != "machine penalties" ) {
                            writer.println("Error: The name of previous not in file");
                            writer.close();
                            System.exit(1);
                        }
                        
                    	line = br.readLine();
                        Previous = "too-near penalties";
                        while(line != null) {
                        	if (line.isEmpty()) {
                        		line = br.readLine();
                        		continue;
                        	}
                        	
                        	if(line.contains("(") || line.contains(",") || line.contains(")")) {
                                line = line.replace(")", "");
                                line = line.replace("(", "");
                                line = line.replace(" ", "");
                        	}
                        	else {
                              	 writer.println("Error: There are Characters missing");
                                 writer.close();
                              	 System.exit(1);
                        	}
                            String[] splitLine = line.split(",");
                            String machines  = splitLine[0];
                            String tasks = splitLine[1];
                            String num = splitLine[2];
                            int taskin = ptasks.indexOf(machines); 
                            int taskout = ptasks.indexOf(tasks); 
                            int theNum = Integer.parseInt(num);
                            if(taskin == -1 || taskout == -1 || theNum == -1) {
                                writer.println("Error: there are no such Characters");
                                writer.close();
                                System.exit(1);
                                
                            }
                            String[] charArray = {machines,tasks,num};
                            tooNearPen.add(charArray);
                            line = br.readLine(); 
                            }
                        line = br.readLine();
                        if (line == null){
                            br.close();
                        }
                    }
                }
            }

        }
        br.close();
    }   
}
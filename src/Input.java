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
        
        line = br.readLine();
        while(line.isEmpty()) {
            line = br.readLine();
        }
        if (line.contains("forced partial assignment:") && (line.replace(" ", "").equals("forcedpartialassignment:"))) {
            System.out.println("forced partial assignment:");
            line = br.readLine();

            while(!line.contains("forbidden")) {
            	if (line.isEmpty()) {
            		line = br.readLine();
            		continue;
            	}
                //System.out.println(line);
                char[] splitline = line.toCharArray();
                char machines  = splitline[1];
                //System.out.println(machines);
                char tasks = splitline[3];
                //System.out.println(tasks);
                int taskin = pmachine.indexOf(machines); 
                //System.out.println(taskin);
                int taskout = ptasks.indexOf(tasks); 
                //System.out.println(taskout);
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
        }
        if (line.contains("forbidden machine:") && (line.replace(" ", "").equals("forbiddenmachine:"))) {
        	System.out.println("forbidden machine");
            line = br.readLine();
            while(!line.contains("too-near tasks:")) {
                //System.out.println(line);
            	if (line.isEmpty()) {
            		line = br.readLine();
            		continue;
            	}
                char[] splitline = line.toCharArray();
                char machines  = splitline[1];
                //System.out.println(machines);
                char tasks = splitline[3];
                //System.out.println(tasks);
                int taskin = pmachine.indexOf(machines); 
                //System.out.println(taskin);
                int taskout = ptasks.indexOf(tasks); 
                //System.out.println(taskout);
                if(taskin == -1 || taskout == -1) {
                    writer.println("Error: there are no such Characters");
                    System.exit(1);
                }
                //System.out.print("a\n" );
                char[] charArray = {machines,tasks};
                //forcedPartial[forcedPartial.length] = charArray; 
                //System.out.print("b\n" );
                forbiddenMachine.add(charArray);
                //System.out.print("c\n" );
                System.out.println(charArray);
                line = br.readLine(); 
                //System.out.println(line);
            }
            if (line.contains("too-near tasks:") && (line.replace(" ", "").equals("too-neartasks:"))) {
                line = br.readLine();
                System.out.println("too-near tasks");

                while(!line.contains("machine penalties:")) {
                	if (line.isEmpty()) {
                		line = br.readLine();
                		continue;
                	}
                    char[] splitline = line.toCharArray();
                    char machines  = splitline[1];
                    //System.out.println(machines);
                    char tasks = splitline[3];
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
                if (line.contains("machine penalties:") && (line.replace(" ", "").equals("machinepenalties:"))) {
                    line = br.readLine();
                    int count = 0;
                    System.out.println("machine penalties");
                    while(!line.contains("too-near penalties")) {
                    	if (line.isEmpty()) {
                    		line = br.readLine();
                    		continue;
                    	}
                        String[] splitted = line.split("\\s+");
                        //System.out.println(splitted[0]);
                        int length = splitted.length;
                        if (splitted.length != 8) {
                            //TODO:file content error
                        }
                        for( int i = 0; i < 8; i++) {

                            machinePen[count][i] = Integer.parseInt(splitted[i]);
                        }
                        line = br.readLine();
                        count++;
                    }
                    if (line.contains("too-near penalties") && (line.replace(" ", "").equals("too-nearpenalties"))) {
                        line = br.readLine();
                        System.out.println("too-near penalties");
                        while(line != null) {
                        	if (line.isEmpty()) {
                        		line = br.readLine();
                        		continue;
                        	}
                            //System.out.println(line);
                            line = line.replace(")", "");
                            line = line.replace("(", "");
                            line = line.replace(" ", "");
                            String[] splitLine = line.split(",");
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
                            //  System.out.println("f");
                            br.close();
                        }
                    }
                }
            }

        }
        br.close();
    }   
}
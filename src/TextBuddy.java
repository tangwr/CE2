 /**
  *  This programs manipulate text in a file
  * @Author Tang Wei Ren
  */

import java.util.*;
import java.io.*;

public class TextBuddy {
	
	private String testFileName; 
	
	public static void main(String args[]){
		enterCommand(args);
	}
	
	/**
	 * User input command
	 */
	public static void enterCommand(String args[]){
		
		Scanner scanLine = new Scanner(System.in);	
		boolean isActive;
		String input, moreInput;
		String fileName = args[0];
		
		System.out.println("Welcome to TextBuddy. " + fileName +" is ready for use");
		
		do{
			System.out.print("Command: ");
			input = scanLine.next();
			moreInput = scanLine.nextLine();
			isActive = command(input, moreInput, fileName);
		}while(isActive);
		
	}
	
	/**
	 * Execute the selected command, return
	 * @return true if user enter all command except exit, false if exit command is issue
	 */
	public static boolean command(String input, String moreInput, String fileName){		
		
		moreInput = trim(moreInput);
		
		switch(input){
			case "add": 
				add(moreInput, fileName);
				return true;
			
			case "display": 
				display(fileName);
				return true;
				
			case "delete": 
				delete(moreInput, fileName);
				return true;
				
			case "clear": 
				clear(fileName);
				return true;
				
			case "sort":
				sort(fileName);
				return true;
				
			case "search":
				search(moreInput, fileName);
				return true;
				
			case "exit":
				return false;
			
			default: 
				System.out.println("Invalid command. Type 'exit' to quit");
				return true;			
		}
	}
	
	/**
	 * Get the user's additional input after the command
	 */
	public static String trim(String str){		
		
		int index = str.indexOf(" ");	
		str = str.substring(index+1);		
		return str;
	}
	
	/**
	 * Add a string of text into the text file
	 */
	public static String add(String text, String fileName){		
		
		writeFile(text, fileName);
		String addedLine = "added to " + fileName + ": \"" + text + "\"";
		System.out.println("added to " + fileName + ": \"" + text + "\"");	
		
		return addedLine;
	}
	
	/**
	 * Display all the content of a specific text file
	 */
	public static ArrayList<String> display(String fileName){
		
		ArrayList<String> displayList = readFile(fileName);
		int count = 0;
		
		for(String eachLine : displayList){
			System.out.println(++count + ". " +eachLine);
		}
		
		return displayList;
	}
	
	/**
	 * Delete the nth line in the specfic text file
	 */
	public static String delete(String line, String fileName){
		
		String lineDeleted;
		
		try{
			int numLine = Integer.parseInt(line);
			
			if(isValidNumLine(numLine, fileName)){
				lineDeleted = "Invalid line number";
				System.out.println("Invalid line number");
			}else{
				String deletedLine = findLine(numLine, fileName);
				deleteLine(numLine, fileName);
				lineDeleted = "deleted from " + fileName + ": \"" + deletedLine + "\"";
				System.out.println("deleted from " + fileName + ": \"" + deletedLine + "\"");
			}
		}catch(NumberFormatException e){
			lineDeleted = "Invalid line number";
			System.out.println("Invalid line number");
		}
		
		return lineDeleted;
	}
	
	/**
	 * Find and locate the sentence at the nth line in the text file
	 * @return sentence at nth line
	 */
	public static String findLine(int numLine, String fileName){
		String line = "";
		
		try{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
            
			for(int i=0; i<numLine; i++){
				line = bufferedReader.readLine();
			}
			bufferedReader.close();   
			
			return line;
			
		}catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                   
        }
		
		return line;
		
	}
	
	/**
	 * Open the text file and delete the nth line in the specfic text file
	 */
	public static void deleteLine(int numLine, String fileName){
		//String path = System.getProperty("java.class.path");	
		File currFile = new File(fileName);
		File tempFile = new File(currFile.getAbsolutePath() + ".tmp");
		String line = null;
		int count = 0;
		
		try{
			FileWriter fileWriter = new FileWriter(tempFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			FileReader fileReader = new FileReader(fileName);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        
	        while((line = bufferedReader.readLine()) != null) {
	        	++count;
	        	
				if(count == numLine){
					continue;
				}else{
					 bufferedWriter.write(line);
					 bufferedWriter.newLine();
				}
			}   
	        bufferedWriter.close();
	        bufferedReader.close();
	        
	        //Delete the original file
	        if (!currFile.delete()) {
	          System.out.println("Could not delete file");
	        } 	        
	        //Rename the new file to the filename the original file had.
	        if (!tempFile.renameTo(currFile)){
	          System.out.println("Could not rename file");
	        }        
		}catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                   
        }
		
	}
	
	/**
	 * Delete all the content of the specific text file
	 */
	public static String clear(String fileName){
		try {
            FileWriter fileWriter = new FileWriter(fileName);
			
			fileWriter.write("");
            fileWriter.close();
			
			System.out.println("all content deleted from " + fileName);
			
			return "all content deleted from " + fileName;
        } catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
            
            return "Error writing to file '" + fileName + "'";
        }		
	}
	
	/**
	 * Read the specific textfile and print out all the text in it
	 */
	public static ArrayList<String> readFile(String fileName){
		// This will reference one line at a time
        String line = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
        	//int count = 0;
        	
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			if(isFileEmpty(fileName)){
				//list.add(fileName + " is empty");
            	System.out.println(fileName + " is empty");
            }else{
				while((line = bufferedReader.readLine()) != null) {
					//list.add(++count + ". " + line);
					list.add(line);
					//System.out.println(++count + ". " + line);
				}   
			}			
            bufferedReader.close();   
            
            
        }catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                   
        }
        
        return list;
	}
	
	/**
	 * Write the text inputted by the user to the specific file
	 */
	public static void writeFile(String text, String fileName){
		try {

            FileWriter fileWriter = new FileWriter(fileName, true);

            BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);
			
			if(isFileEmpty(fileName)){
				bufferedWriter.write("");
			}

            bufferedWriter.write(text);
			bufferedWriter.newLine();

            bufferedWriter.close();
        }catch(IOException ex) {
            System.out.println("Error writing to file '"+ fileName + "'");
        }
	}
	
	/**
	 * Check if the file is empty
	 * @return true/false
	 */
	public static boolean isFileEmpty(String fileName){
		File file = new File(fileName);

		if(file.length() == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Determine if the number of lines input by user is of a valid number
	 * @return true/false
	 */
	public static boolean isValidNumLine(int numLine, String fileName){
		String line = null;
		int count = 0;
		
		try{
			FileReader fileReader = new FileReader(fileName);

			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				count++;
			} 
			
			bufferedReader.close();  
			
			if(count == 0){
				return true;
			}
			else if(numLine > count){
				return true;
			}
			else{
				return false;
			}
		}catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                   
            return true;
        }
	}
	
	public static void sort(String fileName){
		ArrayList<String> sortedList = readFile(fileName);
		
		try {
            FileWriter fileWriter = new FileWriter(fileName);
			
			fileWriter.write("");
            fileWriter.close();
			
        } catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }	
		
		Collections.sort(sortedList);
		
		for(String eachLine : sortedList){
			writeFile(eachLine, fileName);
			//System.out.println(eachLine);
		}
		
		display(fileName);
		
	}
	
	public static ArrayList<String> search(String text, String fileName){
		ArrayList<String> list = readFile(fileName);
		ArrayList<String> searchList = new ArrayList<String>();
		int count = 0;
		
		for(String eachLine : list){
			if(eachLine.contains(text)){			
				searchList.add(eachLine);
				System.out.println(++count + "." + eachLine);
			}
		}
		
		return searchList;
	}
	
	//Function for unit testing.
	public TextBuddy(String fileName){
		testFileName = fileName;
	}
	
	
	public String getFileName(){
		return testFileName;
	}
	
	public ArrayList<String> getReadFile(String fileName){
		return readFile(fileName);
	}
	
}

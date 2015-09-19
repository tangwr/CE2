import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

public class UnitTest {

	/**
	 * Test if the "Sort" works in TextBuddy.java
	 */
	@Test
	public void testSortList() {
		TextBuddy textBuddy = new TextBuddy("unsortedList.txt");
		String fileName = textBuddy.getFileName();
		TextBuddy.sort(fileName);
		
		
		ArrayList<String> unsortedList = TextBuddy.readFile(fileName);
		ArrayList<String> sortedList = TextBuddy.readFile("sortedList.txt");
		
		assertEquals(true, isCompareList(unsortedList, sortedList));		
	}
	
	/**
	 * Test if the "Search" works in TextBuddy.java
	 */
	@Test
	public void testSearchKeywords(){
		TextBuddy textBuddy = new TextBuddy("searchList.txt");
		String fileName = textBuddy.getFileName();
		String keyword = "name";
		ArrayList<String> searchList = TextBuddy.search(keyword, fileName);
		ArrayList<String> searchResultList = TextBuddy.readFile("searchResultList.txt");
		
		assertEquals(true, isCompareList(searchList, searchResultList));
	}
	
	/**
	 * Test if the "Clear" works in TextBuddy.java
	 */
	@Test
	public void testClear(){
		TextBuddy textBuddy = new TextBuddy("clear.txt");
		String fileName = textBuddy.getFileName();
		String command = textBuddy.executeCommand("clear", "", fileName);
		String expectedOutput  = "all content deleted from " + fileName;
		
		assertEquals(true,command.equals(expectedOutput));
	}
	
	/**
	 * Test if the "Delete" works in TextBuddy.java
	 */
	@Test
	public void testDelete(){
		TextBuddy textBuddy = new TextBuddy("delete.txt");
		String fileName = textBuddy.getFileName();
		String numLine = "2";
		String command = textBuddy.executeCommand("delete", numLine, fileName);
		String deletedLine = "deleted from " + fileName + ": \"" + "why delete me" + "\"";
		
		assertEquals(true,command.equals(deletedLine));
	}
	
	/**
	 * Test if the "Display" works in TextBuddy.java
	 */
	@Test
	public void testDisplay(){
		TextBuddy textBuddy = new TextBuddy("display.txt");
		String fileName = textBuddy.getFileName();
		ArrayList<String> originalDisplayList = TextBuddy.readFile("display.txt");
		ArrayList<String> displayList = TextBuddy.display(fileName);
		
		assertEquals(true, isCompareList(displayList, originalDisplayList));
				
	}
	
	/**
	 * Test if the "Add" works in TextBuddy.java
	 */
	@Test
	public void testAdd(){
		TextBuddy textBuddy = new TextBuddy("add.txt");
		String fileName = textBuddy.getFileName();
		String addLine = "hello my world";
		String command = textBuddy.executeCommand("add", addLine, fileName);
		String expectedOutput = "added to " + fileName + ": \"" + "hello my world" + "\"";
		
		assertEquals(true, command.equals(expectedOutput));
	}
	
	/**
	 * Compare if the two ArrayList is the same
	 * @return true/false
	 */
	public static boolean isCompareList(ArrayList<String> unsortedList, ArrayList<String> sortedList){
		
		if(unsortedList.size() != sortedList.size()){
			return false;
		}else{
			for(int i=0; i<unsortedList.size(); i++){
				if(!unsortedList.get(i).equals(sortedList.get(i))){
					return false;
				}
			}
		}
		return true;
	}
}

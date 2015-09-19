import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

public class UnitTest {

	
	@Test
	public void testSortList() {
		TextBuddy textBuddy = new TextBuddy("unsortedList.txt");
		String fileName = textBuddy.getFileName();
		TextBuddy.sort(fileName);
		
		
		ArrayList<String> unsortedList = textBuddy.getReadFile(fileName);
		ArrayList<String> sortedList = textBuddy.getReadFile("sortedList.txt");
		
		assertEquals(true, isCompareList(unsortedList, sortedList));
		
		
	}
	
	
	@Test
	public void testSearchKeywords(){
		TextBuddy textBuddy = new TextBuddy("searchList.txt");
		String fileName = textBuddy.getFileName();
		String keyword = "name";
		ArrayList<String> searchList = TextBuddy.search(keyword, fileName);
		ArrayList<String> searchResultList = textBuddy.getReadFile("searchResultList.txt");
		
		assertEquals(true, isCompareList(searchList, searchResultList));
	}
	
	@Test
	public void testClear(){
		TextBuddy textBuddy = new TextBuddy("clear.txt");
		String fileName = textBuddy.getFileName();
		String expectedOutput  = "all content deleted from " + fileName;
		
		assertEquals(true,TextBuddy.clear(fileName).equals(expectedOutput));
	}
	
	@Test
	public void testDelete(){
		TextBuddy textBuddy = new TextBuddy("delete.txt");
		String fileName = textBuddy.getFileName();
		String line = "2";
		//TextBuddy.command("delete", "2", fileName);
		String deletedLine = TextBuddy.delete(line, fileName);
		
		assertEquals(true,deletedLine.equals("deleted from " + fileName + ": \"" + "why delete me" + "\""));
	}
	
	@Test
	public void testDisplay(){
		TextBuddy textBuddy = new TextBuddy("display.txt");
		String fileName = textBuddy.getFileName();
		ArrayList<String> originalDisplayList = textBuddy.getReadFile("display.txt");
		ArrayList<String> displayList = TextBuddy.display(fileName);
		
		assertEquals(true, isCompareList(displayList, originalDisplayList));
				
	}
	
	@Test
	public void testAdd(){
		TextBuddy textBuddy = new TextBuddy("add.txt");
		String fileName = textBuddy.getFileName();
		String expectedOutput = "added to " + fileName + ": \"" + "hello my world" + "\"";
		
		assertEquals(true, TextBuddy.add("hello my world", fileName).equals(expectedOutput));
	}
	
	public static boolean isCompareList(ArrayList<String> unsortedList, ArrayList<String> sortedList){
		
		if(unsortedList.size() != sortedList.size()){
			return false;
		}
		else{
			for(int i=0; i<unsortedList.size(); i++){
				if(!unsortedList.get(i).equals(sortedList.get(i))){
					return false;
				}
			}
		}
		return true;
	}
}

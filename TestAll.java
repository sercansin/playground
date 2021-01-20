package api_test;

import org.junit.Test;
public class TestAll {
	
	Find_ID fID = new Find_ID();
	
	
	String searchWord = "harry potter";
	String movieTitle = "Harry Potter and the Sorcerer's Stone";

@Test

	public void AssertHarry() {
		String id = fID.bySearch_getID(searchWord, movieTitle);
		fID.IDsearch(id);
		
	}

	 
} 
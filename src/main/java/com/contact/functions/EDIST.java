package com.contact.functions;

import java.util.*;
public class EDIST {
	static int min(int x, int y, int z)
    {
        if (x <= y && x <= z)
            return x;
        if (y <= x && y <= z)
            return y;
        else
            return z;
    }
 
    static int editDist(String str1, String str2, int m,
                        int n)
    {
        if (m == 0)
            return n;
 
     
        if (n == 0)
            return m;
 
      
        if (str1.charAt(m - 1) == str2.charAt(n - 1))
            return editDist(str1, str2, m - 1, n - 1);
 
        
        return 1
            + min(editDist(str1, str2, m, n - 1), // Insert
                  editDist(str1, str2, m - 1, n), // Remove
                  editDist(str1, str2, m - 1,
                           n - 1) // Replace
              );
    }
    public String nearWord(List<String> words,String word) {
    	List<Integer> distances=new ArrayList<>();
    	for (String mot:words) {
    		distances.add(editDist(mot,word,mot.length(),word.length()));
    		
    	}
    	int minumum=Collections.min(distances);
    	int index=distances.indexOf(minumum);
    	if(minumum<3) {
    	return words.get(index);
    	}
    	return null;
    }

}

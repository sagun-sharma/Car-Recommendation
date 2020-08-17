package com.ml.onkmean;
	import java.util.ArrayList;
	import java.util.Map;
	import java.util.TreeMap;
	import java.util.*;

	public class Stopwords {
		public static String[] stopWordsofwordnet= {"a",",","about", "above", "above", "across", "after", "afterwards", "again", "against", "all", "almost",
	        "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "amoungst", "amount", "an", "and",
	        "another", "any", "anyhow", "anyone", "anything", "anyway", "anywhere", "are", "around", "as", "at", "back", "be", "became",
	        "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides",
	        "between", "beyond", "bill", "both", "bottom", "but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt",
	        "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either", "eleven", "else",
	        "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few",
	        "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from",
	        "front", "full", "further", "get", "give", "go", "had", "has", "hasnt",
	        "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself",
	        "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into",
	        "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many",
	        "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must",
	        "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none",
	        "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto",
	        "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own", "part", "per", "perhaps",
	        "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she",
	        "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something",
	        "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their",
	        "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon",
	        "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru",
	        "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until",
	        "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever",
	        "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while",
	        "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet",
	        "you", "your", "yours", "yourself", "yourselves", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1.", "2.", "3.", "4.", "5.", "6.", "11",
	        "7.", "8.", "9.", "12", "13", "14", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
	        "terms", "CONDITIONS", "conditions", "values", "interested.", "care", "sure", ".", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "{", "}", "[", "]", ":", ";", ",", "<", ".", ">", "/", "?", "_", "-", "+", "=",
	        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
	        "contact", "grounds", "buyers", "tried", "said,", "plan", "value", "principle.", "forces", "sent:", "is,", "was", "like",
	        "discussion", "tmus", "diffrent.", "layout", "area.", "thanks", "thankyou", "hello", "bye", "rise", "fell","primavera","technologies:a ","(such","hbase", "fall", "psqft.", "http://", "km", "miles", "and","data","is","hadoop"};

		public static ArrayList<String> wordsList = new ArrayList<String>();
		public static String replaceStopWords(String src)
		{
			String _ans="";
			String _src=src.replaceAll("\\s+", " ");
			_src=_src.replaceAll(":"," ");
			_src=_src.replaceAll("\\?"," ");
			
			_src=_src.toLowerCase();
			String []words=_src.split(" ");
					for(String word : words)
			{
				wordsList.add(word);
			}
			for (int i = 0; i < wordsList.size(); i++) {
				// get the item as string
				for (int j = 0; j < stopWordsofwordnet.length; j++) {
				if (stopWordsofwordnet[j].contains(wordsList.get(i))) {
				wordsList.remove(i);
				}
				}
				}
				for (String str : wordsList) {
					_ans+=str+" ";
				}
				wordsList.clear();
				return _ans;
		}
		public static int searchNav(byte[] input, byte[] searchedFor) {
	        //convert byte[] to Byte[]
	        Byte[] searchedForB = new Byte[searchedFor.length];
	        for(int x = 0; x<searchedFor.length; x++){
	            searchedForB[x] = searchedFor[x];
	        }

	        int idx = -1;

	        //search:
	        Deque<Byte> q = new ArrayDeque<Byte>(input.length);
	        for(int i=0; i<input.length; i++){
	            if(q.size() == searchedForB.length){
	                //here I can check
	                Byte[] cur = q.toArray(new Byte[]{});
	                if(Arrays.equals(cur, searchedForB)){
	                    //found!
	                    idx = i - searchedForB.length;
	                    break;
	                } else {
	                    //not found
	                    q.pop();
	                    q.addLast(input[i]);
	                }
	            } else {
	                q.addLast(input[i]);
	            }
	        }

	        return idx;
	    }
		public static String getFrequncy(String _words)
		{
			
			Map map = new TreeMap();
			
			int flag = 1;
			Integer ONE = new Integer(1);
		    String s1;
		    s1 = _words.toLowerCase();
	        String []arr=s1.split("\\s");
	        for(int x=0;x<arr.length;x++)
	        {
	        	        
	       
	            if (arr[x].length() > 0) {

	                Integer frequency = (Integer) map.get(arr[x]);
	                if (frequency == null) {
	                    frequency = ONE;
	                } else {
	                    int value = frequency.intValue();
	                    frequency = new Integer(value + 1);
	                }
	                map.put(arr[x], frequency);
	            }
	        }
	        
	       
	        return map.toString();

}
}
	
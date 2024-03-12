import java.util.HashSet;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;

public class WordBreakSparkified {

	public static void main(String[] args) {
		JavaSparkContext sc = new JavaSparkContext("local[*]","WordBreakSparkified");
		JavaRDD<String> inputtext = sc.textFile("filepath");
		
		String dict[] = {"you","hello","madhu","kar","madhukar"};
		Trie trie = new Trie();
		
		for(String s: dict) {
			trie.add(s);
		}
		
		Broadcast<Trie> broadcasttrie = sc.broadcast(trie);
		JavaRDD<HashSet<String>> results = inputtext.map(text -> getDistinctWords(text, broadcasttrie));
		List<String> combinedresults = results.flatMap(result -> result.iterator()).collect();
		
		System.out.println(combinedresults);
	}
	
	public static HashSet<String> getDistinctWords(String str,Broadcast<Trie> trie){
		HashSet<String> mappedwords = new HashSet<>();
		StringBuilder word = new StringBuilder();
		
		TrieNode temp = trie.value().root;
		
		for(int i=0;i<str.length();i++) {
			char c = str.charAt(i);
			word = word.append(c);
			
			if(temp.arr[(int)(c-97)] == null) {
				temp = trie.value().root;
				word = new StringBuilder();
			}else{
				temp = temp.arr[(int)(c-97)];
				if(temp.endofword == true) {
					mappedwords.add(word.toString());
					temp = trie.value().root;
					word = new StringBuilder();
				}
			}
		}
		
		return mappedwords;
	}

}
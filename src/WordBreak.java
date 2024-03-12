import java.util.HashSet;

public class WordBreak {

	public static void main(String[] args) {
		String str = "hellotheremadhukarhowareyoudoingtodaymadhukar";
		String dict[] = {"you","hello","madhu","kar","madhukar"};
		Trie trie = new Trie();
		
		for(String s: dict) {
			trie.add(s);
		}
		
		HashSet<String> answer = getDistinctWords(str,trie);
		
		System.out.println(answer);
	}
	
	public static HashSet<String> getDistinctWords(String str,Trie trie){
		HashSet<String> mappedwords = new HashSet<>();
		StringBuilder word = new StringBuilder();
		
		TrieNode temp = trie.root;
		
		for(int i=0;i<str.length();i++) {
			char c = str.charAt(i);
			word = word.append(c);
			
			if(temp.arr[(int)(c-97)] == null) {
				temp = trie.root;
				word = new StringBuilder();
			}else{
				temp = temp.arr[(int)(c-97)];
				if(temp.endofword == true) {
					mappedwords.add(word.toString());
					temp = trie.root;
					word = new StringBuilder();
				}
			}
		}
		
		return mappedwords;
	}
}
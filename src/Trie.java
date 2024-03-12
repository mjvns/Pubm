public class Trie {
	TrieNode root;
	
	public Trie(){
		this.root = new TrieNode();
	}
	
	public void add(String key) {
		TrieNode temp = root;
		
		for(int i=0;i<key.length();i++) {
			if(temp.arr[(int)(key.charAt(i)-97)] == null) {
				temp.arr[(int)(key.charAt(i)-97)] = new TrieNode();
			}
			temp = temp.arr[(int)(key.charAt(i)-97)];
		}	
			temp.endofword = true;
	}
	
	public boolean search(String key) {
		TrieNode temp = root;
		
		for(int i=0;i<key.length();i++) {
			char c = key.charAt(i);
			
			if(temp.arr[(int)(c-97)] == null) {
				return false;
			}
			temp = temp.arr[(int)(c-97)];
		}
		
		if(temp.endofword == true) {
			return true;
		}
		
		return false;
	}
	
}
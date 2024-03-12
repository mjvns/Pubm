public class TrieNode {
	TrieNode arr[];
	boolean endofword;
	
	public TrieNode(){
		this.arr = new TrieNode[26];
		this.endofword = false;
	}
}
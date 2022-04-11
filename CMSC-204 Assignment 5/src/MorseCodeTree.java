import java.util.ArrayList;
/**
 * This is a MorseCodeTree which is specifically used for the conversation of
 * morse code to english. It relies on a root (reference to root of the tree).
 * The root is set to null when the tree is empty. The class uses an external generic 
 * TreeNode class which consists of a reference to the data and a reference to the 
 * left and right child. The TreeNode is parameterized as a String, TreeNode 
 * This class uses a private member root (reference to a TreeNode)
 * The constructor will call the buildTree
 *  
 * @author Wayne Bonifacio
 *
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {

	private TreeNode<String> root;

	/**
	 * Constructor - calls the buildTree method
	 */
	public MorseCodeTree() {
		buildTree();
	}
	
	/**
	 * Returns a reference to the root
	 */
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}
	
	/**
	 * sets the root of the MorseCodeTree
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		this.root = newNode;
	}
	
	/**
	 * Adds element to the correct position in the tree based on the code 
	 * This method will call the recursive method addNode
	 * 
	 * @param code - the code for the new node to be added, example ".-."
	 * @param result - the data of the new TreeNode to be added
	 * @return the MorseCodeTree with the new TreeNode added
	 */
	@Override
	public MorseCodeTree insert(String code, String result) {
		addNode(this.root, code, result);
		return this;
	}
	
	/**
	 * This is a recursive method that adds element to the correct position 
	 * in the tree based on the code.
	 * 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of addNode
	 * @param letter the data of the new TreeNode to be added
	 */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		if(code.length() == 1) {
			if(code.charAt(0) == '.') {
				root.left = new TreeNode<String>(letter);
			} else if(code.charAt(0) == '-') {
				root.right = new TreeNode<String>(letter);
			}	
		} else {
			if(code.charAt(0) == '.') {
				addNode(root.left, code.substring(1), letter);
			} else if(code.charAt(0) == '-') {
				addNode(root.right, code.substring(1), letter);
			}
		}
	}
	
	/**
	 * Fetch the data in the tree based on the code
	 * This method will call the recursive method fetchNode
	 * 
	 * @param code the code that describes the traversals within the tree
	 * @return the result that corresponds to the code
	 */
	@Override
	public String fetch(String code) {
		return fetchNode(root, code);
	}
	
	/**
	 * This is the recursive method that fetches the data of the TreeNode
	 * that corresponds with the code
	 * 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of fetchNode
	 * @return the data corresponding to the code
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		String letter;
		if(code.length() <= 1) {
			if(code.equals(".")) {
				return root.left.data;
			} else {
				return root.right.data;
			}
		} else {
			if(code.charAt(0) == '.') {
				letter = fetchNode(root.left, code.substring(1));
			} else {
				letter = fetchNode(root.right, code.substring(1));
			}
		}
		return letter;
	}

	/**
	 * This operation is not supported for a LinkedConverterTree
	 * @param data data of node to be deleted
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This operation is not supported for a LinkedConverterTree
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This method builds the MorseCodeTree by inserting the nodes of the tree 
	 * level by level based on the code. The root will have a value of "" (empty string) 
	 * level one: insert(".", "e"); insert("-", "t"); level two: insert("..", "i"); 
	 * insert(".-", "a"); insert("-.", "n"); insert("--", "m"); etc. 
	 * Look at the tree and the table of codes to letters in the assignment description.
	 */
	@Override
	public void buildTree() {
		// Level 0
		root = new TreeNode<String>(""); 

		// Level 1
		insert(".", "e");
		insert("-", "t");

		// Level 2
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");

		// Level 3
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");

		// Level 4
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
	}
	
	/**
	 * Returns an ArrayList of the items in the linked Tree in LNR (Inorder) 
	 * Traversal order Used for testing to make sure tree is built correctly
	 * @return an ArrayList of the items in the linked Tree
	 */
	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> myArray = new ArrayList<>();
		LNRoutputTraversal(root, myArray);
		return myArray;
	}
	
	/**
	 * The recursive method to put the contents of the linked converter tree in an ArrayList<T> 
	 * LNR (Inorder)
	 * @param root the root of the tree for this particular recursive instance
	 * @param list the ArrayList that will hold the contents of the tree in LNR order
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if(root.left != null) {
			LNRoutputTraversal(root.left, list);
		}
		list.add(root.data);
		if(root.right != null) {
			LNRoutputTraversal(root.right, list);
		}
	}
	


}

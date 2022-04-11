/**
 * 
 * @author Wayne Bonifacio
 * The external Tree Node for Linked Trees
 * @param <T> data type of TreeNode
 */
public class TreeNode<T> {
	protected T data;
	protected TreeNode<T> left, right;
	
	/**
	 * Create a new TreeNode with left and right child set to null and data set
	 * to the dataNode
	 * @param dataNode
	 */
	public TreeNode(T dataNode) {
		this.data = dataNode;
		this.left = null;
		this.right = null;
	}
	
	/**
	 * used for making deep copies
	 * @param node
	 */
	public TreeNode(TreeNode<T> node) {
		this.data = node.data;
		this.left = node.left;
		this.right = node.right;
	}
	
	/**
	 * Return the data within this TreeNode
	 * @return the data within the TreeNode 
	 */
	public T getData() {
		return data;
	}
}

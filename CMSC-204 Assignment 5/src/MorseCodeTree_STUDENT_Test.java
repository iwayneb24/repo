import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class MorseCodeTree_STUDENT_Test {
	
	MorseCodeTree morseTree = new MorseCodeTree();
	
	@BeforeEach
	public void setUp() throws Exception {
		
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testGetRoot() {
		assertEquals("", morseTree.getRoot().getData());
	}
	
	@Test
	public void testSetRoot() {
		TreeNode<String> tempNode = new TreeNode<>(".");
		morseTree.setRoot(tempNode);
		assertEquals(".", morseTree.getRoot().getData());
	}
	
	@Test
	public void testFetch() {
		assertEquals("x", morseTree.fetch("-..-"));
		assertEquals("l", morseTree.fetch(".-.."));
	}
	
	@Test
	public void testToArrayList() {
		assertEquals("[h, s, v, i, f, u, e, l, r, a, p, w, j, , b, d, x, n, c, k, y, t, z, g, q, m, o]", 
				morseTree.toArrayList().toString());
	}
	
	@Test
	public void testBuildTree() {
		
		ArrayList<String> arrayTree = morseTree.toArrayList();
		
		String finalTree = "";
		for (int i = 0; i < arrayTree.size(); i++) {
			finalTree += arrayTree.get(i) + " ";
		}
		
		assertEquals("h s v i f u e l r a p w j  b d x n c k y t z g q m o", finalTree.trim());
	}
	
	
	
}

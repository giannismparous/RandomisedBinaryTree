
public class BSTree {
	private class TreeNode {
		 Suspect item;
		 TreeNode left;
		 TreeNode right;
		 int N;
		 
		 TreeNode(Suspect item) {
			 this.item=item;
		 }
	}
	private TreeNode root;
	int count;
	int k;
	
	BSTree(int k) {
		root=null;
		count=1;
		this.k=k;
	}
	
	int updateCounter(TreeNode h) {
		if (h==null)return 0;
		h.N=1+updateCounter(h.left)+updateCounter(h.right);
		return h.N;
	}
	
	public void insert(Suspect item) {
		 root = insertR(root, item);
		 updateCounter(root);
		 
	}
	
	private TreeNode insertR(TreeNode h, Suspect item) {
		if (h == null) return new TreeNode(item);
		if (less(item.getSus(), h.item.getSus()))
		h.left = insertR(h.left, item);
		else h.right = insertR(h.right, item);
		return h; } 
	
	void remove(double sus) {
		root = removeR(root, sus);
		updateCounter(root);
	}
	
	private TreeNode removeR(TreeNode h, double sus) {
		if (h == null) return null;
		double w = h.item.getSus();
		if (less(sus, w)) h.left = removeR(h.left, sus);
		if (less(w, sus)) h.right = removeR(h.right, sus);
		if (equals(sus, w)) h = joinLR(h.left, h.right);
		return h; } 

	private TreeNode joinLR(TreeNode a, TreeNode b) {
		if (b == null) return a;
		b = partR(b, 0);
		b.left = a;
		return b; } 
	
	TreeNode partR(TreeNode h, int k) {
		int t = (h.left == null) ? 0 : h.left.N;
		if (t > k) {
		h.left = partR(h.left, k);
		h = rotR(h); }
		if (t < k) {
		h.right = partR(h.right, k-t-1);
		h = rotL(h); }
		return h; } 
	
	public Suspect getMinSus(TreeNode current) {
		  while (current.left != null) { 
	            current = current.left; 
	        } 
		  return current.item;
	}
	
	public void inorderPrint(TreeNode h) {
		if (h == null) return;
		inorderPrint(h.left);
		System.out.println(k+1-count ++ + "." +h.item);
		inorderPrint(h.right);
	}
	
	public boolean isEmpty() {return root==null;}
	
	public TreeNode getRoot() {return root;}
	
	public int getRootN() {return root.N;}
	
	boolean less(double a,double b) {
		return a<b;
	}
	
	boolean equals(double a,double b) {
		return a==b;
	}
	
	private TreeNode rotL(TreeNode h) {
		TreeNode x = h.right; h.right = x.left; x.left = h; return x; 
	}
	
	private TreeNode rotR(TreeNode h) {
		TreeNode x = h.left; h.left = x.right; x.right = h; return x; 
	}
	
}

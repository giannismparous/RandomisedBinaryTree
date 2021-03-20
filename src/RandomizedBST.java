import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RandomizedBST implements MafiaInterface {
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
	private List SuspectList;
	int savingsSum;
	int count;//gia arithmish stoixeiwn pou ektypwnontai
	BSTree TreeK;//vohthitikh domh (aplo dda)
	
	
	RandomizedBST() {
		root=null;
		SuspectList=new List();
		count=1;
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
		if (h == null) {
			return new TreeNode(item);
		}
		if (Math.random()*(h.N+1) < 1.0) {
		return insertAsRoot(h, item);
		}
		if (item.key() < h.item.key()) {
		h.left = insertR(h.left, item);
		}
		else
		{
		h.right = insertR(h.right, item);
		}
		return h; 
		}
	
	
	private TreeNode insertAsRoot(TreeNode h, Suspect item) {
		if (h == null) return new TreeNode(item);
		if (item.key() < h.item.key()) {
			h.left = insertAsRoot(h.left, item);
			h = rotR(h); 
		}
		else {
			h.right = insertAsRoot(h.right, item);
			h = rotL(h); 
		}
		return h; 
	} 
	
	
	boolean less(int a,int b) {
		return a<b;
	}
	
	boolean equals(int a,int b) {
		return a==b;
	}
	
	private TreeNode rotL(TreeNode h) {
		TreeNode x = h.right; h.right = x.left; x.left = h; return x; 
	}
	
	private TreeNode rotR(TreeNode h) {
		TreeNode x = h.left; h.left = x.right; x.right = h; return x; 
	}

	public void load(String filename) {
		
		try {
		File file = new File(filename);
        Scanner myReader=new Scanner(file);
        String temp[]=new String[5];
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            temp=data.split(" ",5);
            insert(new Suspect(Integer.parseInt(temp[0]),temp[1],temp[2],Double.parseDouble(temp[3]),Double.parseDouble(temp[4])));
			}
        myReader.close();
		}
		catch (FileNotFoundException e) {
            System.out.println("An error occurred trying reading the file.");
            e.printStackTrace();
          }
		
	}

	private void searchChange(TreeNode h,int AFM,double savings) {
		if (h == null) {System.out.println("AFM not found.");return;}
		if (equals(AFM, h.item.key())) {h.item.setSavings(savings);return;}
		if (less(AFM, h.item.key())) searchChange(h.left, AFM, savings);
		else searchChange(h.right, AFM, savings);
		} 

	public void updateSavings(int AFM, double savings) {
		searchChange(root,AFM,savings);
	}

	private Suspect searchR(TreeNode h, int key) {
		if (h == null) return null;
		if (equals(key, h.item.key())) {return h.item;}
		if (less(key, h.item.key())) return searchR(h.left, key);
		else return searchR(h.right, key); } 
	
	public Suspect searchByAFM(int AFM) {
		return searchR(root,AFM);
	}

	private void searchMultipleNames(TreeNode h,String lastName){
		if (h == null) {return;}
		if (lastName.equals( h.item.getlastName())) SuspectList.insertAtBack(h.item);
		searchMultipleNames(h.left,lastName);
		searchMultipleNames(h.right,lastName);
	}
	
	public List searchByLastName(String last_name){
		SuspectList=new List();
		searchMultipleNames(root,last_name);
		if (SuspectList.size()==0) System.out.println("Last Name not found.");
		if (SuspectList.size()<=5)SuspectList.print();
		return SuspectList;
	}

	private TreeNode joinLR(TreeNode a, TreeNode b) {
		if (a == null) return b;
		if (b == null) return a;
		int N = a.N + b.N;
		if (Math.random()*N < 1.0*a.N) {
		a.right = joinLR(a.right, b);
		return a; }
		else { b.left = joinLR(a, b.left); return b; }
		}
	
	private TreeNode removeR(TreeNode h, int key) {
		if (h == null) return null;
		if (less(key, h.item.key())) {h.left = removeR(h.left, key);}
		if (less(h.item.key(), key)) h.right = removeR(h.right, key);
		if (equals(key, h.item.key())) h = joinLR(h.left, h.right);
		return h; }
	
	
	public void remove(int AFM) {
		root = removeR(root,AFM);
		updateCounter(root);
	}
	

	private void preorderTraverseSum(TreeNode h) {
		if (h == null) return;
		savingsSum+=h.item.getSavings(); 
		preorderTraverseSum(h.left);
		preorderTraverseSum(h.right);
	}
	
	public double getMeanSavings() {
		preorderTraverseSum(root);
		double average= savingsSum/root.N;
		savingsSum=0;
		return average;
	}
	
	private void preorderTraverseDynamicK(TreeNode h,int k) {
		if (h == null) return;
		if (TreeK.isEmpty() || TreeK.getRootN()<k)TreeK.insert(h.item);
		else {
			Suspect minSuspect=TreeK.getMinSus(TreeK.getRoot());
			if (minSuspect.getSus()<h.item.getSus()) {
				TreeK.remove(minSuspect.getSus());
				TreeK.insert(h.item);
			}
		}
		preorderTraverseDynamicK(h.left,k);
		preorderTraverseDynamicK(h.right,k);
	}
	
	public void printTopSuspects(int k) {
		TreeK = new BSTree(k);
		preorderTraverseDynamicK(root,k);
		TreeK.inorderPrint(TreeK.getRoot());
		}

	private void inorderPrint(TreeNode h) {
		if (h == null) return;
		inorderPrint(h.left);
		System.out.println(count++ + "." + h.item);
		inorderPrint(h.right);
	}
	
	public void printByAFM() {
		count=1;
		inorderPrint(root);
	}

}

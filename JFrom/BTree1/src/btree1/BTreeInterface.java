package BTree1;

public interface BTreeInterface {

	
	public final static int M = 340;
	public final static String index_file_addr = "index.txt";
	public final static String conf_file_addr = "conf.txt";
	public final static int BLOCKSIZE = 1024 * 4;
	
	public static final int DEGREE = 5;
	public static final int LEFT = 3;
	public static final int RIGHT = 3;

	
	public abstract int getRootBID();

	public abstract void setRootBID(int bid);

	
	public abstract boolean writeNode(BTNode node);

	
	public abstract BTNodeInterface readNode(int blockID);

	
	public abstract boolean deleteNode(int blockID);

	
	public abstract int getFirstAvail();

	
	public abstract void delete(long k);

	public abstract void InternalDelete(long k, BTNode t) throws NokeyException
	;

	
	public abstract void moveFromNode(BTNode node1, BTNode node2, boolean flag);

	public abstract void InsertOne(long data);

	
	public abstract boolean search(long data);

}
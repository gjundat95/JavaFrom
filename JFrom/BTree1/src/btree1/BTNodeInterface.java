package BTree1;

public interface BTNodeInterface {

	public static final int M = 340; //BÊ÷µÄ½×Êý-1

	public abstract int getTag();

	public abstract int getLen();

	public abstract long[] getKey();

	public abstract long getKey(int i);

	public abstract int[] getPointer();

	public abstract int getPointer(int i);

	public abstract int getLoc();

	public abstract void setTag(int i);

	public abstract void setLen(int i);

	public abstract void setKey(long[] k);

	public abstract void setKey(int i, long k);

	public abstract void setPointer(int[] k);

	public abstract void setPointer(int i, int k);

	public abstract void setLoc(int i);

}
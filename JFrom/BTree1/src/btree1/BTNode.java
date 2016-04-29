package BTree1;



public class BTNode implements BTNodeInterface
{
	public int tag;		
	public int loc;		
	public int len;		
	public long[] key;		
	public int[] pointer;	
	
	public BTNode()
	{
		tag = 0;
		loc = 0;
		len = 0;
		key = new long[M];
		pointer = new int[M + 1];
	}
	
	/* (non-Javadoc)
	 * @see BTree.BTNodeInterface#getTag()
	 */
	@Override
	public int getTag()
	{
		return tag;
	}
	
	/* (non-Javadoc)
	 * @see BTree.BTNodeInterface#getLen()
	 */
	@Override
	public int getLen()
	{
		return len;
	}
	
	/* (non-Javadoc)
	 * @see BTree.BTNodeInterface#getKey()
	 */
	@Override
	public long[] getKey()
	{
		return key;
	}
	
	/* (non-Javadoc)
	 * @see BTree.BTNodeInterface#getKey(int)
	 */
	@Override
	public long getKey(int i)
	{
		return key[i];
	}
	
	/* (non-Javadoc)
	 * @see BTree.BTNodeInterface#getPointer()
	 */
	@Override
	public int[] getPointer()
	{
		return pointer;
	}
	
	/* (non-Javadoc)
	 * @see BTree.BTNodeInterface#getPointer(int)
	 */
	@Override
	public int getPointer(int i)
	{
		return pointer[i];
	}
	
	/* (non-Javadoc)
	 * @see BTree.BTNodeInterface#getLoc()
	 */
	@Override
	public int getLoc()
	{
		return loc;
	}
	
	/* (non-Javadoc)
	 * @see BTree.BTNodeInterface#setTag(int)
	 */
	@Override
	public void setTag(int i)
	{
		tag = i;
	}
	
	/* (non-Javadoc)
	 * @see BTree.BTNodeInterface#setLen(int)
	 */
	@Override
	public void setLen(int i)
	{
		len = i;
	}
	
	/* (non-Javadoc)
	 * @see BTree.BTNodeInterface#setKey(long[])
	 */
	@Override
	public void setKey(long[] k)
	{
		key = k;
	}
	
	/* (non-Javadoc)
	 * @see BTree.BTNodeInterface#setKey(int, long)
	 */
	@Override
	public void setKey(int i, long k)
	{
		key[i] = k;
	}
	
	/* (non-Javadoc)
	 * @see BTree.BTNodeInterface#setPointer(int[])
	 */
	@Override
	public void setPointer(int[] k)
	{
		pointer = k;
	}
	
	/* (non-Javadoc)
	 * @see BTree.BTNodeInterface#setPointer(int, int)
	 */
	@Override
	public void setPointer(int i, int k)
	{
		pointer[i] = k;
	}
	
	/* (non-Javadoc)
	 * @see BTree.BTNodeInterface#setLoc(int)
	 */
	@Override
	public void setLoc(int i)
	{
		loc=i;
	}
}

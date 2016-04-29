package BTree1;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Stack;



public class BTree implements BTreeInterface 
{
	final static int Threshold=170;
	static int flag; 
	
	public static int fin=-1;
	public static long fda=0;
	public static int Ftag=0;
	
	
	public BTree()
	{
		
		try{
			RandomAccessFile ra=new RandomAccessFile(index_file_addr,"rw");
			byte[] blockInfo=new byte[BLOCKSIZE];
			for(int i=0;i<blockInfo.length;i++)
			   blockInfo[i]=0;
			ra.write(blockInfo);
			ra.close();
			setRootBID(-1);
			
		}catch(Exception e){
		   e.printStackTrace();
		}
	}
	public BTree(int i)
	{
		
	}
    
	
	@Override
	public int getRootBID()
	{
		BufferedReader input;
		try
		{
			input=new BufferedReader(new FileReader(conf_file_addr));
			int bid=Integer.parseInt(input.readLine());
			input.close();
			return bid;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
		
	}
	
	@Override
	public void setRootBID(int bid)
	{
		PrintWriter output;
		try
		{
			output=new PrintWriter(new FileOutputStream(conf_file_addr));
			output.println(bid);
			output.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
    @Override
	public boolean writeNode(BTNode node)
    {
       
       int tag=node.tag;
	   int blockid=node.loc;
	   int len=node.len;
	   long[] k=node.key;
	   int[] p=node.pointer;
	   //Éú³ÉÒ»¸öbyteÊý×é
	   byte[] nodeInfo;
	   nodeInfo=new byte[BLOCKSIZE];
	   int i,j;
	   for (i = 0;i < 4; i++) 
	   {
           nodeInfo[i] = (byte)(tag>>(8*i) & 0xFF ); 
           nodeInfo[4+i] = (byte)(blockid>>(8*i)& 0xFF ); 
           nodeInfo[8+i] = (byte)(len>>(8*i)& 0xFF ); 
	   }
	   for(i=0;i<len;i++)
		   for(j=0;j<8;j++)
			   nodeInfo[12+8*i+j]=(byte)(k[i]>>(8*j) & 0xFF ); 
	   for(i=0;i<=len;i++)
		   for(j=0;j<4;j++)
			   nodeInfo[12+8*M+4*i+j]=(byte)(p[i]>>(8*j)& 0xFF ); 
	  
	   RandomAccessFile ranfile;
	   try
	   {
	      ranfile=new RandomAccessFile(index_file_addr,"rw");
          ranfile.skipBytes(BLOCKSIZE*blockid);	
          ranfile.write(nodeInfo);
          ranfile.close();
          return true;
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
		   return false;
	   }	   
    }
  
@Override
public BTNode readNode(int blockID)
   {
	   BTNode node=new BTNode();
	   byte[] nodeInfo=new byte[BLOCKSIZE];
	   
	   RandomAccessFile ranfile;
	   try
	   {
	     ranfile=new RandomAccessFile(index_file_addr,"rw");
	     ranfile.skipBytes(BLOCKSIZE*blockID);	
	     ranfile.read(nodeInfo);
         ranfile.close();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }    
	   node.tag=byteToInt(nodeInfo,0);
	   node.loc=byteToInt(nodeInfo,4);
	   int len=byteToInt(nodeInfo,8);
	   node.len=len;
	   long[] k=new long[M];
 	   for(int i=0;i<len;i++)
 		   k[i]=byteToLong(nodeInfo,12+8*i);
 	   node.key=k;
 	   int[] p=new int[M+1];
 	   for(int i=0;i<=len;i++)
 		   p[i]=byteToInt(nodeInfo,12+M*8+4*i);
 	   node.pointer=p;
 	   return node;
		   
   }
  
@Override
public boolean deleteNode(int blockID)
   {
	   byte[] blockInfo=new byte[BLOCKSIZE];
	  
	   RandomAccessFile ranfile;
	   try
	   {
	     ranfile=new RandomAccessFile(index_file_addr,"rw");	 
	     ranfile.read(blockInfo);
         ranfile.close();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }    
	   byte temp=blockInfo[(blockID-1)/8];
	   int offset=(blockID-1)%8;
	   switch(offset)
	   {
		   case 7:temp=(byte) (temp & 0xFE);break;
		   case 6:temp=(byte) (temp & 0xFD);break;
		   case 5:temp=(byte) (temp & 0xFB);break;
		   case 4:temp=(byte) (temp & 0xF7);break;
		   case 3:temp=(byte) (temp & 0xEF);break;
		   case 2:temp=(byte) (temp & 0xDF);break;
		   case 1:temp=(byte) (temp & 0xBF);break;
		   case 0:temp=(byte) (temp & 0x7F);break;
	   }
	   blockInfo[(blockID-1)/8]=temp;
	   try
	   {
	      ranfile=new RandomAccessFile(index_file_addr,"rw");
          ranfile.write(blockInfo);
          ranfile.close();
          return true;
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
		   return false;
	   }	   
	   
   }
  
@Override
public int getFirstAvail()
   {
	   byte[] blockInfo=new byte[BLOCKSIZE];
	  
	   RandomAccessFile ranfile;
	   try
	   {
	     ranfile=new RandomAccessFile(index_file_addr,"rw");	 
	     ranfile.read(blockInfo);
         ranfile.close();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   int i,j,offset=0;
	   for(i=0;i<BLOCKSIZE;i++)
		   if(blockInfo[i]!=(byte)0xFF)
			   break;
	   byte temp=blockInfo[i];
	  	   
	   for(j=0;j<8;j++)
	      if((byte)(temp & 0x80>>j)==(byte)0x00)
	      {
		    offset=j;
		    temp=(byte)(temp | 0x80>>j);
		    break;
	      }
	   int bid=8*i+offset+1;
	   blockInfo[i]=temp;
	   try
	   {
	      ranfile=new RandomAccessFile(index_file_addr,"rw");
          ranfile.write(blockInfo);
          ranfile.close();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }	 
	   return bid;
   }
   
   private int byteToInt(byte[] b,int offset)
   {
	   int i,out=0;
	   byte temp;
	   for(i=0;i<4;i++)
	   {
		   temp=b[offset+i];
		   out+=(temp & 0xFF) << (8 * i);
	   }
	   return out;
   }
  
   private int byteToLong(byte[] b,int offset)
   {
	   int i,out=0;
	   byte temp;
	   for(i=0;i<8;i++)
	   {
		   temp=b[offset+i];
		   out+=(temp & 0xFF) << (8 * i);
	   }
	   return out;
   }
	

	
	@Override
	public void delete(long k)
	{
		BTNode t=null;	
		int rbid=getRootBID();
		t=readNode(rbid);
		flag=0;
		int len=t.len;
		try
	    {	
			InternalDelete(k, t);
		    if (t.len == 0)
		   
		    {
		      deleteNode(rbid);
		      if(t.tag==1)
		    	  setRootBID(-1);
		      else
		          setRootBID(t.pointer[0]);
		    }
		    else if(t.len!=len&&t.len!=0)
		    	writeNode(t);
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e.getMessage());
	    	//e.printStackTrace();
	    }
	}
	
	@Override
	public void InternalDelete(long k,BTNode t) throws NokeyException
	{
	    int i,j,m;
	    boolean nextFlag=false,orderFlag=false;
	    BTNode child,l=null,r=null;	  		  	 
       
	    for(i=0,j=t.len-1,m=(i+j)/2; i<j;)
	    {
	    	if(k>t.key[m])
	    		i=m+1;
	    	else
	    		j=m;
	    	 m=(j+i)/2;
	    }
	    if(t.tag==1)
	    {
	    	if(k==t.key[ m ])
	    	{
	    		for(i=m;i<t.len-1;i++)
	    			t.key[i]=t.key[i+1];
	    		for(i=m;i<t.len;i++)
	    			t.pointer[i]=t.pointer[i+1];
	    		t.len--;
		    	if(t.len<Threshold)
		          flag=1;
		        return;
	    	}
	    	else
	    		throw new NokeyException(); 	   
	    }
	    else
	    {
	    	if(k>=t.key[m])
	    	{
	    		child=readNode(t.pointer[m+1]);
	    		nextFlag=true;
	    	}
	    	else
	    		child=readNode(t.pointer[m]);
	    	InternalDelete(k,child);
	    }	    	

	    
	    if (flag == 0)
	    {
	    	
	    	if(child.tag==1&&k==t.key[m])
	    	{
	    		t.key[m]=child.key[0];	    		
	    		writeNode(t);
	    	}
	    	writeNode(child);
	    	return;
	    }
	 
    	if(!orderFlag)
    	{
    		if(!nextFlag)
    		{
    			if(m==0)
    				orderFlag=true;   
    			else
    		       l=readNode(t.pointer[m-1]);
    		}
    		else
    			l=readNode(t.pointer[m]);
    		if(!orderFlag&&l.len>Threshold)
    		{
    			if(child.tag==1)
    			{
    				
        			moveFromNode(child,l,orderFlag);
        			writeNode(child);
        			writeNode(l);
        			flag=0;
        			if(nextFlag)
        	    	{
        	    		t.key[m]=child.key[0];	    		
        	    		writeNode(t);
        	    	}
        			else
        			{
        				t.key[m-1]=child.key[0];	    		
        	    		writeNode(t);
        			}
    			}
    			else
    			{
        			flag=0;
        			for(i=child.len;i>0;i--)
    					child.key[i]=child.key[i-1];
        			for(i=child.len+1;i>0;i--)
        				child.pointer[i]=child.pointer[i-1];
        			if(nextFlag)
        	    	{        				
        				child.key[0]=t.key[m];
        				child.pointer[0]=l.pointer[l.len];
        	    		t.key[m]=l.key[l.len-1];	    		       	    		
        	    	}
        			else
        			{
        				child.key[0]=t.key[m-1];
        				child.pointer[0]=l.pointer[l.len];
        				t.key[m-1]=l.key[l.len-1];	    		
        			}
        			child.len++;
        			l.len--;
        			writeNode(l);
        			writeNode(t);
        			writeNode(child);
    			}
    			
    			return;
    		}
    		
    		else
    			orderFlag=true;
    	}
    	if(orderFlag)
    	{
    		if(!nextFlag)
    		    r=readNode(t.pointer[m+1]);
    		else
    		{
    			if(m>=t.len-1)
    				orderFlag=false;
    			else
    				r=readNode(t.pointer[m+2]);
    		}
    		if(orderFlag&&r.len>Threshold)
    		{
    			if(child.tag==1)
    			{
    				
        			moveFromNode(child,r,orderFlag);
        			writeNode(child);
        			writeNode(r);
        			flag=0;
        			
        			if(!nextFlag)
        	    	{
        	    		t.key[m]=r.key[0];	    		
        	    		writeNode(t);
        	    	}
        			else
        			{
        				t.key[m+1]=r.key[0];	    		
        	    		writeNode(t);
        			}
        			return;
    			}
    			else
    			{
        			flag=0;
        			//ÐÞ¸ÄË÷Òý¼üÖµ
        			if(!nextFlag)
        	    	{
        				child.key[child.len]=t.key[m];
        				child.pointer[child.len+1]=r.pointer[0];
        	    		t.key[m]=r.key[0];
        	    	}
        			else
        			{
        				child.key[child.len]=t.key[m+1];
        				child.pointer[child.len+1]=r.pointer[0];
        				t.key[m+1]=r.key[0];	    		
        			}
        			for(i=0;i<r.len-1;i++)
        				r.key[i]=r.key[i+1];
        			for(i=0;i<r.len;i++)
        				r.pointer[i]=r.pointer[i+1];
        			child.len++;
        			r.len--;
        			writeNode(child);
        			writeNode(r);
        			writeNode(t);
        			return;
    			}   			
    		}
    		//ÓÒÐÖµÜµÄkeyÖµÊý=Threshold
    	}
	    if(flag==1)//ÐÖµÜ½Úµã½èÖµÊ§°Ü£¬½«child½ÚµãºÏ²¢µ½ËüµÄ×óÐÖµÜ,²¢ÐÞ¸ÄtµÄkeyÖµºÍpÖµ
	    {
	    	if(m==0&&!nextFlag)//´Ë½ÚµãÖ»ÓÐÓÒÐÖµÜ
	    	{	    
	    		if(child.tag==1)//Ò¶½ÚµãµÄºÏ²¢
	    		{
	    			for(i=0;i<r.len;i++)
			    		child.key[child.len+i]=r.key[i];
			    	for(i=0;i<=r.len;i++)
			    		child.pointer[child.len+1+i]=r.pointer[i];
			    	child.len+=r.len;	    	
			    	for(i=m;i<t.len-1;i++)
			    		t.key[i]=t.key[i+1];
			    	for(i=m+1;i<t.len;i++)
			    	    t.pointer[i]=t.pointer[i+1];
			    	t.len--;   	
			    	if(t.len>=Threshold)
			    		flag=0;
			    	deleteNode(r.loc);
			    	writeNode(child);
	    		}
	    		else//ÄÚ²¿½ÚµãµÄºÏ²¢
	    		{
	    			child.key[child.len]=t.key[0];
	    			for(i=0;i<r.len;i++)
			    		child.key[child.len+1+i]=r.key[i];
			    	for(i=0;i<=r.len;i++)
			    		child.pointer[child.len+1+i]=r.pointer[i];
			    	child.len=child.len+r.len+1;	    	
			    	for(i=m;i<t.len-1;i++)
			    		t.key[i]=t.key[i+1];
			    	for(i=m+1;i<t.len;i++)
			    	    t.pointer[i]=t.pointer[i+1];
			    	t.len--;   	
			    	if(t.len>=Threshold)
			    		flag=0;System.out.println("child.key0="+child.key[0]);
			    	deleteNode(r.loc);
			    	writeNode(child);
	    		}
	    	}
	    	else
	    	{	  
	    		if(child.tag==1)//Ò¶×Ó½Úµã
	    		{
	    			for(i=0;i<child.len;i++)
			    		l.key[l.len+i]=child.key[i];
			    	for(i=0;i<=child.len;i++)
			    		l.pointer[l.len+1+i]=child.pointer[i];
			    	l.len+=child.len;	    	
			    	if(!nextFlag)
			    	{
			    	   for(i=m-1;i<t.len-1;i++)
			    		   t.key[i]=t.key[i+1];
			    	   for(i=m;i<t.len;i++)
			    		   t.pointer[i]=t.pointer[i+1];
			    	   t.len--;
			    	}
			    	else
			    	{
			    		for(i=m;i<t.len-1;i++)
				    		t.key[i]=t.key[i+1];
				    	for(i=m+1;i<t.len;i++)
				    		t.pointer[i]=t.pointer[i+1];
				    	t.len--;
			    	}	    	
			    	if(t.len>=Threshold)
			    		flag=0;
			    	
			    	deleteNode(child.loc);
			    	writeNode(l);
	    		}
	    		else//ÄÚ²¿½ÚµãÓë×óÐÖµÜºÏ²¢
	    		{
	    			for(i=0;i<child.len;i++)
			    		l.key[l.len+1+i]=child.key[i];
			    	for(i=0;i<=child.len;i++)
			    		l.pointer[l.len+1+i]=child.pointer[i];			    		    	
			    	if(!nextFlag)
			    	{
			    	   l.key[l.len]=t.key[m-1];
			    	   for(i=m-1;i<t.len-1;i++)
			    		   t.key[i]=t.key[i+1];
			    	   for(i=m;i<t.len;i++)
			    		   t.pointer[i]=t.pointer[i+1];
			    	   t.len--;
			    	}
			    	else
			    	{
			    		l.key[l.len]=t.key[m];
			    		for(i=m;i<t.len-1;i++)
				    		t.key[i]=t.key[i+1];
				    	for(i=m+1;i<t.len;i++)
				    		t.pointer[i]=t.pointer[i+1];
				    	t.len--;
			    	}
			    	l.len=child.len+l.len+1;
			    	if(t.len>=Threshold)
			    		flag=0;
			    	
			    	deleteNode(child.loc);
			    	writeNode(l);
	    		}
		    	
	    	}
	    }
	}
	/*
	 * ´Ó½Úµãnode2ÖÐÒÆ¶¯Ò»¸ökey¼°ÆäÖ¸Õëµ½½Úµãnode1ÖÐ£¬
	 * flag=0±íÊ¾node2Îªnode1µÄ×ó½Úµã£¬flag=1±íÊ¾node2Îªnode1µÄÓÒ½Úµã
	 */
    /* (non-Javadoc)
	 * @see BTree.BTreeInterface#moveFromNode(BTree.BTNode, BTree.BTNode, boolean)
	 */
    @Override
	public void moveFromNode(BTNode node1,BTNode node2,boolean flag)
    {
    	//node2Îª×óÐÖµÜ£¬½«node2µÄ×î´óÖµÒÆ¶¯µ½node1µÄÊ×Î»
    	if(!flag)
    	{
    		for(int i=node1.len;i>0;i--)
        		node1.key[i]=node1.key[i-1];
        	for(int i=node1.len+1;i>0;i--)
        		node1.pointer[i]=node1.pointer[i-1];
        	node1.len++;
        	//key[0]¸³ÖµÎªÔ­pointer[0]º¢×ÓµÄÊ×¸öÖµ
        	if(node1.tag==1)
        		node1.key[0]=node2.key[node2.len-1];
        	else
        	{
    	       BTNode child=readNode(node1.pointer[0]);
    	       node1.key[0]=child.key[0];
        	}
    	    node1.pointer[0]=node2.pointer[node2.len];
    	    node2.len--;
    	}
    	else//node2ÎªÓÒÐÖµÜ£¬½«node2µÄ×îÐ¡ÖµÒÆµ½node1µÄ×îºóÎ»
    	{
    		//key[len]¸³ÖµÎªnode2.pointer[0]º¢×ÓµÄÊ×¸öÖµ
    		if(node1.tag==1)
    			node1.key[node1.len]=node2.key[0];
    		else
    		{
    			BTNode child=readNode(node2.pointer[0]);       		
        		node1.key[node1.len]=child.key[0];
    		}   
    		node1.len++;
    		node1.pointer[node1.len]=node2.pointer[0];
    		//µ÷Õûnode2
    		for(int i=0;i<node2.len-1;i++)
    			node2.key[i]=node2.key[i+1];
    		for(int i=0;i<node2.len;i++)
    			node2.pointer[i]=node2.pointer[i+1];
    		node2.len--;
    	}   	
    }

    ////////////////////////////////////////////²åÈë²¿·Ö///////////////////////////////////////////////////
    public static BTNode SimpleInsert(BTNode bt,long data){//Ò¶½Úµã²åÈë
		int len=bt.getLen();
		if(len<DEGREE)
			bt.setLen(len+1);
		else
			Ftag=1;
		int m=0;
		while(m<len&&bt.getKey(m)<data)m++;
		if(Ftag==1){
			if(m!=DEGREE){
				fin=bt.getPointer(len);
				fda=bt.getKey(len-1);
				for(int i=len-1;i>m;i--){
					bt.setKey(i, bt.getKey(i-1));
					bt.setPointer(i+1, bt.getPointer(i));
				}
				bt.setPointer(m+1, -1);
				bt.setKey(m, data);
			}
			else{
				fda=data;
				fin=bt.getPointer(len);
				bt.setPointer(len, -1);
			}
		}
		else{
			for(int i=len;i>m;i--){
				bt.setKey(i, bt.getKey(i-1));
				bt.setPointer(i+1, bt.getPointer(i));
			}
			bt.setKey(m, data);
			bt.setPointer(m+1, -1);
		}
		
		return bt;
	}
	
	public static BTNode SimpleInsert2(BTNode bt,long data,int pointer){//·ÇÒ¶½áµã²åÈë£¬°üÀ¨Ö¸Õë
		int len=bt.getLen();
		if(len<DEGREE)
			bt.setLen(len+1);
		else
			Ftag=1;
		int m=0;
		while(m<len&&bt.getKey(m)<data)m++;
		if(Ftag==1){
			if(m!=DEGREE){
				fin=bt.getPointer(len);
				fda=bt.getKey(len-1);
				for(int i=len-1;i>m;i--){
					bt.setKey(i, bt.getKey(i-1));
					bt.setPointer(i+1, bt.getPointer(i));
				}
				bt.setKey(m, data);
				bt.setPointer(m+1, pointer);
			}
			else{
				fda=data;
				fin=pointer;
			}
		}
		else{
			for(int i=len;i>m;i--){
				bt.setKey(i, bt.getKey(i-1));
				bt.setPointer(i+1, bt.getPointer(i));
			}
			bt.setKey(m, data);
			bt.setPointer(m+1, pointer);
		}
		
		return bt;
	}
	
	/* (non-Javadoc)
	 * @see BTree.BTreeInterface#InsertOne(long)
	 */
	@Override
	public  void InsertOne(long data){
		if(!search(data)){//½á¹ûÎªfalse
			Ftag=0;
			Stack<BTNode> s=new Stack<BTNode>();
			
			if(getRootBID()==-1){
				BTNode bt1=new BTNode();
				bt1.setKey(0,data);
				bt1.setLen(1);
				bt1.setLoc(getFirstAvail());
				bt1.setPointer(0, -1);
				bt1.setPointer(1, -1);
				bt1.setTag(1);//Ò¶½Úµã
				setRootBID(1);
				writeNode(bt1);
				return;
			}
			BTNode bt=readNode(getRootBID());
			int m;
			while(bt.getTag()==0){//·ÇÒ¶½áµã
				m=0;
				if(bt.getLen()==DEGREE){
					s.push(bt);
				}
				else{ 
					s.clear();
					s.push(bt);
				}
				while(m<bt.getLen()&&bt.getKey(m)<data)m++;
				bt=readNode(bt.getPointer(m));
			}
			//µ½ÁËÒ¶½áµãºó
			
			//ÏÂÃæÏÈ²åÈë£¬ÔÙÅÐ¶ÏÊÇ·ñÒª·ÖÁÑ
			bt=SimpleInsert(bt,data);
			int Ftag2=1;//¸¸½Úµã ÊÇ·ñÒª·ÖÁÑ£¬0²»·ÖÁÑ
			if(Ftag==1){//Ò¶½ÚµãÐèÒª·ÖÁÑ
				Ftag=0;
				BTNode Bbt=new BTNode();//´´½¨ÐÂ½Úµã
				int t=getFirstAvail();
				Bbt.setLoc(t);
				Bbt.setLen(RIGHT);
				Bbt.setTag(1);
				m=LEFT;
				for(int i=m;i<DEGREE;i++){//ÒÆÖÁÐÂ½Úµã
					Bbt.setKey(i-m, bt.getKey(i));
					Bbt.setPointer(i-m, bt.getPointer(i));
				}
				Bbt.setPointer(RIGHT-1, bt.getPointer(DEGREE));
				Bbt.setKey(RIGHT-1, fda);
				Bbt.setPointer(RIGHT, fin);
				bt.setPointer(LEFT, Bbt.getLoc());
				bt.setLen(LEFT);
				bt.setTag(1);
				writeNode(Bbt);
				writeNode(bt);
				int loc=Bbt.getLoc();
				long nda=Bbt.getKey(0);
				int temp=0;
				if(bt.getLoc()==getRootBID()){//¸ù½ÚµãÒª·ÖÁÑ
					BTNode root=new BTNode();
					root.setLoc(getFirstAvail());
					root.setLen(1);
					root.setKey(0, nda);
					root.setPointer(0, bt.getLoc());
					root.setPointer(1, loc);
					root.setTag(0);
					setRootBID(root.getLoc());
					writeNode(root);
					return;
				}
				do{//¸¸½ÚµãÊÇ·ñÐèÒª·ÖÁÑ
					BTNode Abt=(BTNode) s.pop();
					Ftag=0;
					Abt=SimpleInsert2(Abt,nda,loc);
					if(Ftag==0){
						Ftag2=0;
						writeNode(Abt);
						break;
					}
					else{//¸¸½ÚµãÒ²Òª·ÖÁÑ
						Ftag=0;
						Ftag2=1;
						m=LEFT;
						Bbt=new BTNode();
						Bbt.setLen(RIGHT);
						Bbt.setLoc(getFirstAvail());
						Bbt.setTag(0);
						for(int i=m;i<DEGREE;i++){//ÒÆÖÁÐÂ½Úµã
							Bbt.setKey(i-m, Abt.getKey(i));
							Bbt.setPointer(i-m, Abt.getPointer(i));
						}
						Bbt.setPointer(0,Abt.getPointer(LEFT));
						Bbt.setPointer(RIGHT-1, Abt.getPointer(DEGREE));
						Bbt.setKey(RIGHT-1, fda);
						Bbt.setPointer(RIGHT, fin);
						Abt.setPointer(LEFT, Bbt.getLoc());
						
						loc=Bbt.getLoc();
						nda=Abt.getKey(LEFT-1);Abt.setLen(LEFT-1);
						writeNode(Bbt);
						writeNode(Abt);
						temp=Abt.getLoc();
					}
				}while(s.empty()==false);
				if(Ftag2==1){//¸ù½ÚµãÒª·ÖÁÑ
					BTNode root=new BTNode();
					root.setLoc(getFirstAvail());
					root.setLen(1);
					root.setKey(0, nda);
					root.setPointer(0, temp);
					root.setPointer(1, loc);
					root.setTag(0);
					setRootBID(root.getLoc());
					writeNode(root);
				}
			}
			else
				writeNode(bt);
		}
	}

	//////////////////////////////////////////////////////²éÕÒ/////////////////////////////
	/* ²éÕÒÖµdata
	 * ·µ»ØÖµdataËùÔÚË÷Òý½áµãµÄ¿éºÅºÍKÖµ
	 * ÒÔ¼°²éÕÒ½á¹û*/
	/* (non-Javadoc)
	 * @see BTree.BTreeInterface#search(long)
	 */
	@Override
	public boolean search(long data){
		int m,id;
		BTNodeInterface bt = new BTNode();
		id = getRootBID();
		if(id==-1)
			return false;
		bt = readNode(id);                      //È¡Ë÷Òý¸ù½áµã
		while(bt.getTag()!=1){
			for(m = 0;bt.getKey(m)<data && m<bt.getLen();m++);
			bt = readNode(bt.getPointer(m));
		}
		//·ÃÎÊÒ¶½áµã
		for(m = 0;bt.getKey(m)<data && m<bt.getLen();m++);
		if(m == bt.getLen()) return false;
		if(data == bt.getKey(m)) return true;
		else return false;
	}
}

package BTree1;
import btree1.UI.MainFrame;
import java.io.*;





public class BTreeApp {
	public final static int M=340;
	final static int Threshold=170;
	public final static String index_file_addr ="index.txt";
	public final static String conf_file_addr ="conf.txt";
	public final static int BLOCKSIZE=1024*4;
	static int flag;
    public static void main(String[] args)
    {   	
    	BTreeInterface btree =new BTree();
    	
    	try
    	{
    		BufferedReader input = new BufferedReader(new FileReader("all200000.txt"));
    		for(int i = 0; i < 10; i++)
    		{
    			long k = Long.parseLong(input.readLine());
    			btree.InsertOne(k);
    		}
    		input.close();
    		new MainFrame(btree);
    		System.out.println("creat tree test successfully.continue y(1)/n(0)");
    		BufferedReader   reader   =   new   BufferedReader(new   InputStreamReader(System.in));
    		boolean state=true;
    		while(state)
    		{
    		int ch=Integer.parseInt(reader.readLine());
    		if(ch==1)
    			state=false;
    		else if(ch==0)
    			return;
    		}	
    		reader.close();
        	System.out.println("now begin the search test");
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	//²éÑ¯²¿·Ö
		try
    	{
    		BufferedReader input = new BufferedReader(new FileReader("search20000.txt"));
    		for(int i = 0; i < 20000; i++)
    		{
    			long k = Long.parseLong(input.readLine());
    			if(!btree.search(k))
    				System.out.println("key does't exist");
    		}
    		input.close();
    		System.out.println("search test successfully.continue y(1)/n(0)");
    		BufferedReader   reader   =   new   BufferedReader(new   InputStreamReader(System.in));
    		boolean state=true;
    		while(state)
    		{
    		int ch=Integer.parseInt(reader.readLine());
    		if(ch==1)
    			state=false;
    		else if(ch==0)
    			return;
    		}	
    		reader.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		
		

		try
    	{
    		BufferedReader input = new BufferedReader(new FileReader("del5000.txt"));
    		for(int i = 0; i < 5000; i++)
    		{System.out.println("delete i="+i);
    			long k = Long.parseLong(input.readLine());
    			btree.delete(k);
    		}
    		input.close();
    		System.out.println("delete test successfully.continue y(1)/n(0)");
    		BufferedReader   reader   =   new   BufferedReader(new   InputStreamReader(System.in));
    		boolean state=true;
    		while(state)
    		{
    		int ch=Integer.parseInt(reader.readLine());
    		if(ch==1)
    			state=false;
    		else if(ch==0)
    			return;
    		}
    		reader.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	//²åÈë²¿·Ö
    	try
    	{
    		BufferedReader input = new BufferedReader(new FileReader("ist5000.txt"));
    		for(int i = 0; i < 5000; i++)
    		{System.out.println("insert i="+i);
    			long k = Long.parseLong(input.readLine());
    			btree.InsertOne(k);
    		}
    		input.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	System.out.println("search test successfully.");
    	return;
    }
 	
}

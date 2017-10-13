import java.util.HashMap;
import java.util.Scanner;
public class droptoken {
	static int board[][] = new int[4][4];
	int rows = board.length;
	int cols = board[0].length;
	StringBuilder sb = new StringBuilder();
	HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
	int isGameOver = 0;
	int turn =1;
	static boolean exit_pressed = false;
	
	//printing board
	public void printboard()
	{
		for(int i=1;i<rows+1;i++)
		{
			System.out.print("|");
			for(int j=1;j<cols+1;j++)
			{
				System.out.print(board[i-1][j-1]);
			}
			System.out.println("");
		}
		System.out.println("+----");
		System.out.println(" 1234");
	}
	
	//Verify rows
    public  void verifyRows(int selected, int turn)
     {
	   if(board[selected][0]==board[selected][1]&&board[selected][1]==board[selected][2]&&board[selected][2]==board[selected][3]&& board[selected][0]==turn)
	    {
		    isGameOver = turn;
	    }  
     }
  
    //Verify columns
	public  void verifyColumns(int put_num,int turn)
	{		
			if(board[0][put_num-1]==board[1][put_num-1]&&board[1][put_num-1]==board[2][put_num-1]&&board[2][put_num-1]==board[3][put_num-1] && board[0][put_num-1]==turn)
			{
				isGameOver = turn;
			}
		
	}
	
    //Verify diagonal from top-left to bottom-right
	public void verifyFrontDiagonals(int turn)
	{
		if(board[0][0]==board[1][1]&&board[1][1]==board[2][2]&&board[2][2]==board[3][3]&&board[0][0]==turn)
		{
			isGameOver = turn;
		}
	}
	
	//Verify diagonals from top-right to bottom-left
	public void verifyBackDiagonals(int turn)
	{
		if(board[0][3]==board[1][2]&&board[1][2]==board[2][1]&&board[2][1]==board[3][0]&&board[0][3]==turn)
		{
			isGameOver = turn;
		}
    }
	
	//PUT method
	@SuppressWarnings("static-access")
	private void put(int put_num) 
	{
		if(put_num==0 || put_num>=rows+1 ||put_num>=cols+1)
		{
			System.out.println("ERROR");
			return;
		}
		
		
	    for(int i=1;i<=rows;i++)
         {
           if(this.board[rows-i][put_num-1]==0)
    	  {
        	  
        	if(turn % 2 == 1)
        	{
        		board[rows-i][put_num-1]= 1;
        		verifyRows(rows-i,1);
        		verifyColumns(put_num,1);
        		verifyFrontDiagonals(1);
        		verifyBackDiagonals(1);
        	}
        	else
        	{
        		board[rows-i][put_num-1]= 2;
        		verifyRows(rows-i,2);
        		verifyColumns(put_num,2);
        		verifyFrontDiagonals(2);
        		verifyBackDiagonals(2);
        	}
        	
        	turn = turn + 1;
        	if(sb.length()==15)
    		{
    			System.out.println("DRAW");
    			return;
    		}
        	if(hm.containsKey(put_num))
        	{
        	   int row_limit = hm.get(put_num);
        	   hm.put(put_num, row_limit+1); 
        	}
        	else
        	{
        	   hm.put(put_num, 1);
        	}
           
           if(hm.get(put_num)>4)
     		{
     			System.out.println("ERROR");
     		}

    	   sb.append(put_num);
    	   break;
    	  }    	   
       }
	System.out.println("OK");
	sb.toString();
 	}

	//EXIT method
    public void exit()
    {
    	exit_pressed= true;
    }
	
    //GET method
	public void get()
	{
		for(int i=0;i<sb.length();i++)
		{
			System.out.println(sb.charAt(i));
		}
		
	}
	
	//main method
	@SuppressWarnings("resource")
	public static void main(String[] args) {
     droptoken t = new droptoken();
	
     do
     {   
	   Scanner reader = new Scanner(System.in);  // Reading from System.in
 	   System.out.print("> ");
 	   String input = reader.nextLine();
 	   String[] commands = input.split(" ");
 	   
 	   //Entering the commands
 	   if(commands.length==2)
 	   {
 	   if(commands[0].equals("PUT"))
 		   {
 		      int value = Integer.parseInt(commands[1]);
 			  t.put(value);
 		   }
 	   }

 		   switch(commands[0])
 		   {
 		   case "BOARD" : t.printboard();
 		                  break;
 		   case "GET"   : t.get();     
 		                  break;
 		   case "EXIT"  : t.exit();    
 		                  break;
 		   }
 	   	
     } while(t.isGameOver == 0 && exit_pressed == false);
     
     if(exit_pressed==true)
     {
    	 return;
     }
     System.out.println("WIN");
	 
	// Checking the table is empty or not
	if(t.rows==0 && t.cols==0)
	  {
		System.out.println("table is empty");
	  }
	}
}
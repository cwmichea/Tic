import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe 
{
	static ArrayList<Integer> playerPositionSoFar = new ArrayList<Integer>(); 
	static ArrayList<Integer> cpuPositionSoFar = new ArrayList<Integer>(); 

	public static void main(String[] args) 
	{
	//TicTacToe Game
	char[][] gameboard = {{' ','|',' ','|',' '}, 
						  {'-','+','-','+','-'},
						  {' ','|',' ','|',' '},
						  {'-','+','-','+','-'},
						  {' ','|',' ','|',' '}};
	char[][] gameboard2 = {{'1','|','2','|','3'}, 
						  {'-','+','-','+','-'},
						  {'4','|','5','|','6'},
						  {'-','+','-','+','-'},
						  {'7','|','8','|','9'}};
	int pos;
	String user;
	//char[][] gboard = new char[5][5];
	printGameBoard(gameboard2);
	while (true) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your play(1-9):");
		pos = scan.nextInt();
		boolean invalidPos = false;
		do {
		   for(int x : playerPositionSoFar)
		   { if (pos==x) {invalidPos = true;System.out.println(pos+".sorry Already played from player");break;}
		     else if(pos!=x){invalidPos = false;}//it is ok
	       }
		   if(invalidPos) {} 
		   else {
		   for(int x : cpuPositionSoFar)
		   { if (pos==x) {invalidPos = true;System.out.println(pos+".sorry Already played from Cpu");break;}
		     else if(pos!=x) {invalidPos = false;}//it is ok
	       }
		   }
		   if(invalidPos) 
		   {System.out.println("Enter a Position different from "+ pos);
			pos = scan.nextInt();}
		}while(invalidPos); 
		user = "player";
		gameboard = placePosition(gameboard, pos, user, playerPositionSoFar);
		printGameBoard(gameboard);
		
		String result = checkWinner(playerPositionSoFar, cpuPositionSoFar);
		if(!result.equals(" "))
		{System.out.println(result);break;}
		
		Random ran = new Random();
		pos = ran.nextInt(9) + 1;
		invalidPos = false;
		do {
		   for(int x : playerPositionSoFar)
		   { if (pos==x) {invalidPos = true;System.out.println(pos+".sorry Already played from player");break;}
		     else if(pos!=x){invalidPos = false;}//it is ok
	       }
		   if(invalidPos) {} 
		   else {
		   for(int x : cpuPositionSoFar)
		   { if (pos==x) {invalidPos = true;System.out.println(pos+".sorry Already played from Cpu");break;}
		     else if(pos!=x) {invalidPos = false;}//it is ok
	       }
		   }
		   if(invalidPos) 
		   {pos = ran.nextInt(9) + 1; System.out.println("Number changed to "+ pos);}
		}while(invalidPos); 
		
		user = "cpu";
		gameboard = placePosition(gameboard, pos, user, cpuPositionSoFar);
		printGameBoard(gameboard);
		result = checkWinner(playerPositionSoFar, cpuPositionSoFar);
		if(!result.equals(" "))
		{System.out.println(result);
		break;}
	}
	System.out.println(" ---END OF THE GAME--- ");
	}
	//Functions used
	public static void printGameBoard(char[][] gameboard) 
	{	
		for (char[] row : gameboard) 
		{	for (char c : row) 
			{
			System.out.print(c);
			} System.out.println(); 
		}
	}
	public static char[][] placePosition(char[][] gameboard, int pos, String user, ArrayList<Integer> plays) 
	{	char symbol = ' ';
		plays.add(pos);
		System.out.println(plays);
		if (user.equals("player")) {
			symbol = 'X';
		}else
		{
			symbol = 'O';
		}
		switch (pos) {
		case 1:
			gameboard[0][0] = symbol; 
			break;
		case 2:
			gameboard[0][2] = symbol; 
			break;
		case 3:
			gameboard[0][4] = symbol; 
			break;
		case 4:
			gameboard[2][0] = symbol; 
			break;
		case 5:
			gameboard[2][2] = symbol; 
			break;
		case 6:
			gameboard[2][4] = symbol; 
			break;
		case 7:
			gameboard[4][0] = symbol; 
			break;
		case 8:
			gameboard[4][2] = symbol; 
			break;
		case 9:
			gameboard[4][4] = symbol; 
			break;
		default:
			
			break;
		}
		System.out.println(user + " played " + symbol + " in position " + pos);
		return gameboard;
	}
	public static String checkWinner(ArrayList<Integer> playerPlays, ArrayList<Integer> cpuPlays) 
	{
		List<Integer> topRow = Arrays.asList(1,2,3);
		List<Integer> midRow = Arrays.asList(4,5,6);
		List<Integer> botRow = Arrays.asList(7,8,9);
		List<Integer> leftCol = Arrays.asList(1,4,7);
		List<Integer> midCol = Arrays.asList(2,5,8);
		List<Integer> rightCol = Arrays.asList(3,6,9);
		List<Integer> cross1 = Arrays.asList(1,5,9);
		List<Integer> cross2 = Arrays.asList(3,5,7);
		
		List<List> winningConditions = new ArrayList<List>();
		winningConditions.add(topRow);
		winningConditions.add(midRow);
		winningConditions.add(botRow);
		winningConditions.add(leftCol);
		winningConditions.add(midCol);
		winningConditions.add(rightCol);
		winningConditions.add(cross1);
		winningConditions.add(cross2);
		for (List list : winningConditions) 
		{
			if(playerPlays.containsAll(list)) 
			{return "Congrats, you won.";}
			else if(cpuPlays.containsAll(list))
			{return "Wow! you lost.";}
			else if(playerPlays.size()+cpuPlays.size() == 9)
			{return "It is a tie.";}	
		}
		return " ";
	}
}

package TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTakToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();	//crea un array de listado con las posibles posiciones para ganar el jugador
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();		//crea un array con las posibles posiciones para ganar el cpu

	public static void main(String[] args) {
		Random rand = new Random();
		int cpuPos = rand.nextInt(9) + 1;
		
		char[][] gameBoard = {	{' ', '|', ' ', '|', ' '},		//creacion de la tabla que se verá visualmente
								{'-', '+', '-', '+', '-'},
								{' ', '|', ' ', '|', ' '},
								{'-', '+', '-', '+', '-'},
								{' ', '|', ' ', '|', ' '}};
		
		printGameBoard(gameBoard);
		
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your next placement (1-9):");
			int PlayerPos = scan.nextInt();
			while(playerPositions.contains(PlayerPos) || cpuPositions.contains(PlayerPos)) {		//bucle para q si una posicion está ocupada nos haga elegir otra opcion
				System.out.println("Position taken! Enter a correct position.");
				PlayerPos = scan.nextInt();
			}
		
			placePiece(gameBoard, PlayerPos, "player");
			String result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
		
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {		//bucle para q la cpu no pueda cambiar iconos en la tabla
				cpuPos = rand.nextInt(9) + 1;
			}
			placePiece(gameBoard, cpuPos, "cpu");
		
			printGameBoard(gameBoard);
			
			result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
		}
		
	}
	
	
	public static void printGameBoard(char[][] gameBoard) {		//método para imprimir la tabla por pantalla
		
		for(char[] row : gameBoard) {
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placePiece(char[][] gameBoard, int pos, String user) {		//método para introducir simbolo en las posiciones
		
		char symbol = ' ';
		
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		}else if(user.equals("cpu")){
			symbol = 'O';
			cpuPositions.add(pos);
		}
		
		switch(pos) {
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
		}
	}
	
	public static String checkWinner() {		//método para comprobar si alguien ha ganado
		
		List topRow = Arrays.asList(1, 2, 3);
		List middleRow = Arrays.asList(4, 5, 6);
		List bottomRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List middleCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(7, 5, 3);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(middleRow);
		winning.add(bottomRow);
		winning.add(leftCol);
		winning.add(middleCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
		
		for(List l: winning) {
			if(playerPositions.containsAll(l)) {
				return "Congratulations you won!";
			}else if(cpuPositions.containsAll(l)) {
				return "CPU wins!";
			}else if(playerPositions.size() + cpuPositions.size() == 9){
				return "CAT!";
			}
		}

/*
 Crea una lista donde mete cada una de las posibles combinaciones de victoria y luego con "l", comprueba si player o cpu ha ganado, o si es empate
 */
		return "";
	}

}

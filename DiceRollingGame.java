package com.ggl.testing;

import java.util.Random;
import java.util.Scanner;

public class DiceRollingGame {

	public static void main(String[] args) {
		DiceRollingGame drg = new DiceRollingGame();
		drg.playGame();
	}
	
	public void playGame() {
		GameModel gameModel = new GameModel();
		System.out.println(displayInformation());
		Scanner scanner = new Scanner(System.in);
		int rounds = readNumberOfRounds(scanner);
		
		String roundString = "Round";
		String totalString = " total";
		Player[] players = gameModel.getPlayers();
		System.out.print(createOutputHeader(players, 
				roundString, totalString));
		
		for (int round = 1; round <= rounds; round++) {
			updateGameModel(gameModel);
			System.out.print(createDetailLine(players, 
					roundString, totalString, round));
			System.out.print(createDashedLine(players, 
					roundString, totalString));
		}
		
		scanner.close();
	}
	
	private String displayInformation() {
		StringBuilder builder = new StringBuilder();
		builder.append("Welcome to a dice rolling game simulation.");
		builder.append(System.lineSeparator());
		builder.append(System.lineSeparator());
		builder.append("Three players will each take a turn rolling one die.");
		builder.append(System.lineSeparator());
		builder.append(System.lineSeparator());
		builder.append("At the end of each round, the die rolls are compared.  If");
		builder.append(System.lineSeparator());
		builder.append("two players have the same roll, their die roll value is");
		builder.append(System.lineSeparator());
		builder.append("doubled and added to their total score.  Otherwise, each");
		builder.append(System.lineSeparator());
		builder.append("player adds the die value to his total score.");
		builder.append(System.lineSeparator());
		builder.append(System.lineSeparator());
		builder.append("The player with the highest score after N rounds wins!");
		builder.append(System.lineSeparator());
		
		return builder.toString();
	}
	
	private int readNumberOfRounds(Scanner scanner) {
		int rounds = -1;
		while (rounds < 0) {
			System.out.print("Enter number of rounds: ");
			String line = scanner.nextLine().trim();
			rounds = valueOf(line);
		}
		
		System.out.println();
		return rounds;
	}
	
	private int valueOf(String number) {
		try {
			return Integer.valueOf(number);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
	private void updateGameModel(GameModel gameModel) {
		Player[] players = gameModel.getPlayers();
		for (Player player : players) {
			player.rollDie();
		}
		
		if (gameModel.isPair(players, 0, 1, 2)) {
			updatePlayersTotal(players, 0, 1, 2);
		} else if (gameModel.isPair(players, 0, 2, 1)) {
			updatePlayersTotal(players, 0, 2, 1);
		} else if (gameModel.isPair(players, 1, 2, 0)) {
			updatePlayersTotal(players, 1, 2, 0);
		} else {
			updatePlayersTotal(players);
		}
	}
	
	private void updatePlayersTotal(Player[] players, 
			int index1, int index2, int index3) {
		players[index1].incrementTotalValue(
				players[index1].getDieValue() * 2);
		players[index2].incrementTotalValue(
				players[index2].getDieValue() * 2);
		players[index3].incrementTotalValue(
				players[index3].getDieValue());
	}
	
	private void updatePlayersTotal(Player[] players) {
		for (Player player : players) {
			player.incrementTotalValue(player.getDieValue());
		}
	}
	
	private String createOutputHeader(Player[] players, 
			String round, String total) {	
		StringBuilder builder = new StringBuilder();
		builder.append(createDashedLine(players, round, total));
		builder.append(createHeaderLine(players, round, total));
		builder.append(createDashedLine(players, round, total));
		
		return builder.toString();
	}
	
	private StringBuilder createHeaderLine(Player[] players, 
			String roundString, String totalString) {
		StringBuilder headerLine = new StringBuilder();
		headerLine.append("| ");
		headerLine.append(roundString);
		headerLine.append(" ");
		
		for (Player player : players) {
			String name = player.getName();
			headerLine.append("| ");
			headerLine.append(name);
			headerLine.append(" ");
		}
		
		for (Player player : players) {
			String name = player.getName() + totalString;
			headerLine.append("| ");
			headerLine.append(name);
			headerLine.append(" ");
		}
		
		headerLine.append("|");
		headerLine.append(System.lineSeparator());
		
		return headerLine;
	}
	
	private StringBuilder createDetailLine(Player[] players, 
			String roundString, String totalString, int round) {
		StringBuilder detailLine = new StringBuilder();
		detailLine.append("| ");
		String formatter = "%" + roundString.length() + "d";
		detailLine.append(String.format(formatter, round));
		detailLine.append(" ");
		
		for (Player player : players) {
			String name = player.getName();
			detailLine.append("| ");
			formatter = "%" + name.length() + "d";
			detailLine.append(String.format(formatter, 
					player.getDieValue()));
			detailLine.append(" ");
		}
		
		for (Player player : players) {
			String name = player.getName() + totalString;
			detailLine.append("| ");
			formatter = "%" + name.length() + "d";
			detailLine.append(String.format(formatter, 
					player.getTotalValue()));
			detailLine.append(" ");
		}
		
		detailLine.append("|");
		detailLine.append(System.lineSeparator());
		
		return detailLine;
	}
	
	private StringBuilder createDashedLine(Player[] players, 
			String roundString, String totalString) {
		StringBuilder dashedLine = new StringBuilder();
		dashedLine.append("+");
		dashedLine.append(createRepeatingGroup('-', roundString.length() + 2));
		
		for (Player player : players) {
			String name = player.getName();
			dashedLine.append("+");
			dashedLine.append(createRepeatingGroup('-', name.length() + 2));
		}
		
		for (Player player : players) {
			String name = player.getName() + totalString;
			dashedLine.append("+");
			dashedLine.append(createRepeatingGroup('-', name.length() + 2));
		}
		
		dashedLine.append("+");
		dashedLine.append(System.lineSeparator());
		
		return dashedLine;
	}
	
	private StringBuilder createRepeatingGroup(char c, int count) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < count; i++) {
			builder.append(c);
		}
		
		return builder;
	}
	
	public class GameModel {
		
		private final Player[] players;
		
		public GameModel() {
			this.players = createPlayerFactory();
		}
		
		private Player[] createPlayerFactory() {
			Player[] players = new Player[3];
			players[0] = new Player("Player 1");
			players[1] = new Player("Player 2");
			players[2] = new Player("Player 3");
			
			return players;
		}
		
		public boolean isPair(Player[] players, 
				int index1, int index2, int index3) {
			int die1 = players[index1].getDieValue();
			int die2 = players[index2].getDieValue();
			int die3 = players[index3].getDieValue();
			return (die1 == die2) && (die1 != die3);
		}

		public Player[] getPlayers() {
			return players;
		}
		
	}
	
	public class Player {
		
		private int dieValue;
		private int totalValue;
		
		private final String name;
		
		private final Random random;

		public Player(String name) {
			this.name = name;
			resetValues();
			this.random = new Random();
		}

		public void resetValues() {
			this.dieValue = 0;
			this.totalValue = 0;
		}
		
		public void rollDie() {
			this.dieValue = random.nextInt(6) + 1;
		}

		public void incrementTotalValue(int dieValue) {
			this.totalValue += dieValue;
		}
		
		public int getDieValue() {
			return dieValue;
		}

		public int getTotalValue() {
			return totalValue;
		}

		public String getName() {
			return name;
		}
		
	}

}

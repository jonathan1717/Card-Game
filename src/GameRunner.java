import java.util.ArrayList;
import java.util.Scanner;
public class GameRunner
	{
		private static ArrayList<Card> shuffledDeck = new ArrayList<Card>();
		public static void main(String[] args)
			{
				ArrayList <Card> cards = new ArrayList<Card>();
				ArrayList <String> suit = new ArrayList<String>();
				suit.add("spades");
				suit.add("clubs");
				suit.add("diamonds");
				suit.add("hearts");
				String cardName;			 
				for (int i = 0; i<4; i++)
					{
						for (int j = 0; j<13; j++)
							{
								if(j == 0)
									{
										cardName = "Ace";
									}
								else if (j == 10)
									{
										cardName = "Jack";
									}
								else if (j == 11)
									{
										cardName = "Queen";
									}
								else if (j == 12)
									{
										cardName = "King";
									}
								else
									{
										cardName = Integer.toString(j + 1);
									}
								cards.add(new Card(cardName, j+1, suit.get(i)));
							}
					}
				int k = 0;
				for (int i = 0; i< 52; i++)
					{
						int number = (int) (Math.random()*(52-k));
						shuffledDeck.add(cards.get(number));
						cards.remove(number);
						k++;
					}
				//Collections.shuffle(deck);
				ArrayList <Card> playerOneDeck = new ArrayList<Card>();
				ArrayList <Card> playerTwoDeck = new ArrayList<Card>();
				ArrayList <Card> playerOnePairs = new ArrayList<Card>();
				ArrayList <Card> playerTwoPairs = new ArrayList<Card>();
				for (int i = 0; i<5; i++)
					{
						addCard(playerOneDeck, shuffledDeck, 0);
						addCard(playerTwoDeck, shuffledDeck, 0);
					}
				System.out.println(cards);
//				System.out.println(playerOneDeck);
				System.out.println("The cards in your deck are: " + playerTwoDeck);
//				for (Card s : shuffledDeck)
//					{
//						System.out.println(s);
//					}
				Scanner input = new Scanner(System.in);
				boolean turn = true;
				while(playerOneDeck.size() > 0 || playerTwoDeck.size() > 0)
					{
						checkDeck(playerOneDeck, playerOnePairs);
						checkDeck(playerTwoDeck, playerTwoPairs);
						System.out.println("These are your pairs: " + playerTwoPairs);
						System.out.println("These are your opponets pairs: " + playerOnePairs);
						try
							{
								Thread.sleep(2500);
							} catch (InterruptedException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						if (turn == true)
							{
								int random = (int) (Math.random() * playerOneDeck.size());
								System.out.println("Do you have a " + playerOneDeck.get(random).getName() + "?");
								for (int i = 0; i < playerTwoDeck.size(); i++)
									{
										if (playerTwoDeck.get(i).getValue() == playerOneDeck.get(random).getValue())
											{
												System.out.println("I have a " + playerTwoDeck.get(i));
												addCard(playerOnePairs, playerTwoDeck, i);
												addCard(playerOnePairs, playerOneDeck, random);
												turn = false;
												break;
											}
									}
								if (turn == true)
									{
										System.out.println("Go fish!");
										turn = false;
										addCard(playerOneDeck, shuffledDeck, 0);
									}
//								System.out.println(playerOneDeck);
								System.out.println("The cards in your deck are: " + playerTwoDeck);
								System.out.println("These are your pairs: " + playerTwoPairs);
								System.out.println("These are your opponets pairs: " + playerOnePairs);
							}
						checkDeck(playerOneDeck, playerOnePairs);
						checkDeck(playerTwoDeck, playerTwoPairs);
						try
							{
								Thread.sleep(2500);
							} catch (InterruptedException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						if (turn == false)
							{
								System.out.println("Which of your cards do you want to ask for?");
								int user = input.nextInt();
								for (int i = 0; i < playerOneDeck.size(); i++)
									{
										if (playerOneDeck.get(i).getValue() == playerTwoDeck.get(user - 1).getValue())
											{
												System.out.println("I have a " + playerOneDeck.get(i));
												addCard(playerTwoPairs, playerOneDeck, i);
												addCard(playerTwoPairs, playerTwoDeck, (user - 1));
												turn = true;
												break;
											}
									}
								if (turn == false)
									{
										System.out.println("Go fish!");
										System.out.println("You got a " + shuffledDeck.get(0));
										turn = true;
										addCard(playerTwoDeck, shuffledDeck, 0);
									}
							}
					}
				if (playerTwoDeck.size() == 0)
					{
						System.out.println("You Win!");
					}
				else if (playerOneDeck.size() == 0)
					{
						System.out.println("You Lose! :(");
					}
			}
		public static void addCard(ArrayList<Card> card, ArrayList<Card> deck, int num)
		{
			card.add(deck.get(num));
			deck.remove(num);
		}
		public static void checkDeck(ArrayList<Card> deck, ArrayList<Card> deck2)
		{
			for (int i = 0; i < deck.size(); i++)
				{
					for (int j = 0; j < deck.size(); j++)
						{
							if (i == j && j !=deck.size())
								{
									j++;
								}
							else
								{
									if (deck.get(i).getValue() == deck.get(j).getValue())
										{
											addCard(deck2, deck, i);
											addCard(deck2, deck, j);
										}
								}
						}
				}
		}
	}

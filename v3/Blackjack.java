

import java.util.ArrayList;
import cs1.Keyboard;

public class Blackjack extends Game {

    private ArrayList<Card> _dealer;
    private ArrayList<Card> _player;
    private Deck _deck;
    private int choice;
    private boolean win;
    private double mult = 2.0;

    public Blackjack() {
	cost = 10.0;
	_deck = new Deck();

	_player.add(_deck.draw());
	_player.add(_deck.draw());
	System.out.println("Your hand is: " + _player + "\n");

	_dealer.add(_deck.draw());
	_dealer.add(_deck.draw());	
	System.out.println("The dealer has a: " + _dealer.get(0) + "\n");	
	play();
	
    }



    public String about() {
	String s;
	s = "\nBlackjack is a card game where your objective is to reach a value of 21.\n";
	s += "You are dealt a hand of two cards at the beginning, and a dealer is also dealt two cards";
	s += "If you or the dealer automatically gets a value of 21, you have blackjack, and win quick money";
	s += "Otherwise, you are given the choice to hit, stand, or double.";
	s += "By hitting, you draw a card from the deck, and can keep going until you go over 21";
	s += "By standing, you trust your luck, and compare your value with the dealer's";
	s += "By doubling, you raise your bet amount, and draw one more card";

	return s;
    }
    public int value(ArrayList<Card> hand){
	int sum = 0;
	for (int i = 0; i < hand.size(); i++){
	    if ((hand.get(i)).getRank() == 0){
		sum += 11; }
	    else if ((hand.get(i)).getRank() == 11){
		sum += 10; }
	    else if ((hand.get(i)).getRank() == 13){
		sum += 10; }
	    else if ((hand.get(i)).getRank() == 12){
		sum += 10; }
	    else{
		sum += (hand.get(i)).getRank();}
	}
	return sum;
    }
    
    public void play() {
	String i;
	
	i = "Would you like to see the rules of the game? \n";
	i += "\t1: Yes\n";
	i += "\t2: No\n";
	System.out.println(i);

	int rules = cs1.Keyboard.readInt();

	if (rules == 1) {
	   System.out.println(about());
	}
	
	if (value(_player) == 21){
	    System.out.println("You had a blackjack! \n");
	    mult = 2.5;
	    win = true;	    
	    win();
	}
	if (value(_dealer) == 21){
	    System.out.println("You lost. The dealer had a blackjack! \n");
	}
	String s;
	s = "Make your decision";
	s += "\t1: Stand\n";
	s += "\t2: Hit\n";
	s += "\t3: Double\n";
	System.out.println(s);


	choice = cs1.Keyboard.readInt();

	if (choice == 1) {
	    reveal();
	}
	else if (choice == 2) {
	    _player.add(_deck.draw());
	    reveal();
	}
	else if (choice == 3) {
	    cost *= 2;
	    _player.add(_deck.draw());
	    reveal();
	}
	else {
	    System.out.println("You've chosen an invalid choice. Please select again");
	    play();
	}
	}

    public void reveal() {
	if (choice == 3) {
	    if (value(_player) > 21) {
	    System.out.println("Sorry, you busted. (Over the winning amount)");
		win = false;
	    }
	}

	else if (choice == 2) {
	    if (value(_player) > 21) {
	    System.out.println("Sorry, you busted. (Over the winning amount)");
	    win = false;
	    }
	    else {
		play();
	    }
	}
	else if (choice == 1) {
	    win();
	}
    }

    public boolean win() {
	if (value(_dealer) < 17) {
	    while (value(_dealer) <17) {
		_dealer.add(_deck.draw());
	    }
	    win();
	}
	if (value(_dealer) > 21) {
	    System.out.println("Dealer busted. Congrats!");
	    win = true;
	}
	
	if (value(_player) > value(_dealer)) {
	    System.out.println("Congrats! You beat the dealer!");
	    win = true;
	}
	else {
	    System.out.println("Dealer has beaten you. Sorry");
	    win = false;
	}
	return win;
    }

}

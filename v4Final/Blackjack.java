

import java.util.ArrayList;
import cs1.Keyboard;

public class Blackjack extends Game {

    private ArrayList<Card> _dealer = new ArrayList<Card>();
    private ArrayList<Card> _player = new ArrayList<Card>();
    private Deck _deck;
    private int choice;
    private boolean win;

    public Blackjack() {
	cost = 10.0;
	winnings = 20;
	_deck = new Deck();

	_player.add(_deck.draw());
	_player.add(_deck.draw());

	_dealer.add(_deck.draw());
	_dealer.add(_deck.draw());

	String i;

	i = "Would you like to see the rules of the game? \n";
	i += "\t1: Yes\n";
	i += "\t2: No\n";
	System.out.println(i);

	int rules = cs1.Keyboard.readInt();

	if (rules == 1) {
	   System.out.println(about());
	}

	play();

    }

    public boolean getWin(){
	return win;}

    public String about() {
	String s;
	s = "\nBlackjack is a card game where your objective is to reach a value of 21.\n";
	s += "You are dealt a hand of two cards at the beginning, and a dealer is also dealt two cards. ";
  s += "\nAll of the number cards are valued at their number.";
  s += "\nAll face cards are worth 10 and the ace is worth 11.";
	s += "\nIf you or the dealer automatically gets a total value of 21 (adding together card values), you have blackjack, and win quick money. ";
	s += "Otherwise, you are given the choice to hit, stand, or double. ";
	s += "\nBy hitting, you draw a card from the deck, and can keep going until you go over 21";
	s += "\nBy standing, you trust your luck, and compare your value with the dealer's";
	s += "\nBy doubling, you raise your bet amount, and draw one more card\n";

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
		sum += (hand.get(i)).getRank(); }
	}
	return sum;
    }

    public void play() {

	System.out.println("Your hand is: " + _player + "\n");

	System.out.println("The dealer has a: " + _dealer.get(0) + "\n");

	if (value(_player) == 21 && (_player.size() == 2)){

	    if (value(_player) == 21){
		System.out.println("You had a blackjack! \n");
		winnings *= 1.5;
		win = true;
		return;
	    }

	    if (value(_dealer) == 21){
		System.out.println("You lost. The dealer had a blackjack! \n");
		win = false;
		return;
	    }
	}


	if (chooseaction() == true){
	    win = win();
	}
    }

    public boolean chooseaction(){

	String s;
	s = "Make your decision\n";
	s += "\t1: Stand\n";
	s += "\t2: Hit\n";
	s += "\t3: Double\n";
	System.out.println(s);
	
	choice = cs1.Keyboard.readInt();

	if (choice == 1) {
	    return reveal(choice);
	}
	else if (choice == 2) {
	    _player.add(_deck.draw());
	    return reveal(choice);
	}
	else if (choice == 3) {
	    winnings *= 2;
	    _player.add(_deck.draw());
	    return reveal(choice);
	}
	else {
	    System.out.println("You've chosen an invalid choice. Please select again");
	    return  chooseaction();
	}
    }
    
    public boolean reveal(int choice) {
	if (choice == 3) {
	    if (value(_player) > 21) {
	    System.out.println("Sorry, you busted. (Over the winning amount)");
	    System.out.println("Your hand was: " + _player + "\n");	    
		win = false;
		return false;
	    }
	}

	else if (choice == 2) {
	    if (value(_player) > 21) {
		System.out.println("Sorry, you busted. (Over the winning amount)");
		System.out.println("Your hand was: " + _player + "\n");
		win = false;
		return false;
	    }
	    else {
		System.out.println("Your hand is: " + _player + "\n");
		if (value(_player) > 21){return false;}
		return chooseaction();
	    }
	}
	else if (choice == 1) {
	}
	return true;
    }

    public boolean win() {
	
	if (value(_dealer) < 17) {
	    while (value(_dealer) <17) {
		_dealer.add(_deck.draw());
	    }
	}

	if (value(_dealer) > 21) {
	    System.out.println("Dealer had : " + _dealer + "\n");
	    System.out.println("Your hand was: " + _player + "\n");
	    System.out.println("Dealer busted. Congrats!");
	    return true;
	}
	
	if (value(_player) > value(_dealer)) {

	    System.out.println("Dealer had : " + _dealer + "\n");
	    System.out.println("Your hand was: " + _player + "\n");
	    System.out.println("Congrats! You beat the dealer!");
	    win = true;
	}
	else {
	    System.out.println("Dealer had : " + _dealer + "\n");
	    System.out.println("Your hand was: " + _player + "\n");
	    System.out.println("Dealer has beaten you. Sorry");
	    win = false;
	}
	return false;
    }
}
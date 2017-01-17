package javaweek2;
//Author: Luke Hodinka

import java.util.ArrayList;

public class GameOfWar 
{
    private ArrayList<Card> p1Hand, p2Hand;
    
    //This is the constructor for the gameofwar class
    public GameOfWar()
    {
        p1Hand = new ArrayList<>();
        p2Hand = new ArrayList<>();
        
        DeckOfCards theDeck = new DeckOfCards();
        theDeck.shuffle(); 
        
        dealCards(theDeck); 
    }//end of constructor
    
    //This method will deal all the cards equally to the 2 players
    private void dealCards(DeckOfCards theDeck)
    {
        for(int cardNum=0; cardNum<52;cardNum++)
        {
            if(cardNum % 2 == 0)
                p1Hand.add(theDeck.dealTopCard());
            else
                p2Hand.add(theDeck.dealTopCard());
        }
    }
    
    //This method will run the game
    public void playGame()
    {
        while(!p1Hand.isEmpty() && !p2Hand.isEmpty())
        {
            playHand();
        }
        if(p1Hand.isEmpty())
        {
            System.out.println("The winner is player 2");
        }
        else
            System.out.println("The winner is player 1");
    }
    
    //This method will simulate playing a hand in the game of war
    private void playHand()
    {
        if(p1Hand.size() > 0 && p2Hand.size()>0)
        {
            Card p1Card = p1Hand.remove(0);
            Card p2Card = p2Hand.remove(0);
            
            System.out.printf("Player 1 card: %s, total cards: %d", p1Card, p1Hand.size()+1);
            System.out.printf("\tPlayer 2 card: %s, total cards: %d%n", p2Card, p2Hand.size()+1);
            //If player 1 has higher card
            if(p1Card.getFaceValue()>p2Card.getFaceValue())
            {
                p1Hand.add(p1Card);
                p1Hand.add(p2Card);
            }
            //If player 2 has higher card
            else if (p1Card.getFaceValue()<p2Card.getFaceValue())
            {
                p2Hand.add(p1Card);
                p2Hand.add(p2Card);
            }
            //It must be war
            else
            {
                ArrayList<Card> pile = new ArrayList<>();
                pile.add(p1Card);
                pile.add(p2Card);
                playWarHand(pile); 
            }
        }
    }//end of method playhand
    
    //This method will simulate playing a "war" hand
    private void playWarHand(ArrayList<Card> pile)
    {
        if(p1Hand.size()<3)
        {
            p2Hand.addAll(p1Hand);
            p1Hand.clear();
            p2Hand.addAll(pile);
            return;
        }
        if(p2Hand.size()<3)
        {
            p1Hand.addAll(p2Hand);
            p2Hand.clear();
            p1Hand.addAll(pile);
            return;
        }
        
        pile.add(p1Hand.remove(0));
        pile.add(p1Hand.remove(0));
        pile.add(p2Hand.remove(0));
        pile.add(p2Hand.remove(0));
        
        Card p1Card = p1Hand.remove(0); 
        Card p2Card = p2Hand.remove(0);
        
        System.out.printf("%n%n-----------------WAR-------------------- %n");
        System.out.printf("Player 1 card: %s, total cards: %d", p1Card, p1Hand.size()+1);
        System.out.printf("\tPlayer 2 card: %s, total cards: %d%n", p2Card, p2Hand.size()+1);
        if (p1Card.getFaceValue() > p2Card.getFaceValue())
        {
            pile.add(p1Card);
            pile.add(p2Card); 
            p1Hand.addAll(pile);
        }
        else if (p1Card.getFaceValue() < p2Card.getFaceValue())
        {
            pile.add(p1Card);
            pile.add(p2Card); 
            p2Hand.addAll(pile);
        }
        else
        {
            pile.add(p1Card);
            pile.add(p2Card);
            playWarHand(pile); 
        }
    }
}

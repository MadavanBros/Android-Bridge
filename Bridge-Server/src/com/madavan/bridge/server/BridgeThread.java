package com.madavan.bridge.server;

import java.util.ArrayList;
import java.io.IOException;

public class BridgeThread extends Thread {

  private ArrayList<BridgePlayer> _players;
  
  public BridgeThread(ArrayList<BridgePlayer> players) {
    _players = players;
  }
  
  @Override
  public void run() {
    // Tell players game has begun
    // Reset, shuffle, and deal deck
    
    // Bidding
    //  - Pass is the bid CLUBS,0 (or we could do PASS)
    //  - Use Rank, Suit compareTo methods
    
    // Play Game
  }
}

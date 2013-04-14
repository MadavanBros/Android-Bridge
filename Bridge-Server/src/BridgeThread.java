import java.util.ArrayList;
import java.io.IOException;

public class BridgeThread extends Thread {

  private ArrayList<BridgePlayer> _players;
  
  public BridgeThread(ArrayList<BridgePlayer> players) {
    _players = players;
  }
  
  @Override
  public void run() {
    
  }
}

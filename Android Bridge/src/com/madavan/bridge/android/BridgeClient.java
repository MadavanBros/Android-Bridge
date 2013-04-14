public class BridgeClient extends AsyncTask<Void, Void, Void> {
  
  private static final String HOST = "serverHost";
  private static final int PORT    = 2221;
  
  private Socket _socket;
  
  @Override
  protected void onPreExecute() {
    _socket = new Socket(HOST, PORT);
  }
  
  @Override
  protected void doInBackground(Void... params) {
    
  }
}

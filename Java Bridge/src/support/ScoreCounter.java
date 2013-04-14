package support;

public class ScoreCounter {
	private int team1;
	private int team2;
	
	public ScoreCounter() {
		team1 = 0;
		team2 = 0;
	}
	
	public void addPoint(int i) {
		switch (i) {
			case (1): team1++; break;
			case (2): team2++; break;
		}
	}
	
	public void setScore(int i, int s) {
		switch (i) {
			case (1): team1 = s; break;
			case (2): team2 = s; break;
		}
	}
	
	public int getScore(int i) {
		int score = 0;
		switch (i) {
			case (1): score = team1; break;
			case (2): score = team2; break;
		}
		return score;
	}
	
	public void resetScore() {
		team1 = 0;
		team2 = 0;
	}
}

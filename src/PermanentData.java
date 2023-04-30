public class PermanentData {
    private int tempMaxScore;
    public void saveTempMaxScore(int score){
        tempMaxScore = score;
    }

    public int getTempMaxScore(){
        return tempMaxScore;
    }
}

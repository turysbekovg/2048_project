import java.util.*;

public class Model {
    private static int FIELD_WIDTH = 0;  // matrix 4x4
    private Tile[][] gameTiles; // desk 4x4

    int score = 0; // score
    int maxTile = 2; // initially maxTile is 2

    private Stack<Tile[][]> previousStates = new Stack<>(); // to keep count of previous states
    private Stack<Integer> previousScores = new Stack<>(); // to keep count of previous scores

    private boolean isSaveNeeded = true; // to check if save is neeede

    public Model(int FIELD_WIDTH){ // creating new model by resetting game tiles
        resetGameTiles();
        this.FIELD_WIDTH = FIELD_WIDTH;
    }

    private List<Tile> getEmptyTiles() {  // to get empty tiles
        List<Tile> result = new ArrayList<>(); // creating temp array
        for(int i = 0; i< FIELD_WIDTH; i++){ // checking the matrix
            for(int j =0; j < FIELD_WIDTH; j++){
                if(gameTiles[i][j].value == 0){ // if = 0 -> adding tile with 0
                    result.add(gameTiles[i][j]);
                }
            }
        }
        return result; // returning list of empty tiles
    }

    private void addTile(){ // randomly adding new tile 2 or 4
        List<Tile> emptyTiles = getEmptyTiles(); // getting list of empty tiles

        if(emptyTiles.isEmpty() == false){ // if not empty
            int index = (int) (Math.random() * emptyTiles.size()); // getting random index
            Tile tile = emptyTiles.get(index); // firstly assigning 0 to a new tile
            tile.value = Math.random() < 0.9 ? 2 : 4; // then adding new value (2 or 4) to this empty tile
        }
    }

    public void resetGameTiles(){ // making tile look like not 0 but empty
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for(int i = 0; i< FIELD_WIDTH; i++){
            for(int j = 0; j< FIELD_WIDTH; j++){
                gameTiles[i][j] = new Tile();
            }
        }

        addTile();
        addTile();
    }

    private boolean compressTiles(Tile[] tiles){ // changing tiles
        boolean changed = false;

        for(int i = 1; i<tiles.length; i++){
            if(tiles[i].isEmpty() == false) { // if the tile on the index is not empty ->
                int index = i;
                while(index > 0){
                    if(tiles[index-1].isEmpty()) {
                        Tile temp = tiles[index-1];
                        tiles[index-1] = tiles[index];
                        tiles[index] = temp;
                        changed = true;
                    }
                    --index;
                }
            }
        }
        return changed;
    }

    private boolean mergeTiles (Tile[] tiles) { // to connect tiles
        boolean changed = false;
        for( int i = 1; i< tiles.length; i++){
            if(tiles[i].isEmpty() == false){
                if(tiles[i-1].value == tiles[i].value){
                    tiles[i-1].value *=2;
                    tiles[i].value = 0;
                    changed = true;
                    int newValue = tiles[i-1].value;
                    score+= newValue;

                    if(newValue > maxTile){
                        maxTile = newValue;
                    }
                }
            }
        }
        compressTiles(tiles);
        return changed;
    }

    void left () {
        if (isSaveNeeded){
            saveState(gameTiles);
        }
        boolean changed = false;
        for(int i = 0; i < gameTiles.length; i++){
            if(compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])){
                changed = true;
            }
        }
        if(changed){
            addTile();
        }
        isSaveNeeded = true;
    }

    private void rotateGameTilesBy90Degrees(){
        Tile[][] newTile = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for(int i = 0; i<FIELD_WIDTH; i++){
            for(int j = 0; j<FIELD_WIDTH; j++){
                newTile[j][3-i] = gameTiles[i][j];
            }
        }
        gameTiles = newTile;
    }

    void down() {
        saveState(gameTiles);
        rotateGameTilesBy90Degrees();
        left();
        rotateGameTilesBy90Degrees();
        rotateGameTilesBy90Degrees();
        rotateGameTilesBy90Degrees();
    }

    void right(){
        saveState(gameTiles);
        rotateGameTilesBy90Degrees();
        rotateGameTilesBy90Degrees();
        left();
        rotateGameTilesBy90Degrees();
        rotateGameTilesBy90Degrees();
    }

    void up(){
        saveState(gameTiles);
        rotateGameTilesBy90Degrees();
        rotateGameTilesBy90Degrees();
        rotateGameTilesBy90Degrees();
        left();
        rotateGameTilesBy90Degrees();
    }

    public Tile[][] getGameTiles(){
        return gameTiles;
    }

    public boolean isFull() {
        return getEmptyTiles().isEmpty();
    }

    public boolean canMove(){
        if(!isFull()){
            return true;
        }

        for(int i =0; i<FIELD_WIDTH; i++){
            for(int j =0; j<FIELD_WIDTH; j++){
                Tile tile = gameTiles[i][j];
                if(((i<FIELD_WIDTH-1) && (tile.value == gameTiles[i+1][j].value)) ||
                        ((j<FIELD_WIDTH-1) && (tile.value == gameTiles[i][j+1].value))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] tiles1 = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for(int i = 0; i<FIELD_WIDTH; i++){
            for(int j = 0; j<FIELD_WIDTH; j++){
                Tile tile = new Tile();
                tile.value = tiles[i][j].value;
                tiles1[i][j]=tile;
            }
        }

        previousStates.push(tiles1);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollBack(){
        if(!previousScores.isEmpty() && !previousStates.isEmpty()){
            score = previousScores.pop();
            gameTiles = previousStates.pop();
        }
    }

    void randomMove () {
        int randomMove = (int) (Math.random()*100)%4;

        if(randomMove==0){
            left();
        }

        if(randomMove==1){
            up();
        }

        if(randomMove==2){
            right();
        }

        if(randomMove==3){
            down();
        }

    }

    boolean hasBoardChanged (){
        for(int i =0; i<FIELD_WIDTH; i++){
            for(int j =0; j<FIELD_WIDTH; j++){
                if(gameTiles[i][j].value != previousStates.peek()[i][j].value){
                    return true;
                }
            }
        }
        return false;
    }

    MoveEfficiency getMoveEfficiency (Move move) {
        move.move();

        int emptyTiles = -1;
        int newScore = 0;

        if(hasBoardChanged()){
            emptyTiles = getEmptyTiles().size();
            newScore = score;
        }
        MoveEfficiency moveEfficiency = new MoveEfficiency(emptyTiles, newScore, move);
        rollBack();

        return moveEfficiency;
    }

    void autoMove () { // auto move
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue<>(4, Collections.reverseOrder());
        priorityQueue.offer(getMoveEfficiency(this::left));
        priorityQueue.offer(getMoveEfficiency(this::up));
        priorityQueue.offer(getMoveEfficiency(this::right));
        priorityQueue.offer(getMoveEfficiency(this::down));

        priorityQueue.peek().getMove().move();
    }

    void botMove(){
        while(canMove() == true){
            randomMove();
        }
    }

}

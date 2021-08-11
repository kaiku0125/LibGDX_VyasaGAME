package com.kaiku.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import libs.MyColors;

import static libs.MyColors.*;


public class LabyrinthScreen implements Screen {
    public enum Direction{
        NONE, UP, DOWN, LEFT, RIGHT, MOVING
    }
    private Direction currentDir = Direction.NONE;
    private static final String TAG = "LabyrinthView";
    private final static boolean DEBUG = false;
    private Cell[][] cells;
    private Cell player, nextMovement, previousMovement;
    private ArrayList<Cell> snake = new ArrayList<>();
    private static final int COLS = 12;
    private static final int ROWS = 12;
    private float cellSize, hMargin, vMargin;
    private float speed = 40f;
    private float x, y;
    private Random random;
    private PlayerActor pActor;
    private MoveToAction action;
    private Stage stage;

    private Camera camera;
    private Viewport viewport;
    private ShapeRenderer sr;

    private SpriteBatch batch;
    private Texture background, texture;
    Rectangle rect;


    private final int WORLD_WIDTH = Gdx.graphics.getWidth();
    private final int WORLD_HEIGHT = Gdx.graphics.getHeight();

    LabyrinthScreen(){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        sr = new ShapeRenderer();
        random = new Random();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        createLabyrinth();
//        stage.addActor(pActor);


    }

    @Override
    public void render(float delta) {
        MyColors.glClearColor(MAYA_BLUE);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render labyrinth
        sr.begin(ShapeRenderer.ShapeType.Filled);
        renderLabyrinth();
//        renderPlayer();
        sr.end();

        //actor move
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        batch.begin();
        batch.draw(texture, rect.x, rect.y );
        checkNextPosition();
        if(currentDir != Direction.NONE){
            goDir(currentDir);
        }
        batch.end();
    }

    private void checkNextPosition(){
        player.visited = true;
        if(currentDir != Direction.NONE)
            return;
        ArrayList<Direction> directions = new ArrayList<>();
        if(!cells[player.col][player.row].topWall && !cells[player.col][player.row + 1].visited){
            log(TAG, "run: top" );
            directions.add(Direction.UP);

        }
        if(!cells[player.col][player.row].leftWall && !cells[player.col - 1][player.row].visited){
            log(TAG, "run: left" );
            directions.add(Direction.LEFT);

        }
        if(!cells[player.col][player.row].bottomWall && !cells[player.col][player.row - 1].visited){
            log(TAG, "run: bottom" );
            directions.add(Direction.DOWN);

        }
        if(!cells[player.col][player.row].rightWall && !cells[player.col + 1][player.row].visited){
            log(TAG, "run: right" );
            directions.add(Direction.RIGHT);
        }

        if(directions.size() == 0){
            previousMovement.visited = false;
            checkNextPosition();

        }else{
            int index = random.nextInt(directions.size());
            currentDir = directions.get(index);
        }

    }

    private void goDir(Direction direction){
        log(TAG, "goDir : " + direction.toString());
        switch (direction){
            case UP:
                nextMovement = cells[player.col][player.row+1];
                y = cellSize*2;
                rect.y += Gdx.graphics.getDeltaTime()*y;
                if(rect.y >= nextMovement.centerY() - 0.4f * cellSize){
                    currentDir = Direction.NONE;
                    y = 0;
                    player = nextMovement;
                    player.visited = true;
                    previousMovement.visited = false;
                    previousMovement = snake.get(0);
                    snake.set(0, player);
                }
                break;

            case LEFT:
                nextMovement = cells[player.col-1][player.row];
                x = -cellSize*2;
                rect.x += Gdx.graphics.getDeltaTime()*x;
                if(rect.x <= nextMovement.centerX() - 0.4f * cellSize){
                    currentDir = Direction.NONE;
                    x = 0;
                    player = nextMovement;
                    player.visited = true;
                    previousMovement.visited = false;
                    previousMovement = snake.get(0);
                    snake.set(0, player);
                }
                break;

            case DOWN:
                nextMovement = cells[player.col][player.row-1];
                y = -cellSize*2;
                rect.y += Gdx.graphics.getDeltaTime()*y;
                if(rect.y <= nextMovement.centerY() - 0.4f * cellSize){
                    currentDir = Direction.NONE;
                    y = 0;
                    player = nextMovement;
                    player.visited = true;
                    previousMovement.visited = false;
                    previousMovement = snake.get(0);
                    snake.set(0, player);
                }
                break;

            case RIGHT:
                nextMovement = cells[player.col+1][player.row];
                x = cellSize*2;
                rect.x += Gdx.graphics.getDeltaTime()*x;
                if(rect.x >= nextMovement.centerX() - 0.4f * cellSize){
                    currentDir = Direction.NONE;
                    x = 0;
                    player = nextMovement;
                    player.visited = true;
                    previousMovement.visited = false;
                    previousMovement = snake.get(0);
                    snake.set(0, player);
                }
                break;

            case NONE:
                break;
        }
        if(DEBUG) log(TAG, "rectX = " + rect.x + " ,rectY = " + rect.y);
        if(DEBUG) log(TAG, "nextX = " + (nextMovement.centerX() - 0.4f*cellSize));
        if(DEBUG) log(TAG, "nextY = " + (nextMovement.centerY() - 0.4*cellSize));

    }


    private void renderLabyrinth(){
        sr.setColor(Color.BLACK);
        for(int x = 0; x < COLS; x++){
            for(int y = 0; y < ROWS; y++){
                if(cells[x][y].bottomWall){
                    sr.rectLine(x*cellSize + hMargin, y*cellSize + vMargin, (x+1)*cellSize + hMargin, y*cellSize + vMargin,4);
                }
                if(cells[x][y].leftWall){
                    sr.rectLine(x*cellSize + hMargin, y*cellSize + vMargin, x*cellSize + hMargin, (y+1)*cellSize + vMargin,4);
                }
                if(cells[x][y].topWall){
                    sr.rectLine(x*cellSize + hMargin, (y+1)*cellSize + vMargin, (x+1)*cellSize + hMargin, (y+1)*cellSize + vMargin, 4);
                }
                if(cells[x][y].rightWall){
                    sr.rectLine((x+1)*cellSize + hMargin, y*cellSize + vMargin, (x+1)*cellSize + hMargin, (y+1)*cellSize + vMargin, 4);
                }
            }
        }

    }

    private void renderPlayer(){
        sr.setColor(Color.RED);

        if(DEBUG) log(TAG, player.centerX() + "," + player.centerY());

        sr.rect(player.centerX() - cellSize*0.4f, player.centerY() - cellSize*0.4f , cellSize*0.8f, cellSize*0.8f);

    }

    private void createLabyrinth(){
        if(WORLD_WIDTH / WORLD_HEIGHT < COLS / ROWS){
            cellSize = Math.round(WORLD_WIDTH / (COLS + 1f));
        }else{
            cellSize = Math.round(WORLD_HEIGHT / (ROWS + 1f));
        }
        log(TAG, "cellSize : " + cellSize);
        hMargin = (WORLD_WIDTH - COLS * cellSize) / 2;
        vMargin = (WORLD_HEIGHT - ROWS * cellSize) / 2;
        if(hMargin < 0 || vMargin < 0){
            log(TAG, "onDraw: onDraw position error !!");
            return;
        }
//        if(DEBUG) log(TAG, "hMargin:" + hMargin + "vMargin" + vMargin);

        Stack<Cell> stack = new Stack<>();
        Cell current, next;

        cells = new Cell[COLS][ROWS];

        for(int x = 0; x < COLS; x++){
            for(int y = 0; y < ROWS; y++){
                cells[x][y] = new Cell(x, y, cellSize, hMargin, vMargin);
            }
        }
        player = cells[6][6];
        action = new MoveToAction();
        action.setPosition(player.leftX(), player.bottomY());
        action.setDuration(2f);
        pActor = new PlayerActor(player, action);
        texture = pActor.getPixmapTexture(Color.RED);
        batch = new SpriteBatch();
        rect = new Rectangle(player.centerX() - cellSize*0.4f, player.centerY() - cellSize*0.4f , cellSize*0.8f, cellSize*0.8f);

        previousMovement = cells[0][0];


        current = cells[6][6];
        current.visited = true;

        do{
            next = getNeighbor(current);
            if(next != null){
                removeWall(current, next);
                stack.push(current);
                current = next;
                current.visited = true;
            }else{
                current = stack.pop();
            }
        } while(!stack.empty());

        for(int x = 0; x < COLS; x++){
            for(int y = 0; y < ROWS; y++){
                cells[x][y].visited = false;
            }
        }
        snake.add(player);
    }

    public class Cell{
        public boolean topWall = true;
        public boolean leftWall = true;
        public boolean bottomWall = true;
        public boolean rightWall = true;
        public boolean visited = false;

        public int col, row;
        public float cellSize, vMargin, hMargin;

        public Cell(int col, int row, float cellSize, float hMargin, float vMargin){
            this.col = col;
            this.row = row;
            this.cellSize = cellSize;
            this.vMargin = vMargin;
            this.hMargin = hMargin;
        }

        public float centerX(){
            return hMargin + cellSize * col + cellSize * 0.5f;
        }

        public float centerY(){
            return vMargin + cellSize * row + cellSize * 0.5f;
        }

        public float leftX(){
            return hMargin + cellSize * col;
        }

        public float bottomY(){
            return vMargin + cellSize * row;
        }


    }

    private Cell getNeighbor(Cell cell){
        ArrayList<Cell> neighbors = new ArrayList<>();

        //leftNeighbor
        if(cell.col > 0){
            if(!cells[cell.col-1][cell.row].visited){
                neighbors.add(cells[cell.col-1][cell.row]);
            }
        }
        //rightNeighbor
        if(cell.col < COLS - 1){
            if(!cells[cell.col+1][cell.row].visited){
                neighbors.add(cells[cell.col+1][cell.row]);
            }
        }
        //topNeighbor
        if(cell.row < ROWS - 1){
            if(!cells[cell.col][cell.row+1].visited){
                neighbors.add(cells[cell.col][cell.row+1]);
            }
        }
        //bottomNeighbor
        if(cell.row > 0){
            if(!cells[cell.col][cell.row-1].visited){
                neighbors.add(cells[cell.col][cell.row-1]);
            }
        }

        if(neighbors.size() > 0){
            int index = random.nextInt(neighbors.size());
            return neighbors.get(index);
        }
        return null;
    }

    private void removeWall(Cell current, Cell next){
        //current below next
        if(current.col == next.col && current.row == next.row-1){
            current.topWall = false;
            next.bottomWall = false;
        }
        //current above next
        if(current.col == next.col && current.row == next.row+1){
            current.bottomWall = false;
            next.topWall = false;
        }
        //current toRight next
        if(current.col == next.col+1 && current.row == next.row){
            current.leftWall = false;
            next.rightWall = false;
        }
        //current toLeft next
        if(current.col == next.col-1 && current.row == next.row){
            current.rightWall = false;
            next.leftWall = false;
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void dispose() {
        sr.dispose();
    }

    private void log(String tag, String msg){
        Gdx.app.error(tag, msg);
    }
}

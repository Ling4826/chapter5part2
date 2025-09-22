package se233.chapter5part2;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se233.chapter5part2.controller.GameLoop;
import se233.chapter5part2.model.Food;
import se233.chapter5part2.model.Snake;
import se233.chapter5part2.view.GameStage;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    static GameStage gameStage = new GameStage();
    static Snake snake = new Snake(new Point2D(gameStage.WIDTH / 2, gameStage.HEIGHT / 2));
    static Food food = new Food();
    static GameLoop gameLoop = new GameLoop(gameStage, snake, food);
    static Scene scene = new Scene(gameStage, gameStage.WIDTH * gameStage.TILE_SIZE, gameStage.HEIGHT * gameStage.TILE_SIZE);

    @Override
    public void start(Stage stage) {
        scene.setOnKeyPressed(event -> gameStage.setKey(event.getCode()));
        scene.setOnKeyReleased(event -> gameStage.setKey(null));

        stage.setTitle("Snake Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        (new Thread(gameLoop)).start();
    }

    public static void reset(Stage stage)
    {
        snake = new Snake(new Point2D(gameStage.WIDTH / 2, gameStage.HEIGHT / 2));
        food = new Food();
        gameLoop = new GameLoop(gameStage, snake, food);

        stage.setScene(scene);
        (new Thread(gameLoop)).start();
        stage.show();

    }

}
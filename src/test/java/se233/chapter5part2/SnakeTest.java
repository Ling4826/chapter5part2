package se233.chapter5part2;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se233.chapter5part2.model.Direction;
import se233.chapter5part2.model.Food;
import se233.chapter5part2.model.FoodType;
import se233.chapter5part2.model.Snake;
import static org.junit.jupiter.api.Assertions.*;

public class SnakeTest {
    private Snake snake;

    @BeforeEach
    public void setup() {
        snake = new Snake(new Point2D(10, 10));
    }


    @Test
    void testEatingNormalFoodIncreasesScoreByOne() {
        Food normalFood = new Food();
        normalFood.setFoodType(FoodType.NORMAL);
        assertEquals(0, snake.getScore(), "Initial score should be 0.");
        snake.eat(normalFood);
        assertEquals(1, snake.getScore(), "Score should be 1 after eating normal food.");
    }


    @Test
    void testEatingSpecialFoodIncreasesScoreByFive() {
        Food specialFood = new Food();
        specialFood.setFoodType(FoodType.SPECIAL);
        assertEquals(0, snake.getScore(), "Initial score should be 0.");
        snake.eat(specialFood);
        assertEquals(5, snake.getScore(), "Score should be 5 after eating special food.");
    }

    @Test
    void snakeShouldNotReverseFromRightToLeft() {
        snake.setDirection(Direction.RIGHT);
        snake.changeDirection(KeyCode.LEFT);
        assertEquals(Direction.RIGHT, snake.getDirection(), "Snake should not reverse direction from Right to Left.");
    }


    @Test
    void snakeShouldNotReverseFromUpToDown() {
        snake.setDirection(Direction.UP);
        snake.changeDirection(KeyCode.DOWN);
        assertEquals(Direction.UP, snake.getDirection(), "Snake should not reverse direction from Up to Down.");
    }


    @Test
    void snakeShouldTurnFromRightToUp() {
        snake.setDirection(Direction.RIGHT);
        snake.changeDirection(KeyCode.UP);
        assertEquals(Direction.UP, snake.getDirection(), "Snake should be able to turn from Right to Up.");
    }
}


package se233.chapter5part2;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se233.chapter5part2.model.Direction;
import se233.chapter5part2.model.Food;
import se233.chapter5part2.model.Snake;
import static org.junit.jupiter.api.Assertions.*;

public class SnakeTest {
    private Snake snake;

    @BeforeEach
    public void setup() {
        snake = new Snake(new Point2D(0, 0));
    }

    @Test
    public void initialPosition_shouldBe_atOrigin() {
        assertEquals(new Point2D(0, 0), snake.getHead());
    }

    @Test
    public void move_afterInitialized_headShouldBeInDownwardDirection() {
        snake.setDirection(Direction.DOWN);
        snake.move();
        assertEquals(new Point2D(0, 1), snake.getHead());
    }

    @Test
    public void grow_shouldIncreaseLengthByOne() {
        snake.move(); // move once to set prev_tail
        snake.grow();
        assertEquals(2, snake.getLength());
    }

    @Test
    public void grow_shouldAddPreviousHeadToBody() {
        Point2D cur_head = snake.getHead();
        snake.move();
        snake.grow();
        assertTrue(snake.getBody().contains(cur_head));
    }

    @Test
    public void collided_withSnake_shouldBeDetected() {
        Food food = new Food(new Point2D(0, 0));
        assertTrue(snake.collided(food));
    }

    @Test
    public void checkDead_ifHitGameBorder_snakeWillDie() {
        snake = new Snake(new Point2D(29, 29));
        snake.setDirection(Direction.RIGHT);
        snake.move();
        assertTrue(snake.checkDead());
    }

    @Test
    public void checkDead_ifHitItself_snakeWillDie() {
        snake = new Snake(new Point2D(5, 5));
        snake.setDirection(Direction.RIGHT);
        snake.move(); snake.grow(); // (6,5), length 2
        snake.move(); snake.grow(); // (7,5), length 3
        snake.move(); snake.grow(); // (8,5), length 4
        snake.setDirection(Direction.UP);
        snake.move(); snake.grow(); // (8,4), length 5
        snake.setDirection(Direction.LEFT);
        snake.move(); snake.grow(); // (7,4), length 6
        snake.setDirection(Direction.DOWN);
        snake.move(); // Moves head to (7,5), colliding with its body
        assertTrue(snake.checkDead());
    }
}
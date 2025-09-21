package se233.chapter5part2;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se233.chapter5part2.model.Food;
import se233.chapter5part2.model.FoodType;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {
    private Food food;

    @BeforeEach
    public void setup() {
        food = new Food(new Point2D(0, 0));
    }

    @Test
    public void respawn_shouldBeOnNewPosition() {
        Point2D initialPosition = food.getPosition();
        food.respawn();
        assertNotEquals(initialPosition, food.getPosition());
    }

    @Test
    public void getColor_shouldReturnRedForNormalFood() {
        food.setFoodType(FoodType.NORMAL);
        assertEquals(Color.RED, food.getColor(), "Normal food should be red.");
    }

    @Test
    public void getColor_shouldReturnGreenForSpecialFood() {
        food.setFoodType(FoodType.SPECIAL);
        assertEquals(Color.GREEN, food.getColor(), "Special food should be green.");
    }
}


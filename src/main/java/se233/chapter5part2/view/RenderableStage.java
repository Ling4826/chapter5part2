package se233.chapter5part2.view;

import javafx.scene.input.KeyCode;
import se233.chapter5part2.model.Food;
import se233.chapter5part2.model.Snake;

public interface RenderableStage {
    void render(Snake snake, Food food);
    KeyCode getKey();
}


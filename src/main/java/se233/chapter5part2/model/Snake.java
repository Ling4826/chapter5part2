package se233.chapter5part2.model;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import se233.chapter5part2.view.GameStage;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private Direction direction;
    private Point2D head;
    private Point2D prev_tail;
    private List<Point2D> body;
    private  int score = 0;

    public Snake(Point2D position) {
        direction = Direction.DOWN;
        body = new ArrayList<>();
        this.head = position;
        this.body.add(this.head);
        this.score = 0;
    }

    public void move() {
        head = head.add(direction.current);
        prev_tail = body.remove(body.size() - 1);
        body.add(0, head);
    }

    public boolean checkDead() {
        boolean isOutOfBound = head.getX() < 0 || head.getY() < 0 || head.getX() >= GameStage.WIDTH || head.getY() >= GameStage.HEIGHT;
        boolean isHitBody = body.lastIndexOf(head) > 0;
        return isOutOfBound || isHitBody;
    }


    public boolean collided(Food food) {
        return head.equals(food.getPosition());
    }

    public void grow() {
        body.add(prev_tail);
    }
    public void grow(int amount) {
        for (int i = 0; i < amount; i++) {
            grow();
        }
    }

    public void changeDirection(KeyCode key) {
        Direction curDirection = this.direction;
        if (key == KeyCode.UP && curDirection != Direction.DOWN)
            this.direction = Direction.UP;
        else if (key == KeyCode.DOWN && curDirection != Direction.UP)
            this.direction = Direction.DOWN;
        else if (key == KeyCode.LEFT && curDirection != Direction.RIGHT)
            this.direction = Direction.LEFT;
        else if (key == KeyCode.RIGHT && curDirection != Direction.LEFT)
            this.direction = Direction.RIGHT;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Point2D getHead() {
        return head;
    }

    public int getLength() {
        return body.size();
    }
    public void eat(Food food) {
        if (food.getFoodType() == FoodType.SPECIAL) {
            this.score += 5;
            grow(5);
        } else {
            this.score += 1;
            grow();
        }
    }
    public int getScore() {
        return this.score;
    }

    public List<Point2D> getBody() {
        return body;
    }
}
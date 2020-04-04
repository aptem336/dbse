package view;

import model.Model;

public interface Positional<T extends Model> {
    int getX();

    void setX(int x);

    int getY();

    void setY(int y);
}

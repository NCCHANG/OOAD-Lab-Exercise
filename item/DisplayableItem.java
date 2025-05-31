package item;

import java.awt.*;

public interface DisplayableItem {
    void draw(Graphics2D g2d);        // 把图像画出来
    boolean contains(Point p);        // 鼠标点在它身上吗？

    void move(int dx, int dy);        // 移动
    void rotate(double angle);        // 旋转

     String getType();                       // animal, flower, custom...
    void flip();                            // 左右翻转
    Point getCenter();                      // 中心点（用于旋转、缩放）
    Point getPosition();                    // 左上角
    void setPosition(Point p);

    double getRotation();
    void setRotation(double angle);

    double getScale();
    void setScale(double scale);
}

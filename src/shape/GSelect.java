package shape;

import java.awt.*;

public class GSelect extends GShape {
    public GSelect() {
        this.myShape = new Rectangle.Double();
    }

    @Override
    public void setLocation(double x, double y) {
        Rectangle.Double tempSelect = (Rectangle.Double) this.myShape;
        tempSelect.x = x;
        tempSelect.y = y;
        px = x;
        py = y;
    }

    @Override
    public void setSize(double x, double y) {
        Rectangle.Double tempSelect = (Rectangle.Double) this.myShape;
        tempSelect.setFrameFromDiagonal(px, py, x, y);
    }
}
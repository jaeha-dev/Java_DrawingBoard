package shape;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class GRectangle extends GShape {
    public GRectangle() {
        this.myShape = new Rectangle.Double();
    }

    @Override
    public void setLocation(double x, double y) {
        Rectangle.Double tempRectangle = (Rectangle.Double) this.myShape;
        tempRectangle.x = x;
        tempRectangle.y = y;
        px = x;
        py = y;
    }

    @Override
    public void setSize(double x, double y) {
        Rectangle.Double tempRectangle = (Rectangle.Double) this.myShape;
        tempRectangle.setFrameFromDiagonal(px, py, x, y);
    }

    @Override
    public GShape shapeCopy() {
        AffineTransform tempAffine = new AffineTransform();
        Shape copyShape = tempAffine.createTransformedShape(myShape);
        GRectangle shape = new GRectangle();
        shape.setShape(copyShape);
        shape.setGraphicsAttributes(this);
        return shape;
    }
}
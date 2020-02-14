package shape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class GLine extends GShape {
    public GLine() {
        this.myShape = new Line2D.Double();
    }

    @Override
    public void setLocation(double x, double y) {
        Line2D.Double tempLine = (Line2D.Double) this.myShape;
        tempLine.setLine(x, y, x, y);
    }

    @Override
    public void setSize(double x, double y) {
        Line2D.Double tempLine = (Line2D.Double) this.myShape;
        tempLine.setLine(tempLine.x1, tempLine.y1, x, y);
    }

    @Override
    public GShape shapeCopy() {
        AffineTransform tempAffine = new AffineTransform();
        Shape copyShape = tempAffine.createTransformedShape(myShape);
        GLine shape = new GLine();
        shape.setShape(copyShape);
        shape.setGraphicsAttributes(this);
        return shape;
    }
}
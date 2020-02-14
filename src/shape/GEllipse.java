package shape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class GEllipse extends GShape {
    public GEllipse() {
        this.myShape = new Ellipse2D.Double();
    }

    @Override
    public void setLocation(double x, double y) {
        Ellipse2D.Double tempEllipse = (Ellipse2D.Double) this.myShape;
        tempEllipse.x = x;
        tempEllipse.y = y;
        px = x;
        py = y;
    }

    @Override
    public void setSize(double x, double y) {
        Ellipse2D.Double tempEllipse = (Ellipse2D.Double) this.myShape;
        tempEllipse.setFrameFromDiagonal(px, py, x, y);
    }

    @Override
    public GShape shapeCopy() {
        AffineTransform tempAffine = new AffineTransform();
        Shape copyShape = tempAffine.createTransformedShape(myShape);
        GEllipse shape = new GEllipse();
        shape.setShape(copyShape);
        shape.setGraphicsAttributes(this);
        return shape;
    }
}
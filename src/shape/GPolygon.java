package shape;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class GPolygon extends GShape {
    public GPolygon() {
        this.myShape = new Polygon();
    }

    @Override
    public void setLocation(double x, double y) {
        Polygon tempPolygon = (Polygon) this.myShape;
        tempPolygon.addPoint((int) x, (int) y);
        tempPolygon.addPoint((int) x, (int) y);
    }

    @Override
    public void setSize(double x, double y) {
        Polygon tempPolygon = (Polygon) this.myShape;
        tempPolygon.xpoints[tempPolygon.npoints - 1] = (int) x;
        tempPolygon.ypoints[tempPolygon.npoints - 1] = (int) y;
    }

    public void addPoint(double x, double y) {
        Polygon tempPolygon = (Polygon) this.myShape;
        tempPolygon.addPoint((int) x, (int) y);
    }

    @Override
    public GShape shapeCopy() {
        AffineTransform tempAffine = new AffineTransform();
        Shape copyShape = tempAffine.createTransformedShape(myShape);
        GPolygon shape = new GPolygon();
        shape.setShape(copyShape);
        shape.setGraphicsAttributes(this);
        return shape;
    }
}
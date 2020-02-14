package shape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.QuadCurve2D; //x1, y1, ctrlx, ctrly, x2, y2, 두 개의 끝점과 한 개의 제어점

public class GCurve extends GShape {
    public GCurve() {
        this.myShape = new QuadCurve2D.Double();
    }

    @Override
    public void setLocation(double x, double y) {
        QuadCurve2D.Double tempCurve = (QuadCurve2D.Double) this.myShape;
        tempCurve.x1 = x;
        tempCurve.y1 = y;
        tempCurve.x2 = x;
        tempCurve.y2 = y;
    }

    @Override
    public void setSize(double x, double y) {
        QuadCurve2D.Double tempCurve = (QuadCurve2D.Double) this.myShape;
        tempCurve.x2 = x;
        tempCurve.y2 = y;
    }

    public void addPoint(double x, double y) {
        QuadCurve2D.Double tempCurve = (QuadCurve2D.Double) this.myShape;
        tempCurve.ctrlx = x;
        tempCurve.ctrly = y;
    }

    @Override
    public GShape shapeCopy() {
        AffineTransform tempAffine = new AffineTransform();
        Shape copyShape = tempAffine.createTransformedShape(myShape);
        GCurve shape = new GCurve();
        shape.setShape(copyShape);
        shape.setGraphicsAttributes(this);
        return shape;
    }
}
package shape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class GStar extends GShape {
    private double[] xPoints;
    private double[] yPoints;

    public GStar() {
        this.myShape = new GeneralPath();
        this.xPoints = new double[]{50, 60, 100, 60, 50, 40, 0, 40};
        this.yPoints = new double[]{0, 40, 50, 60, 100, 60, 50, 40};
    }

    @Override
    public void setLocation(double x, double y) {
        GeneralPath tempStar = (GeneralPath) this.myShape;
        for (int i = 0; i < this.xPoints.length; i++) {
            this.xPoints[i] = this.xPoints[i] + x;
            this.yPoints[i] = this.yPoints[i] + y;
        }
        for (int i = 0; i < this.xPoints.length; i++) {
            tempStar.moveTo(xPoints[i], yPoints[i]);
        }
    }

    @Override
    public void setSize(double x, double y) {
        GeneralPath tempStar = (GeneralPath) this.myShape;
        for (int i = 0; i < this.xPoints.length; i++) {
            tempStar.lineTo(xPoints[i], yPoints[i]);
        }
    }

    @Override
    public GShape shapeCopy() {
        AffineTransform tempAffine = new AffineTransform();
        Shape copyShape = tempAffine.createTransformedShape(myShape);
        GStar shape = new GStar();
        shape.setShape(copyShape);
        shape.setGraphicsAttributes(this);
        return shape;
    }
}
package transformer;

import shape.GShape;
import java.awt.*;
import java.awt.geom.Point2D;

public class GRotator extends GTransformer {
    private double degree;

    public GRotator(GShape shape) {
        super(shape);
    }

    @Override
    public void initTransforming(Graphics g, double x, double y) {
        degree = Math.atan2(this.shape.getBounds().height / 2 - y, this.shape.getBounds().width / 2 - x);
    }

    @Override
    public void keepTransforming(Graphics g, double x, double y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setXORMode(Color.WHITE);
        g2d.setStroke(stroke);
        Point2D resizeCoordinate = computeLocation(x, y);
        this.shape.draw(g2d);
        this.shape.setRotation(Math.toRadians(degree), resizeCoordinate.getX(), resizeCoordinate.getY());
        this.shape.draw(g2d);
    }

    private Point2D.Double computeLocation(double x, double y) {
        double xCoordinate = 0;
        double yCoordinate = 0;
        xCoordinate = this.shape.getBounds().x + this.shape.getBounds().width / 2;
        yCoordinate = this.shape.getBounds().y + this.shape.getBounds().height / 2;
        return new Point2D.Double(xCoordinate, yCoordinate);
    }

    public void finishTransforming(double x, double y) { }
}
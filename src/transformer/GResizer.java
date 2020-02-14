package transformer;

import shape.GAnchors.EAnchors;
import shape.GShape;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class GResizer extends GTransformer {
    private double px, py;
    private double sx, sy;
    private EAnchors eAnchor;

    public GResizer(GShape shape) {
        super(shape);
    }

    @Override
    public void initTransforming(Graphics g, double x, double y) {
        px = x;
        py = y;
        sx = this.shape.getBounds().getX();
        sy = this.shape.getBounds().getY();
        eAnchor = this.shape.getEAnchor();
    }

    @Override
    public void keepTransforming(Graphics g, double x, double y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setXORMode(Color.WHITE);
        g2d.setStroke(stroke);
        Point2D resizeCoordinate = computeLocation(x, y);
        Point2D resizeFactor = computeSize(x, y);

		// this.shape.draw(g2d);
		// this.shape.setResize(resizeCoordinate, resizeFactor);
		// this.shape.draw(g2d);

        AffineTransform tempAffine = g2d.getTransform();
        g2d.translate(resizeCoordinate.getX() - sx, resizeCoordinate.getY() - sy);
        this.shape.draw(g2d);
		// this.shape.setMove(resizeCoordinate.getX()-sx, resizeCoordinate.getY()-sy);
		// this.shape.setMove(x-px, y-py);
        this.shape.setResize(resizeFactor);
        this.shape.draw(g2d);
        g2d.setTransform(tempAffine);

        px = x;
        py = y;
    }

    private Point2D.Double computeLocation(double x, double y) {
        double xCoordinate = 0;
        double yCoordinate = 0;
        if (eAnchor == EAnchors.NW) {
            xCoordinate = x;
            yCoordinate = y;
        } else if (eAnchor == EAnchors.NN) {
            xCoordinate = sx;
            yCoordinate = y;
        } else if (eAnchor == EAnchors.NE) {
            xCoordinate = sx;
            yCoordinate = y;
        } else if (eAnchor == EAnchors.EE) {
            xCoordinate = sx;
            yCoordinate = sy;
        } else if (eAnchor == EAnchors.SE) {
            xCoordinate = sx;
            yCoordinate = sy;
        } else if (eAnchor == EAnchors.SS) {
            xCoordinate = sx;
            yCoordinate = sy;
        } else if (eAnchor == EAnchors.SW) {
            xCoordinate = x;
            yCoordinate = sy;
        } else if (eAnchor == EAnchors.WW) {
            xCoordinate = x;
            yCoordinate = sy;
        }
        return new Point2D.Double(xCoordinate, yCoordinate);
    }

    private Point2D.Double computeSize(double x, double y) {
        double deltaW = 0;
        double deltaH = 0;
        if (eAnchor == EAnchors.NW) {
            deltaW = -(x - px);
            deltaH = -(y - py);
        } else if (eAnchor == EAnchors.NN) {
            deltaW = 0;
            deltaH = -(y - py);
        } else if (eAnchor == EAnchors.NE) {
            deltaW = x - px;
            deltaH = -(y - py);
        } else if (eAnchor == EAnchors.WW) {
            deltaW = -(x - px);
            deltaH = 0;
        } else if (eAnchor == EAnchors.EE) {
            deltaW = x - px;
            deltaH = 0;
        } else if (eAnchor == EAnchors.SW) {
            deltaW = -(x - px);
            deltaH = y - py;
        } else if (eAnchor == EAnchors.SS) {
            deltaW = 0;
            deltaH = y - py;
        } else if (eAnchor == EAnchors.SE) {
            deltaW = x - px;
            deltaH = y - py;
        }
        double currentW = this.shape.getBounds().getWidth();
        double currentH = this.shape.getBounds().getHeight();
        double xFactor = 1.0 + deltaW / currentW;
        double yFactor = 1.0 + deltaH / currentH;
        return new Point2D.Double(xFactor, yFactor);
    }

    public void finishTransforming(double x, double y) { }
}
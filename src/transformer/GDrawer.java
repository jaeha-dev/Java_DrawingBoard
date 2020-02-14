package transformer;

import shape.*;
import java.awt.*;
import java.util.Vector;

public class GDrawer extends GTransformer {
    private double px, py;

    public GDrawer(GShape shape) {
        super(shape);
    }

    @Override
    public void initTransforming(Graphics g, double x, double y) {
        this.shape.setLocation(x, y);
        px = x;
        py = y;
    }

    @Override
    public void keepTransforming(Graphics g, double x, double y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setXORMode(Color.WHITE);
        g2d.setStroke(stroke);
        this.shape.draw(g2d);
        this.shape.setSize(x, y);
        this.shape.draw(g2d);
    }

    public void continuePolygonTransforming(double x, double y) {
        ((GPolygon) shape).addPoint(x, y);
    }

    public void continueCurveTransforming(double x, double y) {
        ((GCurve) shape).addPoint(x, y);
    }

    public void finishCurveTransforming(Vector<GShape> drawingShapes, double x, double y) {
        ((GCurve) shape).setSize(x, y);
        drawingShapes.add(shape);
    }

    public void finishTextTransforming(Vector<GShape> drawingShapes, double x, double y) {
        ((GText) shape).setText(x, y);
        drawingShapes.add(shape);
    }

    public void finishTransforming(Vector<GShape> drawingShapes, double x, double y) {
        if (px == x && py == y) {
            return;

        } else {
            drawingShapes.add(shape);
        }
    }
}
package transformer;

import shape.GShape;
import java.awt.*;
import java.util.Vector;

public class GGrouper extends GTransformer {
    public GGrouper(GShape shape) {
        super(shape);
    }

    @Override
    public void initTransforming(Graphics g, double x, double y) {
        this.shape.setLocation(x, y);
    }

    @Override
    public void keepTransforming(Graphics g, double x, double y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setXORMode(Color.WHITE);
        this.shape.draw(g2d);
        this.shape.setSize(x, y);
        this.shape.draw(g2d);
    }

    public void finishTransforming(Vector<GShape> drawingShapes) {
        for (GShape tempShape : drawingShapes) {
            if (shape.getBounds().contains(tempShape.getBounds())) {
                tempShape.setSelected(true);
            }
        }
    }
}
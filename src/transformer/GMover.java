package transformer;

import shape.GShape;
import java.awt.*;

public class GMover extends GTransformer {
    private double px, py;

    public GMover(GShape shape) {
        super(shape);
    }

    @Override
    public void initTransforming(Graphics g, double x, double y) {
        px = x;
        py = y;
    }

    @Override
    public void keepTransforming(Graphics g, double x, double y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setXORMode(Color.WHITE);
        g2d.setStroke(stroke);
        this.shape.draw(g2d);
        this.shape.setMove(x - px, y - py);
        this.shape.draw(g2d);
        px = x;
        py = y;
    }
}
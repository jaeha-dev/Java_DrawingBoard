package transformer;

import shape.GShape;
import java.awt.*;

public abstract class GTransformer {
    protected GShape shape;
    BasicStroke stroke;

	GTransformer(GShape shape) {
        this.shape = shape;
		float[] dashes = {4};
		this.stroke = new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 10, dashes, 0);
    }

    public abstract void initTransforming(Graphics g, double x, double y);

    public abstract void keepTransforming(Graphics g, double x, double y);
}
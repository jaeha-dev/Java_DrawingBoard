package shape;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class GText extends GShape {
    public GText() {
        this.myShape = new Rectangle.Double();
    }

    @Override
    public void setLocation(double x, double y) {
        Rectangle.Double tempText = (Rectangle.Double) this.myShape;
        tempText.x = x;
        tempText.y = y;
        px = x;
        py = y;
    }

    @Override
    public void setSize(double x, double y) {
        Rectangle.Double tempText = (Rectangle.Double) this.myShape;
        tempText.setFrameFromDiagonal(px, py, x, y);
    }

    public void setText(double x, double y) {
        Rectangle.Double tempText = (Rectangle.Double) this.myShape;
        JTextPane jTextPane = new JTextPane();
        jTextPane.setText("asdasd");

    }

    @Override
    public GShape shapeCopy() {
        AffineTransform tempAffine = new AffineTransform();
        Shape copyShape = tempAffine.createTransformedShape(myShape);
        GText shape = new GText();
        shape.setShape(copyShape);
        shape.setGraphicsAttributes(this);
        return shape;
    }
}
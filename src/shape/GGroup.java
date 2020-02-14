package shape;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Vector;

public class GGroup extends GShape {
    private Vector<GShape> groupShapes;

    public GGroup() {
        this.groupShapes = new Vector<GShape>();
        this.myShape = new Rectangle.Double();
    }

    public void addShape(GShape shape) {
        groupShapes.add(0, shape);
        if (groupShapes.size() == 1) {
            myShape = shape.getBounds();
        } else {
            myShape = myShape.getBounds().createUnion(shape.getBounds());
        }
    }

    public Vector<GShape> getChildVector() {
        return groupShapes;
    }

    @Override
    public GShape clone() {
        return null;
    }

    @Override
    public void setLocation(double x, double y) {
        for (GShape shape : groupShapes) {
            shape.setLocation(x, y);
        }
    }

    @Override
    public void setSize(double x, double y) {
        for (GShape shape : groupShapes) {
            shape.setSize(x, y);
        }
    }

    @Override
    public GShape shapeCopy() {
        GGroup returnShape = new GGroup();
        for (GShape shape : groupShapes) {
            returnShape.addShape(shape.shapeCopy());
        }
        return returnShape;
    }

    @Override
    public void setLineColor(Color lineColor) {
        for (GShape shape : groupShapes) {
            shape.setLineColor(lineColor);
        }
    }

    @Override
    public void setFillColor(Color fillColor) {
        for (GShape shape : groupShapes) {
            shape.setFillColor(fillColor);
        }
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            anchors = new GAnchors();
            anchors.setPosition(myShape.getBounds());
        } else {
            anchors = null;
        }
    }

    @Override
    public void draw(Graphics2D g2D) {
        for (GShape shape : groupShapes) {
            shape.draw(g2D);
        }
        if (this.isSelected()) {
            g2D.setColor(Color.BLACK);
            g2D.draw(myShape);
            g2D.setStroke(new BasicStroke());
            this.getAnchors().setPosition(this.getBounds());
            this.getAnchors().draw(g2D);
        }
    }

    @Override
    public void setMove(double x, double y) {
        super.setMove(x, y);
        for (GShape shape : groupShapes) {
            shape.setMove(x, y);
        }
    }

    @Override
    public void setResize(Point2D resizeFactor) {
        super.setResize(resizeFactor);
        for (GShape shape : groupShapes) {
            shape.setResize(resizeFactor);
        }
    }

    @Override
    public void setRotation(double degree, double x, double y) {
        super.setRotation(degree, x, y);
        for (GShape shape : groupShapes) {
            shape.setRotation(degree, x, y);
        }
    }
}
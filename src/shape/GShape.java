package shape;

import shape.GAnchors.EAnchors;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

public abstract class GShape implements Serializable {
    protected Shape myShape;
    private Color lineColor, fillColor;
    private AffineTransform affineTransform;
    boolean selected;
    GAnchors anchors;
    private EAnchors eAnchor;
    double px, py;
    private GradientPaint gp;

    void setGraphicsAttributes(GShape shape) {
        setLineColor(shape.getLineColor());
        setFillColor(shape.getFillColor());
        setAnchors(shape.getAnchors());
        setSelected(shape.isSelected());
    }

    public void setShape(Shape copyShape) {
        myShape = copyShape;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    private void setAnchors(GAnchors anchors) {
        this.anchors = anchors;
    }

    GAnchors getAnchors() {
        return anchors;
    }

    public EAnchors getEAnchor() {
        return this.eAnchor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    private Color getLineColor() {
        return lineColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    private Color getFillColor() {
        return fillColor;
    }

    public void setTextureColor(ImageIcon textureColor) {
        // BufferedImage image = new BufferedImage(6, 6, textureColor);
        // tp = new TexturePaint(textureColor, new Rectangle(20, 20));
    }

    public void setGradientColor(Color selectColor1, Color selectColor2) {
        gp = new GradientPaint(100, 100, selectColor1, 200, 200, selectColor2);
    }

    public GShape() {
        this.affineTransform = new AffineTransform();
        this.anchors = new GAnchors();
        this.selected = false;
    }

    public GShape clone() {
        try {
            return this.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void draw(Graphics2D g2D) {
        if (this.myShape.getBounds().width == 0 && this.myShape.getBounds().height == 0) {
            return;
        }
        // g2D.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, 0));
        g2D.setColor(fillColor);
        g2D.fill(this.myShape);
        g2D.setColor(lineColor);
        g2D.draw(this.myShape);
    }

    public void drawAnchor(Graphics2D g2D) {
        if (this.myShape.getBounds().width == 0 && this.myShape.getBounds().height == 0) {
            return;
        }
        if (this.selected) {
            g2D.setColor(Color.BLACK);
            this.anchors.setPosition(this.myShape.getBounds());
            this.anchors.draw(g2D);
        }
    }

    public Rectangle getBounds() {
        return this.myShape.getBounds();
    }

    public boolean isOn(double x, double y) {
        if (this.selected) {
            this.eAnchor = this.anchors.isOn(x, y);
            if (this.eAnchor != null) {
                return true;
            }
        }
        if (this.myShape.intersects(new Rectangle.Double(x, y, 8, 8))) {
            this.eAnchor = EAnchors.MM;
            return true;
        }
        return false;
    }

    // shape copy
    public GShape shapeCopy() {
        return null;
    }

    // draw
    public void setLocation(double x, double y) {
    }

    public void setSize(double x, double y) {
    }

    // move
    public void setMove(double x, double y) {
        affineTransform.setToTranslation(x, y);
        myShape = affineTransform.createTransformedShape(myShape);
    }

    // resize
    public void setResize(Point2D resizeFactor) {
        affineTransform.setToScale(resizeFactor.getX(), resizeFactor.getY());
        myShape = affineTransform.createTransformedShape(myShape);
    }

    public void setResize(Point2D resizeCoordinate, Point2D resizeFactor) {
        AffineTransform move = new AffineTransform();
        AffineTransform resize = new AffineTransform();
        move.translate(resizeCoordinate.getX(), resizeCoordinate.getY());
        resize.scale(resizeFactor.getX(), resizeFactor.getY());

        move.setTransform(resize);
        myShape = move.createTransformedShape(myShape);
    }

    public void setResizeMove(Point2D resizeCoordinate) {
        affineTransform.setToTranslation(resizeCoordinate.getX(), resizeCoordinate.getY());
        myShape = affineTransform.createTransformedShape(myShape);
    }

    public void setResizeMoveReverse(double x, double y) {
        affineTransform.setToTranslation(x, y);
        myShape = affineTransform.createTransformedShape(myShape);
    }

    // rotate
    public void setRotation(double degree, double x, double y) {
        affineTransform.setToRotation(degree, x, y);
        myShape = affineTransform.createTransformedShape(myShape);
    }
}
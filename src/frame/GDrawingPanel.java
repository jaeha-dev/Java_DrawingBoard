package frame;

import shape.GAnchors.EAnchors;
import shape.*;
import transformer.*;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Stack;
import java.util.Vector;

public class GDrawingPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    enum EDrawingState { eIdle, eDrawing, eNDrawing, eCDrawing, eSelecting, eMoving, eResizing, eRotating };

    private GShape currentTool;

    void setCurrentTool(GShape currentTool) {
        this.currentTool = currentTool;
    }

    private GTransformer transformer;
    private GShape currentShape;
    private Vector<GShape> drawingShapes;
    private Vector<GShape> tempShapes;
    private Stack<GShape> stackShapes;
    private EDrawingState eDrawingState;
    private Color lineColor, fillColor;
    private TexturePaint textureColor;
    private GradientPaint gradientColor;

    public GDrawingPanel() {
        this.currentTool = null;
        this.currentShape = null;
        this.drawingShapes = new Vector<GShape>();
        this.tempShapes = new Vector<GShape>();
        this.stackShapes = new Stack<GShape>();
        this.eDrawingState = EDrawingState.eIdle;
        this.setBackground(Color.WHITE);
        this.lineColor = Color.BLACK;
        this.fillColor = Color.WHITE;
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseMotionListener(mouseHandler);
        this.addMouseListener(mouseHandler);
    }

    void initialize() {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
//		for (int i = drawingShapes.size(); i > 0; i--) {
//			GShape shape = drawingShapes.get(i - 1);
//			shape.draw(g2D);
//			shape.drawAnchor(g2D);
//		}

        for (GShape shape : drawingShapes) {
            shape.draw(g2D);
            shape.drawAnchor(g2D);
        }
    }

    public void clearDrawingShapes() {
        drawingShapes.clear();
        this.repaint();
    }

    public Vector<GShape> getDrawingShapes() {
        return drawingShapes;
    }

    public void setDrawingShapes(Vector<GShape> drawingShapes) {
        this.drawingShapes = drawingShapes;
        this.repaint();
    }

    public void UnDo() {
        if (drawingShapes.size() != 0) {
            GShape shape = this.drawingShapes.get(this.drawingShapes.size() - 1);
            this.stackShapes.push(shape);
            this.drawingShapes.remove(shape);
            this.repaint();
        }
    }

    public void ReDo() {
        if (!this.stackShapes.isEmpty()) {
            GShape shape = this.stackShapes.pop();
            this.drawingShapes.add(shape);
            this.repaint();
        }
    }

    public void cutShapes() {
        tempShapes.clear();
        for (int i = drawingShapes.size(); i > 0; i--) {
            GShape shape = drawingShapes.get(i - 1);
            if (shape.isSelected()) {
                drawingShapes.remove(shape);
                tempShapes.add(shape);
            }
        }
        this.repaint();
    }

    public void copyShapes() {
        tempShapes.clear();
        for (int i = drawingShapes.size(); i > 0; i--) {
            GShape shape = drawingShapes.get(i - 1);
            if (shape.isSelected()) {
                tempShapes.add(shape.shapeCopy());
            }
        }
    }

    public void pasteShapes() {
        for (int i = tempShapes.size(); i > 0; i--) {
            GShape shape = tempShapes.get(i - 1);
            if (drawingShapes.contains(shape)) {
                drawingShapes.add(shape.shapeCopy());
            } else {
                drawingShapes.add(shape);
            }
            shape.setSelected(true);
        }
        this.repaint();
    }

    public void deleteShapes() {
        for (int i = drawingShapes.size(); i > 0; i--) {
            GShape shape = drawingShapes.get(i - 1);
            if (shape.isSelected()) {
                drawingShapes.remove(shape);
            }
        }
        this.repaint();
    }

    public void selectAllShapes() {
        for (GShape shape : this.drawingShapes) {
            shape.setSelected(true);
            Graphics2D g2d = (Graphics2D) this.getGraphics();
            shape.drawAnchor(g2d);
        }
        this.repaint();
    }

    public void groupShapes(GGroup group) {
        boolean check = false;
        for (int i = drawingShapes.size(); i > 0; i--) {
            GShape shape = drawingShapes.get(i - 1);
            if (shape.isSelected()) {
                shape.setSelected(false);
                group.addShape(shape);
                drawingShapes.remove(shape);
                check = true;
            }
        }
        if (check) {
            group.setSelected(true);
            drawingShapes.add(group);
        }
        this.repaint();
    }

    public void unGroupShapes() {
        Vector<GShape> unGroupShapes = new Vector<GShape>();
        for (int i = drawingShapes.size(); i > 0; i--) {
            GShape shape = drawingShapes.get(i - 1);
            if (shape instanceof GGroup && shape.isSelected()) {
                for (GShape childShape : ((GGroup) shape).getChildVector()) {
                    childShape.setSelected(true);
                    unGroupShapes.add(childShape);
                }
                drawingShapes.remove(shape);
            }
        }
        drawingShapes.addAll(unGroupShapes);
        this.repaint();
    }

    public void setLineColor(Color lineColor) {
        for (GShape shape : this.drawingShapes) {
            if (shape.isSelected()) {
                shape.setLineColor(lineColor);
            }
            this.repaint();
        }
        this.lineColor = lineColor;
    }

    public void defaultLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setFillColor(Color fillColor) {
        for (GShape shape : this.drawingShapes) {
            if (shape.isSelected()) {
                shape.setFillColor(fillColor);
            }
            this.repaint();
        }
        this.fillColor = fillColor;
    }

    public void defaultFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setTexture(ImageIcon image) {
        for (GShape shape : this.drawingShapes) {
            if (shape.isSelected()) {
                shape.setTextureColor(image);
            }
            this.repaint();
        }
    }

    public void setGradation(Color selectColor1, Color selectColor2) {
        for (GShape shape : this.drawingShapes) {
            if (shape.isSelected()) {
                shape.setGradientColor(selectColor1, selectColor2);
            }
            this.repaint();
        }
    }

    public void insertImage(Image image) {
        System.out.println(image.toString());
//		this.paintComponent(image.getGraphics());
//		Graphics2D g2d = (Graphics2D) image.getGraphics();
//		g2d.drawImage(image, 40, 50, this);
    }

    public void insertAudio() { }

    public void insertVideo() { }

    public void insertTextBox() {
        JTextArea text = new JTextArea("asd");
        this.add(text);
    }

    private void setSelection() {
        this.repaint();
        this.currentShape.setSelected(true);
        Graphics2D g2d = (Graphics2D) this.getGraphics();
        this.currentShape.drawAnchor(g2d);
    }

    private void clearSelection() {
        for (GShape shape : this.drawingShapes) {
            shape.setSelected(false);
        }
        this.repaint();
    }

    private GShape onShape(double x, double y) {
        for (GShape shape : this.drawingShapes) {
            if (shape.isOn(x, y)) {
                return shape;
            }
        }
        return null;
    }

    private void continueTransforming(int button, int clickCount, double x, double y) {
        if (button == MouseEvent.BUTTON1) {
            if (this.eDrawingState == EDrawingState.eNDrawing) {
                ((GDrawer) this.transformer).continuePolygonTransforming(x, y);
            } else if (this.eDrawingState == EDrawingState.eCDrawing) {
                ((GDrawer) this.transformer).continueCurveTransforming(x, y);
            }

        } else if (button == MouseEvent.BUTTON3) {
            if (this.eDrawingState == EDrawingState.eNDrawing) {
                ((GDrawer) this.transformer).finishTransforming(drawingShapes, x, y);
            } else if (this.eDrawingState == EDrawingState.eCDrawing) {
                ((GDrawer) this.transformer).finishCurveTransforming(drawingShapes, x, y);
            }
            this.eDrawingState = EDrawingState.eIdle;
            this.clearSelection();
        }
    }

    private void initTransforming(double x, double y) {
        if (this.eDrawingState == EDrawingState.eIdle) {
            if (currentTool instanceof GSelect) {
                currentShape = onShape(x, y);

                if (currentShape != null) {
                    this.clearSelection();
                    currentShape.setSelected(true);
                    if (currentShape.getEAnchor() == EAnchors.MM) {
                        this.transformer = new GMover(this.currentShape);
                        this.eDrawingState = EDrawingState.eMoving;
                    } else if (currentShape.getEAnchor() == EAnchors.RR) {
                        this.transformer = new GRotator(this.currentShape);
                        this.eDrawingState = EDrawingState.eRotating;
                    } else {
                        this.transformer = new GResizer(this.currentShape);
                        this.eDrawingState = EDrawingState.eResizing;
                    }
                    this.transformer.initTransforming(this.getGraphics(), x, y);

                } else {
                    this.clearSelection();
                    this.currentShape = this.currentTool.clone();
                    this.currentShape.setLineColor(new Color(0, 120, 215));
                    this.currentShape.setFillColor(new Color(170, 204, 238));
                    this.transformer = new GGrouper(this.currentShape);
                    this.eDrawingState = EDrawingState.eSelecting;
                    this.transformer.initTransforming(this.getGraphics(), x, y);
                }

            } else {
                this.clearSelection();
                this.currentShape = this.currentTool.clone();
                this.currentShape.setLineColor(lineColor);
                this.currentShape.setFillColor(fillColor);
                this.transformer = new GDrawer(this.currentShape);

                if (currentTool instanceof GPolygon) {
                    this.eDrawingState = EDrawingState.eNDrawing;
                } else if (currentTool instanceof GCurve) {
                    this.eDrawingState = EDrawingState.eCDrawing;
                } else {
                    this.eDrawingState = EDrawingState.eDrawing;
                }

                this.transformer.initTransforming(this.getGraphics(), x, y);
            }
        }
    }

    private void keepTransforming(double x, double y) {
        this.transformer.keepTransforming(this.getGraphics(), x, y);
    }

    private void finishTransforming(double x, double y) {
        if (this.eDrawingState == EDrawingState.eDrawing) {
            if (this.currentTool instanceof GText) {
                ((GDrawer) this.transformer).finishTextTransforming(drawingShapes, x, y);
            } else {
                ((GDrawer) this.transformer).finishTransforming(drawingShapes, x, y);
            }
			// System.out.println(drawingShapes.size());

        } else if (this.eDrawingState == EDrawingState.eNDrawing) {
            return;
        } else if (this.eDrawingState == EDrawingState.eCDrawing) {
            return;
        } else if (this.eDrawingState == EDrawingState.eSelecting) {
            ((GGrouper) this.transformer).finishTransforming(drawingShapes);
        } else if (this.eDrawingState == EDrawingState.eResizing) {
            ((GResizer) this.transformer).finishTransforming(x, y);
        } else if (this.eDrawingState == EDrawingState.eRotating) {
            ((GRotator) this.transformer).finishTransforming(x, y);
        }

        this.eDrawingState = EDrawingState.eIdle;
        this.repaint();
    }

    private void cursorTransforming(int x, int y) {
        if (this.eDrawingState == EDrawingState.eNDrawing) {
            ((GDrawer) this.transformer).keepTransforming(this.getGraphics(), x, y);
        } else if (this.eDrawingState == EDrawingState.eCDrawing) {
            ((GDrawer) this.transformer).keepTransforming(this.getGraphics(), x, y);
        } else if (this.eDrawingState == EDrawingState.eIdle) {
            GShape shape = onShape(x, y);

            if (shape == null) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            } else {
                if (shape.isSelected()) {
                    EAnchors eAnchor = shape.getEAnchor();
                    if (eAnchor == EAnchors.NW) {
                        setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                    } else if (eAnchor == EAnchors.NN) {
                        setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
                    } else if (eAnchor == EAnchors.NE) {
                        setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
                    } else if (eAnchor == EAnchors.EE) {
                        setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
                    } else if (eAnchor == EAnchors.SE) {
                        setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                    } else if (eAnchor == EAnchors.SS) {
                        setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
                    } else if (eAnchor == EAnchors.SW) {
                        setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
                    } else if (eAnchor == EAnchors.WW) {
                        setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                    } else if (eAnchor == EAnchors.RR) {
                        setRotateCursor();
                    } else if (eAnchor == EAnchors.MM) {
                        setCursor(new Cursor(Cursor.MOVE_CURSOR));
                    }
                }
            }
        }
    }

    private void setRotateCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image rotateIconImage = toolkit.getImage("resource/rotate.png");
        Point point = new Point(15, 15);
        Cursor ROTATE_CURSOR = toolkit.createCustomCursor(rotateIconImage, point, "Rotate");
        setCursor(ROTATE_CURSOR);
    }

    private class MouseHandler implements MouseInputListener, MouseMotionListener {
        @Override
        public void mouseClicked(MouseEvent event) {
            continueTransforming(event.getButton(), event.getClickCount(), event.getX(), event.getY());
        }

        @Override
        public void mousePressed(MouseEvent event) {
            initTransforming(event.getX(), event.getY());
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            keepTransforming(event.getX(), event.getY());
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            finishTransforming(event.getX(), event.getY());
        }

        @Override
        public void mouseMoved(MouseEvent event) {
            cursorTransforming(event.getX(), event.getY());
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void mouseExited(MouseEvent event) {
        }
    }
}
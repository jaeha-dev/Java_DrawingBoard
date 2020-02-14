package shape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Vector;

public class GAnchors implements Serializable {
    public enum EAnchors {NW, NN, NE, EE, SE, SS, SW, WW, RR, MM}
    private final int WIDTH = 7;
    private final int HEIGHT = 7;

    private Vector<Ellipse2D> anchors;

    public Vector<Ellipse2D> getAnchors() {
        return anchors;
    }

    GAnchors() {
        this.anchors = new Vector<Ellipse2D>();
        for (int i = 0; i < EAnchors.values().length-1; i++) {
            Ellipse2D anchor = new Ellipse2D.Double();
            anchor.setFrame(0, 0, WIDTH, HEIGHT);
            this.anchors.add(anchor);
        }
    }

    EAnchors isOn(double x, double y) {
        for (int i = 0; i < this.anchors.size(); i++) {
            if (this.anchors.get(i).contains(x, y)) {
                return EAnchors.values()[i];
            }
        }
        return null;
    }

    void setPosition(Rectangle r) {
        for (int i = 0; i < this.anchors.size(); i++) {
            int x = 0;
            int y = 0;
            EAnchors eAnchor = EAnchors.values()[i];
            switch (eAnchor) {
                case NW:
                    x = r.x;
                    y = r.y;
                    break;
                case NN:
                    x = r.x + r.width / 2;
                    y = r.y;
                    break;
                case NE:
                    x = r.x + r.width;
                    y = r.y;
                    break;
                case EE:
                    x = r.x + r.width;
                    y = r.y + r.height / 2;
                    break;
                case SE:
                    x = r.x + r.width;
                    y = r.y + r.height;
                    break;
                case SS:
                    x = r.x + r.width / 2;
                    y = r.y + r.height;
                    break;
                case SW:
                    x = r.x;
                    y = r.y + r.height;
                    break;
                case WW:
                    x = r.x;
                    y = r.y + r.height / 2;
                    break;
                case RR:
                    x = r.x + r.width / 2;
                    y = r.y - 40;
                    break;
                default:
                    break;
            }
            x = x - WIDTH / 2;
            y = y - HEIGHT / 2;
            this.anchors.get(i).setFrame(x, y, WIDTH, HEIGHT);
        }
    }

    void draw(Graphics2D g2D) {
        Color lineColor = g2D.getColor();
        Color fillColor = g2D.getBackground();
        for (Ellipse2D eAnchor: this.anchors) {
            // setPaintMode 로 드로잉하여 앵커 아래에 도형의 테두리가 보여지는 것을 방지함.
            // 채우기를 먼저 한 후, 테두리를 그려야 함.
            g2D.setPaintMode();
            g2D.setColor(fillColor);
            g2D.fill(eAnchor);
            g2D.setColor(lineColor);
            g2D.draw(eAnchor);
        }
    }
}
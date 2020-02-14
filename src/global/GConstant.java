package global;

import shape.*;

public class GConstant {

	private static String PATH = "resource";

	public enum EShapes {
		eSelect("select.gif","selectSLT.gif", new GSelect()),
		eRectangle("rectangle.gif","rectangleSLT.gif", new GRectangle()),
		eEllipse("ellipse.gif","ellipseSLT.gif", new GEllipse()),
		eLine("line.gif","lineSLT.gif", new GLine()),
		ePolygon("polygon.gif","polygonSLT.gif", new GPolygon()),
		eCurve("curve.gif","curveSLT.gif", new GCurve()),
		eStar("star.gif","starSLT.gif", new GStar()),
		eText("text.gif","textSLT.gif", new GText());

		private String iconName;
		private String iconSLTName;
		private GShape shape;

		EShapes(String iconName, String iconSLTName, GShape shape) {
			this.iconName = iconName;
			this.iconSLTName = iconSLTName;
			this.shape = shape;
		}

		public String getIconName() {
			return PATH + '/' + this.iconName;
		}

		public String getIconSLTName() {
			return PATH + '/' + this.iconSLTName;
		}

		public GShape getShape() {
			return this.shape;
		}
	}

	public enum EFileMenus {
		New("new.png"),
		Open("open.png"),
		Close("close.png"),
		Save("save.png"),
		SaveAs("saveAs.png"),
		Print("print.png"),
		Exit("exit.png");

		private String iconName;

		EFileMenus(String iconName) {
			this.iconName = iconName;
		}

		public String getIconName() {
			return PATH + '/' + this.iconName;
		}
	}

	public enum EEditMenus {
		UnDo("undo.png"),
		ReDo("redo.png"),
		Cut("cut.png"),
		Copy("copy.png"),
		Paste("paste.png"),
		Delete("delete.png"),
		SelectAll("selectAll.png"),
		Group("group.png"),
		UnGroup("unGroup.png");

		private String iconName;

		EEditMenus (String iconName) {
			this.iconName = iconName;
		}

		public String getIconName() {
			return PATH + '/' + this.iconName;
		}
	}

	public enum EColorMenus {
		LineColor("line.png"),
		DefaultLineColor(null),
		FillColor("fill.png"),
		DefaultFillColor(null),
		Texture("texture.png"),
		Gradient("gradient.png");

		private String iconName;

		EColorMenus (String iconName) {
			this.iconName = iconName;
		}

		public String getIconName() {
			return PATH + '/' + this.iconName;
		}
	}

	public enum EInsertMenus {
		Image(null),
		Audio(null),
		Video(null),
		TestBox(null);

		private String iconName;

		EInsertMenus (String iconName) {
			this.iconName = iconName;
		}

		public String getIconName() {
			return PATH + '/' + this.iconName;
		}
	}
}
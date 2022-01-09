package domain;

public abstract class Rectangle {

	protected int positionX;
	protected int positionY;

	protected int width;
	protected int height;

	public Rectangle(int positionX, int positionY, int width, int heigth) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		this.height = heigth;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeigth(int heigth) {
		this.height = heigth;
	}
	
	private java.awt.Rectangle createAwtRectangle() {
		return new java.awt.Rectangle(positionX, positionY, width, height);
	}

	public boolean intersected(Rectangle rec) {
		return createAwtRectangle().intersects(rec.createAwtRectangle());
	}

}

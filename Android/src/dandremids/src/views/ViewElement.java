package dandremids.src.views;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class ViewElement {
	Rect rect;
	Bitmap bitmap;
	
	public ViewElement(Bitmap bitmap, Rect rect) {
		super();
		this.rect = rect;
		this.bitmap = bitmap;
	}

	public Rect getRect() {
		return rect;
	}

	public void setRect(Rect rect) {
		this.rect = rect;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	
}

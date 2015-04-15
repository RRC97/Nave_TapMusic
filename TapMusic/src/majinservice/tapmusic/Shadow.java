package majinservice.tapmusic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Shadow extends Shape
{
	private Shadow myChild;
	public Shadow(float x, float y, float dX, float dY, int t) 
	{
		super(x + 20, y, dX, dY, t);
		// TODO Auto-generated constructor stub
		alpha = 90;
		try {
			myChild = (Shadow)this.clone();
			myChild.alpha -= 10;
			myChild.myChild = (Shadow)myChild.clone();
			myChild.myChild.alpha -= 10;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		if(myChild != null)
		 myChild.draw(canvas);
	}
	
	public void setY(float y)
	{
		this.rect.top = y;
		if(myChild != null)
			myChild.setY(y - 10);
	}
}

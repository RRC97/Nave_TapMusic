package majinservice.tapmusic;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Shadow extends Quadrate
{
	public Shadow(int x, int y, int w, int h, int c, int a)
	{
		super(x,y,w,h,c);
		// TODO Auto-generated constructor stub
	}
	
	/*@Override
	public void Draw(Canvas c)
	{
		Paint paint = new Paint();
		paint.setColor(this.color);
		paint.setAlpha(this.alpha);
		c.drawCircle(this.x, this.y, this.width/2, paint);
	}*/

	@Override
	public void Update()
	{
		y+= 1.5;
		// TODO Auto-generated method stub
	}
}

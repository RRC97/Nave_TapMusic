package majinservice.tapmusic; 

import android.graphics.Canvas;
import android.graphics.Paint;


public class Quadrate extends Element
{
	public Quadrate(int x, int y, int w, int h, int c)
	{
            super(x,y,w,h,c,255);
            index = 0;
	}
	
	@Override
	public void Draw(Canvas c)
	{
            Paint paint = new Paint();
            paint.setColor(this.color);
            c.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, paint);
	}
	
        @Override
	public void Update()
	{
            y+= 5;
	}
}

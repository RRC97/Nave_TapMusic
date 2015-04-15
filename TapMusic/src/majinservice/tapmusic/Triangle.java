package majinservice.tapmusic; 

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;


public class Triangle extends Element
{
	public Triangle(int x, int y, int w, int h, int c)
	{
            super(x,y,w,h,c,255);
            index = 2;
	}
	
	@Override
	public void Draw(Canvas c)
	{
            Paint paint = new Paint();
            paint.setColor(this.color);
            Path lines = new Path();
            lines.moveTo(x, y + height);
            lines.lineTo(x + width, y + height);
            lines.lineTo(x + width/2, y);
            c.drawPath(lines, paint);
        }
	
        @Override
	public void Update()
	{
            y+= 5;
	}
}

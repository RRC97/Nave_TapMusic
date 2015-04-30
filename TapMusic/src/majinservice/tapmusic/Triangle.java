package majinservice.tapmusic; 

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;


public class Triangle extends Element
{
    private Shadow[] shadows;
    public Triangle(int x, int y, int w, int h, int c)
    {
        super(x,y,w,h,c,255);
        index = 2;
        shadows = new Shadow[10];

        for(int i = 0; i < shadows.length; i++)
        {
            shadows[i] = new Shadow(x, y, w, h, c, 50 - (i * 5), index);
        }
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
        for(Shadow shadow : shadows)
            shadow.Draw(c);
    }

    @Override
    public void Update()
    {
        y+= 5;
        for(int i = 0; i < shadows.length; i++)
        {
            shadows[i].setY(y - (i + 1) * height/5);
        }
    }
}

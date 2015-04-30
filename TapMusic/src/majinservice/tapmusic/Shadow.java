package majinservice.tapmusic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

public class Shadow extends Element
{
    int type;
    public Shadow(int x, int y, int w, int h, int c, int t, int i) 
    {
        super(x, y, w, h, c, t);
        type = i;
    }

    @Override
    public void Draw(Canvas c)
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        paint.setAlpha(alpha);
        
        switch(type)
        {
            case 0:
                c.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, paint);
                break;
            case 1:
                c.drawOval(new RectF(this.x, this.y, this.x + this.width, this.y + this.height), paint);
                break;
            case 2:
                Path lines = new Path();
                lines.moveTo(x, y + height);
                lines.lineTo(x + width, y + height);
                lines.lineTo(x + width/2, y);
                c.drawPath(lines, paint);
                break;
        }
    }

    @Override
    public void Update()
    {
    
    }
}

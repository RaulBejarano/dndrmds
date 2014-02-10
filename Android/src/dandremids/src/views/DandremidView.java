package dandremids.src.views;

import dandremids.src.DandremidActivity;
import dandremids.src.R;
import dandremids.src.model.Dandremid;
import dandremids.src.threads.CreatureViewLoopThread;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class DandremidView extends SurfaceView {
	
	private Bitmap bmp;
	private CreatureViewLoopThread creatureViewLoopThread;
	private SurfaceHolder holder;
	private Dandremid creature;
	
	private Display display;
	
	public DandremidView(Context context, Display display) {
		  super(context);
		  this.creature= ((DandremidActivity) this.getContext()).getCreature();
		  this.display=display;
		  
          creatureViewLoopThread = new CreatureViewLoopThread(this);
          holder = getHolder();
          holder.addCallback(new SurfaceHolder.Callback() {

                 @Override
                 public void surfaceDestroyed(SurfaceHolder holder) {
                        boolean retry = true;
                        creatureViewLoopThread.setRunning(false);
                        while (retry) {
                               try {
                                     creatureViewLoopThread.join();
                                     retry = false;
                               } catch (InterruptedException e) {
                               }
                        }
                 }

                 @Override
                 public void surfaceCreated(SurfaceHolder holder) {
                        creatureViewLoopThread.setRunning(true);
                        creatureViewLoopThread.start();
                 }

                 @Override
                 public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                 }
          });
    }

    @Override
	public void onDraw(Canvas canvas) {
    	if(canvas!=null){
			
    		
			Point dp = new Point();
			display.getSize(dp);
			
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.dandremid_view_background);						
			Rect src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
			
			int x1 = 0;					
			int y1 = 0;
			
			int x2 = dp.x;
			int y2 = dp.y;
			
			Rect backgroundRect = new Rect(x1, y1, x2, y2);
	        canvas.drawBitmap(bmp, src, backgroundRect, null);
			
			
			// Bot Panel			
			bmp = BitmapFactory.decodeResource(getResources(), R.drawable.dandremid_view_bot_panel);						
			src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
			
			x2 = dp.x;					
			y2 = dp.y;
			
			x1 = 0;
			double proportion = ((double)(x2-x1))/bmp.getWidth();
			y1 = (int) (dp.y-proportion*bmp.getHeight());
			
			Rect botPanel = new Rect(x1, y1, x2, y2);
	        canvas.drawBitmap(bmp, src, botPanel, null);
	        
	        // << Bot panel bars >>
	        
	        double barValue, barMaxValue;
	        
	        // Food Bar
	        Paint p = new Paint();
	        p.setColor(Color.GREEN);
	        
	        barMaxValue=100;
	        barValue=0;	        		
	        
	        proportion = barValue/barMaxValue;
	        
	        x1=(int) (dp.x*1.3/10.0);
	        y1=(int) (botPanel.top + dp.y*1/30);
	        x2=(int) (x1 + dp.x*7.7/10.0 * proportion);
	        y2=(int) (y1 + dp.y*1/40.0);	        
	          
	        Rect foodBar = new Rect(x1,y1,x2,y2);
	        canvas.drawRect(foodBar, p);
	        
	        // Food Bar Text
	        
	        p.setColor(Color.BLACK);
	        p.setTextSize(dp.x*1/30);	       
	        String barText = (int)barValue + "/"+(int)barMaxValue;
	        Rect foodBarTextRect = new Rect ();	        
	        p.getTextBounds(barText, 0, barText.length(), foodBarTextRect);	
	        
	        x1 = (int) (foodBar.left + (dp.x*7.7/10.0) - foodBarTextRect.width());
	        y1 = (int) (foodBar.top + foodBar.height());	        
	        
	        canvas.drawText(barText,x1,y1, p);
	        
	        
	        // Happiness Bar
	        
	        p.setColor(Color.GREEN);
	        
	        barMaxValue=100;
	        barValue=50;	        		
	        
	        proportion = barValue/barMaxValue;
	        
	        x1=(int) (dp.x*1.3/10.0);
	        y1=(int) (foodBar.top + foodBar.height() + dp.y*1/60.0);
	        x2=(int) (x1 + dp.x*7.7/10.0 * proportion);
	        y2=(int) (y1 + dp.y*1/40.0);	        
	        
	        Rect happinessBar = new Rect(x1,y1,x2,y2);
	        canvas.drawRect(happinessBar, p);
	        
	        
	        // Happiness Bar Text
	        
	        p.setColor(Color.BLACK);
	        p.setTextSize(dp.x*1/30);	       
	        barText = (int)barValue + "/"+(int)barMaxValue;
	        Rect happinessBarTextRect = new Rect ();	        
	        p.getTextBounds(barText, 0, barText.length(), happinessBarTextRect);	
	        
	        x1 = (int) (happinessBar.left + (dp.x*7.7/10.0) - happinessBarTextRect.width());
	        y1 = (int) (happinessBar.top + happinessBar.height());	        
	        
	        canvas.drawText(barText,x1,y1, p);
	        
	        
	        // Life Bar	        
	        p.setColor(Color.GREEN);
	        
	        barMaxValue=100;
	        barValue=97;	        		
	        
	        proportion = barValue/barMaxValue;
	        
	        x1=(int) (dp.x*1.3/10.0);
	        y1=(int) (happinessBar.top + happinessBar.height() + dp.y*1/60.0);
	        x2=(int) (x1 + dp.x*7.7/10.0 * proportion);
	        y2=(int) (y1 + dp.y*1/40.0);	        
	        
	        Rect lifeBar = new Rect(x1,y1,x2,y2);
	        canvas.drawRect(lifeBar, p);
	        
	        
	        // Life Bar Text	        
	        p.setColor(Color.BLACK);
	        p.setTextSize(dp.x*1/30);	       
	        barText = (int)barValue + "/"+(int)barMaxValue;
	        Rect lifeBarTextRect = new Rect ();	        
	        p.getTextBounds(barText, 0, barText.length(), lifeBarTextRect);	
	        
	        x1 = (int) (lifeBar.left + (dp.x*7.7/10.0) - lifeBarTextRect.width());
	        y1 = (int) (lifeBar.top + lifeBar.height());	        
	        
	        canvas.drawText(barText,x1,y1, p);
	        
	        
	        
	        // Right Panel
	        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.dandremid_view_right_panel);						
			src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
			
			x2 = dp.x;
			y2 = (int)((dp.y - botPanel.height())*9d/10);
			
			
			y1 = (int)((dp.y - botPanel.height())*1d/10);		
			proportion = ((double)(y2-y1))/bmp.getHeight();
			x1= (int)(dp.x - bmp.getWidth()*proportion);
			
			Rect rightPanel = new Rect(x1, y1, x2, y2);
	        canvas.drawBitmap(bmp, src, rightPanel, null);
	        
	        // Creature
	        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.dndrmd_lasentu);						
	        src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
						
			x1 = (int) ((double)dp.x - rightPanel.width()) * 1/10;
			x2 = (int) ((double)dp.x - rightPanel.width()) * 9/10;
			
			y2 =  (int) ((double)dp.y - botPanel.height()) * 9/10;
			proportion = ((double)(x2-x1))/bmp.getWidth();
			y1 = (int) (dp.y - botPanel.height() - bmp.getHeight() * proportion);
									
			Rect creatureRect = new Rect(x1, y1, x2, y2);
	        canvas.drawBitmap(bmp, src, creatureRect, null);
	        
	        
	        // Level Background
	        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.background_level);
	        src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
	        
	        x1 = (int) ((double)dp.x*1/50);
	        y1 = x1;
	        
	        x2 = (int) (x1+ (double)dp.x * 1/5);
	        proportion = ((double)(x2-x1))/bmp.getWidth();
	        y2 = (int) (y1 + bmp.getHeight()*proportion);
	        
	        Rect levelBackground = new Rect(x1, y1, x2, y2);
	        canvas.drawBitmap(bmp, src, levelBackground, null);
	        
	        // Level Foreground
	        
	        p = new Paint();
	        p.setColor(Color.BLACK);
	        p.setTextSize(dp.x*1/10);
	       
	        
	        String levelText = "12";
	        Rect levelRect = new Rect ();
	        
	        p.getTextBounds(levelText, 0, levelText.length(), levelRect);	
	        
	        x1 = (int) (levelBackground.left +  (((double)levelBackground.width())/2) - ((double)levelRect.width())/2 );
	        y1 = (int) (levelBackground.top + levelRect.height()+ (((double)levelBackground.height())/2) - ((double)levelRect.height())/2);	        
	        
	        canvas.drawText(levelText,x1,y1, p);
	        
	        // Type 1
	        bmp=BitmapFactory.decodeResource(getResources(), R.drawable.type_thunder);
	        src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
	        
	        x1 = levelBackground.left + levelBackground.width() + (int)((double)dp.x * 1/50);
	        y1 = levelBackground.top;
	        
	        x2 = x1 + (int)((double)dp.x * 1/10);
	        proportion = ((double)(x2-x1))/bmp.getWidth();
	        y2 = (int) (y1 + bmp.getHeight()*proportion);
	        
	        
	       	Rect type1Rect = new Rect(x1, y1, x2, y2);
	        canvas.drawBitmap(bmp, src, type1Rect, null);
	        
	        
	        // Type 2
	        
	        bmp=BitmapFactory.decodeResource(getResources(), R.drawable.type_rare);
	        src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
	        
	        x1 = type1Rect.left;
	        y1 = type1Rect.top + type1Rect.height();
	        
	        x2 = x1 + type1Rect.width();
	        proportion = ((double)(x2-x1))/bmp.getWidth();
	        y2 = (int) (y1 + bmp.getHeight()*proportion);
	                
	       	Rect type2Rect = new Rect(x1, y1, x2, y2);
	        canvas.drawBitmap(bmp, src, type2Rect, null);
	        
	        
	        // << Right Buttons >>
	        
	        //Button 1 
	        bmp=BitmapFactory.decodeResource(getResources(), R.drawable.icon_food_burguer);
	        src = new Rect(0 , 0, bmp.getWidth(), bmp.getHeight());
	        
	        y1= (int) (rightPanel.top + dp.y/24.3);
	        
	        y2=rightPanel.top + (int)(rightPanel.height()/3.8);
	        x2=dp.x - dp.x*1/20;
	       
	        proportion = ((double)(y2-y1))/bmp.getHeight();
	        x1= (int) (x2 - bmp.getWidth()*proportion);
	        
	       	Rect button1Rect = new Rect(x1, y1, x2, y2);
	        canvas.drawBitmap(bmp, src, button1Rect, null);
	        
	        
	        
	        //Button 2
	        bmp=BitmapFactory.decodeResource(getResources(), R.drawable.icon_food_chocolate);
	        src = new Rect(0 , 0, bmp.getWidth(), bmp.getHeight());
	        
	        	        
	        y1=(int) (button1Rect.top + button1Rect.height() + dp.y * 1/46.0);
	        
	        y2=y1+button1Rect.width();	        
	        x2=button1Rect.right;
	        
	        proportion = ((double)(y2-y1))/bmp.getHeight();
	        x1= (int) (x2 - bmp.getWidth()*proportion);
	        
	       	Rect button2Rect = new Rect(x1, y1, x2, y2);
	        canvas.drawBitmap(bmp, src, button2Rect, null);
	        
	        
	        
	        // Button 3	       
	        bmp=BitmapFactory.decodeResource(getResources(), R.drawable.icon_food_coke);
	        src = new Rect(0 , 0, bmp.getWidth(), bmp.getHeight());
	        
	        	        
	        y1=(int) (button2Rect.top + button2Rect.height() + dp.y * 1/46.0);
	        
	        y2=y1+button2Rect.width();	        
	        x2=button2Rect.right;
	        
	        proportion = ((double)(y2-y1))/bmp.getHeight();
	        x1= (int) (x2 - bmp.getWidth()*proportion);
	        
	       	Rect button3Rect = new Rect(x1, y1, x2, y2);
	        canvas.drawBitmap(bmp, src, button3Rect, null);
	        
	        
	        // Button 4
	        bmp=BitmapFactory.decodeResource(getResources(), R.drawable.icon_food_donut);
	        src = new Rect(0 , 0, bmp.getWidth(), bmp.getHeight());
	        
	        	        
	        y1=(int) (button3Rect.top + button3Rect.height() + dp.y * 1/46.0);
	        
	        y2=y1+button3Rect.width();	        
	        x2=button3Rect.right;
	        
	        proportion = ((double)(y2-y1))/bmp.getHeight();
	        x1= (int) (x2 - bmp.getWidth()*proportion);
	        
	       	Rect button4Rect = new Rect(x1, y1, x2, y2);
	        canvas.drawBitmap(bmp, src, button4Rect, null);
    	}
         
         
    }

}

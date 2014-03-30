package dandremids.src.views;

import dandremids.src.R;
import dandremids.src.model.Dandremid;
import dandremids.src.threads.CombatViewLoopThread;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


@SuppressLint("DrawAllocation")
public class CombatView extends SurfaceView {
 
	private Dandremid dLocal;
	private Dandremid dRival;
	
	private Display display;
	private SurfaceHolder holder;
	private CombatViewLoopThread combatViewLoopThread;

	public boolean dialogLaunched;
	
	
	public CombatView(Context context, Display display, Dandremid dLocal, Dandremid dRival) {
		super(context);
		this.display=display;
		this.dLocal=dLocal;
		this.dRival=dRival;
		this.dialogLaunched=false;
		
		combatViewLoopThread = new CombatViewLoopThread(this);		
		holder = this.getHolder();		
		holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                   boolean retry = true;
                   combatViewLoopThread.setRunning(false);
                   while (retry) {
                          try {
                        	  combatViewLoopThread.join();
                                retry = false;
                          } catch (InterruptedException e) {
                          }
                   }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
            	combatViewLoopThread.setRunning(true);
            	if(!combatViewLoopThread.isAlive()){
            		combatViewLoopThread.start();
            	}            	
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
		});
			
		
	}
			
	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas) {
		if(canvas!=null){
						
			int x1, y1, x2, y2, level;
			double proportion;	
			double barValue, barMaxValue;	        
	        Paint p = new Paint();

			String text;
			Point dp = new Point();
			display.getSize(dp);
			
			canvas.drawColor(Color.WHITE);
			Drawable background = getResources().getDrawable(R.drawable.background_par);
			background.setBounds(0, 0, dp.x, dp.y);
			background.draw(canvas);
			
			
			// Combat Top Panel			
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.combat_top_panel);						
			Rect src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
			
			x1 = (int) (dp.x /2.0);
			y1 = (int) (dp.y /20.0);
			
			x2 = dp.x;			
			proportion = ((double)(x2-x1))/bmp.getWidth();
			y2 = (int) (y1 + proportion*bmp.getHeight());		
			
			Rect combatTopPanel = new Rect(x1, y1, x2, y2);
	        canvas.drawBitmap(bmp, src, combatTopPanel, null);
	        
	        
	        //Top life Bar	        
	        p.setColor(Color.GREEN);
	        
	        barMaxValue=dRival.getMaxLife();
	        barValue=dRival.getLife();	        		
	        
	        proportion = barValue/barMaxValue;
	        
	        x1=(int) (combatTopPanel.left+dp.x*3.7/30.0);
	        y1=(int) (combatTopPanel.top + combatTopPanel.height()*1/45.0);
	        x2=(int) (x1 + dp.x*10.8/30.0 * proportion);
	        y2=(int) (y1 + dp.y*1.8/30.0);	        
	          
	        Rect topLifeBar = new Rect(x1,y1,x2,y2);
	        canvas.drawRect(topLifeBar, p);
	        
	        
	        // Top Life Bar Text	        	        
	        p.setColor(Color.BLACK);
	        p.setTextSize((int)(dp.x*1/40.0));	  	       
	        text = (int)barValue + "/"+(int)barMaxValue;
	        Rect topLifeBarTextRect = new Rect ();	        
	        p.getTextBounds(text, 0, text.length(), topLifeBarTextRect);	
	        
	        x1 = (int) (topLifeBar.left +(combatTopPanel.width()*71/100.0) - topLifeBarTextRect.width());
	        y1 = (int) (topLifeBar.top + topLifeBar.height() - topLifeBarTextRect.height()/2);	        
	        
	        canvas.drawText(text,x1,y1, p);
	        
	        // Top Level Text	   
	        level=dRival.getLevel();
	        
	        p.setColor(Color.BLACK);
	        p.setTextSize((int)(dp.x*1/20.0));	       
	        text = level+"";
	        
	        x1 = (int) (combatTopPanel.right - combatTopPanel.width()* 16/100.0);
	        y1 = (int) (combatTopPanel.bottom - combatTopPanel.height()*  21/100.0);	        
	        
	        canvas.drawText(text,x1,y1, p);	
	        
	        // Top Creature Name
	        text = dRival.getName();
	        
	        p.setColor(Color.BLACK);
	        p.setTextSize((int)(dp.x*1/30.0));		        
	        Rect topCreatureNameRect = new Rect ();	        
	        p.getTextBounds(text, 0, text.length(), topCreatureNameRect);	
	        
	        x1 = (int) (combatTopPanel.right - combatTopPanel.width() * 25/100.0 - topCreatureNameRect.width());
	        y1 = (int) (combatTopPanel.bottom - combatTopPanel.height() * 15/100.0);	        
	        
	        canvas.drawText(text,x1,y1, p);	
	        
	        
	        //Combat Bot Panel
	        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.combat_bot_panel);
	        src = new Rect (0,0,bmp.getWidth(),bmp.getHeight());
	        
	        x2= (int) (dp.x/2.0);
	        y2= (int) (dp.y - dp.y/20.0);
	        
	        x1 = 0;
	        proportion = ((double)(x2-x1))/bmp.getWidth();
	        y1 = (int) (y2 - bmp.getHeight() * proportion);
	        
	        Rect combatBotPanel = new Rect(x1, y1, x2, y2);
	        canvas.drawBitmap(bmp, src, combatBotPanel, null);
	        
	        //Bot life Bar
	        p.setColor(Color.GREEN);
	        
	        barMaxValue=dLocal.getMaxLife();
	        barValue=dLocal.getLife();	        		
	        
	        proportion = barValue/barMaxValue;
	        
	        x1=(int) (combatBotPanel.left+dp.x*2.1/30.0);
	        y1=(int) (combatBotPanel.bottom - combatBotPanel.height()*2.7/10.0);
	        x2=(int) (x1 + dp.x*10.8/30.0 * proportion);
	        y2=(int) (y1 + dp.y*1.8/30.0);	        
	          
	        Rect botLifeBar = new Rect(x1,y1,x2,y2);
	        canvas.drawRect(botLifeBar, p);
	        
	        // Bot Life Bar Text	        	        
	        p.setColor(Color.BLACK);
	        p.setTextSize((int)(dp.x*1/40.0));	       
	        text = (int)barValue + "/"+(int)barMaxValue;
	        Rect botLifeBarTextRect = new Rect ();	        
	        p.getTextBounds(text, 0, text.length(), botLifeBarTextRect);	
	        
	        x1 = (int) (botLifeBar.left +(combatBotPanel.width()*71/100.0) - botLifeBarTextRect.width());
	        y1 = (int) (botLifeBar.top + botLifeBar.height() - botLifeBarTextRect.height()/2);	        
	        
	        canvas.drawText(text,x1,y1, p);
	        
	        // Bot Level Text	   
	        level=dLocal.getLevel();
	        
	        p.setColor(Color.BLACK);
	        p.setTextSize((int)(dp.x*1/20.0));	       
	        text = level+"";
	        Rect botLevelTextRect = new Rect ();	        
	        p.getTextBounds(text, 0, text.length(), botLevelTextRect);	
	        
	        x1 = (int) (combatBotPanel.left + combatBotPanel.width()* 5/100.0);
	        y1 = (int) (combatBotPanel.top + combatBotPanel.height()*  48/100.0);	        
	        
	        canvas.drawText(text,x1,y1, p);	        
	        
	        
	        // Bot Creature Name
	        text = dLocal.getName();
	        
	        p.setColor(Color.BLACK);
	        p.setTextSize((int)(dp.x*1/30.0));		        
	        Rect botCreatureNameRect = new Rect();	        
	        p.getTextBounds(text, 0, text.length(), botCreatureNameRect);	
	        
	        x1 = (int) (combatBotPanel.left + combatBotPanel.width()* 25/100.0);
	        y1 = (int) (combatBotPanel.top + combatBotPanel.height() * 30/100.0);	        
	        
	        canvas.drawText(text,x1,y1, p);	
	        
	        
	        // Top Creature (Rival)
	        bmp = dRival.getDandremidBase().getImage();
	        
	        x1=(int) (dp.x* 1/8);
	        y1=0;
	        
	        x2 = (int) (dp.x* 3/8);
	        proportion = ((double)(x2-x1))/bmp.getWidth();
	        y2 = (int) (bmp.getHeight()*proportion);	        
	        
	        Rect topCreature = new Rect(x1, y1, x2, y2);
	        
	        // Top Creature Background
	        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.background_combat_ground);
	        src = new Rect (0,0,bmp.getWidth(),bmp.getHeight());
	        x1 = (int) (topCreature.left - topCreature.left/1.5);
	        x2 = (int) (topCreature.right + topCreature.left/1.5);
	        proportion = ((double)(x2-x1))/bmp.getWidth();	       
	        y1 = (int) (topCreature.bottom - bmp.getHeight()*proportion/1.75);
	        y2 = (int) (y1 + bmp.getHeight()*proportion);
	        	        
	        Rect topCreatureBackground = new Rect(x1, y1, x2, y2);
	        canvas.drawBitmap(bmp, src, topCreatureBackground, null);
	        
	        bmp = dRival.getDandremidBase().getImage();
	        src = new Rect (0,0,bmp.getWidth(),bmp.getHeight()); //Esto tiene que ajustarse a los parametros de ROW y FRAME correspondientes a la animación
	        canvas.drawBitmap(bmp, src, topCreature, null);
	        	        
	        // Bot Creature (Local)
	        bmp = dLocal.getDandremidBase().getImage();
	         
	        x2= (int) (dp.x * 7/8);
	        y2=dp.y;
	        
	        x1=(int)(dp.x * 5/8);
	        proportion = ((double)(x2-x1))/bmp.getWidth();
	        y1 = (int) (dp.y-bmp.getHeight()*proportion);
	       
	        Rect botCreature = new Rect(x1, y1, x2, y2);
	        
	        // Bot Creature Background
	        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.background_combat_ground);
	        src = new Rect (0,0,bmp.getWidth(),bmp.getHeight());
	        x1 = (int) (botCreature.left - botCreature.left/4);
	        x2 = (int) (botCreature.right + botCreature.left/4);
	        proportion = ((double)(x2-x1))/bmp.getWidth();	       
	        y1 = (int) (botCreature.bottom - bmp.getHeight()*proportion/1.75);
	        y2 = (int) (y1 + bmp.getHeight()*proportion);
	        
	        Rect botCreatureBackground = new Rect(x1, y1, x2, y2);
	        canvas.drawBitmap(bmp, src, botCreatureBackground, null);
	        
	        bmp = dLocal.getDandremidBase().getImage();
	        src = new Rect (0,0,bmp.getWidth(),bmp.getHeight()); //Esto tiene que ajustarse a los parametros de ROW y FRAME correspondientes a la animación
	        canvas.drawBitmap(bmp, src, botCreature, null);
	        	        
		}
         
    }

	public void changeLocalDandremid(Dandremid d) {
		dLocal=d;		
	}

}

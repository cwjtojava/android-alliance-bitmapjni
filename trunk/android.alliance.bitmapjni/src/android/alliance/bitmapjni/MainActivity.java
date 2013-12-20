package android.alliance.bitmapjni;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import com.jni.bitmap_operations.JniBitmapHolder;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView ivImage;
	private Button btRotateRight;
	private Button btRotateLeft;
	private JniBitmapHolder bitmapHolder = new JniBitmapHolder();
	private Bitmap bitmap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Kopiere ein Bild auf die SD-Karte
		
		bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/bitmap_jni_test.png");
		
		
		btRotateLeft = (Button) findViewById(R.id.btRotateLeft);
		btRotateLeft.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
			   bitmapHolder.storeBitmap(bitmap);
			   bitmapHolder.rotateBitmapCcw90();
			   ivImage.setImageBitmap(bitmapHolder.getBitmapAndFree());
			}
		});
		
		btRotateRight = (Button) findViewById(R.id.btRotateRight);
		btRotateRight.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				bitmapHolder.storeBitmap(bitmap);
			    bitmapHolder.rotateBitmapCw90();
			    ivImage.setImageBitmap(bitmapHolder.getBitmapAndFree());
				
			}
			
		});

		
		ivImage = (ImageView) findViewById(R.id.imageView);
		ivImage.setImageBitmap(bitmap);
	}

	
	/*
	 *   //
    // original
    //
    final ImageView imageViewOriginal=(ImageView)findViewById(R.id.imageViewOriginal);
    final Bitmap b=BitmapFactory.decodeResource(getResources(),IMAGE_RESID_TO_TEST);
    imageViewOriginal.setImageBitmap(b);
    //
    // rotated 90 degress CCW
    //
    final ImageView imageViewRotated90degressCcw=(ImageView)findViewById(R.id.imageViewRotated90degressCcw);
    bitmapHolder.storeBitmap(b);
    bitmapHolder.rotateBitmapCcw90();
    imageViewRotated90degressCcw.setImageBitmap(bitmapHolder.getBitmapAndFree());
    //
    // rotated 90 degress CW
    //
    final ImageView imageViewRotated90degressCw=(ImageView)findViewById(R.id.imageViewRotated90degressCw);
    bitmapHolder.storeBitmap(b);
    bitmapHolder.rotateBitmapCw90();
    imageViewRotated90degressCw.setImageBitmap(bitmapHolder.getBitmapAndFree());
    //
    // cropped
    //
    final ImageView imageViewCropped=(ImageView)findViewById(R.id.imageViewCropped);
    bitmapHolder.storeBitmap(b);
    bitmapHolder.cropBitmap(b.getWidth()/4,b.getHeight()/4,b.getWidth()*3/4,b.getHeight()*3/4);
    imageViewCropped.setImageBitmap(bitmapHolder.getBitmapAndFree());
    //
    // scaled
    //
    final ImageView imageViewScaled=(ImageView)findViewById(R.id.imageViewScaled);
    // final Bitmap b2=resize(b,b.getWidth()/2,b.getHeight()*2);
    bitmapHolder.storeBitmap(b);
    bitmapHolder.scaleBitmap(b.getWidth()*2,b.getHeight());
    imageViewScaled.setImageBitmap(bitmapHolder.getBitmapAndFree());
    // imageViewScaled.setImageBitmap(b2);
	 */
}

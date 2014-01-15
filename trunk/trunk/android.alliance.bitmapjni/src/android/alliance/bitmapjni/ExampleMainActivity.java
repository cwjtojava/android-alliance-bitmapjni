package android.alliance.bitmapjni;

/**
 * Copy the jni_test.bmp from the assets folder to the sdcard of your device.
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

public class ExampleMainActivity extends Activity {

	private ImageView ivImage;
	private Button btRotateRight;
	private Button btRotateLeft;
	private JniBitmapHolder bitmapHolder = new JniBitmapHolder();
	private Bitmap bitmap;
	private File bitmapFile;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		bitmapFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jni_test.png");
		
		bitmap = BitmapFactory.decodeFile(bitmapFile.getAbsolutePath());
		
		
		btRotateLeft = (Button) findViewById(R.id.btRotateLeft);
		btRotateLeft.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
			   bitmapHolder.storeBitmap(bitmap);
			   bitmapHolder.rotateBitmapCcw90();
			   bitmap = bitmapHolder.getBitmapAndFree();
			   ivImage.setImageBitmap(bitmap);
			   saveToFile();
			}
		});
		
		btRotateRight = (Button) findViewById(R.id.btRotateRight);
		btRotateRight.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				bitmapHolder.storeBitmap(bitmap);
			    bitmapHolder.rotateBitmapCw90();
			    bitmap = bitmapHolder.getBitmapAndFree();
			    ivImage.setImageBitmap(bitmap);
			   	saveToFile();
			}
		});

		
		ivImage = (ImageView) findViewById(R.id.imageView);
		ivImage.setImageBitmap(bitmap);
	}

	private void saveToFile(){
	    try {
	    	FileOutputStream fOut = new FileOutputStream(bitmapFile);
	    	bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

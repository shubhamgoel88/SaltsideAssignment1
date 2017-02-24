package in.sgroid.com.saltsideassignment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {
TextView details,title;
    ImageView img;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        details = (TextView)findViewById(R.id.descrip);
        title=(TextView)findViewById(R.id.detail_title);
        img=(ImageView)findViewById(R.id.img);
        Intent intent = getIntent();
        details.setText(intent.getStringExtra("des"));
        title.setText(intent.getStringExtra("title"));
        path=intent.getStringExtra("image");
            new DownloadImage().execute();
    }

    private class DownloadImage extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp=null;
            try {
                URL url = new URL(path);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            img.setImageBitmap(bitmap);
        }
    }
}

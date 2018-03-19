package leon.android.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import ext.android.imageloader.ImageLoaderManager;
import ext.android.imageloader.ImageLoaderOptions;
import ext.android.imageloader.annotations.ResourceType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.iv);
        final ImageLoaderOptions options = new ImageLoaderOptions.Builder()
                .as(ResourceType.BITMAP)
                .progressListener((url, bytesRead, contentLength, done) -> Log.e("ImageLoader", "url=" + url + ", bytesRead=" + bytesRead + ",contentLength=" + contentLength))
                .build();
        ImageLoaderManager.get().showImage(imageView, "https://www.baidu.com/img/bd_logo1.png", options);

        ImageView imageView1 = findViewById(R.id.iv_1);
        ImageLoaderManager.get().showImage(imageView1, "http://img1.imgtn.bdimg.com/it/u=594559231,2167829292&fm=27&gp=0.jpg", options);
    }
}

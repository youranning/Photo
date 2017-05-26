package fuzhiyan.bwei.com.photo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.nostra13.universalimageloader.core.ImageLoader;

import uk.co.senab.photoview.PhotoView;

public class MainActivity extends AppCompatActivity {
    private PhotoView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photo= (PhotoView) findViewById(R.id.photo);
        ImageLoader.getInstance().displayImage("http://d.5857.com/xgmn_150416/desk_007.jpg",photo);
        photo.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {






                try {
                    float scale = photo.getScale();//获取当前缩放值
                    float x = e.getX();
                    float y = e.getY();
                    //PhotoView预定义了3种缩放大小，大，中，小，可以实际使用感受下，双击后先缩放到中，再双击缩放到大，再双击缩放到小。
                    if (scale < photo.getMediumScale()) {//当前缩放为小，变换为中
                        photo.setScale(photo.getMediumScale(), x, y, true);
                    } else if (scale >= photo.getMediumScale() && scale < photo.getMaximumScale()) {//当前缩放为中，变化为大
                        photo.setScale(photo.getMaximumScale(), x, y, true);
                    } else {//当前缩放为大，变换为小
                        photo.setScale(photo.getMinimumScale(), x, y, true);
                    }
                } catch (ArrayIndexOutOfBoundsException exx) {
                    // Can sometimes happen when getX() and getY() is called
                }

                return false;



            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        });

    }
}

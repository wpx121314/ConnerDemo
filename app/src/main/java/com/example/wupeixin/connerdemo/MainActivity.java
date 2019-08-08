package com.example.wupeixin.connerdemo;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        SelectRadiusLoaderImageView simpleDraweeView = findViewById(R.id.my_image_view);
        SimpleDraweeView simpleDraweeView_1 = findViewById(R.id.my_image_view_1);
        Uri uri = Uri.parse("http://s1.dwstatic.com/group1/M00/46/2D/3c1cd1f7946ee2a4654d32a3cf6906f9.gif");

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        simpleDraweeView.setController(controller);

        DraweeController controller1 = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        simpleDraweeView_1.setController(controller1);

        simpleDraweeView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "11211", Toast.LENGTH_SHORT).show();
            }
        });
        StateAttachLinearLayout state_attach_layout = findViewById(R.id.state_attach_layout);
        setForGround(simpleDraweeView, state_attach_layout, false);

    }

    public void setForGround(SelectRadiusLoaderImageView view, StateAttachLinearLayout stateAttachLinearLayout, boolean isForceOldType) {
        if (Build.VERSION.SDK_INT >= 21 && !isForceOldType) {
            RoundRectDrawable background = new RoundRectDrawable(null, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
            view.setBackground(background);
            view.setClipToOutline(true);
        } else {
            CoverConnerDrawable coverConnerDrawable = new CoverConnerDrawable(new CoverConner(R.color.cover_conner_color, 10, this));
            stateAttachLinearLayout.addDrawable(coverConnerDrawable);
            view.setForeground(coverConnerDrawable);
        }
    }
}

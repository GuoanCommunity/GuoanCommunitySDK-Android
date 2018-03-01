package guoan.app2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.guoan.community.sdk.CommunityFactory;

/**
 * Created by andylove on 2018/3/1.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView enter = (TextView) findViewById(R.id.cl_enter_sdk);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommunityFactory.Companion.getInstance().onIntoCommunityHome(MainActivity.this);
            }
        });

    }

}

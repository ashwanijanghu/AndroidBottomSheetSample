package com.jango.bottomsheetsample;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bottom sheet view
        LinearLayout bottomSheetLayout = (LinearLayout) findViewById(R.id.bottomSheet);

        //Get @BottomSheetBehavior object from the Bottom Sheet View
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        //You can modify peek height also from code. It takes value in pixels. So convert your dp values to pixels
        Resources r = getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, r.getDisplayMetrics());
        bottomSheetBehavior.setPeekHeight((int) px);

        // You can modify bottom sheet behaviour dynamically also
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_DRAGGING);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        //Don't let your bottom sheet hide at all
        bottomSheetBehavior.setHideable(false);

        //You can put callback when bottom sheet is changed (Dragged Collapsed Expanded)
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                StringBuffer str = new StringBuffer("Bottom sheet is ");
                switch (newState){
                    case BottomSheetBehavior.STATE_DRAGGING:
                        str.append("STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        str.append("STATE_SETTLING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        str.append("STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        str.append("STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        str.append("STATE_HIDDEN");
                        break;
                }
                Toast.makeText(MainActivity.this, str.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        //Handling widgets present in bottom sheet
        Button bottomSheetButton = (Button) bottomSheetLayout.findViewById(R.id.btmSheetBtn);
        bottomSheetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Clicked from Bottom Sheet", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

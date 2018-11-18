package jp.gr.java_conf.cookie91.sentaku_shibou;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    DialogFragment dialogFragment;
    android.support.v4.app.FragmentManager flagmentManager;

    Button button;
    Spinner spinner;

    subjectsDB db = new subjectsDB();
    String Math;
    String Mon56;
    String Tue56;
    String Wed45;
    String Wed56;
    String Thr12;
    String yome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        spinner = (Spinner) findViewById(R.id.spinner);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoukan(v);
            }
        });

    }

    private void shoukan(View v) {

        yome = (String) spinner.getSelectedItem();
        int seed = spinner.getSelectedItemPosition();

        Random r = new Random(seed);
        
        Math = db.Math[r.nextInt(db.Math.length)];

        if (Math.equals("数学β")) {
            Mon56 = "数学";
            Thr12 = "数学";
        } else {
            Mon56 = db.Mon56[r.nextInt(db.Mon56.length)];
            Thr12 = db.Thr12[r.nextInt(db.Thr12.length)];
        }

        while(true) {
            Tue56 = db.Tue56[r.nextInt(db.Tue56.length)];

            if (!(Tue56.equals(Mon56)) && !(Tue56.equals(Thr12))) {
                   break;
            }
        }
        if (Tue56.equals("物理")) {
            int sum = r.nextInt(2);
            if (sum == 0) {
                while(true) {
                    Wed56 = db.Wed56[r.nextInt(db.Wed56.length)];

                    if (!(Wed56.equals(Mon56)) && !(Wed56.equals(Thr12)) && !(Wed56.equals("物理"))) {
                        break;
                    }
                }
                Wed45 = "物理";
            } else {
                while(true) {
                    Wed45 = db.Wed45[r.nextInt(db.Wed45.length)];

                    if (!(Wed45.equals(Mon56)) && !(Wed45.equals(Thr12)) && !(Wed45.equals("物理"))) {
                        break;
                    }
                }
                Wed56 = "物理";
            }
        } else {
            while(true) {
                Wed45 = db.Wed45[r.nextInt(db.Wed45.length)];

                if (!(Wed45.equals(Mon56)) && !(Wed45.equals(Thr12)) && !(Wed45.equals(Tue56))) {
                    break;
                }
            }
            while(true) {
                Wed56 = db.Wed56[r.nextInt(db.Wed56.length)];

                if (!(Wed56.equals(Mon56)) && !(Wed56.equals(Thr12)) && !(Wed56.equals(Tue56))) {
                    break;
                }
            }
        }

        flagmentManager = getSupportFragmentManager();

        dialogFragment = new AlertDialogFragment();
        dialogFragment.show(flagmentManager, "cookie");


    }

    public static class AlertDialogFragment extends DialogFragment {

        private AlertDialog dialog;
        private AlertDialog.Builder alert;

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            alert = new AlertDialog.Builder(getActivity());

            View alertView = getActivity().getLayoutInflater().inflate(R.layout.result_layout, null);

            ImageView yomeView = (ImageView) alertView.findViewById(R.id.yomeView);
            TextView mathView = (TextView) alertView.findViewById(R.id.mathView);
            TextView mon56View = (TextView) alertView.findViewById(R.id.mon56View);
            TextView tue56View = (TextView) alertView.findViewById(R.id.tue56View);
            TextView wed34View = (TextView) alertView.findViewById(R.id.wed34View);
            TextView wed56View = (TextView) alertView.findViewById(R.id.wed56View);
            TextView thr12View = (TextView) alertView.findViewById(R.id.thr12View);

            MainActivity mainActivity = (MainActivity) getActivity();

            switch (mainActivity.yome) {
                case ("瑞鳳"):
                    yomeView.setImageResource(R.drawable.zuiho);
                    break;
                case ("熊野"):
                    yomeView.setImageResource(R.drawable.kumano);
                    break;
                case ("舞風"):
                    yomeView.setImageResource(R.drawable.maikaze);
                    break;
                case ("島風"):
                    yomeView.setImageResource(R.drawable.shimakaze);
                    break;
                case ("神風"):
                    yomeView.setImageResource(R.drawable.kamikaze);
                    break;
                case ("秋月"):
                    yomeView.setImageResource(R.drawable.akizuki);
                    break;
            }

            mathView.setText(mainActivity.Math);
            mon56View.setText(mainActivity.Mon56);
            tue56View.setText(mainActivity.Tue56);
            wed34View.setText(mainActivity.Wed45);
            wed56View.setText(mainActivity.Wed56);
            thr12View.setText(mainActivity.Thr12);

            alert.setView(alertView);

            dialog = alert.create();
            dialog.show();

            return dialog;
        }
    }



    public class subjectsDB {

        String[] Math  = {"数学α", "数学β"};
        String[] Mon56 = {"数学", "倫理", "日本史1", "世界史1b", "地理1", "地学", "数学演習", "古文2", "体育"};
        String[] Tue56 = {"政経", "日本史2", "物理", "情報", "英語2a", "漢文", "小論文"};
        String[] Wed45 = {"倫理", "世界史1a", "地理1", "物理", "生物", "ドイツ語", "フランス語", "中国語", "古文1", "体育", "音楽"};
        String[] Wed56 = {"政経", "世界史1b", "地理2", "物理", "数学演習", "中国語", "小論文", "書道", "音楽", "日本画", "西洋画", "工芸"};
        String[] Thr12 = {"数学", "日本史1", "日本史2", "世界史1a", "社会演習", "化学", "生物", "英語2b", "古文1"};
    }
}

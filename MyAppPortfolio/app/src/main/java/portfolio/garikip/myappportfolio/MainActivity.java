package portfolio.garikip.myappportfolio;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.content.Context;

public class MainActivity extends AppCompatActivity {

    private Toast toast = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerToast(R.id.spotifystreamerbtn);
        registerToast(R.id.scoresappbtn);
        registerToast(R.id.bldbiggerbtn);
        registerToast(R.id.xyzreaderbtn);
        registerToast(R.id.capstonelnchrbtn);
        registerToast(R.id.libraryappbtn);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void registerToast(int idname)
    {
        Button btn = (Button) findViewById(idname);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                raiseToast(v);
            }
        });
    }

    private int[] getPointOfView(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        return location;
    }

    public void raiseToast(View view) {
        Button b = null;
        try {
            b = (Button) (view);
        } catch (Exception e) {
            return;
        }
        String btnText = "This button will launch " + b.getText().toString().toLowerCase();

        if(toast != null)
        {
            toast.cancel();
        }

        toast = Toast.makeText(getApplicationContext(), btnText,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, getPointOfView(b)[1] + 80);

        toast.show();
    }
}

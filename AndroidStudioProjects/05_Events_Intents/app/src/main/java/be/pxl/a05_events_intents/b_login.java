package be.pxl.a05_events_intents;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class b_login extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_login);

        Button btn_register = (Button)findViewById(R.id.login_btn_register);
        btn_register.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        EditText txt_username = (EditText)findViewById(R.id.login_txt_username);
        EditText txt_password = (EditText)findViewById(R.id.login_txt_password);

        String txt_U = txt_username.getText().toString();
        String txt_P = txt_password.getText().toString();

        Context context = getApplicationContext();
        CharSequence text = "De registratie is gelukt!";
        int duration = Toast.LENGTH_SHORT;

        if (txt_U.length() < 5){
            text = "Uw naam moet minstens 5 karakters lang zijn!";
        }

        if (txt_P.length() < 5){
            text = "Uw wachtwoord moet minstens 5 karakters lang zijn!";
        }

        if (!hasOneDigitAndOneLetter(txt_P)){
            text = "Uw wachtwoord moet minstens 1 letter en  1 cijfer bevatten!";
        }

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    public static boolean hasOneDigitAndOneLetter(String pass)
    {
        boolean hasLetter = false;
        boolean hasDigit = false;
        for(int i=0; i<pass.length(); i++)
        {
            if(Character.isLetter((pass.charAt(i))))
            {
                hasLetter = true;
            }
            if(Character.isDigit((pass.charAt(i))))
            {
                hasDigit = true;
            }

        }
        return hasLetter&&hasDigit;
    }
}

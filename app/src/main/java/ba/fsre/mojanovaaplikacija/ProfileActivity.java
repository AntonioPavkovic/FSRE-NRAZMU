package ba.fsre.mojanovaaplikacija;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ba.fsre.mojanovaaplikacija.model.Users;

public class ProfileActivity extends AppCompatActivity {


    FirebaseAuth auth;

    FirebaseDatabase db;

    FirebaseUser loggedUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.auth = FirebaseAuth.getInstance();
        this.db = FirebaseDatabase.getInstance();
        this.loggedUser = this.auth.getCurrentUser();
        DatabaseReference userDatabaseRef = this.db.getReference("users");


        EditText nameTxt = findViewById(R.id.nameTxt);
        EditText  surnameTxt = findViewById(R.id.surnameTxt);
        EditText phoneTxt = findViewById(R.id.phoneTxt);
        EditText addressTxt = findViewById(R.id.addressTxt);
        EditText placeTxt = findViewById(R.id.placeTxt);
        EditText countryTxt = findViewById(R.id.countryTxt);

        Button saveBtn = findViewById(R.id.buttonSubmit);

        userDatabaseRef.child("users").child(this.loggedUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                try {
                    if (task.isSuccessful()) {
                        Users currentUser = task.getResult().getValue(Users.class);
                        nameTxt.setText(currentUser.firstName);
                        surnameTxt.setText(currentUser.lastName);
                        phoneTxt.setText(currentUser.phone);
                        addressTxt.setText(currentUser.address);
                        placeTxt.setText(currentUser.place);
                        countryTxt.setText(currentUser.country);
                    }

                } catch (NullPointerException e) {

                    Log.e("No data", e.getMessage());


                }
            }

        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users currentUser = new Users(
                        nameTxt.getText().toString(),
                        surnameTxt.getText().toString(),
                        phoneTxt.getText().toString(),
                        addressTxt.getText().toString(),
                        placeTxt.getText().toString(),
                        countryTxt.getText().toString()
                );
                userDatabaseRef.child("users").child(loggedUser.getUid()).setValue(currentUser);

                Toast.makeText(getApplicationContext(), "Uspjesno ste poslali podatke", Toast.LENGTH_LONG).show();
            }
        });
    }
}
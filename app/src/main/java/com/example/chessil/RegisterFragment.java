package com.example.chessil;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegisterFragment extends Fragment {
    Button loginbtnreg,registerbtnreg;
    private Context mContext;
    private EditText email,password,confirmation,phonenum,username,feideelo,chesscom;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String userid;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register,container,false);
        mAuth = FirebaseAuth.getInstance();
        Button btn=view.findViewById(R.id.loginbtn2);
        email=view.findViewById(R.id.emailregisteredit);
        password=view.findViewById(R.id.passwordregisteredit);
        confirmation=view.findViewById(R.id.confirmationgisteredit);
        phonenum=view.findViewById(R.id.phoneregisteredit);
        username=view.findViewById(R.id.usernameregisteredit);
        feideelo=view.findViewById(R.id.eloregisteredit);
        chesscom=view.findViewById(R.id.chesscomregisteredit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity)getActivity()).navigateFrag(new LoginFragment(),false);


            }
        });

        registerbtnreg=view.findViewById(R.id.regeisterbtn2);
        registerbtnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> newuser = new HashMap<>();
                newuser.put("username", username.getText().toString().trim());
                newuser.put("phone number", phonenum.getText().toString().trim());
                newuser.put("fide elo", feideelo.getText().toString().trim());
                newuser.put("chesscom", chesscom.getText().toString().trim());
                if(checkEmail(email)&&check_password(password,confirmation)){

                    mAuth.createUserWithEmailAndPassword(email.getText().toString().trim()
                            , password.getText().toString().trim())
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        userid=user.getUid();
                                        Toast.makeText(mContext, "Authentication successful.",
                                                Toast.LENGTH_SHORT).show();
                                        DocumentReference documentReference=db.collection("users").document(userid);

                                        db.collection("users").document(mAuth.getCurrentUser().getUid()).collection("profile")
                                                .add(newuser)
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                                        if(username.getText().toString()!="")
                                                        startActivity(new Intent(mContext, Home.class));
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w("TAG", "Error adding document", e);
                                                    }
                                                });


                                    }
                                    else {
                                        Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(mContext, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });


//                    ((MainActivity)getActivity()).navigateFrag(new LoginFragment(),false);

                }

                email.setText("");
                password.setText("");
                confirmation.setText("");
                phonenum.setText("");
                feideelo.setText("");
                username.setText("");
                chesscom.setText("");



            }
        });


        return view;
    }
    public boolean check_password(EditText password,EditText confirmation) {
        String pass1 = password.getText().toString();
        String pass2 = confirmation.getText().toString();
        if (! pass1.equals(pass2)){
            password.setError("Passwords are not the same");
            return false;
        }

        else if (! pass1.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            password.setError("Password must be at least 8 characters, containing at least one number,one lower case and one upper case");
            return false;
        }
        return true;
    }
    public boolean checkEmail(EditText email){
        if (!email.getText().toString().matches(Patterns.EMAIL_ADDRESS.pattern())) {
            email.setError("Email must contain @domain. etc");
            return false;
        }
        return true;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}

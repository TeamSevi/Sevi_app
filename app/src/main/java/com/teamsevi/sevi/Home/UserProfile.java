package com.teamsevi.sevi.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.teamsevi.sevi.Database.SessionManager;
import com.teamsevi.sevi.Database.UserHelperClass;
import com.teamsevi.sevi.Login_Signup.Forget_Password;
import com.teamsevi.sevi.Login_Signup.LoginScreen;
import com.teamsevi.sevi.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {
    //EditText first_name,last_name,user_mail,user_phone;
    TextView user_phone,user_mail;
    TextView name;
    private CircleImageView choosePhoto;
    private Uri filePath;
    private String phone;

    // request code
    private final int PICK_IMAGE_REQUEST = 22;

    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_user_profile);

        //first_name = findViewById(R.id.fe_name);
        //last_name = findViewById(R.id.le_name);
        user_phone = findViewById(R.id.E_mobile);
        user_mail = findViewById(R.id.E_Email);
        name =findViewById(R.id.name);
        Button btnUpload = (Button) findViewById(R.id.btnUpload);
        choosePhoto = (CircleImageView) findViewById(R.id.image_profile);


        SessionManager sessionManager = new SessionManager(this);
        HashMap usersDetails = sessionManager.getUserDetailFromSession();
        String firstname = (String) usersDetails.get(SessionManager.KEY_FIRSTNAME);
        String lastname = (String) usersDetails.get(SessionManager.KEY_LASTNAME);
        phone = (String) usersDetails.get(SessionManager.KEY_PHONENO);
        String email = (String) usersDetails.get(SessionManager.KEY_EMAIL);
        //first_name.setText(firstname);
        //last_name.setText(lastname);
        user_phone.setText(phone);
        //user_mail.setText(email);
        name.setText(firstname + " " + lastname);
        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        // on pressing btnSelect SelectImage() is called
        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SelectImage();
            }
        });

        // on pressing btnUpload uploadImage() is called
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                uploadImage();
            }
        });
        Query checkUser = FirebaseDatabase.getInstance().getReference("App/users").orderByChild("phoneno").equalTo(phone);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                        String _url = dataSnapshot.child(phone).child("image").getValue(String.class);
                        Glide.with(choosePhoto).load(_url).into(choosePhoto);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UserProfile.this, "add user photo", Toast.LENGTH_SHORT).show();

            }
        });
//        Bitmap bmp = BitmapFactory.decodeStream(imageReference.openConnection().getInputStream());
//        image.setImageBitmap(bmp);
// Load the image using Glide
//        if(imageReference!=null) {
//            CircleImageView  image = (CircleImageView) findViewById(R.id.image_profile);
//            Glide.with(this).load(imageReference).into(image);
//        }
//        Glide.with(this /* context */)
//                .using(new FirebaseImageLoader())
//                .load(imageReference)
//                .into(image);
    }
    // Select Image method
    private void SelectImage()
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                choosePhoto.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    // UploadImage method
    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "UserImages/"+ phone + "/"
                                    + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri downloadPhotoUrl) {
                                            //Now play with downloadPhotoUrl
                                            //Store data into Firebase Realtime Database
                                            FirebaseDatabase rootnode = FirebaseDatabase.getInstance();
                                            DatabaseReference reference = rootnode.getReference("App/users");

                                            UserHelperClass addNewUser = new UserHelperClass();
                                            reference.child(phone).child("image").setValue(downloadPhotoUrl.toString());
//                                            FriendlyMessage friendlyMessage = new FriendlyMessage
//                                                    (null, mUsername, downloadPhotoUrl.toString());
//                                            mDatabaseReference.push().setValue(friendlyMessage);
                                        }
                                    });
                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(UserProfile.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(UserProfile.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }
    public void callHomeScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), HomePage.class);
        startActivity(intent);
    }
}
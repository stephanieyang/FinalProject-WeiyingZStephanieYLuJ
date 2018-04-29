package com.example.android.mstu5031_knittingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Movie;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;


public class EditPairActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String userId;
    String pairId;
    String stitchImgName;
    String itemImgName;
    UserCreatedPair viewedPair;
    Stitch pairStitch;
    Item pairItem;
    boolean pairExists;
    final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pair);

        Log.v("TESTING", "in EditPairActivity onCreate");

        Intent intent = getIntent();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();
        pairId = intent.getStringExtra(Keys.PAIR_ID);
        pairExists = !(pairId == null || pairId.equals("null") || pairId.length() == 0); // true if pairId is non-empty, false if it's empty

        final Context context = this;

        Log.v("TESTING", "figuring out pair info...");

        if(!pairExists) {
            Log.v("TESTING", "pair does not exist");
            stitchImgName = intent.getStringExtra(Keys.STITCH_NAME);
            itemImgName = intent.getStringExtra(Keys.ITEM_NAME);

            // TODO: TAKE THIS PART OUT WHEN TESTING IS DONE
            stitchImgName = "stockinette";
            itemImgName = "gloves";

        } else {
            Log.v("TESTING","pairID = " + pairId);
            // getting information from server
            DatabaseReference pairRef = database.getReference("users/" + userId + "/matches/" + pairId);
            final String loadFailureText = this.getResources().getString(R.string.load_failure_text);

            // Read from the database
            pairRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    final UserCreatedPair currentPair = dataSnapshot.getValue(UserCreatedPair.class);
                    viewedPair = currentPair;
                    Log.d("V", "Read pair is: " + currentPair.getName());

                    // because UserCreatedPair only holds stitch/item names, we have to read from the database again to get info for each
                    DatabaseReference stitchRef = database.getReference("stitches/" + currentPair.getStitch());
                    final DatabaseReference itemRef = database.getReference("items/" + currentPair.getItem());

                    stitchRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // get stitch data
                            final Stitch currentStitch = dataSnapshot.getValue(Stitch.class);
                            pairStitch = currentStitch;
                            Log.d("V", "Read stitch is: " + currentStitch.getName());
                            itemRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // get item data
                                    Item currentItem = dataSnapshot.getValue(Item.class);
                                    pairItem = currentItem;
                                    Log.d("V", "Read pair is: " + currentItem.getName());


                                    // with all data loaded, modify the values onscreen
                                    ((ImageView)findViewById(R.id.pair_item_img)).setImageResource(Item.getDrawableIdFromImgName(currentItem.getImage_name()));
                                    ((TextView)findViewById(R.id.pair_item_name)).setText(currentItem.getName());
                                    ((ImageView)findViewById(R.id.pair_stitch_img)).setImageResource(Item.getDrawableIdFromImgName(currentStitch.getImage_name()));
                                    ((TextView)findViewById(R.id.pair_stitch_name)).setText(currentStitch.getName());
                                    ((EditText)findViewById(R.id.pair_name_entry)).setText(currentPair.getName());
                                    ((EditText)findViewById(R.id.pair_notes_entry)).setText(currentPair.getNotes());
                                    ((CheckBox)findViewById(R.id.pair_done_entry)).setChecked(currentPair.isIs_done());
                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value
                                    Log.w("V", "Failed to read value.", error.toException());
                                    // error message toast
                                    Toast.makeText(context, loadFailureText, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w("V", "Failed to read value.", error.toException());
                            Toast.makeText(context, loadFailureText, Toast.LENGTH_SHORT).show();
                        }
                    });


                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("V", "Failed to read value.", error.toException());
                    Toast.makeText(context, loadFailureText, Toast.LENGTH_SHORT).show();
                }
            });


            Button photoActionButton = (Button)findViewById(R.id.btn_photo);
            if(!pairExists || !viewedPair.isIs_done()) { // if user hasn't completed the item, don't let them upload a photo
                photoActionButton.setClickable(false);
                photoActionButton.setFocusable(false);
                photoActionButton.setEnabled(false);
            } else {
                Toast.makeText(this, "Image view placeholder", Toast.LENGTH_SHORT).show();
                if(viewedPair.getUser_photo() != null && viewedPair.getUser_photo().length() > 0) { // user can upload photo where there is none
                    photoActionButton.setText(R.string.photo_upload_text);
                } else { // user can view photo that's already uploaded
                    photoActionButton.setText(R.string.photo_view_text);
                }
            }
        }
    }

    public void savePairInfo(View view) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = auth.getCurrentUser().getUid();
        if(pairExists) {
            // get info
            String pairName = ((EditText) findViewById(R.id.pair_name_entry)).getText().toString();
            String pairNotes = ((EditText) findViewById(R.id.pair_notes_entry)).getText().toString();
            boolean isDone = ((CheckBox) findViewById(R.id.pair_done)).isChecked();
            DatabaseReference currentPairRef = database.getReference("users/" + userId + "/matches/" + pairId);
            viewedPair.setName(pairName);
            viewedPair.setNotes(pairNotes);
            viewedPair.setIs_done(isDone);
            // save to already existing place in database
            currentPairRef.setValue(viewedPair);
        } else {
            // get info
            String newPairId = UUID.randomUUID().toString();
            String newPairName = ((EditText) findViewById(R.id.pair_name_entry)).getText().toString();
            String newPairNotes = ((EditText) findViewById(R.id.pair_notes_entry)).getText().toString();
            boolean isDone = ((CheckBox) findViewById(R.id.pair_done_entry)).isChecked();
            // save to new place in database
            UserCreatedPair dummyPair = new UserCreatedPair(isDone, newPairId, itemImgName, stitchImgName, newPairName, newPairNotes,"");
            DatabaseReference newPairRef = database.getReference("users/" + userId + "/matches/" + newPairId);
            newPairRef.setValue(dummyPair);
        }

        // return to the library view when done
        Intent intent = new Intent(this, LibraryViewActivity.class);
        intent.putExtra(Keys.PAIR_STATUS, Keys.PAIR_SAVED);
        startActivity(intent);
    }

    public void doPhotoAction(View view) {
        // if the user hasn't completed the item, don't let them upload anything
        if(!pairExists || !viewedPair.isIs_done()) {
            Toast.makeText(this, R.string.upload_unallowed_text, Toast.LENGTH_SHORT).show();
        } else {
            // if user has completed an item, then the user photo is non-empty iff they have uploaded one (empty if they haven't)
            if(viewedPair.getUser_photo() != null && viewedPair.getUser_photo().length() > 0) { // photo exists already
                // get stored byte string representing the photo and send it with an intent
                String imageByteString = viewedPair.getUser_photo();
                Intent intent = new Intent(this, PhotoViewActivity.class);
                intent.putExtra(Keys.VIEW_PHOTO, imageByteString);
                startActivity(intent);
            } else { // photo doesn't exist, so let user take a photo
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (photoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(photoIntent, REQUEST_IMAGE_CAPTURE);
                }
                startActivityForResult(photoIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //DatabaseReference photoRef = database.getReference("users/" + userId + "/matches/" + pairId + "/user_photo");

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // get image data
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            // save to pair object - don't write to the database yet, only when the user chooses to save the entire pair
            viewedPair.setUser_photo(ImageUtil.bitmapToByteString(imageBitmap));
            // but do let the user know it worked
            Toast.makeText(this, R.string.photo_success_text, Toast.LENGTH_SHORT).show();
        } else {
            // error message
            Toast.makeText(this, R.string.upload_failure_text, Toast.LENGTH_SHORT).show();
        }
    }

    public void deletePairInfo(View view) {
        if(pairExists) {
            // delete information at appropriate location in database
            DatabaseReference pairRef = database.getReference("users/" + userId + "/matches/" + pairId);
            pairRef.removeValue();
        }
        // if the pair ID is empty, then this pair hasn't actually been created so we can't really delete
        // so just go to the library

        Intent intent = new Intent(this, LibraryViewActivity.class);
        intent.putExtra(Keys.PAIR_STATUS, Keys.PAIR_DELETED);
        startActivity(intent);
    }
}

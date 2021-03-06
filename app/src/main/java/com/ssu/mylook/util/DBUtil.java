//package com.ssu.readingd.util;
package com.ssu.mylook.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ssu.mylook.dto.ClotheDTO;
import com.ssu.mylook.dto.CoordiDTO;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

public class DBUtil {
    final static String TAG = "Database";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // Create a storage reference from our app
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    CoordiDTO coordiDTO;

    public static boolean coordiImg = false;

    public CoordiDTO getCoordiDTO() {
        return coordiDTO;
    }

    public void getCoordi(String id) {
        db.collection("coordi").document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                coordiDTO = document.toObject(CoordiDTO.class);
                                Log.v(TAG, "DocumentSnapshot data: " + coordiDTO.toString());
                            } else {
                                Log.v(TAG, "No such document");
                            }
                        } else {
                            Log.v(TAG, "get failed with ", task.getException());
                        }
                    }
                });
    }

    public void addCoordi(CoordiDTO dto) {
        db.collection("coordi")
                .add(dto)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.v(TAG, "coordi success: " + documentReference.getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.w(TAG, "Error adding document", e);
            }
        });

    }

    public void addClothe(ClotheDTO dto) {
        db.collection("clothes")
                .add(dto)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.v(TAG, "clothe success: " + documentReference.getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.w(TAG, "Error adding document", e);
            }
        });

    }


    public void updateCoordi(String id, CoordiDTO dto) {
        db.collection("coordi").document(id).set(dto)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }


    public void updateClothe(String id, ClotheDTO dto) {
        db.collection("clothes").document(id).set(dto)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }

    public void updateClotheCount(final String id) {
        db.collection("clothes").document(id).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        long cnt = 1;
                        if (documentSnapshot.get("count") != null) {
                            cnt = (long) documentSnapshot.get("count") + 1;
                        }
                        db.collection("clothes").document(id).update("count", cnt)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error updating document", e);
                                    }
                                });
                    }
                });

    }

    public void addData() {

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        final String id = "kkdsfk@ssu.ac.kr";
        user.put("password", "비밀번호~~");

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void deleteData(final String collection, final String id) {
        db.collection(collection).document(id).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.v(TAG, collection + "에서 " + id + " 삭제 성공");
                } else {
                    Log.v(TAG, collection + "에서 " + id + " 삭제 실패");
                }
            }
        });
    }

    public void updateData(String collection, String id, Map<String, Object> data) {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("name", "San Francisco");


        //db.collection("컬렉션명").document("바꾸고 싶은 데이터 고유 아이디").update(data1);
        //db.collection("clothes").document("53ii8P0Bax7znoZeQ3yM").update(data1);//예시
        db.collection(collection).document(id).update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }


    public void getData(String collection, String id) {
        db.collection(collection).document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                coordiDTO = document.toObject(CoordiDTO.class);
                                Log.v(TAG, "DocumentSnapshot data: " + coordiDTO.toString());
                            } else {
                                Log.v(TAG, "No such document");
                            }
                        } else {
                            Log.v(TAG, "get failed with ", task.getException());
                        }
                    }
                });
    }


    public ArrayList<CoordiDTO> getDatas(String collection, String criteria, boolean order) {
        final ArrayList<CoordiDTO> coordiView = new ArrayList<>();
        if (order) { //내림차순 정렬
            db.collection("coordi").orderBy(criteria, Query.Direction.DESCENDING)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                //DocumentSnapshot document = task.getResult();

                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    coordiView.add(CoordiDTO.mapToDTO(document.getData()));
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        } else { //오름차순 정렬
            db.collection(collection).orderBy(criteria, Query.Direction.ASCENDING)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    //a.add(CoordiDTO.mapToDTO(document.getData()));
                                }
                            } else {
                                //Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        return coordiView;
    }

    public void uploadImage(Bitmap bitmap, String name) {

        StorageReference imgRef = storageRef.child(name + ".jpg");

        final StorageReference imgRef2 = storageRef.child("images/" + name + ".jpg");

        imgRef.getName().equals(imgRef2.getName());    // true
        imgRef.getPath().equals(imgRef2.getPath());    // false


        // Get the data from an ImageView as bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = imgRef.putBytes(data);
//        Task<Uri> urlTask = uploadTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
//
//
//            @Override
//            public void onSuccess(Uri uri) {
//                coordiImg = true;
//                Log.v(TAG, "img upload success/" + uri.toString());
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.v(TAG, "Image upload Fail", e);
//            }
//        });

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                coordiImg = true;
                Log.v("dbimg upload", DBUtil.coordiImg + "");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                coordiImg = true;
                Log.v("dbimg upload fail", DBUtil.coordiImg + "");
            }
        });
    }

    //https://firebase.google.com/docs/storage/android/create-reference?authuser=0
    public void uploadImage(ImageView imageView, String name) {
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        uploadImage(bitmap, name);
    }

    //https://firebase.google.com/docs/storage/android/download-files?authuser=0
    //인자인 imageView가 imageResource가 세팅이 되어있어야만 이미지 삽입가능
    // ex)        ImageView img = new ImageView(this);
    //            img.setImageResource(R.drawable.pre_img);
    //            DBUtil.setImageViewFromDB(this, img, "coordi1");

    public void setImageViewFromDB(final Context con, final ImageView imageView, String name) {
        StorageReference httpsReference = FirebaseStorage.getInstance()
                .getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/" +
                        "ssu-mylook.appspot.com/o/" + name + ".jpg");

        httpsReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(con)
                        .load(uri)
                        .into(imageView);
                coordiImg = false;
                Log.v("dbimg db set img", DBUtil.coordiImg + "");
            }
        });

    }

}

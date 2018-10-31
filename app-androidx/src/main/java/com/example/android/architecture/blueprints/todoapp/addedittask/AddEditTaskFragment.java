/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.architecture.blueprints.todoapp.addedittask;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.camera.CameraPreviewActivity;
import com.example.android.architecture.blueprints.todoapp.util.ImageUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Main UI for the add task screen. Users can enter a task title and description.
 */
public class AddEditTaskFragment extends Fragment implements AddEditTaskContract.View {

    public static final String ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID";
    private static final int PERMISSION_REQUEST_CAMERA = 0;

    private AddEditTaskContract.Presenter mPresenter;

    private TextView mTitle;
    private View contentView;
    private ImageView imageView;
    private ImageView cameraImageView;

    private TextView mDescription;

    public static AddEditTaskFragment newInstance() {
        return new AddEditTaskFragment();
    }

    public AddEditTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull AddEditTaskContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_task_done);
        fab.setImageResource(R.drawable.ic_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] imageViewBytes = null;
                if (imageView != null) {
                    imageViewBytes = bitmapToByte(((BitmapDrawable)imageView.getDrawable()).getBitmap());
                }
                mPresenter.saveTask(mTitle.getText().toString(), mDescription.getText().toString(),imageViewBytes);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.addtask_frag, container, false);
        contentView = getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
        mTitle = (TextView) root.findViewById(R.id.add_task_title);
        mTitle.requestFocus();
        //mTitle.getAccessibilityNodeProvider().createAccessibilityNodeInfo()
        mDescription = (TextView) root.findViewById(R.id.add_task_description);
        imageView = (ImageView) root.findViewById(R.id.imageView);
        ImageButton imageButton = (ImageButton) root.findViewById(R.id.getImage);
        imageButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                onImageButtonClick();
            }
        });

        cameraImageView = (ImageView) root.findViewById(R.id.makePhoto);
        cameraImageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                showCameraPreview();
            }
        });

        setHasOptionsMenu(true);
        return root;
    }

    private static final int SELECT_PICTURE = 1;
    private static final int TAKE_PICTURE = 2;

    public void onImageButtonClick() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SELECT_PICTURE);
    }

    private void showCameraPreview() {
        // BEGIN_INCLUDE(startCamera)
        // Check if the Camera permission has been granted
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            // Permission is already available, start camera preview
            Snackbar.make(contentView,
                    "Available premission",
                    Snackbar.LENGTH_SHORT).show();
            startCamera();
        } else {
            // Permission is missing and must be requested.
            requestCameraPermission();
        }
        // END_INCLUDE(startCamera)
    }

    /**
     * Requests the {@link Manifest.permission#CAMERA} permission.
     * If an additional rationale should be displayed, the user has to launch the request from
     * a SnackBar that includes additional information.
     */
    private void requestCameraPermission() {
        // Permission has not been granted and must be requested.
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.CAMERA)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // Display a SnackBar with cda button to request the missing permission.
            Snackbar.make(contentView, "Access required",
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Request the permission
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            PERMISSION_REQUEST_CAMERA);
                }
            }).show();

        } else {
            Snackbar.make(contentView, "Camera unavailable", Snackbar.LENGTH_SHORT).show();
            // Request the permission. The result will be received in onRequestPermissionResult().
            requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
        }
    }

    private void startCamera() {
        Intent intent = new Intent(getActivity(), CameraPreviewActivity.class);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
                    startCamera();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE || requestCode == TAKE_PICTURE) {
                Uri selectedImageUri = data.getData();
                BitmapDrawable bitmapDrawable =
                        ImageUtils.scaleAndSetImage(selectedImageUri, getContext(), 200);

                ExifInterface ei = null;
                try {
                    ei = new ExifInterface(selectedImageUri.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int orientation = 0;
                if (ei != null) {
                    orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                            ExifInterface.ORIENTATION_UNDEFINED);
                }

                BitmapDrawable rotatedBitmap = null;
                switch(orientation) {

                    case ExifInterface.ORIENTATION_ROTATE_90:
                        rotatedBitmap = rotateImage(bitmapDrawable, 90);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_180:
                        rotatedBitmap = rotateImage(bitmapDrawable, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                    case ExifInterface.ORIENTATION_UNDEFINED:
                        rotatedBitmap = rotateImage(bitmapDrawable, 270);
                        break;

                    case ExifInterface.ORIENTATION_NORMAL:
                    default:
                        rotatedBitmap = bitmapDrawable;
                }

                // Apply the scaled bitmap
                imageView.setImageDrawable(rotatedBitmap);

                // Now change ImageView's dimensions to match the scaled image
                ConstraintLayout.LayoutParams params =
                        (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
                params.width = imageView.getWidth();
                params.height = imageView.getHeight();
                imageView.setLayoutParams(params);
            }
        }
    }

    public static BitmapDrawable rotateImage(BitmapDrawable source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return new BitmapDrawable(Bitmap.createBitmap(source.getBitmap(), 0, 0, source.getBitmap().getWidth(), source.getBitmap().getHeight(),
                matrix, true));
    }

    @Override
    public void showEmptyTaskError() {
        Snackbar snackbar = Snackbar.make(contentView, getString(R.string.empty_task_message), Snackbar.LENGTH_SHORT);
        snackbar.show();
        EditText title = getActivity().findViewById(R.id.add_task_title);
        title.setError(getResources().getString(R.string.add_task_empty_title));
        title.setHintTextColor(Color.RED);
    }

    @Override
    public void showTasksList() {
        getActivity().setResult(RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void setDescription(String description) {
        mDescription.setText(description);
    }

    @Override
    public void setImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    // Bitmap to byte[]
    private byte[] bitmapToByte(Bitmap bitmap) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            //bitmap to byte[] stream
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] x = stream.toByteArray();
            //close stream to save memory
            stream.close();
            return x;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

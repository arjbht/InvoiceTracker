package com.ab.invoicetracker.fragments;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.invoicetracker.BaseApplication;
import com.ab.invoicetracker.BaseFragment;
import com.ab.invoicetracker.BuildConfig;
import com.ab.invoicetracker.R;
import com.ab.invoicetracker.activities.Camera2Activity;
import com.ab.invoicetracker.databinding.FragmentInvoiceDetailsDataBinding;
import com.ab.invoicetracker.network.NetworkCallController;
import com.ab.invoicetracker.network.NetworkResponseListner;
import com.ab.invoicetracker.network.model.image_upload.ImageUploadData;
import com.ab.invoicetracker.network.model.image_upload.ImageUploadRequest;
import com.ab.invoicetracker.network.model.login.LoginData;
import com.ab.invoicetracker.utils.AppUtils;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;


import es.dmoral.toasty.Toasty;
import io.realm.RealmResults;

import static android.app.Activity.RESULT_OK;
import static android.view.View.GONE;
import static androidx.core.content.ContextCompat.getSystemService;

public class InvoiceDetailsDataFragment extends BaseFragment implements FragmentDatePicker.onDatePickerListener {
    FragmentInvoiceDetailsDataBinding mFragmentInvoiceDetailsBinding;

    public static final String ARGS_NAME = "ARGS_NAME";
    public static final String ARGS_SFDC_NO = "ARGS_SFDC_NO";
    public static final String ARGS_REGION = "ARGS_REGION";
    public static final String ARGS_NET_COST = "ARGS_NET_COST";
    public static final String ARGS_INVOICE_NO = "ARGS_INVOICE_NO";
    public static final String ARGS_INVOICE_ID = "ARGS_INVOICE_ID";
    public static final String ARGS_VERIFIED = "ARGS_VERIFIED";
    public static final String ARGS_WRONG = "ARGS_WRONG";
    public static final String ARGS_DATE = "ARGS_DATE";
    public static final String ARGS_IMG = "ARGS_MG";
    public static final String ARGS_INQUEUE = "ARGS_INQUEUE";
    private File mPhotoFile;
    private String selectedImagePath;
    private Bitmap bitmap;
    private Bitmap selectedBmp;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_GALLERY_PHOTO = 2;
    private String invoiceName = "";
    private String sfdcNo = "";
    private String region = "";
    private String netCost = "";
    private String invoiceNo = "";
    private String invoiceId = "";
    private boolean verified = false;
    private boolean wrong = false;
    private boolean inQueue = false;
    private static final int REQUEST_CODE = 1234;
    private boolean mPermissions;
    private String date = "";
    private String imgUrl = "";


    public InvoiceDetailsDataFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static InvoiceDetailsDataFragment newInstance(String invoiceName, String sfdcNo, String region, String netCost, String invoiceNo, String invoiceId, boolean verified, boolean wrong, boolean inQueue, String date, String img) {
        InvoiceDetailsDataFragment fragment = new InvoiceDetailsDataFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_INVOICE_ID, invoiceId);
        args.putString(ARGS_INVOICE_NO, invoiceNo);
        args.putString(ARGS_NAME, invoiceName);
        args.putString(ARGS_NET_COST, netCost);
        args.putString(ARGS_REGION, region);
        args.putString(ARGS_SFDC_NO, sfdcNo);
        args.putString(ARGS_DATE, date);
        args.putString(ARGS_IMG, img);
        args.putBoolean(ARGS_VERIFIED, verified);
        args.putBoolean(ARGS_WRONG, wrong);
        args.putBoolean(ARGS_INQUEUE, inQueue);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            invoiceName = getArguments().getString(ARGS_NAME);
            invoiceId = getArguments().getString(ARGS_INVOICE_ID);
            invoiceNo = getArguments().getString(ARGS_INVOICE_NO);
            sfdcNo = getArguments().getString(ARGS_SFDC_NO);
            netCost = getArguments().getString(ARGS_NET_COST);
            region = getArguments().getString(ARGS_REGION);
            verified = getArguments().getBoolean(ARGS_VERIFIED);
            inQueue = getArguments().getBoolean(ARGS_INQUEUE);
            wrong = getArguments().getBoolean(ARGS_WRONG);
            date = getArguments().getString(ARGS_DATE);
            imgUrl = getArguments().getString(ARGS_IMG);
        }
        AppUtils.CAMERA_SCREEN = "INVOICE";
        /*LocalBroadcastManager.getInstance(requireActivity()).registerReceiver(mMessageReceiver,
                new IntentFilter(AppUtils.CAMERA_SCREEN));*/
    }

    /*private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String base64 = intent.getStringExtra("base64");
            uploadOnsiteImage(base64);
            Log.d("receiver", "Got message: " + base64);
            LocalBroadcastManager.getInstance(context).unregisterReceiver(mMessageReceiver);
        }
    };*/

    private void uploadOnsiteImage(String base64) {
        try {

            ImageUploadRequest request = new ImageUploadRequest();
            request.setFileUrl("");
            request.setFileName("");
            request.setFileContent(base64);
            request.setActivityId("");

            NetworkCallController controller = new NetworkCallController(this);
            controller.setListner(new NetworkResponseListner<ImageUploadData>() {
                @Override
                public void onResponse(ImageUploadData response) {
                    try {
                        mFragmentInvoiceDetailsBinding.lnrUpload.setVisibility(View.GONE);
                        mFragmentInvoiceDetailsBinding.lnrImage.setVisibility(View.VISIBLE);
                        Picasso.get().load(response.getFileUrl()).fit().into(mFragmentInvoiceDetailsBinding.imgUploadedCheque);
                        imgUrl = response.getFileUrl();
                        Log.d("TAG", imgUrl);
                        //LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(mMessageReceiver);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure() {

                }
            });
            controller.uploadImage(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentInvoiceDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_invoice_details_data, container, false);
        return mFragmentInvoiceDetailsBinding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Invoice #" + invoiceNo);
        if (imgUrl!=null &&!imgUrl.equals("")) {
            mFragmentInvoiceDetailsBinding.lnrUpload.setVisibility(View.GONE);
            mFragmentInvoiceDetailsBinding.lnrImage.setVisibility(View.VISIBLE);
            Picasso.get().load(imgUrl).fit().into(mFragmentInvoiceDetailsBinding.imgUploadedCheque);
        }

        if( date!= null && !date.equals("")){
            mFragmentInvoiceDetailsBinding.edtDate.setText(date);
        }

        mFragmentInvoiceDetailsBinding.txtName.setText(invoiceName);
        mFragmentInvoiceDetailsBinding.txtName.setTypeface(mFragmentInvoiceDetailsBinding.txtName.getTypeface(), Typeface.BOLD);
        mFragmentInvoiceDetailsBinding.txtNetCost.setText(netCost);
        mFragmentInvoiceDetailsBinding.txtSfdcNo.setText(sfdcNo);
        mFragmentInvoiceDetailsBinding.txtRegion.setText(region);
        mFragmentInvoiceDetailsBinding.txtDateLabel.setTypeface(mFragmentInvoiceDetailsBinding.txtDateLabel.getTypeface(), Typeface.BOLD);
        mFragmentInvoiceDetailsBinding.txtImageLabel.setTypeface(mFragmentInvoiceDetailsBinding.txtImageLabel.getTypeface(), Typeface.BOLD);
        mFragmentInvoiceDetailsBinding.txtInvoiceLabel.setTypeface(mFragmentInvoiceDetailsBinding.txtInvoiceLabel.getTypeface(), Typeface.BOLD);
        mFragmentInvoiceDetailsBinding.txtStatus.setTypeface(mFragmentInvoiceDetailsBinding.txtStatus.getTypeface(), Typeface.BOLD);
        if (verified && wrong && inQueue) {
            mFragmentInvoiceDetailsBinding.txtStatus.setText("Not Verified");
            mFragmentInvoiceDetailsBinding.txtStatus.setTextColor(getActivity().getColor(R.color.lightRed));
            mFragmentInvoiceDetailsBinding.lnrStatus.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.status_border_not));
            mFragmentInvoiceDetailsBinding.btnSave.setVisibility(View.VISIBLE);
        } else if (inQueue && verified && !wrong) {
            mFragmentInvoiceDetailsBinding.txtStatus.setText("Verified");
            mFragmentInvoiceDetailsBinding.txtStatus.setTextColor(getActivity().getColor(R.color.themeColor));
            mFragmentInvoiceDetailsBinding.lnrStatus.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.ststus_border));
            mFragmentInvoiceDetailsBinding.btnSave.setVisibility(View.GONE);
        } else if (inQueue && !verified && !wrong) {
            mFragmentInvoiceDetailsBinding.txtStatus.setText("In-Queue");
            mFragmentInvoiceDetailsBinding.txtStatus.setTextColor(getActivity().getColor(R.color.colorBlue));
            mFragmentInvoiceDetailsBinding.lnrStatus.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.status_border_blue));
            mFragmentInvoiceDetailsBinding.btnSave.setVisibility(View.GONE);
        } else {
            mFragmentInvoiceDetailsBinding.txtStatus.setText("Pending");
            mFragmentInvoiceDetailsBinding.txtStatus.setTextColor(getActivity().getColor(R.color.late_pink));
            mFragmentInvoiceDetailsBinding.lnrStatus.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.status_border_pending));
            mFragmentInvoiceDetailsBinding.btnSave.setVisibility(View.VISIBLE);
        }
        mFragmentInvoiceDetailsBinding.lnrUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //init();
                pickImage();
            }
        });
        mFragmentInvoiceDetailsBinding.imageCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgUrl = "";
                mFragmentInvoiceDetailsBinding.lnrImage.setVisibility(View.GONE);
                mFragmentInvoiceDetailsBinding.lnrUpload.setVisibility(View.VISIBLE);
            }
        });

        mFragmentInvoiceDetailsBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInvoiceDetails();
            }
        });

        mFragmentInvoiceDetailsBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String DownloadUrl = "http://apps.hicare.in/api/api/invoice/DownloadInvoicePdf?InvoiceNo=" + invoiceId;
                DownloadManager.Request request1 = new DownloadManager.Request(Uri.parse(DownloadUrl));
                request1.allowScanningByMediaScanner();
                request1.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request1.setDescription(invoiceId);   //appears the same in Notification bar while downloading
                request1.setTitle("Invoice #" + invoiceNo);
                request1.setVisibleInDownloadsUi(false);

//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//                    request1.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
//                }
                request1.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Invoice1.pdf");
                DownloadManager manager1 = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                Objects.requireNonNull(manager1).enqueue(request1);
                if (DownloadManager.STATUS_SUCCESSFUL == 8) {
                    Toasty.success(getActivity(), "Download successful", Toasty.LENGTH_SHORT).show();
                }
            }
        });

        mFragmentInvoiceDetailsBinding.imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });


    }

    private void showDatePicker() {
        try {
            FragmentDatePicker mFragDatePicker = new FragmentDatePicker();
            mFragDatePicker.setmDatePickerListener(this);
            mFragDatePicker.show(getActivity().getSupportFragmentManager(), "datepicker");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        try {
            month++;
            mFragmentInvoiceDetailsBinding.edtDate.setText("" + day + "-" + month + "-" + year);
            date = AppUtils.reFormatDateAndTime(mFragmentInvoiceDetailsBinding.edtDate.getText().toString(), "yyyy-MM-dd HH:mm aa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveInvoiceDetails() {
        try {
            if (isValidated()) {
                if (getActivity() != null) {
                    RealmResults<LoginData> LoginRealmModels =
                            BaseApplication.getRealm().where(LoginData.class).findAll();
                    if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
                        String id = LoginRealmModels.get(0).getEmail();
                        NetworkCallController controller = new NetworkCallController(this);
                        controller.setListner(new NetworkResponseListner() {
                            @Override
                            public void onResponse(Object response) {
                                Toasty.success(getActivity(), "Saved Sucessfully", Toasty.LENGTH_SHORT).show();
                                getActivity().finish();
                            }

                            @Override
                            public void onFailure() {

                            }
                        });
                        controller.saveInvoiceDetails(invoiceId, imgUrl, id, date);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*private void pickImage() {
        new CameraUtils().startCrop();

        *//*CropImage.activity()
                .setMinCropResultSize(400, 400)
                .setMaxCropResultSize(3000, 3000)
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(requireContext(), this);*//*
    }*/

    private boolean isValidated() {
        if (mFragmentInvoiceDetailsBinding.edtDate.getText().toString().length() == 0) {
            Toasty.error(getActivity(), "Please select date!", Toasty.LENGTH_SHORT).show();
            return false;
        } else if (imgUrl.length() == 0) {
            Toasty.error(getActivity(), "Image required!", Toasty.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }


    private void init() {
        if (mPermissions) {
            if (checkCameraHardware(getActivity())) {
                startCamera2();
            } else {
                showSnackBar("You need a camera to use this application", Snackbar.LENGTH_INDEFINITE);
            }
        } else {
            verifyPermissions();
        }
    }

    private boolean checkCameraHardware(Context context) {
        // this device has a camera
        // no camera on this device
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    private void startCamera2() {
        Intent intent = new Intent(getActivity(), Camera2Activity.class);
        intent.putExtra(AppUtils.CAMERA_ORIENTATION, "BACK");
        startActivity(intent);
    }

    private void showSnackBar(final String text, final int length) {
        View view = getActivity().findViewById(android.R.id.content).getRootView();
        Snackbar.make(view, text, length).show();
    }

    public void verifyPermissions() {
        Log.d("TAG", "verifyPermissions: asking user for permissions.");
        String[] permissions = {
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                permissions[1]) == PackageManager.PERMISSION_GRANTED) {
            mPermissions = true;
            init();
        } else {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    permissions,
                    REQUEST_CODE
            );
        }
    }


    private void pickImage() {

        LayoutInflater li = LayoutInflater.from(getActivity());

        View promptsView = li.inflate(R.layout.layout_choose_gallery_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setView(promptsView);
        final AlertDialog alertDialog = alertDialogBuilder.create();

        final ImageView selectedImg =
                promptsView.findViewById(R.id.selectedImg);
        final LinearLayout lnrCamera =
                promptsView.findViewById(R.id.lnrCamera);
        final LinearLayout lnrGallery =
                promptsView.findViewById(R.id.lnrGallery);
        final TextView btn_cancel =
                promptsView.findViewById(R.id.btnCancel);
        final LinearLayout cardSelected = promptsView.findViewById(R.id.cardSelected);

        int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray typedArray = getActivity().obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        lnrCamera.setBackgroundResource(backgroundResource);
        lnrGallery.setBackgroundResource(backgroundResource);

        /*if (selectedBmp != null) {
            cardSelected.setVisibility(View.VISIBLE);
            Glide.with(getActivity())
                    .load(selectedBmp)
                    .error(android.R.drawable.stat_notify_error)
                    .into(selectedImg);
        } else {
            cardSelected.setVisibility(GONE);
        }*/

        cardSelected.setOnClickListener(view -> {
            if (selectedBmp != null) {
                alertDialog.dismiss();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                selectedBmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] b = baos.toByteArray();
                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                uploadOnsiteImage(encodedImage);
            }
        });
        lnrCamera.setOnClickListener(view -> {
            requestStoragePermission(true);
            alertDialog.dismiss();
        });

        lnrGallery.setOnClickListener(view -> {
            requestStoragePermission(false);
            alertDialog.dismiss();
        });

        btn_cancel.setOnClickListener(v -> alertDialog.dismiss());
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    private void requestStoragePermission(boolean isCamera) {
        try {
            Dexter.withActivity(getActivity())
                    .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            // check if all permissions are granted
                            if (report.areAllPermissionsGranted()) {
                                if (isCamera) {
                                    dispatchTakePictureIntent();
                                } else {
                                    dispatchGalleryIntent();
                                }
                            }
                            // check for permanent denial of any permission
                            if (report.isAnyPermissionPermanentlyDenied()) {
                                // show alert dialog navigating to Settings
                                showSettingsDialog();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                                       PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    })
                    .withErrorListener(
                            error -> Toast.makeText(getActivity(), "Error occurred! ", Toast.LENGTH_SHORT)
                                    .show())
                    .onSameThread()
                    .check();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dispatchTakePictureIntent() {
        try {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    // Error occurred while creating the File
                }
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(requireContext(), BuildConfig.APPLICATION_ID + ".provider", photoFile);
                    mPhotoFile = photoFile;
                    takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", Camera.CameraInfo.CAMERA_FACING_BACK);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void dispatchGalleryIntent() {
        try {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickPhoto.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(pickPhoto, REQUEST_GALLERY_PHOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String mFileName = "JPEG_" + timeStamp + "_";
        File storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File mFile = File.createTempFile(mFileName, ".jpg", storageDir);
        return mFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
                //images.add(mPhotoFile.getPath());
                //imgFile = new File(mPhotoFile.getPath());
                selectedImagePath = mPhotoFile.getPath();
                if (selectedImagePath != null) {
                    Bitmap bit = new BitmapDrawable(getActivity().getResources(), selectedImagePath).getBitmap();
                    int i = (int) (bit.getHeight() * (1024.0 / bit.getWidth()));
                    bitmap = Bitmap.createScaledBitmap(bit, 1024, i, true);
                    selectedBmp = bitmap;
                }
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                byte[] b = baos.toByteArray();
                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                uploadOnsiteImage(encodedImage);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void showSettingsDialog() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Need Permissions");
            builder.setMessage(
                    "This app needs permission to use this feature. You can grant them in app settings.");
            builder.setPositiveButton("GOTO SETTINGS", (dialog, which) -> {
                dialog.cancel();
                openSettings();
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openSettings() {
        try {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
            intent.setData(uri);
            startActivityForResult(intent, 101);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
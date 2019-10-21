package com.nguyenoanh.downloadfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_CODE = 1000 ;
    private EditText edtLink;
    private TextView tvContent;
    private Button btnDownload;
    private Button btnList;

    private String dataJson;

    public void initLayout() {
        edtLink = (EditText) findViewById (R.id.edt_link);
        tvContent = (TextView) findViewById (R.id.tv_content);
        btnDownload = (Button) findViewById (R.id.btn_download);
        btnList = (Button) findViewById (R.id.btn_list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        initLayout ();

//        btnDownload.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ){
//                    if(checkSelfPermission (Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
//                            PackageManager.PERMISSION_DENIED){
//                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
//                        requestPermissions (permissions, PERMISSION_CODE);
//                    } else {
//                        startDownloading();
//                    }
//                } else {
//                    startDownloading();
//                }
//
//            }
//        });
    }

//    private void startDownloading() {
//        String url = edtLink.getText().toString().trim();
//
//        DownloadManager.Request request = new DownloadManager.Request (Uri.parse (url));
//
//        request.setAllowedNetworkTypes (DownloadManager.Request.NETWORK_WIFI |
//                DownloadManager.Request.NETWORK_MOBILE);
//        request.setTitle ("Download");
//        request.setDescription ("Downloading file ...");
//
//        request.allowScanningByMediaScanner ();
//        request.setNotificationVisibility (DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setDestinationInExternalPublicDir (Environment.DIRECTORY_DOWNLOADS, "" +
//                System.currentTimeMillis ());
//
//        DownloadManager manager = (DownloadManager) getSystemService (Context.DOWNLOAD_SERVICE);
//        manager.enqueue (request);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult (requestCode, permissions, grantResults);
//        switch (requestCode){
//            case PERMISSION_CODE: {
//                if (grantResults.length > 0
//                 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    startDownloading ();
//                } else {
//                    Toast.makeText (this, "Permission denied ... !", Toast.LENGTH_SHORT).show ();
//                }
//            }
//        }
//    }



    public void clickListView(View view) {
        if( dataJson == null){
            Toast.makeText (getApplicationContext (), "No data yet!!!", Toast.LENGTH_LONG).show ();
        } else {
            Intent intent = new Intent (this, DisplayList.class);
            intent.putExtra ("json_data", dataJson);
            startActivity (intent);
        }
    }
    public void clickDownload(View view) {
        new DownloadJsonTask().execute();
    }

    class DownloadJsonTask extends AsyncTask<Void, Void, String>{
        String url;
        String inputLine;

        @Override
        protected void onPreExecute() {
            super.onPreExecute ();
            url ="https://jsonplaceholder.typicode.com/users";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL urlLink = new URL (url);
                HttpURLConnection connection = (HttpURLConnection) urlLink.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                Log.v("TAG", "Response code: " + responseCode);

                InputStream inputStream = connection.getInputStream ();
                BufferedReader bufferedReader = new BufferedReader(new
                        InputStreamReader (connection.getInputStream()));

                StringBuilder response = new StringBuilder();
                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine + "\n");
                }

                bufferedReader.close ();
                inputStream.close ();
                connection.disconnect ();
                return response.toString ().trim ();
            } catch (MalformedURLException e) {
                e.printStackTrace ();
            } catch (ProtocolException e) {
                e.printStackTrace ();
            } catch (IOException e) {
                e.printStackTrace ();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute (result);
            tvContent.setText (result);
            dataJson = result;
        }
    }
}

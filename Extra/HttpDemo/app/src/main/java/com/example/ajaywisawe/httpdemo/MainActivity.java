package com.example.ajaywisawe.httpdemo;

import android.content.Context;
import android.content.Entity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.checkNetworkButton).setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                if (isConnectedOnline()) {
                    Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_LONG).show();
                    //new GetData().execute("http://rss.cnn.com/rss/cnn_tech.rss");
                   // new GetImage().execute("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
                    RequestParams params = new RequestParams("GET", "http://dev.theappsdr.com/lectures/params.php");
                    params.addParam("key1","value1");
                    params.addParam("key2","value2");
                    params.addParam("key3","value3");
                    params.addParam("key4","value4");

                    new GetDataWithParams().execute(params);
                } else {
                    Toast.makeText(MainActivity.this, "Not Connected", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    //http://rss.cnn.com/rss/cnn_tech.rss
    private class GetDataWithParams extends AsyncTask<RequestParams, Void, String> {

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p/>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected String doInBackground(RequestParams... params) {
            BufferedReader bufferedReader = null;
            try {
                HttpURLConnection con = params[0].startConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                //con.getInputStream(); // used during parsing
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

            finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        protected void onPostExecute(String result) {
            if (result != null) {
                Log.d("demo", result);
            } else {
                Log.d("demo", "Null data");
            }
        }

    }


    private class GetImage extends AsyncTask<String, Void, Bitmap> {

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p/>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Bitmap doInBackground(String... params) {
            InputStream in = null;
            try {
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                in = con.getInputStream();
                Bitmap image = BitmapFactory.decodeStream(in);
                return image;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
            return null;
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                ImageView iv = (ImageView) findViewById(R.id.imageView);
                iv.setImageBitmap(result);
            } else {
                Log.d("demo", "Null data");
            }
        }

    }


    private class GetData extends AsyncTask<String, Void, String> {

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p/>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected String doInBackground(String... params) {
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                //Bitmap image = BitmapFactory.decodeStream(con.getInputStream());

                bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                //con.getInputStream(); // used during parsing
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                return sb.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        protected void onPostExecute(String result) {
            if (result != null) {
                Log.d("demo", result);
            } else {
                Log.d("demo", "Null data");
            }
        }

    }

    private boolean isConnectedOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null) {
            return true;
        } else {
            return false;
        }
    }
}




/*private class GetDataHttpClient extends AsyncTask<String, Void, String> {

    *//**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     *//*
    @Override
    protected String doInBackground(String... params) {
       AndroidHttpClient client - AndroidHttpClient.newInstance("AndroidAgent");
        HttpGet request = new HttpGet(params[0]);

        try {

            HttpResponse response = client.execute(request);
            return EntityUtils.toString(response.getEntity());

        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            client.close();
        }
        return null;
    }

    protected void onPostExecute(String result) {
        if (result != null) {
            Log.d("demo", result);
        } else {
            Log.d("demo", "Null data");
        }
    }

}*/


package com.preetiharkanth.weatherapp.hw5.group26.weather;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ajay Wisawe on 3/17/2016.
 */
public class ValidateCityStateAsyncTask extends AsyncTask<String,Void,Boolean> {

    IValidateCityStateAsyncTask activity;

    public ValidateCityStateAsyncTask(IValidateCityStateAsyncTask activity){
        this.activity = activity;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        activity.showProcessing();
    }

    @Override
    protected void onPostExecute(Boolean b) {
        super.onPostExecute(b);
        activity.finishProcessing();
        activity.setValidated(b);
    }
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
    protected Boolean doInBackground(String... params) {
        try{
            String formatedUrl = params[0];
            formatedUrl = formatedUrl.replace(" ","%20");
            Log.d("demo", formatedUrl);

            URL url = new URL(formatedUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int statusCode = connection.getResponseCode();

            if(statusCode == HttpURLConnection.HTTP_OK){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder str = new StringBuilder();
                String line = bufferedReader.readLine();

                while (line != null){
                    str.append(line);
                    line = bufferedReader.readLine();
                }

                try{
                    String jsonString = str.toString();
                    Log.d("demo",jsonString);

                    JSONObject locationAPIJsonObject = new JSONObject(jsonString);
                    String likelihood = locationAPIJsonObject.getString("likelihood");
                    String normalizedLocation = locationAPIJsonObject.getString("normalizedLocation");
                    String[] normalizedLocationArray = normalizedLocation.split(",");

                    if(likelihood.equals("1.0") && normalizedLocationArray.length == 2){
                        return true;
                    }
                    else {
                        return false;
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }


        return null;
    }


    public interface IValidateCityStateAsyncTask {
        void setValidated(boolean validated);
        void showProcessing();
        void finishProcessing();
    }
}

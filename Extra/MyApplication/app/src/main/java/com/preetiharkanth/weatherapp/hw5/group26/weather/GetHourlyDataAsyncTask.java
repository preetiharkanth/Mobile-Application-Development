package com.preetiharkanth.weatherapp.hw5.group26.weather;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Ajay Wisawe on 3/18/2016.
 */
public class GetHourlyDataAsyncTask extends AsyncTask<String,Void,List<Weather>> {

    IGetWeather activity;

    public  GetHourlyDataAsyncTask(IGetWeather activity){
        this.execute();
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
    protected List<Weather> doInBackground(String... params) {

        try{
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int statusCode = connection.getResponseCode();

            if(statusCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();
                return WeatherUtils.WeatherPullParser.parseWeather(inputStream);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }


    public interface IGetWeather{
        void setHourlyWeather(List<Weather> list);
        void showProcessing();
        void finishProcessing();

    }
}

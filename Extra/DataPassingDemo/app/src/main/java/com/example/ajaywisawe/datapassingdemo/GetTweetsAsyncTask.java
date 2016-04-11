package com.example.ajaywisawe.datapassingdemo;

import android.content.Context;
import android.os.AsyncTask;

import java.util.LinkedList;

/**
 * Created by Ajay Wisawe on 2/15/2016.
 */
public class GetTweetsAsyncTask extends AsyncTask<String, Void, LinkedList<String>>{

    IData activity;

    public  GetTweetsAsyncTask(IData activity){
        this.activity  = activity;
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
    protected LinkedList<String> doInBackground(String... params) {
        LinkedList<String> tweets = new LinkedList<String>();
        tweets.add("Tweet 0");
        tweets.add("Tweet 1");
        tweets.add("Tweet 2");
        tweets.add("Tweet 3");

        return tweets;
    }

    protected void onPostExecute(LinkedList<String> result) {
        activity.setupData(result);
        super.onPostExecute(result);
    }

    static public interface IData {
        public void setupData(LinkedList<String> result);
        public Context getContext();
    }
}

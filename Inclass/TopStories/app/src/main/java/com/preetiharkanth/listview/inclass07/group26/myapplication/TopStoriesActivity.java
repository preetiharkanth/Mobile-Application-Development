package com.preetiharkanth.listview.inclass07.group26.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TopStoriesActivity extends AppCompatActivity implements  GetTopStoryDataAsyncTask.IGetTopStoryData{

    List<Story> stories = new ArrayList<Story>();
    String category;
    String baseUrl = "http://api.nytimes.com/svc/topstories/v1/";
    //4ec26f049ce3e63cf7f39bec8bc4a193:5:74582549

    ProgressDialog progressDialog;
    ListView listView;
    //colorAdaptor.setNotifyOnChange(true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_stories);



        if(getIntent() != null && getIntent().getExtras() != null){
            category = getIntent().getExtras().getString("CATEGORY");

            String url = baseUrl + category + ".json?api-key=4ec26f049ce3e63cf7f39bec8bc4a193:5:74582549";
            new GetTopStoryDataAsyncTask(TopStoriesActivity.this).execute(url);
        }
    }

    @Override
    public void setStoryData(final List<Story> stories) {
        this.stories = stories;

        StoryAdaptor storyAdaptor = new StoryAdaptor(this,R.layout.row_item_layout,stories);
        listView  = (ListView) findViewById(R.id.topStoryListView);
        listView.setAdapter(storyAdaptor);
        storyAdaptor.setNotifyOnChange(true);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TopStoriesActivity.this, DetailsActivity.class);
                intent.putExtra("STORY", stories.get(position));
                startActivity(intent);
                // Log.d("demo", "position is " + position + ", Value is " + stories.get(position).toString());
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView bookMark = (ImageView)view.findViewById(R.id.bookMarkView);
                bookMark.setImageResource(R.drawable.bookmark_filled);
                return false;
            }
        });

    }

    @Override
    public void startProcessing() {
        progressDialog = new ProgressDialog(TopStoriesActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading Stories..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    public void finishProcessing() {
        progressDialog.dismiss();
    }

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     * <p/>
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     * <p/>
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     * <p/>
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     * <p/>
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;

    }


    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p/>
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

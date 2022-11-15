package com.sonderben.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.sonderben.news.adapters.MainAdapter;
import com.sonderben.news.models.Article;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = this.findViewById(R.id.search);
        recyclerView = this.findViewById(R.id.recyclerView);

         mainAdapter = new MainAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

         newsApiClient = new NewsApiClient("api");

// /v2/everything
        articles = new ArrayList<>();

        //getArticle("Ariel Henry");


        search.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                        getArticle(search.getText().toString());
                        //textView.clearFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                        return true;
                    }

                });


    }
    private void getArticle(String query){
        Log.i("genial",query);
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(query)
                        .language("fr")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {


                    @Override
                    public void onSuccess(ArticleResponse response) {

                        articles.clear();
                        for(int i = 0; i<response.getArticles().size();i++){

                            com.kwabenaberko.newsapilib.models.Article article = response.getArticles().get(i);

                            //if(article.getUrl()){
                                articles.add(new Article(response.getArticles().get(i).getUrl(),
                                        response.getArticles().get(i).getTitle(),
                                        response.getArticles().get(i).getDescription(),
                                        response.getArticles().get(i).getUrlToImage()));
                           // }
                        }

                        mainAdapter.setArticles(articles);
                        recyclerView.setAdapter(mainAdapter);



                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );
    }
    EditText search;
    RecyclerView recyclerView;
    List<Article> articles;
    NewsApiClient newsApiClient;
    MainAdapter mainAdapter;
}
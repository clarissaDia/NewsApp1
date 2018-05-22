package com.example.android.newsapp1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, List<News> newsList) {
        super(context, 0, newsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        }
        News currentNews = getItem(position);
        TextView sectionTextView = (TextView) listItemView.findViewById(R.id.section);
        String newsSection = currentNews.getNewsSection();
        sectionTextView.setText(newsSection);

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("EEEE dd MMMM yyyy", Locale.ENGLISH);
        try {
            Date date = dateFormat.parse(currentNews.getNewsDate());

            String parsedaDate = dateFormat2.format(date);
            dateView.setText(parsedaDate);
        } catch (ParseException e) {
        }

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        String newsTitle = currentNews.getTitle();
        titleTextView.setText(newsTitle);

        TextView authorTextView = (TextView) listItemView.findViewById(R.id.author);
        String newsAuthor = currentNews.getAuthor();
        authorTextView.setText(newsAuthor);
        return listItemView;
    }
}
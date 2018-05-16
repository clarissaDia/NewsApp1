package com.example.android.newsapp1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter (Context context,List<News> newsList){
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

        TextView sectionNameTextView = (TextView) listItemView.findViewById(R.id.section_name);
        String sectionName = currentNews.getSectionName();
        sectionNameTextView.setText(sectionName);

        Date dateObject = new Date (currentNews.getNewsDate());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate (dateObject);
        dateView.setText(formattedDate);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        String newsTitle = currentNews.getTitle();
        titleTextView.setText(newsTitle);

        return listItemView;
    }

    private String formatDate (Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
    private String formatTime (Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}

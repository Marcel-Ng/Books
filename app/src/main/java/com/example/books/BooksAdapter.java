package com.example.books;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    ArrayList<Book> books;

    public BooksAdapter (ArrayList<Book> books){
        this.books = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.book_list_item, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvAuthor;
        TextView tvTitle;
        TextView tvDate;
        TextView tvPublisher;

        public BookViewHolder(View itemView) {
            super(itemView);

            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthors);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDate =  (TextView) itemView.findViewById(R.id.tvPublishedDate);
            tvPublisher = (TextView) itemView.findViewById(R.id.tvPublisher);
            itemView.setOnClickListener(this);
        }

        public void bind (Book book){
            tvTitle.setText(book.title);
            String authors = "";
            int i=0;
            for (String author:book.authors) {
                authors+=author;
                i++;
                if(i<book.authors.length) {
                    authors+=", ";
                }
            }

            tvAuthor.setText(authors);
            tvDate.setText(book.publishedDate);
            tvPublisher.setText(book.publisher);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Book selectedBook = books.get(position);
            Intent intent = new Intent(itemView.getContext(), BookDetail.class);
            intent.putExtra("Book", selectedBook);
            itemView.getContext().startActivity(intent);
        }
    }
}

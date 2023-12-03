package ba.fsre.mojanovaaplikacija;

import android.os.Bundle;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ba.fsre.mojanovaaplikacija.adapter.MovieAdapter;
import ba.fsre.mojanovaaplikacija.fragments.AddMovieDialogFragment;
import ba.fsre.mojanovaaplikacija.model.Movie;

public class MovieActivity extends AppCompatActivity {

    FirebaseDatabase movieDatabase = FirebaseDatabase.getInstance();
    MovieAdapter movieAdapter;
    RecyclerView movieRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        this.movieRecyclerView = findViewById(R.id.movieListView);
        this.movieRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );
        FirebaseRecyclerOptions<Movie> options = new FirebaseRecyclerOptions.Builder<Movie>().setQuery(
                this.movieDatabase.getReference("movies"),
                Movie.class
        ).build();

        this.movieAdapter = new MovieAdapter(options);
        this.movieRecyclerView.setAdapter(this.movieAdapter);

        Button addMovieBtn = findViewById(R.id.addMovieBtn);
        addMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMovieDialogFragment fragment = new AddMovieDialogFragment();
                fragment.show(getSupportFragmentManager(), "addMovieDialog");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.movieAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.movieAdapter.stopListening();
    }
}
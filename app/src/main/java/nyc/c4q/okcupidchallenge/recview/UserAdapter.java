package nyc.c4q.okcupidchallenge.recview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nyc.c4q.okcupidchallenge.R;
import nyc.c4q.okcupidchallenge.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.setBackgroundColor(user.isLiked());
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView userImage;
        private TextView username;
        private TextView userLocation;
        private TextView userMatch;
        private User user;

        public UserViewHolder(View itemView) {
            super(itemView);
            userLocation = itemView.findViewById(R.id.user_location);
            username = itemView.findViewById(R.id.user_name);
            userImage = itemView.findViewById(R.id.user_img);
            userMatch = itemView.findViewById(R.id.user_match);

        }

        public void bind(User user) {
            this.user = user;
            userLocation.setText(user.getAgeLocationForView());
            userMatch.setText(user.getMatchForView());
            username.setText(user.getUserName());
            Picasso.get().load(user.getPhotos().getPhotoThumbnails().getMediumThumbnail()).fit().into(userImage);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (user.isLiked()) {
                setBackgroundColor(false);
                user.setLiked(false);
            } else {
                setBackgroundColor(true);
                user.setLiked(true);
            }
        }

        public void setBackgroundColor(boolean isLiked) {
            if (isLiked) {
                itemView.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.likedColor));
            } else {
                itemView.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.unlikedColor));
            }
        }
    }
}

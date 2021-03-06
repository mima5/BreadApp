package Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.bread.AddUser;
import com.example.android.bread.Models.User;
import com.example.android.bread.R;
import com.example.android.bread.ShowUseerDetails;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class userAdapter extends RecyclerView.Adapter<userAdapter.ViewHolder> {


    Context _ctx;
    RealmResults<User> userList;
    List<User> users ;

    Realm realm;

    public userAdapter(RealmResults<User> userList, Context ctx) {
        _ctx = ctx;
        this.userList = userList;
        this.realm = Realm.getDefaultInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        ViewHolder holder = new ViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final User item = userList.get(position);


        holder.tv_userName.setText(item.getName());
        holder.tv_userFlourAmount.setText(item.getFlourAmount() + "");
//            Toast.makeText(_ctx, "---- waw ----", Toast.LENGTH_SHORT).show();


        holder.rlout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(_ctx, ShowUseerDetails.class);
                intent.putExtra("id", item.getId());
                _ctx.startActivity(intent);


            }
        });


        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                realm.beginTransaction();
                userList.get(position).deleteFromRealm();
//                userList.deleteAllFromRealm();
                realm.commitTransaction();

                notifyDataSetChanged();
                notifyItemRemoved(position);

                notifyItemRangeChanged(position, userList.size());
            }
        });


        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(_ctx, AddUser.class);
                intent.putExtra("id", item.getId());
                _ctx.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return userList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_userName;
        private final TextView tv_userFlourAmount;
        private final ImageView img_delete;
        private final ImageView img_edit;

        private final RelativeLayout rlout;


        public ViewHolder(View itemView) {
            super(itemView);

            tv_userName = (TextView) itemView.findViewById(R.id.tv_userName);
            tv_userFlourAmount = (TextView) itemView.findViewById(R.id.tv_userFlourAmount);
            img_delete = (ImageView) itemView.findViewById(R.id.img_delete);
            img_edit = (ImageView) itemView.findViewById(R.id.img_edit);
            rlout = (RelativeLayout) itemView.findViewById(R.id.rlout);

//

        }
    }
    public void setFilter(List<User> newList)
    {

        users = new ArrayList<>();
        users.addAll(newList);
        notifyDataSetChanged();
    }

}

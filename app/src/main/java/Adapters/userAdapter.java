package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.bread.Models.User;
import com.example.android.bread.R;

import io.realm.Realm;
import io.realm.RealmResults;

public class userAdapter extends RecyclerView.Adapter<userAdapter.ViewHolder> {


        Context _ctx;
        RealmResults<User> userList;
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
//            Toast.makeText(_ctx, "---- waw ----", Toast.LENGTH_SHORT).show();


            holder.rlout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Intent intent = new Intent(_ctx, AddNoteActivity.class);
//                    intent.putExtra("id", item.getId());
//                    _ctx.startActivity(intent);


//                Intent i = new Intent(_ctx, EnterProductActivity.class);
//                i.putExtra("id", item.getPt_id());
//                // _ctx.startActivityForResult(i, 1);
//                ((Activity) _ctx).startActivityForResult(i, 1);
                }
            });


        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Note itemLabel = noteList.get(position);

                realm.beginTransaction();
                userList.get(position).deleteFromRealm();
//                userList.deleteAllFromRealm();
                realm.commitTransaction();

                notifyDataSetChanged();
                notifyItemRemoved(position);

                notifyItemRangeChanged(position,userList.size());
            }
        });

        }


        @Override
        public int getItemCount() {
            return userList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {

            private final TextView tv_userName;
            private final ImageView img_delete;
            private final ImageView img_edit;

            private final RelativeLayout rlout;


            public ViewHolder(View itemView) {
                super(itemView);

                tv_userName = (TextView) itemView.findViewById(R.id.tv_userName);
                img_delete = (ImageView) itemView.findViewById(R.id.img_delete);
                img_edit = (ImageView) itemView.findViewById(R.id.img_edit);
                rlout = (RelativeLayout) itemView.findViewById(R.id.rlout);

//

            }
        }


    }

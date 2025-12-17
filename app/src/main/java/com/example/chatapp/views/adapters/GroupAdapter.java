package com.example.chatapp.views.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.chatapp.R;
import com.example.chatapp.databinding.ItemCardBinding;
import com.example.chatapp.model.ChatGroup;
import com.example.chatapp.views.ChatActivity;
import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    private List<ChatGroup> groupArrayList;

    public GroupAdapter(List<ChatGroup> groupArrayList) {
        this.groupArrayList = groupArrayList;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Initializes the ViewHolder and Inflates the Item Layout
        ItemCardBinding binding = DataBindingUtil
                .inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.item_card,
                        parent,
                        false
                );
        return new GroupViewHolder(binding);
    }
//it acts as an intermediatery between the dta and the view
    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        // Binds data to an existing ViewHolder
        // Populates the Views in the ViewHolder with Data from the Dataset
        ChatGroup currentUser = groupArrayList.get(position);
        holder.itemCardBinding.setChatGroup(currentUser);
        holder.itemCardBinding.executePendingBindings(); // Ensure bindings are executed immediately
    }

    @Override
    public int getItemCount() {
        return groupArrayList.size();
    }

    // Method to update the group list and notify adapter
    public void updateGroupList(List<ChatGroup> newGroupList) {
        this.groupArrayList = newGroupList;
        notifyDataSetChanged();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        // Cache references to the individual views within an item layout
        // of a recyclerView list
        private ItemCardBinding itemCardBinding;

        public GroupViewHolder(ItemCardBinding itemCardBinding) {
            super(itemCardBinding.getRoot());
            this.itemCardBinding = itemCardBinding;

            itemCardBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    ChatGroup clickedChatGroup = groupArrayList.get(position);

                    Intent intent = new Intent(view.getContext(), ChatActivity.class);
                    intent.putExtra("GROUP_NAME", clickedChatGroup.getGroupName());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}

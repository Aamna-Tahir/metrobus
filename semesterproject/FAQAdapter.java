package com.example.semesterproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.ViewHolder> {
    private List<FaqItem> faqList;

    public FAQAdapter(List<FaqItem> faqList) {
        this.faqList = faqList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_faq_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FaqItem faq = faqList.get(position);
        holder.codenametxt.setText(faq.getQuestion());
        holder.versiontxt.setText(faq.getAnswer());
   //     holder.apileveltxt.setText(faq.getApilevel());
   //     holder.descriptiontxt.setText(faq.getDescription());

        boolean isExpandable = faqList.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return faqList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView codenametxt, versiontxt;// apileveltxt, descriptiontxt;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            codenametxt = itemView.findViewById(R.id.questionTextView);
            versiontxt = itemView.findViewById(R.id.version);
         //   apileveltxt = itemView.findViewById(R.id.api_level);
            //   descriptiontxt = itemView.findViewById(R.id.description);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FaqItem faq = faqList.get(getAdapterPosition());
                    faq.setExpandable(!faq.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}

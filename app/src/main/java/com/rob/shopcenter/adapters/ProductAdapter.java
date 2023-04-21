package com.rob.shopcenter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rob.shopcenter.R;
import com.rob.shopcenter.clases.Product;
import com.rob.shopcenter.interfaces.OnItemClickListener;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> mData; //Lista de elementos
    private LayoutInflater mInflater;
    private Context context;

    private OnItemClickListener mListener;

    public ProductAdapter(List<Product> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);//Selecciona una actividad para añadir contenido en un Layout
        this.context = context;
        this.mData = itemList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_items, null);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setItems(List<Product> items){
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageProduct;
        TextView title, description, numStock, price;
        Button buttonAdd;
        ViewHolder(View itemView){
            super(itemView);
            imageProduct = itemView.findViewById(R.id.card_image);
            title = itemView.findViewById(R.id.card_title_object);
            description = itemView.findViewById(R.id.card_description_object);
            numStock = itemView.findViewById(R.id.card_number_objects);
            buttonAdd = itemView.findViewById(R.id.card_button_add_product);
            price = itemView.findViewById(R.id.card_price_objects);
        }
        void bindData(final Product item){
            imageProduct.setImageResource(item.getUrl_photo());
            title.setText(item.getTitle());
            description.setText(item.getDescription());
            if(item.getNumStock() == 1){
                numStock.setText(item.getNumStock() +" "+ context.getResources().getString(R.string.unit) + " "+ context.getResources().getString(R.string.remaining));
            }else{
                numStock.setText(item.getNumStock() + " "+ context.getResources().getString(R.string.units) + " " + context.getResources().getString(R.string.remaining));
            }
            price.setText(item.getPrice() + "€");

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onItemClick(item, numStock);
                    }
                }
            });
        }
    }
}

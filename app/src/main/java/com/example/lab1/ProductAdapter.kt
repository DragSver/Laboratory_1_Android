package com.example.lab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.databinding.ProductBigItemBinding
import com.example.lab1.databinding.ProductSmallItemBinding


class ProductAdapter(
    private val listener: Listener,
    private val isCompact: Boolean
) : ListAdapter<Product, RecyclerView.ViewHolder>(ProductComparator()), View.OnClickListener  {

    override fun onClick(v: View) {
        val product = v.tag as Product
        listener.onClickProduct(product)
    }

    interface Listener {
        fun onClickProduct(product: Product)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            0
        } else {
            1
        }
    }

    class BigItemHolder(val binding: ProductBigItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) = with(binding){
            header.text = product.header
            subhead.text = product.subhead
        }
        companion object{
            fun create(parent: ViewGroup): BigItemHolder{
                return BigItemHolder(ProductBigItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    class SmallItemHolder(val binding: ProductSmallItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) = with(binding){
            header.text = product.header
            subhead.text = product.subhead
        }
        companion object{
            fun create(parent: ViewGroup): SmallItemHolder{
                return SmallItemHolder(ProductSmallItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    class ProductComparator : DiffUtil.ItemCallback<Product>(){
        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        if (isCompact) {
            if (viewType == 0) {
                val binding = ProductBigItemBinding.inflate(inflater, parent, false)

                binding.root.setOnClickListener(this)

//        return ItemHolder.create(parent)
                return BigItemHolder(binding)
            }
            else {
                val binding = ProductSmallItemBinding.inflate(inflater, parent, false)

                binding.root.setOnClickListener(this)

//        return ItemHolder.create(parent)
                return SmallItemHolder(binding)
            }
        }
        else {
            val binding = ProductBigItemBinding.inflate(inflater, parent, false)

            binding.root.setOnClickListener(this)

//        return ItemHolder.create(parent)
            return BigItemHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = getItem(position)



        if (isCompact){
            if (position == 0){
                val bHolder : BigItemHolder = holder as BigItemHolder

                with(bHolder.binding){
                    root.tag = product
                }

                bHolder.bind(getItem(position))
            } else {
                val sHolder : SmallItemHolder = holder as SmallItemHolder

                with(sHolder.binding){
                    root.tag = product
                }

                sHolder.bind(getItem(position))
            }
        } else {
            val bHolder : BigItemHolder = holder as BigItemHolder

            with(bHolder.binding){
                root.tag = product
            }

            bHolder.bind(getItem(position))
        }
    }
}
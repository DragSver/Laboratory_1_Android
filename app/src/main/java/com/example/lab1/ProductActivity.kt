package com.example.lab1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.databinding.ProductActivityBinding

class ProductActivity : AppCompatActivity(){
    lateinit var binding: ProductActivityBinding
    lateinit var adapter: ListAdapter<Product, RecyclerView.ViewHolder>
    lateinit var dbProduct: DBProduct
    var isCompact: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductActivityBinding.inflate(layoutInflater)
        initRCView()
        dbProduct = DBProduct()
        setContentView(binding.root)
        binding.topAppBar.setOnClickListener {
            isCompact = !isCompact
            initRCView()
            adapter.submitList(dbProduct.getData())
        }
        adapter.submitList(dbProduct.getData())
    }

    private fun initRCView() = with(binding)  {
        adapter = ProductAdapter(object : ProductAdapter.Listener {
            override fun onClickProduct(product: Product) {
                val intent = Intent(this@ProductActivity, TitleActivity::class.java)
                intent.putExtra(TitleActivity.ID, product.id.toString())
                startActivity(intent)
            }},
            isCompact)
        rcView.layoutManager = LinearLayoutManager(this@ProductActivity)
        rcView.adapter=adapter
    }
}
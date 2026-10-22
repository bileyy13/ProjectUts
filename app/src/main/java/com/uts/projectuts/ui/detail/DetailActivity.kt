package com.uts.projectuts.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.bumptech.glide.Glide
import com.uts.projectuts.databinding.ActivityDetailBinding
import com.uts.projectuts.model.Product

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val p = IntentCompat.getParcelableExtra(intent, "EXTRA_PRODUCT", Product::class.java)
        if (p == null) {
            finish()
            return
        }

        title = p.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tvTitle.text = p.title
        binding.tvDescription.text = p.description
        binding.tvCategory.text = "Kategori: ${p.category}"
        binding.tvPrice.text = "Harga: $${p.price}"
        binding.tvStock.text = "Stok: ${p.stock} (${p.availabilityStatus})"
        binding.tvRating.text = "Rating: ${p.rating}"
        binding.tvTags.text = "Tags: ${p.tags.joinToString(", ")}"
        binding.tvDimensions.text =
            "Dimensi: ${p.dimensions.width} x ${p.dimensions.height} x ${p.dimensions.depth}"

        // Gambar detail diambil dari kolom images
        Glide.with(this).load(p.images.firstOrNull()).into(binding.imgProduct)

        // Tampilkan 1 review teratas
        val first = p.reviews.firstOrNull()
        binding.tvReview.text = if (first != null) {
            "${first.reviewerName} (${first.rating}★)\n\"${first.comment}\""
        } else {
            "Belum ada review"
        }

        // Link lihat semua review (nilai tambah)
        binding.tvSeeAll.setOnClickListener {
            val text = p.reviews.joinToString("\n\n") {
                "${it.reviewerName} (${it.rating}★)\n${it.comment}"
            }
            AlertDialog.Builder(this)
                .setTitle("Semua Review")
                .setMessage(text)
                .setPositiveButton("Tutup", null)
                .show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
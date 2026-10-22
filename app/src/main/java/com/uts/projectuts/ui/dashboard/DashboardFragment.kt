package com.uts.projectuts.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.uts.projectuts.R
import com.uts.projectuts.adapter.ProductAdapter
import com.uts.projectuts.databinding.FragmentDashboardBinding
import com.uts.projectuts.retrofit.RetrofitClient
import com.uts.projectuts.ui.detail.DetailActivity
import kotlinx.coroutines.launch

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menghubungkan binding ke layout yang sudah di-inflate otomatis
        _binding = FragmentDashboardBinding.bind(view)

        binding.rvData.layoutManager = LinearLayoutManager(requireContext())

        // Tarik ke bawah untuk memuat ulang data
        binding.swipeRefresh.setOnRefreshListener { loadProducts() }

        loadProducts()
    }

    private fun loadProducts() {
        if (!binding.swipeRefresh.isRefreshing) {
            binding.progressBar.visibility = View.VISIBLE
        }

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val result = RetrofitClient.api.getProducts()

                _binding?.rvData?.adapter = ProductAdapter(result.products) { product ->
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra("EXTRA_PRODUCT", product)
                    startActivity(intent)
                }
            } catch (e: Exception) {
                if (isAdded) {
                    Toast.makeText(
                        requireContext(),
                        "Gagal memuat: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } finally {
                _binding?.progressBar?.visibility = View.GONE
                _binding?.swipeRefresh?.isRefreshing = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
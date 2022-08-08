package com.farhanrv.wisatasidoarjo.ui.detail

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.nfc.NfcAdapter.EXTRA_DATA
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.farhanrv.wisatasidoarjo.R
import com.farhanrv.wisatasidoarjo.data.DataResource
import com.farhanrv.wisatasidoarjo.data.local.WisataListEntity
import com.farhanrv.wisatasidoarjo.databinding.ActivityDetailBinding
import eightbitlab.com.blurview.RenderScriptBlur
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.concurrent.schedule

class DetailActivity : AppCompatActivity() {

    private lateinit var myDecor: View
    private lateinit var windowBackground: Drawable
    private lateinit var rootView: ViewGroup
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val radius = 8f // 0 to 25 means 0% to 100%

        myDecor = window.decorView

        // ViewGroup you want to start blur from. Choose root as close to BlurView in hierarchy as possible.
        rootView = myDecor.findViewById(android.R.id.content) as ViewGroup

        // Optional:
        // Set drawable to draw in the beginning of each blurred frame.
        // Can be used in case your layout has a lot of transparent space and your content
        // gets a too low alpha value after blur is applied.
        windowBackground = myDecor.background

        // using androidhiro.com
        binding.blurView.setupWith(rootView, RenderScriptBlur(this))
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(radius)

        binding.btnBackDetail.setOnClickListener {
            finish()
        }

        val intentData = intent.getParcelableExtra<WisataListEntity>(EXTRA_DATA)

        if (intentData != null) {
            tampilkanDetail(intentData)
        }
    }

    fun tampilkanDetail(intentData: WisataListEntity?) {
        intentData?.let { wisata ->
            binding.tvItemTitle.text = wisata.nama
            viewModel.getDetail(wisata.kodeWisata).observe(this) { detail ->
                detail?.let {
                    when (detail) {
                        is DataResource.Loading -> {
                            binding.loadingCover.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.VISIBLE
//                            binding.fab.visibility = View.GONE - change this to favorite/bookmark icon
                        }
                        is DataResource.Success -> {
                            val data = detail.data!![0]

                            // recyclerView Adapter
//                            val adapter = DetailAdapter()
//                            adapter.setData(listOf(data.gambar[0], data.gambar[1]))

//                            binding.recyclerView.setHasFixedSize(true)
//                            binding.recyclerView.adapter = adapter

                            with(binding) {
                                progressBar.visibility = View.GONE
                                loadingCover.visibility = View.GONE
//                                fab.visibility = View.VISIBLE // change this to favorite/bookmark icon
                                val loading = CircularProgressDrawable(this@DetailActivity)
                                loading.strokeWidth = 5f
                                loading.centerRadius = 30f
                                loading.start()
//                                Glide.with(this@DetailActivity).load(data.gambar[0])
//                                    .placeholder(loading).into(detailImgThumbnail) BACKUP
                                Glide.with(this@DetailActivity).load(wisata.thumbnail)
                                    .placeholder(loading).into(imgBackground)
                                tvLokasi.text = data.lokasi
//                                tvOperasional.text = data.jamOperasional
//                                tvBiaya.text = data.biaya

                                tvDescription.text = data.deskripsi
                                tvDescription.setOnClickListener {
                                    if (tvDescription.maxLines != 6) {
                                        val animation = ObjectAnimator.ofInt(
                                            tvDescription,
                                            "maxLines",
                                            6
                                        )
                                        animation.start()
                                    } else {
                                        val animation = ObjectAnimator.ofInt(
                                            tvDescription,
                                            "maxLines",
                                            100
                                        )
                                        animation.duration = 1000
                                        animation.start()
                                    }
                                }
                            }
                            // set favorite

                                var statusFavorite = wisata.isFavorite
                                setStatusFavorite(statusFavorite)
                                binding.btnBookmark.setOnClickListener {
                                    statusFavorite = !statusFavorite
                                    viewModel.setFavorite(intentData.kodeWisata, statusFavorite)
                                    setStatusFavorite(statusFavorite)
                                }

                            // end of set favorite

                            // explore now button

//                                btnLihatLokasi.setOnClickListener {
//                                    val gmmIntentUri: Uri = Uri.parse(data.googleMap)
//                                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
//                                    mapIntent.setPackage("com.google.android.apps.maps")
//                                    startActivity(mapIntent)
//                                }

                            // end of explore now button

                        }

                        is DataResource.Error -> {
                            with(binding) {
                                loadingCover.visibility = View.GONE
                                progressBar.visibility = View.GONE
                                layoutError.root.visibility = View.VISIBLE
                                layoutError.tvError.text =
                                    detail.message ?: "Periksa Koneksi Internet!"
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnBookmark.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.ic_bookmark)
            )
        } else {
            binding.btnBookmark.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.ic_bookmark_border)
            )
        }
    }

    fun hideOthers() {
        binding.btnBackDetail.animate()
            .alpha(0.0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    binding.btnBackDetail.visibility = View.GONE
                }

                override fun onAnimationCancel(animation: Animator?) {
                    super.onAnimationCancel(animation)
                    binding.btnBackDetail.alpha = 1f
                }
            })
        binding.rootCardViewDetail.animate()
            .alpha(0.0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    binding.rootCardViewDetail.visibility = View.GONE
                }
            })
    }

    fun showOthers() {
        if (binding.btnBackDetail.visibility == View.VISIBLE && binding.rootCardViewDetail.visibility == View.VISIBLE) {
            val handler = Handler()
            handler.postDelayed({
                //do something when let go
                // Prepare the View for the animation
                binding.btnBackDetail.visibility = View.VISIBLE;

                // Start the animation
                binding.btnBackDetail.animate()
                    .alpha(1.0f)
                    .setListener(null);

                // Prepare the View for the animation
                binding.rootCardViewDetail.visibility = View.VISIBLE;

                // Start the animation
                binding.rootCardViewDetail.animate()
                    .alpha(1.0f)
                    .setListener(null);
            }, 300)
        } else {

            //do something when let go
            // Prepare the View for the animation
            binding.btnBackDetail.visibility = View.VISIBLE;

            // Start the animation
            binding.btnBackDetail.animate()
                .alpha(1.0f)
                .setListener(null);

            // Prepare the View for the animation
            binding.rootCardViewDetail.visibility = View.VISIBLE;

            // Start the animation
            binding.rootCardViewDetail.animate()
                .alpha(1.0f)
                .setListener(null);
        }

    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
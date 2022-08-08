package com.farhanrv.wisatasidoarjo.ui.main.home

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.farhanrv.wisatasidoarjo.R
import com.farhanrv.wisatasidoarjo.data.local.WisataListEntity
import com.farhanrv.wisatasidoarjo.databinding.ActivityMainBinding
import com.farhanrv.wisatasidoarjo.ui.detail.DetailActivity
import com.farhanrv.wisatasidoarjo.ui.main.settings.SettingsCallback

class MainActivity : AppCompatActivity(), HomeCallback {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.hide()

        // can't use this : bottomAppBar and navigationView is different
        // UPDATE : IT WORKS !!!
        val navView = binding.bottomNavigationView
        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favorite, R.id.navigation_notification, R.id.navigation_settings
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.bottomNavigationView.background = null
    }

    override fun hideSearch() { // we don't use code below because we don't use fab
//        binding.bottomNavigationView.menu.getItem(2).isVisible = false
//        binding.bottomAppBar.fabCradleRoundedCornerRadius = 0F
//        binding.bottomAppBar.fabCradleMargin = 0F
//        binding.bottomAppBar.cradleVerticalOffset = 0F
//        binding.bottomNavigationView.visibility = View.GONE
        binding.bottomAppBar.performHide()
    }

    override fun showSearch() {
//        binding.bottomNavigationView.menu.getItem(2).isVisible = true
//        binding.bottomAppBar.fabCradleRoundedCornerRadius = 10f
//        binding.bottomAppBar.fabCradleMargin = 10f
//        binding.bottomAppBar.cradleVerticalOffset = 10f
//        binding.bottomNavigationView.visibility = View.VISIBLE
        binding.bottomAppBar.performShow()
    }

    override fun toDetailActivity(wisata: WisataListEntity, imageView: ImageView) {

        // get the common element for the transition in this activity : imageView

        // create the transition animation - the images in the layouts
        // of both activities are defined with android:transitionName="robot"

        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, wisata)
        val options = ActivityOptions.makeSceneTransitionAnimation(this@MainActivity, imageView, "detail_image")
        startActivity(intent, options.toBundle())
    }
}
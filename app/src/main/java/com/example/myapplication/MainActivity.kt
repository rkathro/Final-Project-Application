package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.setup.AccountSetupFragment
import com.example.myapplication.ui.setup.UserDataViewModel

import android.graphics.Bitmap
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: UserDataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_account, R.id.navigation_history, R.id.navigation_settings, R.id.navigation_account_setup))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

        //Resets the app to think every login is the users First login. Use for Demonstration purposes
        //Use wisely its a pain in the ass after I implemented all the checks
        resetSharedPref()
        if (isFirstLogin()) {
            navigateToAccountSetup()
        }
    }

    private fun isFirstLogin(): Boolean {
        return sharedPreferences.getBoolean("first_login", true)
    }
    private fun navigateToAccountSetup() {
        navController.navigate(R.id.navigation_account_setup)
    }
    private fun resetSharedPref() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("first_login", true)
        editor.apply()
    }

    public fun classifyImage(context: Context, image: Bitmap) {
        val model = SiameseModelOptimized.newInstance(context)
        val imageSize = 128

        // Reusable function to process image and load into TensorBuffer
        fun loadImageToTensorBuffer(image: Bitmap): TensorBuffer {
            val byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
            byteBuffer.order(ByteOrder.nativeOrder())

            val intValues = IntArray(imageSize * imageSize)
            image.getPixels(intValues, 0, image.width, 0, 0, image.width, image.height)

            for (i in 0 until imageSize) {
                for (j in 0 until imageSize) {
                    val pixel = intValues[i * imageSize + j]
                    byteBuffer.putFloat(((pixel shr 16) and 0xFF) * (1f / 1))
                    byteBuffer.putFloat(((pixel shr 8) and 0xFF) * (1f / 1))
                    byteBuffer.putFloat((pixel and 0xFF) * (1f / 1))
                }
            }

            val inputFeature =
                TensorBuffer.createFixedSize(intArrayOf(1, 128, 128, 3), DataType.FLOAT32)
            inputFeature.loadBuffer(byteBuffer)
            return inputFeature
        }

        val inputFeature0 = loadImageToTensorBuffer(image)
        val inputFeature1 = loadImageToTensorBuffer(image)
        val inputFeature2 = loadImageToTensorBuffer(image)

        // Runs model inference and gets result.
        val outputs = model.process(inputFeature0, inputFeature1, inputFeature2)
        val outputFeatureAN = outputs.getOutputFeature0AsTensorBuffer().floatArray
        val outputFeatureAP = outputs.getOutputFeature1AsTensorBuffer().floatArray

        // Calculate cosine similarity between AN and AP
        val similarity = cosineSimilarity(outputFeatureAN, outputFeatureAP)

        // Releases model resources if no longer used.
        model.close()

        // Check if similarity is 98% or higher
        if (similarity >= 0.98) {
            println("User successfully authenticated")
        }
    }

    // Function to calculate cosine similarity between two vectors
    fun cosineSimilarity(vectorA: FloatArray, vectorB: FloatArray): Float {
        require(vectorA.size == vectorB.size) { "Vector dimensions must be equal" }

        var dotProduct = 0f
        var normA = 0f
        var normB = 0f

        for (i in vectorA.indices) {
            dotProduct += vectorA[i] * vectorB[i]
            normA += vectorA[i].pow(2)
            normB += vectorB[i].pow(2)
        }

        normA = sqrt(normA)
        normB = sqrt(normB)

        return dotProduct / (normA * normB)
    }


}
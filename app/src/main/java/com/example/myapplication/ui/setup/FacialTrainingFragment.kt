package com.example.myapplication.ui.setup

import android.Manifest
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.media.MediaRecorder
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.VideoCapture

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityTrainingBinding
import com.google.common.util.concurrent.ListenableFuture
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FacialTrainingFragment() : Fragment() {

    private lateinit var binding: ActivityTrainingBinding
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var cameraExecutor: ExecutorService
    private var imageCapture: ImageCapture? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ActivityTrainingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the cameraExecutor
        cameraExecutor = Executors.newSingleThreadExecutor()

        // Initialize cameraProviderFuture and set up the camera
        cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener(Runnable {
            // This code will be executed when the cameraProvider is available
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            bindCameraUseCases(cameraProvider)
        }, ContextCompat.getMainExecutor(requireContext()))

        binding.btnCapture.setOnClickListener {
            takePicture()
        }
    }
    private fun bindCameraUseCases(cameraProvider: ProcessCameraProvider) {
        // Define the use cases
        val preview = Preview.Builder().build().also {
            it.setSurfaceProvider(binding.previewView.surfaceProvider)
        }

        imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .build()

        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        // Unbind use cases before rebinding
        cameraProvider.unbindAll()

        // Bind use cases to camera
        try {
            cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
        } catch (exc: Exception) {
            Toast.makeText(requireContext(), "Use case binding failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun takePicture() {
        val outputDir = requireContext().externalMediaDirs.firstOrNull() ?: return
        val photoFile = File(outputDir, SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US)
            .format(System.currentTimeMillis()) + ".jpg")

        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture?.takePicture(
            outputFileOptions,
            cameraExecutor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    // Hide the camera preview
                    binding.previewView.visibility = View.GONE

                    // Get the Uri of the saved file
                    val savedUri = outputFileResults.savedUri ?: Uri.fromFile(photoFile)

                    // Show the photo in an ImageView
                    showCapturedImage(savedUri)
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exception.message}", exception)
                }
            }
        )
    }
    private fun showCapturedImage(uri: Uri) {
        // Use Glide or a similar library to load the image into the ImageView
        Glide.with(this)
            .load(uri)
            .into(binding.photoView)

        // Make the ImageView visible
        binding.photoView.visibility = View.VISIBLE
    }
    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
        // Update the UI on the main thread
        Handler(Looper.getMainLooper()).post {
            // Hide the camera preview
            binding.previewView.visibility = View.GONE

            // Get the Uri of the saved file
            val savedUri = outputFileResults.savedUri ?: Uri.fromFile(photoFile)

            // Show the photo in an ImageView
            showCapturedImage(savedUri)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Shut down the cameraExecutor
        cameraExecutor.shutdown()
    }
}

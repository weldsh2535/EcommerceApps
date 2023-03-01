package com.weldsh2535.ecommerceapp.ui.post

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.weldsh2535.ecommerceapp.Model.Product
import com.weldsh2535.ecommerceapp.R
import com.weldsh2535.ecommerceapp.ViewModel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_post.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class PostFragment : Fragment() {
    private val IMAGE_CAPTURE_CODE = 1001
    private var imageUri: Uri? = null
    val CAMERA_PERMISSION_CODE = 1000;

    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    val item = arrayOf("Agriculture & Food","Animal & Pets","Babies & Kids","Commercial Equipment & Tools","Electronics","Fashion","Health & Beauty","Home , Furniture & Appliances","Jobs","Mobile Phones & Tablets","Property","Repair & Construction","Seeking work CVs","Services","Sports,Arts & Outdoors","Vehicles")
    val itemSelected = arrayOf("Agriculture & Food","Animal & Pets","Babies & Kids","Commercial Equipment & Tools","Electronics","Fashion","Health & Beauty","Home , Furniture & Appliances","Jobs","Mobile Phones & Tablets","Property","Repair & Construction","Seeking work CVs","Services","Sports,Arts & Outdoors","Vehicles")

    lateinit var adapterItem: ArrayAdapter<String>
    lateinit var adapterSelectedItem: ArrayAdapter<String>
    private  val productViewModel:ProductViewModel  by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        btn_choose_image.setOnClickListener { launchGallery() }
        btn_upload_image.setOnClickListener { uploadImage() }

        adapterItem = ArrayAdapter<String>(requireContext(),R.layout.list_item,item)
        auto_complete_txt.setAdapter(adapterItem)
        auto_complete_txt.setOnItemClickListener { parent, view, position, id ->

        }
        //auto complete select type
        adapterSelectedItem = ArrayAdapter<String>(requireContext(),R.layout.list_item,itemSelected)
        auto_complete_select_type.setAdapter(adapterSelectedItem)

       /* button_take_picture.setOnClickListener {
            // Request permission
            val permissionGranted = requestCameraPermission()
            if (permissionGranted) {
                // Open the camera interface
                openCameraInterface()
            }
        }*/

        btn_add_product.setOnClickListener {
            createProduct()
        }
    }

    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }
    private fun uploadImage(){
        if(filePath != null){
            val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)

            val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    addUploadRecordToDb(downloadUri.toString())
                } else {
                    // Handle failures
                    Toast.makeText(context,"Error ",Toast.LENGTH_SHORT).show()
                }
            }?.addOnFailureListener{
                Toast.makeText(context,"Error ${it.localizedMessage} ",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context, "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addUploadRecordToDb(uri: String){
        val db = FirebaseFirestore.getInstance()

        val data = HashMap<String, Any>()
        data["imageUrl"] = uri

        db.collection("posts")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(context, "Saved to DB", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Error saving to DB", Toast.LENGTH_LONG).show()
            }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }
            filePath = data.data
            try {
                // on below line getting bitmap for image from file uri.
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(context?.getContentResolver(), filePath);
                // on below line setting bitmap for our image view.
               // image_preview?.setImageBitmap(bitmap)
               image_preview?.setImageURI(filePath)
                //uploadImage.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    private fun createProduct() {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        val product = Product(
            getId().toString(),
            "",
            tvTitle.text.toString(),
            "",
            filePath.toString(),
            "",
            auto_complete_select_type.text.toString() ,
            tvPirce.text.toString().toFloat(),
            tvDiscount.text.toString().toFloat(),
            tvQuantity.text.toString().toInt(),
            "",
            currentDate,
            currentDate,
            currentDate,
            currentDate,
            currentDate,
            tvContent.text.toString()
        )
        productViewModel.create(product)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)

    }

    private fun requestCameraPermission(): Boolean {
        var permissionGranted = false
// If system os is Marshmallow or Above, we need to request runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val cameraPermissionNotGranted = checkSelfPermission(
                activity as Context,
                Manifest.permission.CAMERA
            ) == PermissionChecker.PERMISSION_DENIED
            if (cameraPermissionNotGranted) {
                val permission = arrayOf(Manifest.permission.CAMERA)
                // Display permission dialog
                requestPermissions(permission, CAMERA_PERMISSION_CODE)
            } else {
                // Permission already granted
                permissionGranted = true
            }
        } else {
            // Android version earlier than M -&gt; no need to request permission
            permissionGranted = true
        }
        return permissionGranted
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode === CAMERA_PERMISSION_CODE) {
            if (grantResults.size === 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted
                openCameraInterface()
            } else {
                // Permission was denied
                showAlert("Camera permission was denied. Unable to take a picture.")
            }
        }
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(activity as Context)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.ok_button_title, null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun openCameraInterface() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, R.string.take_picture)
        values.put(MediaStore.Images.Media.DESCRIPTION, R.string.take_picture_description)
        imageUri =
            activity?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
         // Create camera intent
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        // Launch intent
        startActivityForResult(intent, IMAGE_CAPTURE_CODE)
    }

   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Callback from camera intent
        if (resultCode == Activity.RESULT_OK) {
            // Set image captured to image view
            imageview_picture?.setImageURI(imageUri)
        } else {
            // Failed to take picture
            showAlert("Failed to take camera picture")
        }
    }*/
}
package com.example.camerakt

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1   // 카메라 사진 촬영 요청 코드
    lateinit var curPhotoPath: String   // 문자열 형태의 사진 경로(path) 값 (초기값 null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setPermission() // 권한 체크하는 메서드

        val btn_camera = findViewById<Button>(R.id.btn_camera)
        btn_camera.setOnClickListener {
            takeCapture()   // 기본 카메라 앱 실행
        }
    }

    /**
     * 카메라 촬영
     */
    private fun takeCapture() {

        // 기본 카메라 앱 실행
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager).also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        applicationContext,
                        "com.example.camerakt.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    /**
     * 이미지 파일 생성
     */
    private fun createImageFile(): File? {

        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}_", ".jpg", storageDir).apply {  curPhotoPath = absolutePath }
    }

    /**
     * Ted Permission Config
     */
    private fun setPermission() {
        val permission = object : PermissionListener {
            override fun onPermissionGranted() {    // 설정해놓은 위험권한들이 허용 됐을 경우
                Toast.makeText(this@MainActivity, "권한이 허용 됐습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {  // 설정해놓은 위험권한들 중 거부를 한 경우
                Toast.makeText(this@MainActivity, "권한이 거부 됐습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        TedPermission.with(this)
            .setPermissionListener(permission)
            .setRationaleMessage("카메라 캠을 사용하시려면 권한을 허용해주세요.")
            .setDeniedMessage("권한을 거부하셨습니다. [앱 설정] -> [권한] 항목에서 허용해주세요.")
            .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .check()
    }

    // startActivityForResult를 통해서 기본 카메라 앱으로부터 받아온 사진 결과 값
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // 이미지를 성공적으로 가져 왔다면
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val bitmap: Bitmap
            val file = File(curPhotoPath)
            val iv_profile = findViewById<ImageView>(R.id.iv_profile)
            if (Build.VERSION.SDK_INT < 28) {   // 안드로이드 9.0(Pie) 버전보다 낮을 경우
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, Uri.fromFile(file))
                iv_profile.setImageBitmap(bitmap)
            } else {    // 안드로이드 9.0(Pie) 버전보다 높을 경우
                val decode = ImageDecoder.createSource(
                    this.contentResolver,
                    Uri.fromFile(file)
                )
                bitmap = ImageDecoder.decodeBitmap(decode)
                iv_profile.setImageBitmap(bitmap)
            }
            savePhoto(bitmap)
        }

    }

    /**
     * 갤러리에 저장
     */
    private fun savePhoto(bitmap: Bitmap) {
        val folderPath = Environment.getExternalStorageDirectory().absolutePath + "/Pictures/"  // 사진폴더로 저장하기 위한 경로 설정
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val fileName = "${timestamp}.jpeg"
        val folder = File(folderPath)
        if(!folder.isDirectory) {   // 현재 해당 경로에 폴더가 존재하지 않는다면
            folder.mkdirs() // make directory
        }

        // 저장처리
        val out = FileOutputStream(folderPath + fileName)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        Toast.makeText(this, "사진이 앨범에 저장됐습니다.", Toast.LENGTH_SHORT).show()
    }
}
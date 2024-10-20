package com.simple.utils


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build.*
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Base64
import androidx.annotation.RequiresApi
import org.json.JSONObject
import java.io.FileInputStream
import java.io.InputStream
import java.security.DigestInputStream
import java.security.MessageDigest
import java.util.*

class DeviceInfoHelper {

    @Throws(Throwable::class)
    fun getDigest(`in`: InputStream, algorithm: String?): ByteArray {
        val md = MessageDigest.getInstance(algorithm)
        try {
            val dis = DigestInputStream(`in`, md)
            val buffer = ByteArray(2048)
            while (dis.read(buffer) != -1) {
            }
            dis.close()
        } finally {
            `in`.close()
        }
        return md.digest()
    }

    fun getApkFileDigest(): String {
        val apkPath = context.packageCodePath
        try {
            val hashed: ByteArray =
                getDigest(FileInputStream(apkPath), "SHA-256")
            return Base64.encodeToString(hashed, Base64.DEFAULT)
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }
        return ""
    }

    @RequiresApi(VERSION_CODES.O)
    fun Generate_Signature(): String {
        val jsonData = JSONObject()
        jsonData.put("model", model)
        jsonData.put("hardware", hardware)
        jsonData.put("board", board)
        jsonData.put("version", version)
        jsonData.put("apiLevel", apiLevel)
        jsonData.put("IMEI", getIMEIDeviceId())
        jsonData.put("SIG", getApkFileDigest())
        return java.util.Base64.getEncoder().encodeToString(jsonData.toString().toByteArray())
    }

    val model = deviceModel

    val hardware: String? = HARDWARE

    val board: String? = BOARD

    val version: String? = VERSION.RELEASE

    val apiLevel = VERSION.SDK_INT

    private val deviceModel
        @SuppressLint("DefaultLocale")
        get() = capitalize(
            if (MODEL.lowercase(Locale.getDefault())
                    .startsWith(MANUFACTURER.lowercase(Locale.getDefault()))
            ) {
                MODEL
            } else {
                "$MANUFACTURER $MODEL"
            }
        )
    private fun capitalize(str: String) = str.apply {
        if (isNotEmpty()) {
            first().run { if (isLowerCase()) uppercaseChar() }
        }
    }


    lateinit var context: Context

    fun ProvideContext(_context: Context) {
        context = _context
    }


    fun getIMEIDeviceId(): String? {
        val deviceId: String
        deviceId = if (VERSION.SDK_INT >= VERSION_CODES.Q) {
            Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        } else {
            val mTelephony = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return ""
            }
            if (mTelephony.deviceId != null) {
                if (VERSION.SDK_INT >= VERSION_CODES.O) {
                    mTelephony.imei
                } else {
                    mTelephony.deviceId
                }
            } else {
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            }
        }
        return deviceId
    }
}
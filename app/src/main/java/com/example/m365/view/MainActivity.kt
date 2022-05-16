package com.example.m365.view

import android.Manifest
import android.app.DownloadManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.text.InputFilter
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.m365.R
import com.example.m365.model.Constants
import com.example.m365.model.FloatFilter
import com.example.m365.model.IntFilter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity() {

    private var switches = emptyArray<Switch>()
    private var editTexts = emptyArray<EditText>()
    private var helperTexts = emptyArray<TextView>()
    private var predFirmButtons = emptyArray<Button>()

    /**
     * Configura los listeners necesarios a los componentes
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fill component arrays
        getSwitches()
        getEditTexts()
        getHelperTexts()
        getPredFirmButtons()

        // Set filters to editTexts
        setFilters()

        // Set listeners to predefined firmwares buttons
        for (i in predFirmButtons.indices)
            predFirmButtons[i].setOnClickListener { setFirmwareConfig(Constants.PRED_FIRMS[i]) }

        //  Set listener to help mode button
        toggleHelp.setOnClickListener { setHelperTexts(toggleHelp.isChecked) }

        // Set listeners to switches
        for (i in editTexts.indices) switches[i].setOnCheckedChangeListener { _, isChecked ->
            editTexts[i].isEnabled = isChecked
        }

        // Set listener to share button
        buttonShare.setOnClickListener { setClipboard(makeUrl()) }

        // Set listener to download button
        buttonDownload.setOnClickListener {
            if (checkPermission()) startDownload(
                makeUrl(),
                getFileName()
            )
        }

        buttonDowngrade.setOnClickListener { openApplication(Constants.DOWNG_PACKAGE) }
    }


    /**
     * Abre una aplicación
     * @param packageName String con el nombre del paquete de la app a abrir
     */
    private fun openApplication(packageName: String) {
        val launchIntent = packageManager.getLaunchIntentForPackage(packageName)
        launchIntent?.let { startActivity(it) }
    }

    /**
     * Copia un String al portapapeles
     * @param content String con el contenido a copiar
     */
    private fun setClipboard(content: String) {
        if (content.isNotEmpty()) {
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Content copied", content)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, getString(R.string.clipboard_success), Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Obtiene los botones de los firmwares predefinidos
     */
    private fun getPredFirmButtons() {
        predFirmButtons = arrayOf(
            buttonOriginal,
            buttonDyoc,
            buttonBotox,
            buttonChuru,
            buttonRoller,
            buttonCfw,
            buttonCfw2
        )
    }

    /**
     * Obtiene los elementos textView de ayuda
     */
    private fun getHelperTexts() {
        helperTexts = arrayOf(
            textView,
            textView2,
            textView3,
            textView4,
            textView5,
            textView6,
            textView7,
            textView8,
            textView9,
            textView10,
            textView11,
            textView12,
            textView13,
            textView14,
            textView15,
            textView16,
            textViewOrange,
            textViewBlue,
            textViewRed
        )
    }

    /**
     * Obtiene los editTexts
     */
    private fun getEditTexts() {
        editTexts = arrayOf(
            etKersMinSpeed,
            etMaxNormal,
            etMaxEco,
            etStartSpeed,
            etPowerConstant,
            etCruiseDelay,
            etBatteryVoltage,
            etWheelMulti
        )
    }

    /**
     * Obtiene los switches
     */
    private fun getSwitches() {
        switches = arrayOf(
            sKersMinSpeed,
            sMaxNormal,
            sMaxEco,
            sStartSpeed,
            sPowerConstant,
            sCruiseDelay,
            sBatteryVoltage,
            sWheelMulti,
            sCruiseNoBeep,
            sInstantMode,
            sBootEco,
            sRussianThrottle,
            sRemSpeedLimit,
            sRemChargingMode,
            sChangeEscBms
        )
    }

    /**
     * Comprueba si la app tiene permisos de almacenamiento y, de no tenerlos, los solicita
     * @return true si tiene permisos; false de lo contrario
     */
    private fun checkPermission(): Boolean {
        // Toast.makeText(this, tmpurl, Toast.LENGTH_SHORT).show()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_DENIED
            ) {
                // permission denied
                // show popup for permission
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    Constants.STORAGE_PERMISSION_CODE
                )
            } else {
                // already granted
                return true
            }
        } else {
            //system os is less than M
            return true
        }
        return false
    }

    /**
     * Muestra u oculta los textos de ayuda
     * @param toggle true para habilitar, false para deshabilitar
     */
    private fun setHelperTexts(toggle: Boolean) {
        for (helperText in helperTexts) helperText.isVisible = toggle
    }

    /**
     * Establece los filtros necesarios para los editTexts
     */
    private fun setFilters() {
        val intFilter100 = IntFilter(0, 100)
        val floatFilter100 = FloatFilter(0F, 100F, 1)

        etKersMinSpeed.filters = arrayOf<InputFilter>(floatFilter100)
        etStartSpeed.filters = arrayOf<InputFilter>(floatFilter100)
        etCruiseDelay.filters = arrayOf<InputFilter>(
            FloatFilter(
                0F,
                20F,
                1
            )
        )
        etBatteryVoltage.filters = arrayOf<InputFilter>(
            FloatFilter(
                0F,
                63F,
                2
            )
        )
        etMaxNormal.filters = arrayOf<InputFilter>(intFilter100)
        etMaxEco.filters = arrayOf<InputFilter>(intFilter100)
        etPowerConstant.filters = arrayOf<InputFilter>(
            IntFilter(0, 65535)
        )
    }

    /**
     * Establece la configuración de los switches y editTexts, según el firmware predefinido seleccionado
     * @param firmName String con el nombre del firmware a configurar
     */
    private fun setFirmwareConfig(firmConstants: Array<Any>) {
        var i = 0

        // Configurar versión del firmware
        spinnerVersion.setSelection(firmConstants[i++] as Int)

        // Configurar switches
        for (switch in switches) switch.isChecked = firmConstants[i++] as Boolean

        // Configurar editTexts
        for (editText in editTexts) editText.setText(firmConstants[i++] as String)
    }


    /**
     * Construye la url de descarga, según los parámetros indicados por el usuario
     * @return String con la url de descarga del firmware o
     * un String vacío si algún parámetro introducido por el usuario falla
     */
    private fun makeUrl(): String {
        var tmpUrl = Constants.BASE_URL + spinnerVersion.selectedItem.toString().replace(".", "")

        if (sKersMinSpeed.isChecked) tmpUrl += "&kers_min_speed=" + etKersMinSpeed.text
        if (sMaxNormal.isChecked) tmpUrl += "&normal_max_speed=" + etMaxNormal.text
        if (sMaxEco.isChecked) tmpUrl += "&eco_max_speed=" + etMaxEco.text
        if (sStartSpeed.isChecked) tmpUrl += "&motor_start_speed=" + etStartSpeed.text
        if (sPowerConstant.isChecked) {
            if (Integer.parseInt(etPowerConstant.text.toString()) in 20000..65535)
                tmpUrl += "&motor_power_constant=" + etPowerConstant.text
            else {
                showToast("Por favor, ingrese un valor de constante entre 20000 y 65535")
                return ""
            }
        }
        if (sCruiseDelay.isChecked) tmpUrl += "&cruise_control_delay=" + etCruiseDelay.text
        if (sCruiseNoBeep.isChecked) tmpUrl += "&cruise_control_nobeep=on"
        if (sInstantMode.isChecked) tmpUrl += "&instant_eco_switch=on"
        if (sBootEco.isChecked) tmpUrl += "&boot_with_eco=on"
        if (sRussianThrottle.isChecked) tmpUrl += "&russian_throttle=on"
//        if (sBatteryVoltage.isChecked) tmpUrl += "&voltage_limit=" + etBatteryVoltage.text
        if (sBatteryVoltage.isChecked) {
            val batteryVoltage = etBatteryVoltage.text.toString().toFloat()
            println(batteryVoltage)
            if (batteryVoltage in 43.1f..63f)
                tmpUrl += "&voltage_limit=" + etBatteryVoltage.text
            else {
                showToast("Por favor, ingrese un valor de límite de voltaje entre 43.1 y 63")
                return ""
            }
        }
        if (sRemSpeedLimit.isChecked) tmpUrl += "&remove_hard_speed_limit=on"
        if (sRemChargingMode.isChecked) tmpUrl += "&remove_charging_mode=on"
        if (sChangeEscBms.isChecked) tmpUrl += "&bms_uart_76800=on"
        if (sWheelMulti.isChecked) {
            if (Integer.parseInt(etWheelMulti.text.toString()) in 200..500)
                tmpUrl += "&wheel_speed_const=" + etWheelMulti.text
            else {
                showToast("Por favor, ingrese un valor de multiplicador de velocidad de entre 200 y 500")
                return ""
            }
        }

        return tmpUrl
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /**
     * Inicia la descarga del firmware
     * @param downloadUrl String con el URL de descarga
     * @param fileName String con el nombre con el que desea guardar el archivo
     * o un String vacío para dejar que la función le asigne un nombre automáticamente
     */
    private fun startDownload(downloadUrl: String, fileName: String) {
        var fn = fileName
        if (downloadUrl.isEmpty()) return

        if (fn.isEmpty())
            fn = "DRV" +
                    spinnerVersion.selectedItem.toString().replace(".", "") +
                    "-" + System.currentTimeMillis() / 1000 + ".zip"

        // Download request
        val request = DownloadManager.Request(Uri.parse(downloadUrl))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle(fn)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fn)

        // Get download service and enqueue file
        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }

    /**
     * Obtiene el nombre con el que el usuario desea guardar el archivo
     * @return String con el nombre del fichero
     */
    private fun getFileName(): String {
        val fileName = etFileName.text.toString().trim()
        if (fileName.isEmpty()) return ""
        return "$fileName.zip"
    }

    /**
     * Permission request results
     * @param requestCode Int
     * @param permissions Array<out String>
     * @param grantResults IntArray
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            Constants.STORAGE_PERMISSION_CODE -> {
                if (!(grantResults.isNotEmpty() && grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED)
                )
                    Toast.makeText(
                        this,
                        getString(R.string.permission_denied),
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }

}

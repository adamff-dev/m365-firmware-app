package com.example.m365.model

object Constants {
    const val STORAGE_PERMISSION_CODE = 1000
    const val BASE_URL = "https://m365.botox.bz/cfw?version=DRV"
    const val DOWNG_PACKAGE = "com.m365downgrade"

    val FIRM_ORIGINAL = arrayOf(
        2,      // firmware version index
        false,  // kers
        false,  // vel. máx normal
        false,  // vel. máx ECO
        false,  // vel. iniciar motor
        false,  // retardo ctrl. crucero
        false,  // multi. vel. rueda
        false,  // power constant
        false,  // límite batería
        false,  // Desactivar BEEP
        false,  // cambio inst. ECO
        false,  // ECO startup
        false,  // Russian
        false,  // Eliminar límite vel.
        false,  // Eliminar modo carga
        false,  // ESC - BMS
        "6",    // kers
        "28",   // vel. máx normal
        "22",   // vel. máx ECO
        "5",    // vel. iniciar motor
        "51575",// power constant
        "5",    // retraso ctrl. crucero
        "43.01",// límite batería
        "345"   // multi. vel. rueda
    )

    val FIRM_DYOC = arrayOf(
        2,
        false,
        true,
        true,
        true,
        true,
        false,
        true,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        "6",   // kers
        "31",   // vel. máx normal
        "20",   // vel. máx ECO
        "2",    // vel. iniciar motor
        "40165",// power constant
        "5",    // retraso ctrl. crucero
        "52",    // límite batería
        "345"  // multi. vel. rueda
    )

    val FIRM_BOTOX = arrayOf(
        2,      // firmware version index
        true,   // kers
        true,   // vel. máx normal
        true,   // vel. máx ECO
        true,   // vel. iniciar motor
        true,   // power constant
        false,  // retardo ctrl. crucero
        true,  // límite batería
        false,  // multi. vel. rueda
        false,  // Desactivar BEEP
        true,   // cambio inst. ECO
        false,  // ECO startup
        false,  // Russian
        true,  // Eliminar límite vel.
        false,  // Eliminar modo carga
        false,  // ESC - BMS
        "40",   // kers
        "30",   // vel. máx normal
        "26",   // vel. máx ECO
        "3",    // vel. iniciar motor
        "38000",// power constant
        "5",    // retraso ctrl. crucero
        "52",    // límite batería
        "345"  // multi. vel. rueda
    )

    val FIRM_CHURU = arrayOf(
        1,      // firmware version index
        true,   // kers
        true,   // vel. máx normal
        true,   // vel. máx ECO
        true,   // vel. iniciar motor
        true,   // power constant
        false,  // retardo ctrl. crucero
        false,  // límite batería
        false,  // multi. vel. rueda
        false,  // Desactivar BEEP
        true,   // cambio inst. ECO
        false,  // ECO startup
        false,  // Russian
        false,  // Eliminar límite vel.
        false,  // Eliminar modo carga
        false,  // ESC - BMS
        "35",   // kers
        "35",   // vel. máx normal
        "25",   // vel. máx ECO
        "1",    // vel. iniciar motor
        "36025",// power constant
        "5",    // retraso ctrl. crucero
        "43.01", // límite batería
        "345"  // multi. vel. rueda
    )

    val FIRM_ROLLER = arrayOf(
        2,      // firmware version index
        true,   // kers
        true,   // vel. máx normal
        true,   // vel. máx ECO
        true,   // vel. iniciar motor
        true,   // power constant
        false,  // retardo ctrl. crucero
        false,  // límite batería
        false,  // multi. vel. rueda
        false,  // Desactivar BEEP
        true,   // cambio inst. ECO
        true,   // ECO startup
        false,  // Russian
        true,   // Eliminar límite vel.
        false,   // Eliminar modo carga
        false,  // ESC - BMS
        "40",   // kers
        "30",   // vel. máx normal
        "19",   // vel. máx ECO
        "3",    // vel. iniciar motor
        "33000",// power constant
        "5",    // retraso ctrl. crucero
        "43.01",// límite batería
        "345"   // multi. vel. rueda
    )

    val FIRM_CFW = arrayOf(
        2,      // firmware version index
        true,   // kers
        true,   // vel. máx normal
        true,   // vel. máx ECO
        true,   // vel. iniciar motor
        true,   // power constant
        false,  // retardo ctrl. crucero
        true,  // límite batería
        false,  // multi. vel. rueda
        false,  // Desactivar BEEP
        false,   // cambio inst. ECO
        false,   // ECO startup
        true,  // Russian
        false,   // Eliminar límite vel.
        false,   // Eliminar modo carga
        false,  // ESC - BMS
        "46.3",   // kers
        "45",   // vel. máx normal
        "27",   // vel. máx ECO
        "1.45",    // vel. iniciar motor
        "27877",// power constant
        "5",    // retraso ctrl. crucero
        "52",// límite batería
        "345"   // multi. vel. rueda
    )

    val FIRM_CFW2 = arrayOf(
        2,      // firmware version index
        true,   // kers
        true,   // vel. máx normal
        true,   // vel. máx ECO
        true,   // vel. iniciar motor
        true,   // power constant
        false,  // retardo ctrl. crucero
        true,  // límite batería
        false,  // multi. vel. rueda
        false,  // Desactivar BEEP
        false,   // cambio inst. ECO
        false,   // ECO startup
        true,  // Russian
        false,   // Eliminar límite vel.
        false,   // Eliminar modo carga
        false,  // ESC - BMS
        "46.3",   // kers
        "45",   // vel. máx normal
        "27",   // vel. máx ECO
        "1.45",    // vel. iniciar motor
        "25787",// power constant
        "5",    // retraso ctrl. crucero
        "52",// límite batería
        "345"   // multi. vel. rueda
    )

    val PRED_FIRMS = arrayOf(
        FIRM_ORIGINAL,
        FIRM_DYOC,
        FIRM_BOTOX,
        FIRM_CHURU,
        FIRM_ROLLER,
        FIRM_CFW,
        FIRM_CFW2
    )
}
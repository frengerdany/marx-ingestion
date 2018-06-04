package com.mx.frengersoft.marx

import java.text.SimpleDateFormat
import java.util.Calendar

object Launcher {

  object ModuloVariablesTiempo {
    val formatoFecha = new SimpleDateFormat("yyyy-MM-dd_HHmmss")
    var date = Calendar.getInstance
    var horaInicio: String = formatoFecha.format(date.getTime)
    var horaInicioMiliSegundos: Long = date.getTime.getTime
    var horaFin: String = null
    var tiempoEjecucion: Long = 0
  }

  object initModuloVariablesArchivoSalida {
    val rutaArchivoSalida = "/Users/Frengerdany/Development/data/out/"
    val nombreArchivoSalidaValidacionContenido = "ValidacionContenidoDOIMSS_"
    val nombreArchivoSalidaValidacionEstructura = "ValidacionEstructuraDOIMSS_"
    val nombreArchivoSalidaValido = "doimss_ok_"
    val folioProceso = "000000000000000000"
    val idArchivo = "0000"
  }

  def main(args: Array[String]): Unit = {

    //  Inicio de medicion de tiempos de ejecucion -----------------------------------------
    println
    println("Hora de inicio: " + ModuloVariablesTiempo.horaInicio)

    //  Carga de templates -------------------------------------------------------------------
    Template.cargaTemplate("/Users/Frengerdany/Development/data/schema/DOIMSSTemplate.csv")

    //  Carga de archivo a validar ----------------------------------------------------------
    val in = new Input()
    in.cargaArchivoProceso("/Users/Frengerdany/Development/data/in/doimss.dat")

    val horaFin = Calendar.getInstance.getTime
    var horaFinMiliSegundos = horaFin.getTime
    ModuloVariablesTiempo.tiempoEjecucion = horaFinMiliSegundos - ModuloVariablesTiempo.horaInicioMiliSegundos
    val horaFinProceso = ModuloVariablesTiempo.formatoFecha.format(horaFin)
    println("Hora de fin: " + horaFinProceso)
    println("Tiempo total en milisegundos: " + ModuloVariablesTiempo.tiempoEjecucion + " ms")
    println

    //val resultRDD = sc.textFile("/user/cloudera/data/out/doimss_ok_02.dat")
    //val resultDF = resultRDD.toDF
    //resultDF.show
  }
}

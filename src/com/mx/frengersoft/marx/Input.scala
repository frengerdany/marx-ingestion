package com.mx.frengersoft.marx

import java.io.{File, PrintWriter}

import scala.io.Source

class Input {

  object initModuloVariablesArchivoEntrada {
    val rutaArchivoEntrada = "/Users/Frengerdany/Development/data/in/"
    val nombreArchivo = "doimss.dat"
    val nombreArchivo1 = "doimss_error_estructura.dat"
    val nombreArchivo2 = "doimss_error_orden.dat"
    val nombreArchivo3 = "doimss_error_contenido.dat"
    val nombreArchivo4 = "doimss_error_sumatorias.dat"
  }

  def cargaArchivoProceso(nombreArchivo: String) {

    //carga de archivo
    val archivoProceso = initModuloVariablesArchivoEntrada.rutaArchivoEntrada + nombreArchivo
    val file = Source.fromFile(archivoProceso)

    var rutaSalidaContenido = Launcher.initModuloVariablesArchivoSalida.rutaArchivoSalida + Launcher.initModuloVariablesArchivoSalida.nombreArchivoSalidaValidacionContenido + Launcher.ModuloVariablesTiempo.horaInicio.toString + ".csv"
    var rutaSalidaEstructura = Launcher.initModuloVariablesArchivoSalida.rutaArchivoSalida + Launcher.initModuloVariablesArchivoSalida.nombreArchivoSalidaValidacionEstructura + Launcher.ModuloVariablesTiempo.horaInicio.toString + ".csv"

    var rutaSalidaValido = Launcher.initModuloVariablesArchivoSalida.rutaArchivoSalida + Launcher.initModuloVariablesArchivoSalida.nombreArchivoSalidaValido + Launcher.ModuloVariablesTiempo.horaInicio.toString + ".dat"
    val archivoSalidaContenido = new PrintWriter(new File(rutaSalidaContenido))
    val archivoSalidaEstructura = new PrintWriter(new File(rutaSalidaEstructura))

    //AQUI VA EL ARCHIVO SALIDA SEPARADO POR COMAS
    //val archivoSalidaValido = new PrintWriter(new File(rutaSalidaValido))

    val cabeceraArchivo = """"ID_ARCHIVO","FOLIO","FECHA_CARGA","DETALLE","NO_LINEA","CAMPO","VALIDACION","VALOR_CAMPO","COD_ERROR","DESC_ERROR","VALOR_A_VALIDAR"""" + sys.props("line.separator")
    archivoSalidaContenido.write(cabeceraArchivo)
    archivoSalidaEstructura.write(cabeceraArchivo)

    var lineaResultado = new Line

    //Inicio de validacion
    println
    println("Iniciando proceso de validacion.")
    println

    var registro: String = null

    for (linea <- file.getLines()) {

      lineaResultado.initModuloContadoresLineas.numLinea += 1

      //println(linea)

      /**
        * INICIO VALIDACION DE ESTRUCTURA ----------------------------------------------------------------/
        */
      lineaResultado = Structure.validaEstructura(lineaResultado.initModuloContadoresLineas.numLinea, linea)
      /**
        * FIN VALIDACION DE ESTRUCTURA -------------------------------------------------------------------/
        */

      //if es valida en estructura
      if (lineaResultado.estructuraValida) {

        lineaResultado.initModuloContadoresLineas.lineasValidasEstructura += 1

        //Valida contenido
        lineaResultado = Content.validaContenidoLinea(lineaResultado.initModuloContadoresLineas.numLinea, lineaResultado)

        //se crea la salida para el archivo
        registro = new Output().crearArchivoSalida(lineaResultado)

      } else {

        lineaResultado.initModuloContadoresLineas.lineasErrorEstructura += 1
        var registroEstructura = "\"" + lineaResultado.initModuloContadoresLineas.numLinea + "\",\"" + lineaResultado.tipoError + "\",\"" + lineaResultado.mensajeError + "\",\"" + linea + "\""

        archivoSalidaEstructura.write(registroEstructura + sys.props("line.separator"))

        //TODO terminar proceso al primer error de estructura??
      }

      //string splitter
      var lineaSplit = ""
      lineaResultado.contenidoLinea.foreach { x =>
        var reg = x.valorCampo + ","
        lineaSplit += reg
      }
      //println(registro)


      //archivoSalidaValido.write(lineaSplit + sys.props("line.separator"))

      if (registro != null && !registro.isEmpty()) {
        archivoSalidaContenido.write(registro + sys.props("line.separator"))
      }

    }

    archivoSalidaEstructura.close()
    archivoSalidaContenido.close()
    //archivoSalidaValido.close()

    println
    println("El proceso de validacion ha terminado.")
    println

    println("Archivo de salida Contenido: " + rutaSalidaContenido.toString)
    println("Archivo de salida Estructura: " + rutaSalidaEstructura.toString)
    println
    println("Encabezado: " + Process.initModuloVariablesDispersion.totalLineasEncabezado)
    println("Detalles Individuales: " + Process.initModuloVariablesDispersion.totalLineasDetalle02)
    println("Cuentas por pagar: " + Process.initModuloVariablesDispersion.totalLineasCuentasPorPagar)
    println("Sumario: " + Process.initModuloVariablesDispersion.totalLineasSumario)
    println
    println("Total Lineas procesadas: " + lineaResultado.initModuloContadoresLineas.numLinea)
    println
  }

}

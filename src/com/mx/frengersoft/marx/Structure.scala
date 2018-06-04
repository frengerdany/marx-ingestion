package com.mx.frengersoft.marx

object Structure {

  def validaLongitudLinea(linea: String, longitudLinea: Int): Boolean = {
    if (linea.size == longitudLinea) {
      return true
    } else {
      return false
    }
  }

  def validaLineaVacia(linea: String): Boolean = {
    if (linea.trim.size > 0) {
      return true
    } else {
      return false
    }
  }

  def validaTipoDetalle(linea: String, tipoDetalle: Array[String]): Boolean = {

    var detalleValido = false
    tipoDetalle.foreach { tipo =>

      if (linea.startsWith(tipo)) {
        detalleValido = true
      }

    }
    return detalleValido
  }

  def obtenerTipoDetalleLinea(linea: String): String = {
    return linea.substring(0, 2)
  }

  def validaOrdenDetalle(estadoSiguiente: Int, idLinea: String): Int = {

    if (estadoSiguiente == 0 && idLinea == "01") {
      return 1
    } else if (estadoSiguiente == 1 && idLinea == "02") {
      return 2
    } else if (estadoSiguiente == 2 && idLinea == "02") {
      return 2
    } else if (estadoSiguiente == 2 && idLinea == "08") {
      return 3
    } else if (estadoSiguiente == 3 && idLinea == "08") {
      return 3
    } else if (estadoSiguiente == 3 && idLinea == "09") {
      return 4
    } else {
      return 0
    }
  }

  def validaEstructura(numLinea: Int, linea: String): Line = {

    //arreglo de cadenas para guardar los resultados de la validacion por linea
    var lineaResultado = new Line

    //1.Valicacion de linea vacia
    if (validaLineaVacia(linea)) {

      //2.Validacion de longitud de linea
      if (validaLongitudLinea(linea, Template.initVariablesArchivoTemplate.longitudLineaProceso)) {

        //3. Validacion tipos de detalles
        if (validaTipoDetalle(linea, Template.initVariablesArchivoTemplate.tiposDetalleProceso)) {

          //obtener las primeras dos posiciones de la linea
          val tipoDetalleLinea = obtenerTipoDetalleLinea(linea)

          /**
            * INICIO VALIDACION DE ORDEN DE LINEAS ----------------------------------------------------------------/
            */

          Process.initModuloVariablesDispersion.estadoSiguiente = validaOrdenDetalle(Process.initModuloVariablesDispersion.estadoSiguiente, tipoDetalleLinea)

          if (Process.initModuloVariablesDispersion.estadoSiguiente != 0) {

            //La linea es valida en estructura

            //llenamos el arreglo con los resultados de la validacion
            lineaResultado.idLinea = tipoDetalleLinea
            lineaResultado.numLinea = numLinea
            lineaResultado.estructuraValida = true
            lineaResultado.linea = linea
          } else {
            //println("El orden de la linea es incorrecto: " + numLinea + " : " + linea)
            lineaResultado.idLinea = tipoDetalleLinea
            lineaResultado.numLinea = numLinea
            lineaResultado.estructuraValida = false
            lineaResultado.tipoError = "estructura"
            lineaResultado.mensajeError = "Orden de la linea incorrecto"
            lineaResultado.linea = linea
          }

        } else {
          //println("Tipo de detalle no valido en linea numero: " + numLinea + " : " + linea)
          lineaResultado.idLinea = "-"
          lineaResultado.numLinea = numLinea
          lineaResultado.estructuraValida = false
          lineaResultado.tipoError = "estructura"
          lineaResultado.mensajeError = "Tipo de detalle no valido"
          lineaResultado.linea = linea
        }
      } else {
        //println("Longitud de linea no valida " + linea.size + " en linea numero: " + numLinea + " : " + linea)
        lineaResultado.idLinea = "-"
        lineaResultado.numLinea = numLinea
        lineaResultado.estructuraValida = false
        lineaResultado.tipoError = "estructura"
        lineaResultado.mensajeError = "Longitud de linea no valida: " + linea.size + " caracteres"
        lineaResultado.linea = linea
      }
    } else {
      //println("La linea " + numLinea + " esta vacia" + linea)
      lineaResultado.idLinea = "-"
      lineaResultado.numLinea = numLinea
      lineaResultado.estructuraValida = false
      lineaResultado.tipoError = "estructura"
      lineaResultado.mensajeError = "Linea vacia"
      lineaResultado.linea = linea
    }
    return lineaResultado
  }

}

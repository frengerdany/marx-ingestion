package com.mx.frengersoft.marx

import scala.collection.mutable.MutableList
import scala.io.BufferedSource

object Template {

  object initVariablesArchivoTemplate {
    val rutaRepositorioTemplates = "/Users/Frengerdany/Development/data/schema/"
    val listaTemplates: MutableList[BufferedSource] = MutableList[BufferedSource]()
    var nombreArchivoTemplate: String = null
    var nombreProceso: String = null
    var longitudLineaProceso: Int = 0
    var tiposDetalleProceso: Array[String] = null
  }

  def cargarTemplates(rutaRepositorioTemplates: String) {

    println("Cargando Templates ...")
    val repositorioTemplates = new java.io.File(Template.initVariablesArchivoTemplate.rutaRepositorioTemplates)
    //repositorioTemplates.listFiles.filter(_.getName.endsWith(".txt")).foreach { file => println(file.getAbsolutePath) }
    repositorioTemplates.listFiles.filter(_.getName.endsWith(".csv")).foreach {
      file =>
      //cargaTemplate(file.getAbsolutePath)
      //println(file.getAbsolutePath)
    }
  }

  def cargaTemplate(rutaTemplate: String) {

    //var rutaTemplate = Template.initVariablesArchivoTemplate.rutaRepositorioTemplates + nombreTemplate
    val templateFile = scala.io.Source.fromFile(rutaTemplate)

    //se extrae la primera linea del archivo con la informacion del proceso
    var parametrosProceso = templateFile.getLines().filter(_.startsWith("0")).next

    var arrProceso = parametrosProceso.split(",")

    var nombreProceso = arrProceso(2).toString
    var tiposLineas = arrProceso(3).split("-")
    var longitudLinea = arrProceso(7).toInt

    Template.initVariablesArchivoTemplate.nombreProceso = nombreProceso
    Template.initVariablesArchivoTemplate.tiposDetalleProceso = tiposLineas
    Template.initVariablesArchivoTemplate.longitudLineaProceso = longitudLinea

    for (linea <- templateFile.getLines()) {

      var arrLinea = linea.split(",")

      var idCampo = arrLinea(0).toInt
      var idLinea = arrLinea(1).toInt
      var nombreCampo = arrLinea(2).toString
      var regexCampo = arrLinea(3).toString
      var errorCampo = arrLinea(4).toString
      var descripcionCampo = arrLinea(5).toString
      var esFecha = arrLinea(6).toInt
      var logitud = arrLinea(7).toInt
      var posicionInicial = arrLinea(8).toInt
      var posicionFinal = arrLinea(9).toInt
      var idDependiente = arrLinea(10).toInt
      var esImporte = arrLinea(11).toInt

      var fragmento = new Element(
        idCampo,
        idLinea,
        nombreCampo,
        regexCampo,
        errorCampo,
        descripcionCampo,
        esFecha,
        logitud,
        posicionInicial,
        posicionFinal,
        idDependiente,
        esImporte)

      //TODO hacer dinamico para n tipos de linea
      if (idLinea == 1) {

        Process.initModuloVariablesDispersion.listaTemplateEncabezado += fragmento

      } else if (idLinea == 2) {

        Process.initModuloVariablesDispersion.listaTemplateDetalleIndividual += fragmento

      } else if (idLinea == 8) {

        Process.initModuloVariablesDispersion.listaTemplateCuentasPorPagar += fragmento

      } else if (idLinea == 9) {

        Process.initModuloVariablesDispersion.listaTemplateSumario += fragmento

        //        println("<campo>")
        //        println("<id>" + fragmento.idCampo + "</id>")
        //        println("<posInicial>" + fragmento.posicionInicial + "</posInicial>")
        //        println("<posFinal>" + fragmento.posicionFinal + "</posFinal>")
        //        println("<nombre>" + fragmento.nombreCampo + "</nombre>")
        //        println("<regex>" + fragmento.regexCampo + "</regex>")
        //        println("<descripcion>" + fragmento.descripcionCampo + "</descripcion>")
        //        println("<esFecha>" + fragmento.esFecha + "</esFecha>")
        //        println("<esImporte>" + fragmento.esImporte + "</esImporte>")
        //        println("<idDependencia>" + fragmento.idDependiente + "</idDependencia>")
        //        println("<tipoDato>num</tipoDato>")
        //        println("<comparacion>=</comparacion>")
        //        println("<valor>10</valor>")
        //        println("<esValido>0</esValido>")
        //        println("<errores>")
        //        println("<error>" + fragmento.errorCampo + "</error>")
        //        println("</errores>")
        //        println("</campo>")

      }

    }
  }
}

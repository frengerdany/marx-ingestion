package com.mx.frengersoft.marx

import java.io.{FileNotFoundException, FileReader, IOException}

object MarxException {

  try {
    val f = new FileReader("input.txt")
  } catch {
    case ex: FileNotFoundException => {
      println("Missing file exception")
    }

    case ex: IOException => {
      println("IO Exception")
    }

    case ex: NumberFormatException => {
      println("NumberFormatException en linea: ")

    }
  } finally {
    println("Exiting finally...")
  }
}

package totoro.ocelot.util

import scala.collection.mutable.ArrayBuffer

/**
  * Two dimensional array of chars
  */

class Matrix(width: Int, height: Int) {
  private val array = ArrayBuffer.fill(width * height)(' ')

  private def index(x: Int, y: Int) = y * width + x

  def get(x: Int, y: Int): Char = array(index(x, y))
  def set(x: Int, y: Int, char: Char): Unit = array(index(x, y)) = char
}

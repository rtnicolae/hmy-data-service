package crypto

trait HashFunction {
  def hash(str: String, encoding: String): String
}

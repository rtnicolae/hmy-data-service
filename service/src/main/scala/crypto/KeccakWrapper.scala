package crypto

import com.typesafe.scalalogging.LazyLogging
import org.bouncycastle.jcajce.provider.digest.Keccak
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.util.encoders.Hex

import java.security.Security

object KeccakWrapper extends HashFunction with LazyLogging {

  override def hash(str: String, encoding: String): String = {
    Security.addProvider(new BouncyCastleProvider())
    val md5 = new Keccak.Digest256
    val res = md5.digest(str.getBytes(encoding))

    Hex.toHexString(res)
  }
}

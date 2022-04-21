package crypto

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class KeccakImplSpec extends AnyFlatSpec with LazyLogging {

  it should "properly hash and convert method signature" in {
    val result = (new KeccakImpl).hash("balanceOf(address)", "UTF-8")//TODO looks shit
    val expected = "70a08231"

    result.substring(0,8) shouldBe expected
  }
}

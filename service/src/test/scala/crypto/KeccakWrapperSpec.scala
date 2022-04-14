package crypto

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class KeccakWrapperSpec extends AnyFlatSpec with LazyLogging {

  it should "properly hash and convert method signature" in {
    val result   = KeccakWrapper.hash("balanceOf(address)", "UTF-8")
    val expected = "70a08231"

    result.substring(0, 8) shouldBe expected
  }
}

package http

import com.typesafe.scalalogging.LazyLogging
import conf.Conf.HmyRpcConfig
import org.scalatest.flatspec.AnyFlatSpec
import zio.json.EncoderOps

class ContractsServiceTest extends AnyFlatSpec with LazyLogging {
  val hmyConfig = HmyRpcConfig("")
  def service   = new HmyContractsService(hmyConfig)

  it should "build the contract call" in {
    val contract = "0x22d62b19b7039333ad773b7185bb61294f3adc19"
    val method   = "balanceOf(address)"
    val paramWho = "0xC8Fd6cF68d953A5e55f95472D79044d3937768A9"
    val res      = service.buildContractCall(contract, method, Seq(paramWho))

    logger.info(s"${res.toJsonPretty}")
  }
}

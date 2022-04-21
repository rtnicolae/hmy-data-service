package http

import com.typesafe.scalalogging.LazyLogging
import conf.Conf.HmyRpcConfig
import org.scalatest.flatspec.AnyFlatSpec
import zio.json.EncoderOps

class ContractsServiceTest extends AnyFlatSpec with LazyLogging {
  val hmyConfig = HmyRpcConfig("https://rpc.s0.t.hmny.io/")
  def service   = new HmyContractsService(hmyConfig)

  //TODO move to other test class
  it should "build the contract call" in {
    val contract = "0x22d62b19b7039333ad773b7185bb61294f3adc19"
    val method   = "balanceOf(address)"
    val paramWho = "0xC8Fd6cF68d953A5e55f95472D79044d3937768A9"
    val res      = ContractsCallBuilder.buildContractCall(contract, method, Seq(paramWho))

    logger.info(s"${res.toJsonPretty}")
  }

  it should "make the contract call" in {
    val contractCall = service.callContract("0x22d62b19b7039333ad773b7185bb61294f3adc19", "balanceOf(address)", Seq("0xC8Fd6cF68d953A5e55f95472D79044d3937768A9"))

    val x = zio.Runtime.default.unsafeRun(
      contractCall
    )

    logger.info(s"$x")
  }



}

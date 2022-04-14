package http

import com.typesafe.scalalogging.LazyLogging
import conf.Conf.HmyRpcConfig
import crypto.KeccakImpl
import model.{ContractCallInfo, HmyCall}
import zio._
import zio.json.EncoderOps

trait ContractsService extends LazyLogging {

  def callContract(address: String, mSig: String, params: Seq[String]): Task[Int]

}

class HmyContractsService(rpc: HmyRpcConfig) extends ContractsService {

  override def callContract(address: String, mSig: String, params: Seq[String]): Task[Int] = Task.succeed {
    requests.post(rpc.endpoint, data = buildContractCall(address, mSig, params).toJson).statusCode
  }

  private[http] def buildContractCall(address: String, methodSig: String, params: Seq[String]) = {

    val hashedSig = sigHash(methodSig).padTo(32, "0").mkString
    logger.debug(s"Hashed contract method signature: $hashedSig")

    val paramsHex = params.map(_.substring(2).reverse.padTo(32, "0").reverse).mkString
    logger.debug(s"Params: $paramsHex")

    val data = "0x" + hashedSig + paramsHex
    logger.debug(s"Full contract data: $data")

    HmyCall(
      params =
        (
          ContractCallInfo(address, data),
          "latest"
        )
    )
  }

  private def sigHash(unhashed: String) = KeccakImpl.hash(unhashed, "UTF-8").substring(0, 8)

}

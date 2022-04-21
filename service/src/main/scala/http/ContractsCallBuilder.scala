package http

import com.typesafe.scalalogging.LazyLogging
import crypto.KeccakImpl
import rpcmodel.{ContractCallInfo, HmyCall}

object ContractsCallBuilder extends LazyLogging {

  //TODO properly inject
  val hashFunction = new KeccakImpl

  def unwrapLong: String => Long =
    s => java.lang.Long.parseLong(s, 16)

  def removeLeadingZeros: String => String =
    s => s.replaceAll("^0+(?!$)", "")

  private[http] def buildContractCall(address: String, methodSig: String, params: Seq[String], block: String = "latest") = {
    val hashedSig = sigHash(methodSig).padTo(32, "0").mkString
    logger.debug(s"Hashed contract method signature: $hashedSig")

    val paramsHex = params.map(_.substring(2).reverse.padTo(32, "0").reverse).mkString
    logger.debug(s"Params: $paramsHex")

    val data = "0x" + hashedSig + paramsHex
    logger.debug(s"Full contract data: $data")

    HmyCall(
      params = (
        ContractCallInfo(address, data),
        block,
      )
    )
  }

  def sigHash(unhashed: String) = hashFunction.hash(unhashed, "UTF-8").substring(0, 8)

}

package http

import com.typesafe.scalalogging.LazyLogging
import conf.Conf.HmyRpcConfig
import rpcmodel.{ContractCallResponse, HmyCall}
import zio._
import zio.cache.{Cache, Lookup}
import zio.duration.Duration
import zio.json.{DecoderOps, EncoderOps}

trait ContractsService extends LazyLogging {

  def callContract(address: String, mSig: String, params: Seq[String]): Task[Double]

}

class HmyContractsService(rpc: HmyRpcConfig) extends ContractsService {

  val decimalsCache: URIO[Any, Cache[String, Throwable, Long]] = Cache.make(100, Duration.Infinity, Lookup(getDecimals))

  override def callContract(address: String, mSig: String, params: Seq[String]): Task[Double] =
    for {
      cache <- decimalsCache
      d     <- cache.get(address)
      callRes <- makeCall(ContractsCallBuilder.buildContractCall(address, mSig, params))
        .map(unwrapResult)
    } yield callRes * Math.pow(10.0, -d.toDouble)

  private def makeCall(data: HmyCall) =
    ZIO.fromEither(makeSyncCall(data.toJson))

  private def makeSyncCall(data: String): Either[Throwable, ContractCallResponse] = {
    def resp = requests.post(rpc.endpoint, data = data, headers = Map("Content-Type" -> "application/json"))

    val done = new String(resp.data.array, "UTF-8").fromJson[ContractCallResponse]

    logger.debug(s"Contract Response: $resp")

    done match {
      case Left(value) => Left(new Exception(value))
      case Right(value) => Right(value)
    }
  }

  def unwrapResult: ContractCallResponse => Long =
    resp =>
      ContractsCallBuilder.unwrapLong(
        ContractsCallBuilder.removeLeadingZeros(
          resp.result.substring(2)
        )
      )

  def getDecimals: String => Task[Long] =
    address =>
      //Check inMem storage or make a call -> atm just make a call
      makeCall(ContractsCallBuilder.buildContractCall(address, "decimals()", Seq.empty))
        .map(unwrapResult)
        .mapError(new Exception(_))

}

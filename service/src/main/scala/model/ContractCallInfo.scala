package model

import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}

case class ContractCallInfo(to: String, data: String)

object ContractCallInfo {
  implicit val decoder: JsonDecoder[ContractCallInfo] = DeriveJsonDecoder.gen[ContractCallInfo]
  implicit val encoder: JsonEncoder[ContractCallInfo] = DeriveJsonEncoder.gen[ContractCallInfo]
}
